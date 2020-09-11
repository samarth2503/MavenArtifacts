package com.crm.qa.util;

import java.util.ArrayList;

import com.crm.qa.testdata.ExcelReader;

public class ExcelUtility {
	
	static String path="C:\\Users\\ADMIN\\Desktop\\Contacts.xlsx";
	
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
			
			firstname = ex.getCellData(sheetname, i, "Firstname");
			
			department = ex.getCellData(sheetname, i, "Department");
			
			lastname = ex.getCellData(sheetname, i, "SecondName");
			
			Phone = ex.getCellData(sheetname, i, "Phone");
			
			email = ex.getCellData(sheetname, i, "Email");
			
			Address = ex.getCellData(sheetname, i, "Address Title");
			
			city = ex.getCellData(sheetname, i, "City");
			
			State = ex.getCellData(sheetname, i, "State");
			
			zipcode = ex.getCellData(sheetname, i, "Zipcode");
		}
		
		ar.add(new Object[] {title,firstname,department,lastname,Phone,email,Address,city,State,zipcode});
		
		return ar;
	}
	
	
}
