package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pages.HomePage;

public class HomePagetest extends BaseClass{
	
	public HomePage hp;
	
	public HomePagetest() throws IOException {
		super();
		hp=lp.login(prop.getProperty("username"),prop.getProperty("password"));
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void newContactsTest() throws IOException
	{
		hp.newContacts();
	}

}
