package com.crm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.testdata.ExcelReader;
import com.crm.qa.util.Constants;
import com.crm.qa.util.Logs;
import com.crm.qa.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {
	
	public static WebDriver driver;
	public Properties prop;
	public FileInputStream fis;
	Actions ac;
	public LoginPage lp;
	public ExtentHtmlReporter htmlreport;
	public ExtentReports extent;
	public ExtentTest test;
	public static String browser;
	public TestUtil t;
	public BaseClass() throws IOException
	{
		String path="D:\\Selenium WorkSpace\\Mavenartifact\\src\\main\\java\\com\\crm\\qa\\config\\config.properties";
		fis = new FileInputStream(path);
		prop= new Properties();
		prop.load(fis);
		
	}
	
	
	
	@Parameters({"browser","url"})
	@BeforeTest
	public void Initalize(@Optional String browser,@Optional String url) throws InterruptedException, IOException
	{
		t=new TestUtil();
		if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty())
		{
			browser=System.getenv("browser");
		}
		else
		{
			browser=prop.getProperty("browser");
		}
		
		prop.setProperty("browser", browser);
		
		ChromeOptions opt1 = new ChromeOptions();
		if(browser.equals(prop.getProperty("browser")))
		{ 
			opt1.setAcceptInsecureCerts(true);
			//System.setProperty("webdriver.chrome.driver", "D:\\Selenium WorkSpace\\Mavenartifact\\chromedriver-1.exe");
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver(opt1);
		}
		
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities(); 
		String os = System.getProperty("os.name");
		extent.setSystemInfo("Browser", caps.getBrowserName());
		extent.setSystemInfo("Browser_Version", caps.getVersion());
		extent.setSystemInfo("OS", os);
		
		Reporter.log("OS is = "+os +", Browser = "+caps.getBrowserName());
		
		driver.get(url);
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
	}
	

	public void InitalizeExtentReport()
	{
		htmlreport = new ExtentHtmlReporter("D:\\Selenium WorkSpace\\Mavenartifact\\CRMReport\\ExtentReport.html");
		htmlreport.config().setDocumentTitle("Free CRM automation Test");
		htmlreport.config().setTheme(Theme.STANDARD);
		htmlreport.config().setReportName("Free CRM Extent Report");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlreport);
	}
	
	
	public WebElement getElement(String xpath)
	{
		WebElement element=null;
		try {
			element=driver.findElement(By.xpath(xpath));
		}
		catch(Exception e)
		{
			System.out.println("No Such Element found "+xpath);
			e.printStackTrace();
		}
		
		return element;
		
	}
	

	
	public void ClickObj(String xpath)
	{
		WebElement element=getElement(xpath);
		ScrollToElement(xpath);
		element.click();
	}
	
	public void Lookupwindow(String xpath)
	{
		WebElement element=getElement(xpath);
		element.click();
	}
	
	public void ScrollToElement(String xpath)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element=getElement(xpath);
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	public boolean InsertText(String xpath,String txt)
	{
		
		boolean flag=false;
		try
		{
			WebElement element=getElement(xpath);
			if(element.isDisplayed() && element.isEnabled())
			{
				if(txt=="")
				{
					flag=true;
				}
				
				else
				{
					Thread.sleep(1000);
					element.clear();
					element.sendKeys(txt);
					flag=true;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("No such Element Exception"+xpath);
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public String getText(String xpath)
	{
		WebElement element = getElement(xpath);
		String txt = element.getText();
		return txt;
	}
	

	public void ScrollDown(String xpath)
	{
		JavascriptExecutor js =(JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void movetoElement(WebDriver driver,String from,String to)
	{
		ac = new Actions(driver);
		WebElement element = driver.findElement(By.xpath(from));
		ac.moveToElement(element).moveToElement(driver.findElement(By.xpath(to))).click().perform();
	}
	
	public void waitforVisibility(WebDriver driver,int time,String locator)
	{
		WebDriverWait wait = new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locator))));
	}
	
	public String waitforAlert(WebDriver driver, int time)
	{
		WebDriverWait wait = new WebDriverWait(driver,time);
		String txt = wait.until(ExpectedConditions.alertIsPresent()).getText();
		return txt;
	}
	
	public void selectfromDropDown(WebElement element,String text)
	{
		Select s = new Select(element);
		List<WebElement> opt = element.findElements(By.tagName("option"));
		
		try
		{
			for(int i=0;i<opt.size();i++)
			{
				System.out.println("Text is "+text);
				if(opt.get(i).getText().equals(text))
				{
					s.selectByVisibleText(text);
					break;
				}
				else
				{
					System.out.println("Please select any valid option "+text);
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
//	public void switchWindow(WebDriver driver,String firstwindow,String secondwindow)
//	{
//		Set<String> windhandles = driver.getWindowHandles();
//		
//		for(String window:windhandles)
//		{
//			if(!window.equals(firstwindow) && !window.equals(secondwindow))
//			{
//				driver.switchTo().window(window);
//			}
//		}
//		
//	}
	
	public boolean switchWindow(Set<String> windows,By by)
	{
		boolean flag=false;
		for(String w:windows)
		{
			driver.switchTo().window(w);
			
			if(isElementPresent(by))
			{
				flag=true;
				Logs.info("Element "+by+" found in "+w);
				break;
			}
		}
		return flag;
	}
	
	public boolean isElementPresent(By by)
	{
		WebElement element=null;
		try
		{
			element=driver.findElement(by);
			Logs.info("Element found by "+by);
			return true;
		}
		catch(NoSuchElementException e)
		{
			Logs.error("No Such Element is Present with "+by);
			Logs.error(e.getMessage());
			return false;
		}
		
	}
	
	public String getCurrentdate()
	{
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
		String currentdate=sdf.format(d);
		return currentdate;
	}
	
	public String getDateformat(String date) throws ParseException
	{
		Date d = new SimpleDateFormat("dd-MM-YYYY").parse(date);
		String frmtdt = new SimpleDateFormat("dd-MMM-YYYY").format(d);
		return frmtdt;
	}
	
	public int monthnofrommonthname(String month) throws ParseException
	{
		Date d = new SimpleDateFormat("MMM").parse(month);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int monthno = cal.get(Calendar.MONTH)+1;
		return monthno;
	}
	
	public void enterTextJS(WebDriver driver,String path,String text)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = getElement(path);
		js.executeScript("arguments[0].value='"+text+"'", element);
	}
	
	public String getValueAttribute(WebDriver driver,String path)
	{	
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement element = getElement(path);
		String value = element.getCssValue("value");
		return value;
	}
	
	public void writeTofile(String path) throws IOException
	{
		FileWriter f = new FileWriter(path);
		PrintWriter pr = new PrintWriter(f);
	}
	
	public static void verifyEquals(String expected,String actual)
	{
		try
		{
			Assert.assertEquals(actual, expected);
		}
		catch(Throwable t)
		{
			// Add screeshot
		}
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			Reporter.log("Testcase "+result.getName()+" is Passed");
			test.log(Status.PASS, "Testcase "+result.getName()+" is Passed");
			test.pass(MarkupHelper.createLabel(result.getName()+" is Passed", ExtentColor.GREEN));
		}
		else if(result.getStatus()==ITestResult.FAILURE)
		{
			Reporter.log("Testcase "+result.getName()+" is Passed");
			test.log(Status.FAIL, "Testcase "+result.getName()+" is Failed");
			test.pass(MarkupHelper.createLabel(result.getName()+" is Failed", ExtentColor.RED));
			String path=BaseClass.takesnapshot(result.getName());
			test.addScreenCaptureFromPath(path);
			
			
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			Reporter.log("Testcase "+result.getName()+" is Skipped");
			test.log(Status.FAIL, "Testcase "+result.getName()+" is Skipped");
			test.pass(MarkupHelper.createLabel(result.getName()+" is Skipped", ExtentColor.YELLOW));
		}
		//driver.close();
	}
	
	public static String takesnapshot(String testcasename) throws IOException
	{
		String date = new SimpleDateFormat("dd-MM-YYYY HH-MM").format(new Date());
		
		TakesScreenshot ts =(TakesScreenshot) driver;
		File srcfile = ts.getScreenshotAs(OutputType.FILE);
		String dest = "D:\\Selenium WorkSpace\\Mavenartifact\\Screenshot\\"+testcasename + date+".png";
		File finaldes1 = new File(dest);
		FileUtils.copyFile(srcfile, finaldes1);
		return dest;
	}
	
	
	@AfterClass
	public void tearDown()
	{
		extent.flush();
	}
	
	@AfterTest
	public void finish()
	{
		driver.quit();
	}
	

}
