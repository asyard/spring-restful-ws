package com.asy.http.rest;

import com.asy.http.rest.model.User;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

public class SpringRestTestClient {

	private static String contextPath = "/";
	public static final String REST_SERVICE_URI = "http://localhost:8080" + contextPath;

	public static void main(String args[]){
		listAllUsers();
		try {
			getUser("1");
		}   catch (HttpClientErrorException e) {
			System.err.println("Error while getting user : " + e);
		}

		try {
			createUser("midprickly", 51, 134);
			listAllUsers();
		}   catch (HttpClientErrorException e) {
			System.err.println("Error while creating user : " + e);
		}

		try {
			updateUser(1, "Updated", 33, 70000);
			listAllUsers();
		}   catch (HttpClientErrorException e) {
			System.err.println("Error while updating user : " + e);
		}

		try {
			deleteUser("5");
			listAllUsers();
		}   catch (HttpClientErrorException e) {
			System.err.println("Error while deleting user : " + e);
		}
		//deleteAllUsers();
		//listAllUsers();
	}

	/* GET */
	@SuppressWarnings("unchecked")
	private static void listAllUsers(){
		System.out.println("---------- Testing listAllUsers API");
		
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/user/", List.class);
		
		if(usersMap!=null){
			for(LinkedHashMap<String, Object> map : usersMap){
				System.out.println("User : id=" + map.get("id") + ", Name=" + map.get("name") + ", Age=" + map.get("age") + ", Salary=" + map.get("salary"));
			}
		}else{
			System.out.println("---------- No user exists");
		}
	}


	/* GET */
	private static void getUser(String id){
		System.out.println("---------- Testing getUser API for user " + id);
		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForObject(REST_SERVICE_URI + "/user/" + id, User.class);
		System.out.println(user);
	}
	
	/* POST */
    private static void createUser(String name, int age, double salary) {
		System.out.println("---------- Testing create User API");
    	RestTemplate restTemplate = new RestTemplate();
		User user = new User(-1, name, age, salary);
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/user/", user, User.class);
		System.out.println("Location : "+uri.toASCIIString());
    }

    /* PUT */
    private static void updateUser(long id, String name, int age, double salary) {
		System.out.println("---------- Testing update User API");
        RestTemplate restTemplate = new RestTemplate();
        User user  = new User(id, name, age, salary);
		restTemplate.put(REST_SERVICE_URI + "/user/1", user);
		System.out.println(user);
    }

    /* DELETE */
    private static void deleteUser(String id) {
		System.out.println("---------- Testing delete User API");
        RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/user/" + id);
	}

    /* DELETE */
    private static void deleteAllUsers() {
		System.out.println("---------- Testing all delete Users API");
        RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/user/");
	}


}