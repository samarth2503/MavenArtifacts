package com.crm.qa.reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Report {
	
	public static void main(String[] args) throws IOException
	{
		File file = new File("D:\\Selenium WorkSpace\\Mavenartifact\\report1.html");
		
		if(!file.exists())
		{
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("<html>" + "\n");
			bw.write("<head>"+"\n");
			bw.write("<title>"+ "Test Execution" +"</title>"+"\n");
			bw.write("</head>");
			bw.write("<body>" + "\n");
			bw.write("<div style=width:500px;height:500px;margin:auto;>" + "\n");
			bw.write("<table>" + "\n");
			bw.write("<thead>"+"\n");
			bw.write("<tr>"+"\n");
			bw.write("<tr>"+"\n");
			bw.write("<thead>"+"\n");
			bw.write("<th>"+"Sold by"+"</th>");
			bw.write("<th>"+"Date"+"</th>");
			bw.write("</thead>"+"\n");
			bw.write("</tr>"+"\n");
			bw.write("</table>"+"\n");
			bw.write("</div>"+"\n");
			bw.write("</body>"+"\n");
			bw.write("</html>"+"\n");
		}

	}
	
	
}
