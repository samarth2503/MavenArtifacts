package com.crm.qa.util;

import java.util.ArrayList;

import com.crm.qa.testdata.ExcelReader;

public class ExcelUtility {
	
	static String path="D:\\Selenium WorkSpace\\Mavenartifact\\src\\main\\java\\com\\crm\\qa\\testdata\\Contacts.xlsx";
	
	static ExcelReader ex = new ExcelReader(path);

	static String sheetname="Contacts";
	
	
	
	public static ArrayList<Object[]> getData()
	{
		ArrayList<Object[]> ar = new ArrayList<Object[]>();
		
		int row= ex.getRowCount(sheetname);
		
		String title=null,firstname=null,department=null,lastname=null,Phone=null,email=null,Address=null,city=null,State = null,zipcode=null;
		for(int i=2;i<=row;i++)
		{
			title = ex.getCellData(sheetname, i-1, "Title");
			System.out.println(title);
			
			firstname = ex.getCellData(sheetname, i-1, "Firstname");
			System.out.println(firstname);
			
			lastname = ex.getCellData(sheetname, i-1, "Lastname");
			System.out.println(lastname);
			
			Phone = ex.getCellData(sheetname, i-1, "Phone");
			System.out.println(Phone);
			
			email = ex.getCellData(sheetname, i-1, "Email");
			
			Address = ex.getCellData(sheetname, i-1, "Address Title");
			
			city = ex.getCellData(sheetname, i-1, "City");
			
			State = ex.getCellData(sheetname, i-1, "State");
			
			zipcode = ex.getCellData(sheetname, i-1, "Zipcode");
			
			department = ex.getCellData(sheetname, i-1, "Department");
			System.out.println(department);
			
			ar.add(new Object[] {title,firstname,lastname,Phone,email,Address,city,State,zipcode,department});
		}
		
		return ar;
	}
	
	
}
