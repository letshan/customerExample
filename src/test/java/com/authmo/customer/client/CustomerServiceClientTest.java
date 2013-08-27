package com.authmo.customer.client;

import javax.ws.rs.core.MediaType;

import junit.framework.Assert;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;  

import com.authmo.entities.Customer;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

@Ignore // integration test; mvn package wouldn't pass this test as the web application hasn't started at that point 
public class CustomerServiceClientTest  {
	private final String HOST = "http://localhost:8080/CustomerExample"; 
	 
	@Test
	public void testCustomerService() {
		System.out.println("testCustomerService()");
		try {
			 
			Client client = Client.create();
	 
			WebResource webResource = client
			   .resource(HOST+"/api/customer/random");
	 
			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);
	 
			Assert.assertEquals(200, response.getStatus());
	 
			String output = response.getEntity(String.class);
	 
			System.out.println("Output from Server ....");
			System.out.println(output);
	 
		  } catch (Exception e) { 
			e.printStackTrace();
			Assert.fail("see exception: "+e.getMessage());
		  } 
	}
	
	@Test
	public void testSearch() {
		System.out.println("testSearch()");
		try {
			Client client = Client.create();
			 
			WebResource webResource = client
			   .resource(HOST+"/api/customer");
	 
			Customer cm = new Customer();
			cm.setAddress("604 Great South Rd, Ellerslie, Auckland 1051");
			cm.setName("Yellow page");
			cm.setPhone("0800 803 803");
			cm.setFax("12222");
			cm.setAge(11);
	
			ObjectMapper mapper = new ObjectMapper();
			String input = mapper.writeValueAsString(cm);
			Builder builder = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
			ClientResponse response = builder.post(ClientResponse.class,input);
		 
			Assert.assertEquals(200, response.getStatus());
			
	 
			System.out.println("Output from Server .... ");
			String output = response.getEntity(String.class);
			System.out.println(output);
			Customer cm2 = mapper.readValue(output, Customer.class);
			Assert.assertNotNull(cm2);
			Assert.assertTrue("not saved to db: id not created",cm2.getId()>0);
			 
			webResource = client
			   .resource(HOST+"/api/customer/" +
					   	cm2.getId());
	 
			response = webResource.accept("application/json")
	                   .get(ClientResponse.class);
	 
			Assert.assertEquals(200, response.getStatus());
	 
			output = response.getEntity(String.class);
			System.out.println("search");
			System.out.println("Output from Server ....");
			System.out.println(output);
			Assert.assertTrue(!"".equals(output));
		  } catch (Exception e) { 
			e.printStackTrace();
			Assert.fail("see exception: "+e.getMessage());
		  } 
	}
	@Test
	public void testCreateAndDeleteCustomer() {
		System.out.println("testCreateAndDeleteCustomer()");
		try {
			 
			Client client = Client.create();
	 
			WebResource webResource = client
			   .resource(HOST+"/api/customer");
	 
			Customer cm = new Customer();
			cm.setAddress("604 Great South Rd, Ellerslie, Auckland 1051");
			cm.setName("Yellow page");
			cm.setPhone("0800 803 803");
			cm.setFax("12222"); 
	
			ObjectMapper mapper = new ObjectMapper();
			String input = mapper.writeValueAsString(cm);
			Builder builder = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
			ClientResponse response = builder.post(ClientResponse.class,input);
		 
			Assert.assertEquals(200, response.getStatus());
			
	 
			System.out.println("Output from Server ....");
			String output = response.getEntity(String.class);
			System.out.println(output);
			Customer cm2 = mapper.readValue(output, Customer.class);
			Assert.assertNotNull(cm2);
			Assert.assertTrue("not saved to db: id not created",cm2.getId()>0);
			
			 webResource = client
					   .resource(HOST+"/api/customer/delete?id="+cm2.getId());
			 
			 response = webResource.accept("application/json")
	                   .get(ClientResponse.class);
	 
			Assert.assertEquals(200, response.getStatus());
			output = response.getEntity(String.class);
			System.out.println(output);
		  } catch (Exception e) {
	 
			e.printStackTrace();
			Assert.fail("see exception: "+e.getMessage());
		  }
	  
	}
	
}
