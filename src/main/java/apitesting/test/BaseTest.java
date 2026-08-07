package apitesting.test;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.nimbusds.oauth2.sdk.token.Token;

import apitesting.client.ApiClient;
import io.restassured.response.Response;

public class BaseTest {
	
	final ApiClient api = new ApiClient();
	
	public String getToken() {
		
			HashMap<String, String> payload = new HashMap<String, String>();
			payload.put("username", "mithun@ta.com");
			payload.put("password", "mithun");
			Response response = api.post("login", payload);
			return response.jsonPath().getString("token[0]");
	}

}
