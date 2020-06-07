package com.restAssuredFramework.testCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restAssuredFramework.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_Employee_Record extends TestBase{
	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	void getEmployeeData() throws Exception   {
		logger.info("======Started TC002_Get_Single_Employee_Record=======");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest =RestAssured.given();
		response= httpRequest.request(Method.GET,"/employee/" +empID);

		Thread.sleep(8000);
	}

	@Test
	void checkResponseBody() {
		String responseBody = response.getBody().asString();
		System.out.println("responseBody is "+responseBody);
		assertEquals(responseBody.contains(empID), true);
		
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
	void checkResponseTime()
	{
		logger.info("=====Started checking Response Time =========");

		long responseTime = response.getTime();
		logger.info("response Time =========> "+responseTime);

		if(responseTime>2000)
			logger.info("Response Time is greater than 2000 ");
		Assert.assertTrue(responseTime<2000);
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

		Assert.assertEquals(contentType, "text/html;charset=UTF-8");
	}

	@Test
	void checkServerType()
	{
		logger.info("=====Started checking Server Type =========");

		String serverType = response.header("Server");
		logger.info("Server Type =========> "+serverType);

		Assert.assertEquals(serverType, "nginx/1.16.0");
	}

	@Test
	void checkContentLength()
	{
		logger.info("=====Started checking content Length =========");

		String contentLength = response.header("Content-Length");
		logger.info("content Length =========> "+contentLength);

		if(Integer.parseInt(contentLength)<100)
			logger.info("content Length is less than 100");

		Assert.assertTrue(Integer.parseInt(contentLength)>100);
	}

	@AfterClass
	void tearDown() {
		logger.info("=====Finished TC002_Get_Single_Employee_Record =========");
	}


}
