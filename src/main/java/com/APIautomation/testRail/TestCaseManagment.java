package com.APIautomation.testRail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class TestCaseManagment {
	static String URL = XmlFileHandler.getXmlXpathValue("config/application/url");
	static String USERNAME = XmlFileHandler.getXmlXpathValue("config/user/username");
	static String PASSWORD = XmlFileHandler.getXmlXpathValue("config/user/password");
	static APIClient apiClient = new APIClient(URL);
	
	public static void addTestCase(Map<String, Object> data) {
		apiClient.setUser(USERNAME);
		apiClient.setPassword(PASSWORD);

		
		//data.put("custom_abcd", "T01");
		//data.put(�custom_description�, desc);
		//data.put(�custom_objective�, objctv);
		try {
			JSONObject c = (JSONObject)apiClient.sendPost("add_case/1", data);
			System.out.println(c.get("title"));
		} catch (IOException | APIException e) {
			
			e.printStackTrace();
		}
	}

	public static void updateTestCase(String test_case_Id, String value_to_update) {
		apiClient.setUser(USERNAME);
		apiClient.setPassword(PASSWORD);
		
		Map<String, Object> data = new HashMap<>();
		data.put("custom_expected", value_to_update);
		try {
			JSONObject c = (JSONObject)apiClient.sendPost("update_case/"+test_case_Id, data);
			System.out.println(c.get("title"));
		} catch (IOException | APIException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//addTestCase();
		updateTestCase("38", "Updated value :next");
	}
}
