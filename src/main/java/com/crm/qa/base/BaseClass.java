package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.crm.qa.pages.Contacts;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.Constants;


public class BaseClass {
	
	public static WebDriver driver;
	public Properties prop;
	public FileInputStream fis;
	Actions ac;
	protected LoginPage lp;
	public Contacts ct; 
	
	public BaseClass() throws IOException
	{
		String path="D:\\Selenium WorkSpace\\Mavenartifact\\src\\main\\java\\com\\crm\\qa\\config\\config.properties";
		fis = new FileInputStream(path);
		prop= new Properties();
		prop.load(fis);
	}
	
	
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void Initalize(@Optional String browser,@Optional String url) throws InterruptedException, IOException
	{
		ChromeOptions opt1 = new ChromeOptions();
		if(browser.equals(Constants.Chrome))
		{ 
			opt1.setAcceptInsecureCerts(true);
			System.setProperty("webdriver.chrome.driver", "D:\\Selenium WorkSpace\\Mavenartifact\\chromedriver.exe");
			driver= new ChromeDriver(opt1);
		}
		
		driver.get(url);
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		lp = new LoginPage();
		ct=lp.login(prop.getProperty("username"),prop.getProperty("password"));
		
		driver.switchTo().frame("mainpanel");
		
		ct.navigateToContact();
		
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
	
	public void selectfromDropDown(WebElement element,String text)
	{
		Select s = new Select(element);
		List<WebElement> opt = element.findElements(By.tagName("option"));
		try
		{
			for(int i=0;i<opt.size();i++)
			{
				if(opt.get(i).equals(text))
				{
					s.selectByVisibleText(text);
				}
				else
				{
					System.out.println("Please select any valid option");
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public void switchWindow(WebDriver driver,String firstwindow,String secondwindow)
	{
		Set<String> windhandles = driver.getWindowHandles();
		
		for(String window:windhandles)
		{
			if(!window.equals(firstwindow) && !window.equals(secondwindow))
			{
				driver.switchTo().window(window);
			}
		}
		
	}
	
	
	
	@AfterClass
	public void tearDown()
	{
		driver.close();
	}
	

}
