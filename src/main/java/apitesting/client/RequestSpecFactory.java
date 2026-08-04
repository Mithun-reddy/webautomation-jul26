package apitesting.client;

import org.apache.http.client.methods.RequestBuilder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecFactory {
	
	private RequestSpecFactory() {
		
	}
	
	public static RequestSpecification defaultSpecification() {
		RequestSpecBuilder builder = new RequestSpecBuilder()
				.setBaseUri("https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/")
				.setContentType(ContentType.JSON);
		return builder.build();
	}

}
