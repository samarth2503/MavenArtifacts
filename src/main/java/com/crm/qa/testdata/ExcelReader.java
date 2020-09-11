package com.crm.qa.testdata;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
			sheet=workbook.getSheetAt(0);
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
		}
		
		return rowCount;
		
	}
	
	public String getCellData(String sheetname, int rownum, String columnname)
	{
		int index = workbook.getSheetIndex(path);
		
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
		
		if(row==null)
		{
			return "";
		}
		cell=row.getCell(colcount);
		
		if(cell==null)
		{
			return "";
		}
		else
		{
			data=cell.getStringCellValue();
		}
		
		
		return data;
		
	}

}
