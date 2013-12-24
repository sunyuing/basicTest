package com.jng.basicTest;

import java.util.HashMap;
import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.*;

import org.codehaus.jackson.map.ObjectMapper;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.MediaType;

public class APITestStepDefinitions {

	private String authToken;
	private String userID;
	private String empID;
	private ClientResponse response;

	private static final String APIKey = "95144D49-2EF1-46B0-85A7-CDD0C6F259EE";

	@SuppressWarnings("unchecked")
	@Given("^I authenticate with web service$")
	public void I_authenticate_with_web_service() throws Throwable {
		try {
			ClientConfig cc = new DefaultClientConfig();
			cc.getProperties()
					.put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
			Client client = Client.create(cc);
			// Client client = Client.create();

			WebResource webResource = client
					.resource("http://ares.concerogroup.com/CTApiServiceTest/v1/authenticate");
			String input = "{\"email\":\"a.afonso@tw88.com\", \"password\":\"tailorwell1#\" }";
			response = webResource.type("application/json")
					.header("X_CONCERO_API_KEY", APIKey)
					.header("X_WORKSPACE_KEY", "demo1")
					.accept(MediaType.APPLICATION_JSON)
					.post(ClientResponse.class, input);

			if (response.getStatus() != 200)
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());

			String output = response.getEntity(String.class);
			// System.out.println("Output from Server .... \n");
			// System.out.println(output);

			Map<String, String> map = new HashMap<String, String>();
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(output, HashMap.class);

			empID = map.get("employeeId");
			authToken = map.get("X_CONCERO_AUTH_TOKEN");
			userID = map.get("X_CONCERO_USER_ID");

		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	@When("^I retrieve the employee data$")
	public void I_retrieve_the_results() throws Throwable {
		try {
			ClientConfig cc = new DefaultClientConfig();
			cc.getProperties()
					.put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
			Client client = Client.create(cc);

			WebResource webResource = client
					.resource("http://ares.concerogroup.com/CTApiServiceTest/v1/employees/"
							+ empID);
			response = webResource.type("application/json")
					.header("X_CONCERO_API_KEY", APIKey)
					.header("X_CONCERO_AUTH_TOKEN", authToken)
					.header("X_CONCERO_USER_ID", userID)
					.header("X_WORKSPACE_KEY", "demo1")
					.accept(MediaType.APPLICATION_JSON)
					.get(ClientResponse.class);

		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	@SuppressWarnings("unchecked")
	@Then("^the status code should be (\\d+)$")
	public void the_status_code_should_be(int arg1) throws Throwable {
		try {
			if (response.getStatus() != 200)
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());

			String output = response.getEntity(String.class);
			System.out.println("Output from Server .... \n");

			Map<String, String> map = new HashMap<String, String>();
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(output, HashMap.class);
			System.out.print(map);

			assertTrue("The expected status code was " + arg1
					+ ", but actually was: " + response.getStatus(),
					response.getStatus() == arg1);

		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

	}

}
