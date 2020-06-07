package com.restAssuredFramework.testCases;

import org.testng.Assert;
import org.testng.annotations.*;
import io.restassured.*;
import io.restassured.http.Method;
import io.restassured.response.*;
import io.restassured.specification.*;

import com.restAssuredFramework.base.TestBase;

import io.restassured.RestAssured;

public class TC001_GET_All_Employees extends TestBase {

	@BeforeClass
	void getAllEmployees() throws Exception {
		logger.info("=====Started TC001_GET_All_Employees =========");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");
		Thread.sleep(3);	
	}

	@Test
	void checkResponseBody()
	{
		logger.info("=====Started checking Response Body =========");

		String responseBody = response.getBody().asString();
		logger.info("Response Body =========> "+responseBody);
		Assert.assertTrue(responseBody!=null);
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

	@Test
	void checkContentEncoding()
	{
		logger.info("=====Started checking content Encoding =========");

		String contentEncoding = response.header("Content-Encoding");
		logger.info("content Encoding =========> "+contentEncoding);

		Assert.assertEquals(contentEncoding, "gzip");
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

	@Test
	void checkCookies()
	{
		logger.info("=====Started checking Cookies =========");

		String cookie = response.getCookie("PHPSESSID");

	}

	@AfterClass
	void tearDown() {
		logger.info("=====Finished TC001_GET_All_Employees =========");
	}
}
