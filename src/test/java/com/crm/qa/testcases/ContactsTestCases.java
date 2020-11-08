package com.crm.qa.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pages.Contacts;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.ExcelUtility;
import com.crm.qa.util.TestUtil;

public class ContactsTestCases extends BaseClass{
	
	public HomePage hp;
	public Contacts ct;
	
	public ContactsTestCases() throws IOException {
		super();
		lp=new LoginPage();
		InitalizeExtentReport();
		// TODO Auto-generated constructor stub
	}
	
	@DataProvider(name="CreateContact")
	public Object[][] data(){
		
		Object[][] al = ExcelUtility.getData();
		
		return al;
		/* Iterator<Object[]> itr = al.iterator();					
		can use this also but return type will be "Object[]"*/
		
	}
	
	@BeforeClass
	public void beforeClass() throws IOException, InterruptedException
	{
		Thread.sleep(1000);
		lp=new LoginPage();
		hp=lp.login(prop.getProperty("username"),prop.getProperty("password"));
		driver.switchTo().frame("mainpanel");
		ct=hp.newContacts();
	}
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="getData",priority=1)
	public void addContactTest(Hashtable<String,String> h) throws InterruptedException
	{
		test=extent.createTest("addContactTest");
		
		waitforVisibility(driver,30,prop.getProperty("ConatactInfo"));
		
		ct.AddContacts(h.get("Title"),h.get("Firstname"),h.get("SecondName"),h.get("Company"),h.get("Department"),h.get("Phone"),h.get("Email"),h.get("Birthday"),h.get("Address"),h.get("City"),h.get("State"),h.get("Zipcode"));
		
		String text = ct.saveContact();
		
		Assert.assertEquals(text, "Contact created.");
	}
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="getData",priority=2)
	public void deleteContacts(String name,String Company,String phone,String email) throws InterruptedException
	{
		test=extent.createTest("deleteContactTest");
		
		ct.searchandFetch(name);
		
		boolean flag=ct.deleteContact(name, Company, phone, email);
		
		Assert.assertTrue(flag);
		
		driver.switchTo().alert().accept();
		
	}
	
	@Test(priority=3)
	public void contactInfoTest()
	{
		test=extent.createTest("contactInfoTest");
		String text = getText(prop.getProperty("ConatactInfo"));
		verifyEquals(text,"Contact");
		WebElement ele = getElement(prop.getProperty("LoadFromCompany"));
		Assert.assertTrue(ele.isDisplayed());
	}


}
