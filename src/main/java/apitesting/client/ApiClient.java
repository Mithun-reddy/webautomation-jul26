package apitesting.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class ApiClient {
	
	private final RequestSpecification request;
	
	public ApiClient(RequestSpecification request) {
		this.request = request;
	}
	
	public ApiClient() {
		this(RequestSpecFactory.defaultSpecification());
	}

	public Response get(String path) {
		return given().spec(request).when().get(path);
	}
	
	public Response get(String path, HashMap<String, String> header) {
		return given().spec(request).headers(header).when().get(path);
	}
	
	public Response get(String path, Map<String, String> queryParams) {
		return given().spec(request).queryParams(queryParams).when().get(path);
	}
	
	public Response post(String path, Object body) {
		return given().spec(request).when().body(body).post(path);
	}
	
	public Response post(String path, Map<String, ?> header, Object body) {
		return given().spec(request).headers(header).when().body(body).post(path);
	}
	
	public Response put(String path, Object body) {
		return given().spec(request).when().body(body).put(path);
	}
	
	public Response delete(String path) {
		return given().spec(request).when().delete(path);
	}
}
