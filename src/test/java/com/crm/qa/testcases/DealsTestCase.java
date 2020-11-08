package com.crm.qa.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class DealsTestCase extends BaseClass{
	
	HomePage hp;
	LoginPage lp;
	DealsPage dp;
	public DealsTestCase() throws IOException, InterruptedException {
		super();
		InitalizeExtentReport();
	}
	
	@BeforeClass
	public void setUp() throws IOException, InterruptedException
	{
		lp=new LoginPage();
		hp=lp.login(prop.getProperty("username"),prop.getProperty("password"));
		Thread.sleep(1000);
		driver.switchTo().frame("mainpanel");
		dp=hp.newDeal();
	}
	
	@Test(retryAnalyzer=com.crm.qa.Analyzer.RetryAnalyzer.class,dataProviderClass=TestUtil.class,dataProvider="getData")
	public void addDeals(Hashtable<String,String> h) throws IOException, InterruptedException
	{
		dp.NewDeals(h.get("title"), h.get("Company"), h.get("contact"), h.get("amount"), h.get("probability"),h.get("product"), h.get("predictedDate"), h.get("ActualDate"), h.get("Desc"), h.get("nextstep"));
		
		String dealno = getValueAttribute(driver,prop.getProperty("DealNo"));
		
		String exp =getText(prop.getProperty("DealNo"));
		
		Assert.assertTrue(getText(prop.getProperty("DealNo")).contains("Deal: "+dealno+" : "+h.get("title")));
	}

}
