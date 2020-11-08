package com.crm.qa.pages;

import java.io.IOException;

import com.crm.qa.base.BaseClass;

public class HomePage extends BaseClass{

	public HomePage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Contacts newContacts() throws IOException
	{
		movetoElement(driver,prop.getProperty("Contacts"),prop.getProperty("NewContacts"));
		return new Contacts();
	}
	
	public Products newPrdouct() throws IOException
	{
		movetoElement(driver,prop.getProperty("Deals"),prop.getProperty("Products"));
		return new Products();
	}
	
	public DealsPage newDeal() throws IOException
	{
		movetoElement(driver,prop.getProperty("Deals"),prop.getProperty("New_Deal"));
		return new DealsPage();
	}

}
