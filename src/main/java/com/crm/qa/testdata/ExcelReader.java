package com.crm.qa.testdata;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;

public class ExcelReader {
	
	public String path;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public FileInputStream fis;
	public FileOutputStream fout;
	
	public ExcelReader(String path)
	{
		try
		{
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			//sheet=workbook.getSheetAt(0);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public int getRowCount(String sheetname)
	{
		
		int index = workbook.getSheetIndex(sheetname);
		
		int rowCount;
		if(index==-1)
		{
			rowCount=-1;
		}
		else
		{
			sheet=workbook.getSheetAt(index);
			rowCount=sheet.getLastRowNum();
			System.out.println("Row count is : "+rowCount);
		}
		
		return rowCount;
		
	}
	
	public String getCellData(String sheetname, int rownum, String columnname)
	{
		int index = workbook.getSheetIndex(sheetname);
		
		String data;
		
		if(index==-1)
		{
			data= "";
		}
		
		int colcount=-1;
		
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(0);
		
		for(int i=0;i<row.getLastCellNum();i++)
		{
			if(row.getCell(i).equals(columnname))
			{
				colcount=i;
				System.out.println("Column count is: "+colcount);
				break;
			}
			
		}
		
		if(colcount== -1)
		{
			return "";
		}
		
		sheet=workbook.getSheet(sheetname);
		
		if(rownum==-1)
		{
			return "";
		}
		
		row=sheet.getRow(rownum);
		System.out.println("Row number is: "+row.toString()+"  "+rownum);
		if(row==null)
		{
			return "";
		}
		cell=row.getCell(colcount);
		System.out.println("Row number is: "+cell.toString()+"   "+colcount);
		if(cell==null)
		{
			return "";
		}
		else
		{
			data=cell.getStringCellValue();
		}
		
		System.out.println("Data is: "+data);
		return data;
		
	}
	
	public String getCellData1(String sheetname,int colno,int rowno)
	{
		int index = workbook.getSheetIndex(sheetname);
		String data="";
		if(index==-1)
		{
			return data;
		}
		
		if(isSheetExist(sheetname))
		{
			row=sheet.getRow(rowno);
			if(row==null)
			{
				return data;
			}
			
			cell = row.getCell(colno);
			
			if(cell==null)
			{
				return data;
			}
			
			data+=row.getCell(colno).getStringCellValue();
		}
		else
		{
			return data;
		}

		return data;
	}
	
	public int getColumncount(String sheetname)
	{
		int index = workbook.getSheetIndex(sheetname);
		
		if(!isSheetExist(sheetname))
		{
			return -1;
		}
		
		sheet=workbook.getSheet(sheetname);
		
		row=sheet.getRow(0);
		
		if(row==null)
		{
			return -1;
		}
		
		int column_count= row.getLastCellNum();
		return column_count;
	}
	
	public boolean isSheetExist(String sheetname)
	{
		int index = workbook.getSheetIndex(sheetname);
		if(index==-1)
		{
			index = workbook.getSheetIndex(sheetname.toUpperCase());
			if(index ==-1)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			return true;
		}
	}
	
	public XSSFSheet getSheet()
	{
		return sheet;
	}

}
