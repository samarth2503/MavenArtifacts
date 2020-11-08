package com.crm.qa.util;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import com.crm.qa.testdata.ExcelReader;

public class TestUtil {
	
	public static ExcelReader ex;
	
	public TestUtil()
	{
		ex = new ExcelReader(Constants.path);
	}
	
	
//	@DataProvider
//	public static Object[][] getData(Method m) throws ClassNotFoundException, InstantiationException, IllegalAccessException
//	{
//		String sheetname = m.getName();
//		Class<?> cls = Class.forName("Contacts");
//		Object obj = cls.newInstance();
//		Object[][] obj1 = new Object[ex.getRowCount(sheetname)][ex.getColumncount(sheetname)];
//		Hashtable<String,String> table = new Hashtable<String,String>();
//		for(int i=1;i<=ex.getRowCount(sheetname);i++)
//		{
//			String methname = ex.getCellData(sheetname, 0, "Testcase");
//			for(int j=0;j<ex.getColumncount(sheetname);j++)
//			{
//				if(!ex.getCellData1(sheetname, j, i).equals("##"))
//				{
//					if(!ex.getCellData1(sheetname, j, i).equals("NA"))
//					{
//						continue;
//					}
//					else
//					{
//						table.put(ex.getCellData1(sheetname, j, 0),ex.getCellData1(sheetname, j, i));
//					}
//				}
//				else
//				{
//					break;
//				}
//				//obj[i-1][j]=ex.getSheet().getRow(i).getCell(j).getStringCellValue();
//			}
//			obj1[i-1][0]=table;
//		}
//		return obj1;
//	
//	}
	
	@DataProvider
	public static Object[][] getData(Method m)
	{
		String sheetname = m.getName();
		
		Object [][] obj = new Object[ex.getRowCount(sheetname)][1];
		Hashtable<String,String> h = new Hashtable<String,String>();
		for(int i=1;i<=ex.getRowCount(sheetname);i++)
		{
			for(int j=0;j<=ex.getColumncount(sheetname);j++)
			{
				h.put(ex.getCellData1(sheetname, j, 0),ex.getCellData1(sheetname, j, i));
			}
			obj[i-1][0]=h;
		}
		return obj;
	}
	
	
	public static boolean Runmode(String testcase)
	{
		String sheetname="testsuites";
	
		for(int i=1;i<=ex.getRowCount(sheetname);i++)
		{
			String name=ex.getCellData(sheetname, i, "TCID");
			System.out.println("testcase name is :"+name);
			if(name.equalsIgnoreCase(testcase))
			{
				String runmode = ex.getCellData(sheetname, i, "Runmode");
				if(runmode.equals("Y"))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		
		return false;
	}

}
