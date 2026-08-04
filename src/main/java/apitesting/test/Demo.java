package apitesting.test;


import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Demo {
	
	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";
		
		
		Response loginResponse = RestAssured.given().header("Content-Type", "application/json").when().body("{\"username\":\"mithun@ta.com\",\"password\":\"mithun\"}").post("login")
				.then().statusCode(201).extract().response();
		
		loginResponse.prettyPrint();
		
		System.out.println(loginResponse.statusCode());
		
		System.out.println(loginResponse.getHeader("Content-Length"));
		
		System.out.println(loginResponse.jsonPath().get("token[0]").toString());
		
		// URI and URL (http://, https://)
		
//		ISBN --> unique 
		// all URL's are URI's
		// All URI's are not URL's
		// Given -> define the context
		// when -> define the action
		// then -> validate the outcome
		
	}
	

}
