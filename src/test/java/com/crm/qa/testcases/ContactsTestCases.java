package com.crm.qa.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.BaseClass;
import com.crm.qa.pages.Contacts;
import com.crm.qa.util.ExcelUtility;

public class ContactsTestCases extends BaseClass{

	public ContactsTestCases() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@DataProvider
	public Iterator<Object[]> data(){
		ArrayList<Object[]> al = ExcelUtility.getData();
		return al.iterator();
		/* Iterator<Object[]> itr = al.iterator();					
		can use this also but return type will be "Object[]"*/
		
	}
	
	@Test(dataProvider="data")
	public void FillConatactDetaislTest(String title,String firstname,String Department,String SecondName,String Phone,String Email, String Address, String City,String State,String zipcode) throws InterruptedException
	{
		ct.AddContacts(title,firstname, Department, title, SecondName, Phone, Address, City, State, zipcode);
		
		String text = ct.saveContact();
		
		if(text.equals("Contact created"))
		{
			System.out.println("Contact is created");
		}
		else
		{
			System.out.println("Contact is not created");
		}

	}

}
