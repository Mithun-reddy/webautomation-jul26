package apitesting.assertions;

import java.io.File;

import org.testng.Assert;

import io.restassured.response.Response;
import io.restassured.module.jsv.JsonSchemaValidator;

public class ResponseAssertions {

	private ResponseAssertions() {
	}

	public static void hasStatus(Response response, int expectedStatusCode) {
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
	}

	public static void hasJsonValueMatch(Response response, String jsonPath, Object expectedValue) {
		Assert.assertEquals(response.jsonPath().get(jsonPath), expectedValue);
	}
	
	public static void hasJsonValueNotNull(Response response, String jsonPath) {
		Assert.assertNotNull(response.jsonPath().get(jsonPath), "JSON property is null");
	}
	
	public static void hasHeader(Response response, String headerName) {
		Assert.assertNotNull(response.getHeader(headerName), "headerName is null");
	}
	
	public static void hasHeaderValue(Response response, String headerName, Object expectedValue) {
		Assert.assertEquals(response.getHeader(headerName), expectedValue, "Header is not matching");
	}
	
	public static void matchesSchema(Response response, File file) {
		response.then().body(JsonSchemaValidator.matchesJsonSchema(file));
	}

}
