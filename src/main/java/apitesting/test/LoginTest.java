package apitesting.test;

import java.io.File;

import org.testng.annotations.Test;

import apitesting.assertions.ResponseAssertions;
import apitesting.client.ApiClient;
import io.restassured.response.Response;

public class LoginTest {
	
	private final ApiClient api = new ApiClient();
	
	@Test
	public void validateLogin_TC01() {
		Response response = api.post("login", "{\"username\":\"mithun@ta.com\",\"password\":\"mithun\"}");
		
		ResponseAssertions.hasStatus(response, 201);
		
		ResponseAssertions.matchesSchema(response, new File("/Users/user/eclipse-workspace-june26/salesforce-automation/src/main/java/apitesting/schemas/loginschema.json"));
	}

}
