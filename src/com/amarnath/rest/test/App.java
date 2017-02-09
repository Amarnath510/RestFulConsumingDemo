package com.amarnath.rest.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class App {
	
	private Client client;
	
	private static final String REST_URL = "http://localhost:8081/RestFulDemo/employee";
	
	public static void main(String[] args) {
		App app = new App();
		
		// initialize client.
		app.init();
		
		// POST
		System.out.println("\n***** Adding 3 Employees *****");
		app.saveEmployee(1, "Amarnath", "Chandana");
		app.saveEmployee(2, "Sushma", "Chandana");
		app.saveEmployee(3, "Prasad", "Chandana");
		
		// GET all Employees
		System.out.println("\n***** Getting all the Employees *****");
		app.getAllEmployees();
		
		// GET
		System.out.println("\n***** Get Employee with Id 1 *****");
		app.getEmployeeById(1);
		
		// PUT
		System.out.println("\n***** Update Employee with Id 2 *****");
		app.updateEmployee(2, "Amarnath Updated", "Chandana Updated");
		
		// DELETE
		System.out.println("\n***** Delete Employee with Id 3 *****");
		app.deleteEmployee(3);
		
		// GET all Employees
		System.out.println("\n***** Get all the Employees after deletion. *****");
		app.getAllEmployees();
	}
	
	void init() {
		this.client = ClientBuilder.newClient();
	}
	
	void saveEmployee(int id, String fName, String lName) {
		
		Form form = new Form();
		form.param("id", Integer.toString(id));
		form.param("fName", fName);
		form.param("lName", lName);
		
		String response = client
						.target(REST_URL)
						.path("/save")
						.request(MediaType.TEXT_HTML)
						.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
		
		System.out.println(response);
	}
	
	void getAllEmployees() {
		String response = client
					  .target(REST_URL)
					  .path("/all")
					  .request(MediaType.APPLICATION_JSON)
					  .get(String.class);
		System.out.println(response);
	}
	
	
	void getEmployeeById(int id) {
		String response = client
					  .target(REST_URL)
					  .path("/id/{id}")
					  .resolveTemplate("id", id)
					  .request(MediaType.APPLICATION_JSON)
					  .get(String.class);
		
		System.out.println(response);
	}
	
	void updateEmployee(int id, String fName, String lName) {
		Form form = new Form();
		form.param("id", Integer.toString(id));
		form.param("fName", fName);
		form.param("lName", lName);
		
		String response = client
						  .target(REST_URL)
						  .path("/update")
						  .request(MediaType.TEXT_HTML)
						  .put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
		
		System.out.println(response);
	}
	
	void deleteEmployee(int id) {
		String response = client
						  .target(REST_URL)
						  .path("/delete/{id}")
						  .resolveTemplate("id", id)
						  .request(MediaType.TEXT_HTML)
						  .delete(String.class);
		
		System.out.println(response);
	}
}