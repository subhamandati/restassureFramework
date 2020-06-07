package com.restAssuredFramework.utilities;

import org.testng.TestListenerAdapter;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Listeners extends TestListenerAdapter{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void OnStart(ITestContext testContext) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/myReport.html");
		
		htmlReporter.config().setDocumentTitle("Automation Report"); //Tile of the Report
		htmlReporter.config().setReportName("Rest API Testing Report"); //Name of the Report
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project Name", "Employee Database API");
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "subhashini");
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName()); //create a new entry in the report
		test.log(Status.PASS, "Test Case Passed IS "+result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName()); //create a new entry in the report
		test.log(Status.FAIL, "Test Case Failed IS "+result.getName()); //to add name in extent report
		//test.log(Status.FAIL, "Test Case Failed IS"+result.getThrowable()); //to add error or exception in the extent reports	
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName()); //create a new entry in the report
		test.log(Status.SKIP, "Test Case SKIPPED IS "+result.getName());	
	}
	
	public void onFinish(ITestResult result) {
		extent.flush();
		
	}
}
