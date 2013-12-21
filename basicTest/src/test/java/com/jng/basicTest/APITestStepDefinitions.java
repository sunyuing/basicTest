package com.jng.basicTest;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MultivaluedMap;

public class APITestStepDefinitions {

	private Client client;
	private String authToken;
	private String userID;
	private static final String APIKey = "95144D49-2EF1-46B0-85A7-CDD0C6F259EE";

	@Given("^I authenticate with web service$")
	public void I_authenticate_with_web_service() throws Throwable {
		// Express the Regexp above with the code you wish you had
        //ClientConfig cc = new DefaultClientConfig();
        //cc.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
        //Client client = Client.create(cc);
        Client client = Client.create();
        
        
//		WebResource webResource = client.resource("http://ares.concerogroup.com/CTApiServiceTest/v1/authenticate");
//        MultivaluedMap formData = new MultivaluedMapImpl();
//		formData.add("email", "a.afonso@tw88.com");
//		formData.add("password", "tailorwell1#");
//		ClientResponse response = webResource.type("application/x-www-form-urlencoded").header("X_CONCERO_API_KEY", APIKey).header("X_WORKSPACE_KEY", "demo1").post(ClientResponse.class, formData);

        WebResource webResource = client.resource("http://www.google.com");
//        WebResource webResource = client.resource("http://ares.concerogroup.com/CTApiServiceTest/v1/authenticate");
		try {
	        String s = webResource.get(String.class);
			System.out.println("OK");
		}
		catch (Exception e)
		{
			e.printStackTrace(System.out);
		}
	}

	@When("^I retrieve the results$")
	public void I_retrieve_the_results() throws Throwable {
		// Express the Regexp above with the code you wish you had
		StringBuilder sb = new StringBuilder();
		sb.append("Post");
		System.out.println(sb);
		// throw new PendingException();
	}

	@Then("^the status code should be (\\d+)$")
	public void the_status_code_should_be(int arg1) throws Throwable {
		// Express the Regexp above with the code you wish you had
		StringBuilder sb = new StringBuilder();
		sb.append("Check Result");
		System.out.println(sb);
		// throw new PendingException();
	}

}
