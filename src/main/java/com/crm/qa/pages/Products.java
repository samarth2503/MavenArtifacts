package com.crm.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.crm.qa.base.BaseClass;

public class Products extends BaseClass{

	public Products() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void navigateToProduct()
	{
		movetoElement(driver,prop.getProperty("Deals"),prop.getProperty("Products"));
		
	}
	
	public void addProduct(String Name,String cost,String Retailvalue,String wholesaleprice,String Description) throws InterruptedException
	{
		
		//ClickObj(prop.getProperty("New_Prod"));
			
		InsertText(prop.getProperty("Product_Name"),Name);
		
		InsertText(prop.getProperty("Product_Cost"),cost);
		
		InsertText(prop.getProperty("RetailValue"),Retailvalue);
		
		InsertText(prop.getProperty("Wholesale_price"),wholesaleprice);
		
		InsertText(prop.getProperty("Prod_desc"),Description);
		
		ClickObj(prop.getProperty("Product_Save"));
	}
	
	public String addCost(String cost) throws InterruptedException
	{
		ClickObj(prop.getProperty("New_Prod"));
		InsertText(prop.getProperty("Product_Cost"),cost);
		ClickObj(prop.getProperty("Product_Save"));
		String actual = waitforAlert(driver,10);
		driver.switchTo().alert().accept();
		return actual;
	}

}
