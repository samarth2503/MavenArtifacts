package com.crm.qa.util;

import java.util.ArrayList;

import com.crm.qa.testdata.ExcelReader;

public class ExcelUtility {
	
	static String path="D:\\Selenium WorkSpace\\Mavenartifact\\src\\main\\java\\com\\crm\\qa\\testdata\\DatProvider.xlsx";
	
	static ExcelReader ex = new ExcelReader(path);

	static String sheetname="Contacts";
	
	
	
	public static Object[][] getData()
	{
//		ArrayList<Object[]> ar = new ArrayList<Object[]>();
//		String title=null,firstname=null,department=null,lastname=null,Phone=null,email=null,Address=null,city=null,State = null,zipcode=null;
		int row= ex.getRowCount(sheetname);
//		System.out.println("Sheetname is " + sheetname);
		System.out.println("Row size is " +row);
//		title = ex.getCellData(sheetname, 1, "Title");
//		System.out.println(title);
//		
//		for(int i=1;i<=row;i++)
//		{
//			title = ex.getCellData(sheetname, 1, "Title");
//			System.out.println(title);
//			
//			firstname = ex.getCellData(sheetname, 1, "Firstname");
//			System.out.println(firstname);
//			
//			lastname = ex.getCellData(sheetname, 1, "Lastname");
//			System.out.println(lastname);
//			
//			Phone = ex.getCellData(sheetname, 1, "Phone");
//			System.out.println(Phone);
//			
//			email = ex.getCellData(sheetname, 1, "Email");
//			
//			Address = ex.getCellData(sheetname, 1, "Address Title");
//			
//			city = ex.getCellData(sheetname, 1, "City");
//			
//			State = ex.getCellData(sheetname, 1, "State");
//			
//			zipcode = ex.getCellData(sheetname, 1, "Zipcode");
//			
//			department = ex.getCellData(sheetname, 1, "Department");
//			System.out.println(department);
//			
//			ar.add(new Object[] {title,firstname,lastname,Phone,email,Address,city,State,zipcode,department});
//		}
//		
//		return ar;
		
		Object[][] obj = new Object[ex.getRowCount(sheetname)][ex.getColumncount(sheetname)];
		for(int i=1;i<=ex.getRowCount(sheetname);i++)
		{
			for(int j=0;j<ex.getColumncount(sheetname);j++)
			{
				obj[i-1][j]=ex.getSheet().getRow(i).getCell(j).getStringCellValue();
			}
		}
		return obj;
	}
	
	
}
