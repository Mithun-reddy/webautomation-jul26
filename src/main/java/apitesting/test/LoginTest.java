package apitesting.test;

import java.io.File;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import apitesting.assertions.ResponseAssertions;
import apitesting.client.ApiClient;
import apitesting.testdata.CreateUser;
import io.restassured.response.Response;

public class LoginTest extends BaseTest {

	@Test(priority = -1)
	public void validateLogin_TC01() throws JsonProcessingException {

		HashMap<String, String> payload = new HashMap<String, String>();
		payload.put("username", "mithun@ta.com");
		payload.put("password", "mithun");
		ObjectMapper om = new ObjectMapper();
		String creds = om.writeValueAsString(payload);
		Response response = api.post("login", creds);
		ResponseAssertions.hasStatus(response, 201);
		ResponseAssertions.matchesSchema(response, new File(
				"/Users/user/eclipse-workspace-june26/salesforce-automation/src/main/java/apitesting/schemas/loginschema.json"));
//		CreateUser cu = new CreateUser("TA-222222", "2", "2454333", "67733");
//		String user = om.writeValueAsString(cu);
//		
//		CreateUser deserializedObject = om.readValue(creds, CreateUser.class);
	}

	@Test(priority = 1)
	public void validateGetUsers_TC01() throws JsonProcessingException {

		String token = getToken();
		HashMap<String, String> header = new HashMap<String, String>();
		header.put("token", token);
		Response response = api.get("getdata", header);
		ResponseAssertions.hasStatus(response, 200);

	}

}
