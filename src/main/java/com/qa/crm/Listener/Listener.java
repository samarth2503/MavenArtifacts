package com.qa.crm.Listener;

import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.TestListenerAdapter;

import com.crm.qa.base.BaseClass;

import com.crm.qa.util.MonitoringMail;
import com.crm.qa.util.TestUtil;
import com.crm.qa.util.Testconfig;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener,ISuiteListener{

	public void onTestStart(ITestResult result)
	{
		
		Reporter.log("Test Case " +result.getName()+ " started");
		Reporter.log("<br>");
		
//		if(!TestUtil.Runmode(result.getName()))
//		{
//			throw new SkipException("Skipping the testcase as Runmode is No");
//		}
	}
	
	public void onTestSuccess(ITestResult result)
	{
		Reporter.log("Test Case " +result.getName()+ " is Passed");
		Reporter.log("<br>");

	}
	
	public void onTestFailure(ITestResult result)
	{
		Reporter.log("Test Case " +result.getName()+ " is Failed");
		Reporter.log("<br>");
		Reporter.log("Screeshot Link");
		Reporter.log("<br>");
		String path=null;
		try {
			path = BaseClass.takesnapshot(result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("<a href="+path+" target='_blank'>Screenshot</a>");
// 		<img src=\"C:\\Users\\Public\\Pictures\\Sample Pictures\\Screenshot.PNG\" height=200 width=200></img>
	}
	
	public void onTestSkipped(ITestResult result)
	{
		Reporter.log("Test Case " +result.getName()+ " is skipped");
		Reporter.log("<br>");
	}
	
	public void onTestFinish(ITestResult result)
	{
		Reporter.log("Test Case " +result.getName()+ " is Finished");
		Reporter.log("<br>");
		
	}
	
	public void onFinish(ISuite args0)
	{
		MonitoringMail mail = new MonitoringMail();
		String messageBody=null;
		try
		{
			messageBody = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/FreeCRMProject/ExtentReport/";
		}
		catch(UnknownHostException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			mail.sendMail(Testconfig.server, Testconfig.from, Testconfig.to, Testconfig.Subject, Testconfig.messageBody);
		}
		catch(AddressException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
