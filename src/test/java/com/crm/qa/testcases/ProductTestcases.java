package com.crm.qa.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.Products;
import com.crm.qa.util.TestUtil;

public class ProductTestcases extends BaseClass{
	
	public Products pro;
	public LoginPage lp;
	public HomePage hp;
	
	public ProductTestcases() throws IOException {
		super();
		InitalizeExtentReport();
		
		// TODO Auto-generated constructor stub
	}
	
	@BeforeClass
	public void beforeTest() throws IOException, InterruptedException
	{
		lp=new LoginPage();
		hp=lp.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(1000);
		driver.switchTo().frame("mainpanel");
		pro=hp.newPrdouct();
	}
	
	@Test
	public void NumericTest() throws InterruptedException
	{
		test=extent.createTest("NumericTest");
		String actual = pro.addCost("$123.56");
		Assert.assertTrue(actual.contains("Errors were encountered:"));
	}

	
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="getData")
	public void addProduct(Hashtable<String,String> h) throws InterruptedException
	{
		test=extent.createTest("addProductTest");
		pro.addProduct(h.get("Name"), h.get("Cost"), h.get("RetailValue"), h.get("WholesalePrice"), h.get("Description"));
		String expected = "Product: "+h.get("Name");
		String text = driver.findElement(By.xpath("//td[@class='datacardtitle']/child::table//td[contains(text(),'Product:')]")).getText();
		Assert.assertEquals(text, expected);
	}
	
	
}
