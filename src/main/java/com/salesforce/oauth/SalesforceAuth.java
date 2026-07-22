package com.salesforce.oauth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.Desktop;
import java.io.*;
import java.net.*;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Salesforce OAuth Authorization Code Flow with PKCE. Single-file
 * implementation compatible with newer Salesforce orgs.
 */
public class SalesforceAuth {

	private final String consumerKey;
	private final String consumerSecret;
	private final boolean sandbox;

	private static final String PROD = "https://login.salesforce.com";
	private static final String SBX = "https://test.salesforce.com";
	private static final int PORT = 8080;
	private static final String REDIRECT = "http://localhost:8080/callback";
	private static final String SCOPE = "";

	private String verifier;
	private String challenge;
	private String state;

	public SalesforceAuth(String consumerKey, String consumerSecret, boolean sandbox) {
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.sandbox = sandbox;
	}

	public String start() throws Exception {
		verifier = randomString(64);
		challenge = challenge(verifier);
		state = randomString(24);

		try (ServerSocket server = new ServerSocket(PORT)) {
			String auth = buildAuthorizationUrl();
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(URI.create(auth));
			} else {
				System.out.println(auth);
			}

			String code = waitForCode(server);
			return exchange(code);
		}
	}

	private String waitForCode(ServerSocket server) throws Exception {
		Socket s = server.accept();
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String req = br.readLine();

		URI uri = new URI("http://localhost" + req.split(" ")[1]);
		Map<String, String> q = parse(uri.getRawQuery());

		PrintWriter out = new PrintWriter(s.getOutputStream());
		out.println("HTTP/1.1 200 OK");
		out.println("Content-Type:text/html");
		out.println();
		out.println("<h2>Login successful. Close this window.</h2>");
		out.flush();

		s.close();

		if (q.containsKey("error"))
			throw new RuntimeException(q.get("error") + ": " + q.get("error_description"));

		if (!state.equals(q.get("state")))
			throw new RuntimeException("OAuth state mismatch.");

		return q.get("code");
	}

	private String exchange(String code) throws Exception {
		HttpClient client = HttpClient.newHttpClient();

		Map<String, String> form = new LinkedHashMap<>();
		form.put("grant_type", "authorization_code");
		form.put("client_id", consumerKey);
		form.put("client_secret", consumerSecret);
		form.put("redirect_uri", REDIRECT);
		form.put("code", code);
		form.put("code_verifier", verifier);

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create((sandbox ? SBX : PROD) + "/services/oauth2/token"))
				.header("Content-Type", "application/x-www-form-urlencoded")
				.POST(HttpRequest.BodyPublishers.ofString(form(form))).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		if (response.statusCode() != 200)
			throw new RuntimeException(response.body());

		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.readTree(response.body());

		return json.get("instance_url").asText() + "/secur/frontdoor.jsp?sid="
				+ URLEncoder.encode(json.get("access_token").asText(), StandardCharsets.UTF_8);
	}

	private String buildAuthorizationUrl() {
		Map<String, String> params = new LinkedHashMap<>();
		params.put("response_type", "code");
		params.put("client_id", consumerKey);
		params.put("redirect_uri", REDIRECT);
		params.put("state", state);
		params.put("code_challenge", challenge);
		params.put("code_challenge_method", "S256");

		if (!SCOPE.isBlank()) {
			params.put("scope", SCOPE);
		}
		return (sandbox ? SBX : PROD) + "/services/oauth2/authorize?" + form(params);
	}

	private static String challenge(String verifier) throws Exception {
		byte[] hash = MessageDigest.getInstance("SHA-256").digest(verifier.getBytes(StandardCharsets.UTF_8));
		return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
	}

	private static String randomString(int bytes) {
		byte[] b = new byte[bytes];
		new SecureRandom().nextBytes(b);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(b);
	}

	private static String form(Map<String, String> m) {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String> e : m.entrySet()) {
			if (sb.length() > 0)
				sb.append("&");
			sb.append(URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8));
			sb.append("=");
			sb.append(URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8));
		}
		return sb.toString();
	}

	private static Map<String, String> parse(String q) {
		Map<String, String> map = new LinkedHashMap<>();
		if (q == null)
			return map;
		for (String s : q.split("&")) {
			String[] p = s.split("=", 2);
			map.put(URLDecoder.decode(p[0], StandardCharsets.UTF_8),
					p.length > 1 ? URLDecoder.decode(p[1], StandardCharsets.UTF_8) : "");
		}
		return map;
	}
}
