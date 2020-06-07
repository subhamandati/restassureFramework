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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_Record extends TestBase {
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void createEmployee() throws Exception {
		logger.info("=======started TC005_Delete_Employee_Record ===========");
		
		RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET,"/employees");
		JsonPath JsonPathEvaluator =response.jsonPath();
		String empID=JsonPathEvaluator.get("[0].id");
		
		response = httpRequest.request(Method.DELETE,"/delete/"+empID);
		
		Thread.sleep(5000);	
	}
	
//	@Test
//	void checkResponseBody() {
//		String responseBody = response.getBody().asString();
//		System.out.println(responseBody);
//		assertEquals(responseBody.contains("Successfully! deleted records"), true);
//		
//	}

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
		logger.info("=====Finished TC005_Delete_Employee_Record =========");
	}

}
