package com.crm.qa.pages;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.BaseClass;
import com.crm.qa.util.Logs;

public class Contacts extends BaseClass{

	public Contacts() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public void navigateToContact()
	{
		movetoElement(driver,prop.getProperty("Contacts"),prop.getProperty("NewContacts"));
		//waitforVisibility(driver,30,prop.getProperty("ConatactInfo"));
		
	}
	
	public void AddContacts(String title,String firstname,String lastname,String Company,String Department,String Phone,String Email,String birthday,String AddressTitle,String City,String State,String Zipcode) throws InterruptedException
	{
		Thread.sleep(2000);
		WebElement ele= getElement(prop.getProperty("title1"));
		
		selectfromDropDown(ele,title);
		
		String firstwindow = driver.getWindowHandle();
		
		InsertText(prop.getProperty("firstname"),firstname);
		
		InsertText(prop.getProperty("lastname"),lastname);
		
		ClickObj(prop.getProperty("CompanyLookup"));
		
		Set<String> s = driver.getWindowHandles();
		
		boolean elem=switchWindow(s,By.xpath("//input[@id='search']"));
		
		if(elem)
		{
			driver.findElement(By.xpath("//input[@id='search']")).sendKeys(Company);
			ClickObj(prop.getProperty("search_company"));
			
			String beforepath="/html/body/table/tbody/tr[3]/td/table/tbody/tr[";
			String afterpath="]/td/a";
			
			int rows = driver.findElements(By.xpath("/html/body/table/tbody/tr[3]/td/table/tbody/tr")).size();
			
			for(int i=2;i<=rows;i++)	
			{
				String fullxpath=beforepath+i+afterpath;
				
				WebElement ele1= driver.findElement(By.xpath(fullxpath));
				String company = ele1.getText();
				if(ele1.getText().equals("Infosys"))
				{
					ele1.click();
					Thread.sleep(2000);
					Logs.info(company+" is selected from table");
					break;
				}
			}
		}
		
		driver.switchTo().window(firstwindow);
		
		driver.switchTo().frame("mainpanel");
		
		InsertText(prop.getProperty("Department"),Department);
		
		InsertText(prop.getProperty("Phone"),Phone);
		
		WebElement ele2 = getElement(prop.getProperty("ReceiverEmail"));
		
		if(!ele2.isSelected())
		{
			Thread.sleep(1000);
			ClickObj(prop.getProperty("ReceiverEmail"));
		}
		
		enterTextJS(driver,prop.getProperty("birthday"),birthday);
		
		InsertText(prop.getProperty("AddressTitle"),AddressTitle);
		
		InsertText(prop.getProperty("city"),City);
	
		InsertText(prop.getProperty("state"),State);
		
		InsertText(prop.getProperty("pincode"),Zipcode);
		
	}
	
	public String saveContact() throws InterruptedException
	{
		ClickObj(prop.getProperty("savecontact"));
		Thread.sleep(5000);
		
		String text = getText(prop.getProperty("contactcreated"));
		return text;
	}
	
	public void searchandFetch(String name)
	{
		driver.findElement(By.xpath(prop.getProperty("searchContact"))).clear();
		
		InsertText(prop.getProperty("searchContact"),name);
		
		WebElement element = getElement(prop.getProperty("searchTypes"));
		
		selectfromDropDown(element,"Saved Searches");
		
		ClickObj(prop.getProperty("searchbutton"));
		
	}
	
	public boolean deleteContact(String name,String company,String phone,String email) throws InterruptedException
	{
		String comapany1=null,name1=null,phone1=null;
		boolean flag=false;
		List<WebElement> rows = driver.findElements(By.xpath("//form[@id=\"vContactsForm\"]//tr"));
		System.out.println("Row size is "+rows.size());
		for(int j=4;j<=rows.size();j++)
		{
			System.out.println("Row number is "+j);
			System.out.println("Inside for loop");
			Thread.sleep(1000);
			name1 = driver.findElement(By.xpath("//form[@id=\"vContactsForm\"]//tr['"+j+"']//td[2]//a")).getAttribute("linkText");
			System.out.println("Name is "+name1);
			Thread.sleep(1000);
			comapany1 = driver.findElement(By.xpath("//form[@id=\"vContactsForm\"]//tr['"+j+"']//td[3]//a")).getAttribute("linkText");
			System.out.println("Company is "+comapany1);
			Thread.sleep(1000);
			phone1 = driver.findElement(By.xpath("//form[@id=\"vContactsForm\"]//tr['"+j+"']//td[4]//span")).getText();
			System.out.println("phone no is "+phone1);
			try {
				
				if(name1.equals(name) && comapany1.equals(company) && phone1.equals(phone))
				{
					System.out.println("Inside Try block");
					driver.findElement(By.xpath("//form[@id=\"vContactsForm\"]//tr['"+j+"']//td[8]//a[5]//i")).click();
					flag=true;
					break;
				}
				
			}
			catch(NullPointerException e)
			{
				continue;
			}
		}
		
		
		
		return flag;
		
	}
	
	

}
