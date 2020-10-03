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
		movetoElement(driver,prop.getProperty("Contacts"),prop.getProperty("NewContacts"));
		waitforVisibility(driver,10,prop.getProperty("ConatactInfo"));
		
	}
	
	public void AddContacts(String title,String firstname,String lastname,String phone,String Email,String Addresstitle,String city,String state,String zipcode,String Department) throws InterruptedException
	
	{
		Thread.sleep(2000);
		WebElement ele= getElement(prop.getProperty("title1"));
		
		selectfromDropDown(ele,title);
		
		Thread.sleep(1000);
		InsertText(prop.getProperty("firstname"),firstname);
		
		Thread.sleep(1000);
		InsertText(prop.getProperty("lastname"),lastname);
		
		ClickObj(prop.getProperty("CompanyLookup"));
		
		String firstwindow = driver.getWindowHandle();
		switchWindow(driver,firstwindow,firstwindow);
		
		String secondPage = driver.getWindowHandle();
		switchWindow(driver,firstwindow,secondPage);
			
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='search']")).sendKeys("Infosys");
		
		Thread.sleep(1000);
		InsertText(prop.getProperty("Department"),Department);
		
		Thread.sleep(1000);
		InsertText(prop.getProperty("Phone"),phone);
		
		
		WebElement ele2 = getElement(prop.getProperty("ReceiverEmail"));
		
		if(!ele2.isSelected())
		{
			Thread.sleep(1000);
			ClickObj(prop.getProperty("ReceiverEmail"));
		}
		
		Thread.sleep(1000);
		InsertText(prop.getProperty("AddressTitle"),Addresstitle);
		
		Thread.sleep(1000);
		InsertText(prop.getProperty("city"),city);
		
		Thread.sleep(1000);
		InsertText(prop.getProperty("state"),state);
		
		Thread.sleep(1000);
		InsertText(prop.getProperty("pincode"),zipcode);
		
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
