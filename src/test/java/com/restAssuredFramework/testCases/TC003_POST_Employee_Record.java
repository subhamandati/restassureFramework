package com.restAssuredFramework.testCases;

import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restAssuredFramework.base.TestBase;
import com.restAssuredFramework.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_POST_Employee_Record extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;
	
	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSal();
	String empAge = RestUtils.empAge();
	
	@BeforeClass
	void createEmployee() throws Exception {
		logger.info("=======started TC003_POST_Employee_Record ===========");
		
		RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		//JSONObject is a class that represents a simple json. we can add key-value pairs using put method
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);
		
		//add a header stating the request body is json
		httpRequest.header("Content-Type","application/json");
		
		//add json to the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.request(Method.POST,"/create");
		
		Thread.sleep(5000);	
	}
	
	@Test
	void checkResponseBody() {
		String responseBody = response.getBody().asString();
		assertEquals(responseBody.contains(empName), true);
		assertEquals(responseBody.contains(empSalary), true);
		assertEquals(responseBody.contains(empAge), true);
		System.out.println(responseBody);
	}

	@Test
	void checkStatusCode()
	{
		logger.info("=====Started checking Status Code =========");
		int statusCode = response.getStatusCode();
		logger.info("Status Code =========> "+statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void checkStatusLine()
	{
		logger.info("=====Started checking Status Line =========");

		String statusLine = response.getStatusLine();
		logger.info("Status Line =========> "+statusLine);

		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	void checkContentType()
	{
		logger.info("=====Started checking ContentType =========");

		String contentType = response.header("Content-Type");
		logger.info("Content Type =========> "+contentType);

		Assert.assertEquals(contentType, "application/json;charset=utf-8");
	}

	@Test
	void checkServerType()
	{
		logger.info("=====Started checking Server Type =========");

		String serverType = response.header("Server");
		logger.info("Server Type =========> "+serverType);

		Assert.assertEquals(serverType, "nginx/1.16.0");
	}

	@AfterClass
	void tearDown() {
		logger.info("=====Finished TC003_POST_Employee_Record =========");
	}
	

}
