package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pages.LoginPage;

import org.testng.Assert;

public class LoginPageTestcases extends BaseClass{

	
	public LoginPageTestcases() throws IOException {
		super();
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void verifytitleTest()
	{
		String title=lp.validateLoginPageTitle();
		test=extent.createTest("VerfiytitleTest");
		Assert.assertEquals(title,"#1 Free CRM for Any Bussiness: Online Customer Relationship Software");
	}
	
	@Test
	public void imageTest()
	{
		boolean flag=lp.validateImage();
		test=extent.createTest("VerfiytitleTest");
		Assert.assertFalse(flag);
		//Assert.assertEquals(flag, true);
	}
	
	@Test
	public void loginTest() throws IOException
	{
		test=extent.createTest("VerfiytitleTest");
		lp.login(prop.getProperty("username"),prop.getProperty("password"));
	}

}
