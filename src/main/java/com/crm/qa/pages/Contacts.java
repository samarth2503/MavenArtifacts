package com.crm.qa.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.BaseClass;

public class Contacts extends BaseClass{

	public Contacts() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public void navigateToContact()
	{
		movetoElement(driver,prop.getProperty("Contacts"),prop.getProperty("NewConatcts"));
		waitforVisibility(driver,10,prop.getProperty("ConatactInfo"));
		
	}
	
	public void AddContacts(String firstname,String lastname,String title,String Department,String phone,String Email,String Addresstitle,String city,String state,String pincode)
	
	{
		WebElement ele= getElement(prop.getProperty("title"));
		
		selectfromDropDown(ele,title);
		
		InsertText(prop.getProperty("firstname"),firstname);
		
		InsertText(prop.getProperty("lastname"),lastname);
		
		ClickObj(prop.getProperty("CompanyLookup"));
		
		String firstwindow = driver.getWindowHandle();
		switchWindow(driver,firstname,firstname);
		
		String secondPage = driver.getWindowHandle();
		switchWindow(driver,firstname,secondPage);
			
		InsertText(prop.getProperty("Department"),Department);
		
		InsertText(prop.getProperty("phone"),phone);
		
		WebElement ele2 = getElement(prop.getProperty("ReceiverEmail"));
		
		if(!ele2.isSelected())
		{
			ClickObj(prop.getProperty("ReceiverEmail"));
		}

		InsertText(prop.getProperty("AddressTitle"),Addresstitle);
		
		InsertText(prop.getProperty("city"),city);
		
		InsertText(prop.getProperty("city"),state);
		
		InsertText(prop.getProperty("city"),pincode);
		
	}
	
	public String saveContact() throws InterruptedException
	{
		ClickObj(prop.getProperty("savecontact"));
		Thread.sleep(5000);
		
		String text = getText(prop.getProperty("contactcreated"));
		return text;
	}
	
	public void searchandFetch()
	{
		driver.findElement(By.xpath(prop.getProperty("searchContact"))).clear();
		
		InsertText(prop.getProperty("searchContact"),"Rahul");
		
		WebElement element = getElement(prop.getProperty("searchTypes"));
		
		selectfromDropDown(element,"Saved Searches");
		
		ClickObj(prop.getProperty("searchbutton"));
		
	}
	

}
