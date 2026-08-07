package apitesting.test;

import java.io.File;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import apitesting.assertions.ResponseAssertions;
import apitesting.client.ApiClient;
import apitesting.client.RequestSpecFactory;
import apitesting.testdata.CreateUser;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddUser extends BaseTest {

	@Test(priority = -1)
	public void AddUser_TC003() throws JsonProcessingException {
		RequestSpecification rsToken = new RequestSpecBuilder()
				.addRequestSpecification(RequestSpecFactory.defaultSpecification())
				.addHeader("token", getToken())
				.build();
		
		ApiClient api = new ApiClient(rsToken);
		
		api.post("/addData", "");
		
		
	}
}