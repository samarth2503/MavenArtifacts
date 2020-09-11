package com.crm.qa.testcases;

import org.testng.TestNG;

public class TestRunner {
	
	static TestNG testng;
	
	public static void main(String[] args)
	{
		testng = new TestNG();
		
		testng.setTestClasses(new Class[] {LoginPageTestcases.class});
		testng.run();
	}

}
