package com.qa.crm.Listener;

import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener extends TestListenerAdapter{

	public void onTestStart(ITestResult result)
	{
		Reporter.log("Test Case" +result.getName()+ "started");
		Reporter.log("Name of Test case is = "+result.getTestName(), 1);
		Reporter.log("Status of Testcase is ="+result.getStatus());
	}
	
	public void onTestSuccess(ITestResult result)
	{
		Reporter.log("Test Case" +result.getName()+ "is Passed");
		Reporter.log("Name of Test case is = "+result.getTestName(), 1);
		Reporter.log("Status of Testcase is ="+result.getStatus());
	}
	
	public void onTestFailure(ITestResult result)
	{
		Reporter.log("Test Case" +result.getName()+ "is Failed");
		Reporter.log("Name of Test case is = "+result.getTestName(), 1);
		Reporter.log("Status of Testcase is ="+result.getStatus());
	}
	
	public void onTestSkipped(ITestResult result)
	{
		Reporter.log("Test Case" +result.getName()+ "is skipped");
		Reporter.log("Name of Test case is = "+result.getTestName(), 1);
		Reporter.log("Status of Testcase is ="+result.getStatus());
	}
	
	public void onTestFinish(ITestResult result)
	{
		Reporter.log("Test Case" +result.getName()+ "is Finished");
		Reporter.log("Name of Test case is = "+result.getTestName(), 1);
		Reporter.log("Status of Testcase is ="+result.getStatus());
	}
}
