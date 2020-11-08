package com.crm.qa.pages;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.crm.qa.base.BaseClass;
import com.crm.qa.util.Logs;

public class DealsPage extends BaseClass{

	public DealsPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void NewDeals(String title,String Company,String contact,String amount,String probability,String product,String predictedDate,String ActualDate,String Desc,String nextstep) throws InterruptedException
	{
		InsertText(prop.getProperty("Deals_title"),title);
		
		searchbyLookup(Company,prop.getProperty("DealsCompanyLookup"));
		Thread.sleep(2000);
		searchbyLookup(contact,prop.getProperty("PrimaryContactLookup"));
		
		InsertText(prop.getProperty("Amount"),amount);
		
		InsertText(prop.getProperty("probability"),probability);
		
		WebElement ele = getElement(prop.getProperty("Deals_Product"));
		selectfromDropDown(ele,product);
		
		enterTextJS(driver,prop.getProperty("predicted_date"),predictedDate);
		
		enterTextJS(driver,prop.getProperty("predicted_date"),ActualDate);
		
		InsertText(prop.getProperty("Deals_Descirption"),Desc);
		
		InsertText(prop.getProperty("Deals_Nextstep"),nextstep);
		
		ClickObj(prop.getProperty("Deals_save"));
		
	}
	
	
	public void searchbyLookup(String Company,String locator) throws InterruptedException
	{
		Lookupwindow(locator);
		String firstwindow = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		
		boolean elem=switchWindow(s,By.xpath("//input[@id='search']"));
		
		if(elem)
		{
			driver.findElement(By.xpath("//input[@id='search']")).sendKeys(Company);
			ClickObj(prop.getProperty("search_company"));
			
			String beforepath="/html/body/table/tbody/tr[3]/td/table/tbody/tr[";
			String afterpath="]/td/a";
			
			int rows = driver.findElements(By.xpath("/html/body/table/tbody/tr[3]/td/table/tbody/tr")).size();
			Thread.sleep(3000);
			for(int i=2;i<=rows;i++)	
			{
				String fullxpath=beforepath+i+afterpath;
				
				WebElement ele1= driver.findElement(By.xpath(fullxpath));
				String company = ele1.getText();
				if(ele1.getText().equals(Company))
				{
					ele1.click();
					Logs.info(company+" is selected from table");
					break;
				}
			}
		}
		driver.switchTo().window(firstwindow);
		
	}
	
}
