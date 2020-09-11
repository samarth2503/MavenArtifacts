package com.crm.qa.reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

class CustomReport {
	
	int index=0;
	private String scriptName;
	
	@BeforeMethod
	public void setup()
	{
		
		System.out.println("Inside Before methdo");
	}
	
	@Test
	public void m1()
	{
		org.testng.Assert.assertTrue(false);
		System.out.println("Inside m1");
	}
	
	@Test
	public void m2()
	{
		org.testng.Assert.assertTrue(true);
		System.out.println("Inside m2");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException
	{
		index++;
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			updateResult(result.getTestClass().getName(),index,result.getName().toString(),"FAIL");
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			updateResult(result.getTestClass().getName(),index,result.getName().toString(),"PASS");
		}
		else
		{
			updateResult(result.getTestClass().getName(),index,result.getName().toString(),"SKIP");
		}
	}
	
	public void updateResult(String classname,int index,String methodName,String response) throws IOException
	{
		String datetime = new SimpleDateFormat("MM-dd-yyyy_HH:mm").format(System.currentTimeMillis());
		
		String resultfile="D:\\Selenium WorkSpace\\CRMProject\\CustomReport.html";
		
		File file = new File(resultfile);
		
		if(!file.exists())
		{
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<html>" + "\n");
			bw.write("<head><title>" +"Test Exceution"+"</title>"+"\n");
			bw.write("</head>");
			bw.write("<body>");
			bw.write("<font face='Tahoma' size='2'>" + "\n");
			bw.write("<u><h1 align='center'>" + "Test execution report" + "</h1></u>" + "\n");
			bw.flush();
			bw.close();
		}
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(file,true));
		if(index==1)
		{
			bw1.write("<table align='center' border='0' width='70%' height='10%'>");
			bw1.write("<tr><td width='70%' </td></tr>");
			bw1.write("<table align='center' border='1' width='70%' height='47'>");
			bw1.write("<tr>");
			bw1.write("<td colspan='1' align='center'><b><font color='#000000' face='Tahoma' size='2'> Scriptname :&nbsp;&nbsp;&nbsp;</font> <font face='Tahoma' size='2'>"+ scriptName +"</font></b></td>");
			bw1.write("<td colspan='2' align='left'><b><font color='#000000' face='Tahoma' size='2'> Start Time:&nbsp;</font><font color='#0000FF' face='Tahoma'size='2'>"+ datetime +"</b><font color='#00000'>");
			bw1.write("</tr>");
			bw1.write("</tr>");
			bw1.write("<td bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>S.No</font></b></td>");
			bw1.write("<td bgcolor='#CCCCFF' align='left'><b><font color='#000000' face='Tahoma' size='2'>Class name</font></b></td>");
			bw1.write("<td bgcolor='#CCCCFF' align='left'><b><font color='#000000' face='Tahoma' size='2'>Test name</font></b></td>");
			bw1.write("<td bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>Response</font></b></td>");
			bw1.write("</tr>");
		}
		
		bw1.write("<tr>"+ "\n");
		bw1.write("<td bgcolor='#FFFDC' align='Center'><font color='#000000' face='Tahoma' size='2'>" +index+ "</font></td>"+ "/n");
		bw1.write("<td bgcolor='#FFFDC' valign='middle' align='left'><b><font color='#000000' face='Tahoma' size='2'>" + classname + "</font></b></td>"+ "/n");
		bw1.write("<td bgcolor='#FFFDC' valign='middle' align='left'><b><font color='#000000' face='Tahoma' size='2'>" + methodName + "</font></b></td>"+ "/n");
		bw1.write("<td bgcolor='#FFFDC' valign='middle' align='left'><b><font color='#000000' face='Tahoma' size='2'>" + response + "</font></b></td>"+ "/n");
		bw1.write("</tr>");
	}

}
