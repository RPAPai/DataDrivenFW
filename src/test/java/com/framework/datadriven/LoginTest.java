package com.framework.datadriven;

import org.testng.annotations.Test;

import com.framework.utils.ExcelReader;

import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class LoginTest extends BaseTest{

	//  private static  Logger logger = Logger.getLogger(LoginTest.class);
	@DataProvider
	public Object[][] loginAuthenticationTestDataOrangeHRM() {
		String filePath = "C:\\Users\\sapai\\Roopa\\QA\\Practice\\AutomationFramework\\datadriven\\resources\\LoginTestDataOrangeHRM.xlsx";
			    String testData[][] = ExcelReader.GetExcelData(filePath, "LoginTestData");
//		String testData[][] = ExcelReader.GetExcelData(filePath, "LoginTestData", "LoginTC_P001");
		System.out.println("authentication DATA= "+Arrays.deepToString(testData));
		return testData;
	}
	
	@Test(dataProvider = "loginAuthenticationTestDataOrangeHRM")
	public void testLoginOrangeHRM(String userName, String passWd, String testType, String testMsg) throws InterruptedException {	
		logger.debug("Data set:  userName = "+userName+" passWd = "+passWd+" testType = "+testType+" testMsg="+testMsg);
	
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("txtUsername")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(passWd);
		driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
		logger.debug("Logged in ");
		
		// VAlidating TC
		switch(testType) {
		case "positive":
			logger.debug("Welcome text = *"+driver.findElement(By.xpath("//a[@id='welcome']")).getText()+ "* ::expected Message =*"+testMsg+"*");
//			String welcome = driver.findElement(By.xpath("//a[@id='welcome']")).getText();
//			Assert.assertTrue(welcome.equals(testMsg));
			logger.debug("Welcome greetings matches for positive TC");
			Assert.assertTrue((driver.findElement(By.xpath("//a[@id='welcome']")).getText()).equals(testMsg));
			
			// //a[contains(text(),'Logout')]
			break;
		case "negative":
			logger.debug("Warning MSG = "+driver.findElement(By.xpath("//*[@id='spanMessage']")).getText());
			driver.findElement(By.xpath("//*[@id='spanMessage']")).getText().equals(testMsg);
			logger.debug("Warning Message matches for negative TC");
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id='spanMessage']")).getText().equals(testMsg));
			break;
		}
		

	}
	@DataProvider
	public Object[][] loginAuthenticationTestDataWalmart() {
		String filePath = "C:\\Users\\sapai\\Roopa\\QA\\Practice\\AutomationFramework\\datadriven\\resources\\LoginTestDataWalmart.xlsx";
		//	    String testData[][] = ExcelReader.GetExcelData(filePath, "LoginTestData");
		String testData[][] = ExcelReader.GetExcelData(filePath, "LoginTestData", "LoginTC_P001");
		System.out.println("authentication DATA= "+Arrays.deepToString(testData));
		return testData;
	}

	@Test(enabled=false, dataProvider = "loginAuthenticationTestDataWalmart")
	public void testLoginWalmart(String userName, String passWd, String testType, String testMsg) throws InterruptedException {	
		logger.debug("Data set:  userName = "+userName+" passWd = "+passWd+" testType = "+testType+" testMsg="+testMsg);

		//		driver.get("www.ebay.com");
		//	driver.get("https://www.walmart.com/account/login?ref=domain");
		driver.get("https://www.walmart.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement accountBtn= driver.findElement(By.xpath("//*[@id=\"header-account-toggle\"]"));
		//	logger.debug("Account Btn has text "+ accountBtn.getText());	
		logger.debug("Account Btn has text ");	
		accountBtn.click();
		Thread.sleep(1500);
		//	WebElement signIn= driver.findElement(By.linkText("Sign In"));
		WebElement signIn= driver.findElement(By.xpath("//div[@id='vh-account-menu-root']//a[contains(.,'Sign In')]"));
		//		WebElement signIn= driver.findElement(By.xpath("//a[contains(.,'Sign In')])"));
		//		WebElement signIn= driver.findElement(By.xpath("//*[@id='signed-out']//a[contains(.,'Sign In')])"));
		//		logger.debug("Signin  link has text "+ signIn.getText());	
		logger.debug("Signin  link has text ");	
		signIn.click();

		driver.findElement(By.id("email")).sendKeys(userName);;
		driver.findElement(By.xpath("//input[@id=\"password\"]")).sendKeys(passWd);;
		driver.findElement(By.xpath("//button[@data-automation-id='signin-submit-btn']")).click();

		Thread.sleep(2500);

		logger.debug("Account Btn has text "+ accountBtn.getText());	
		accountBtn.click();
		WebElement signOut= driver.findElement(By.linkText("Sign Out"));
		logger.debug("Signout  link has text "+ signOut.getText());	

		//assertTrue(signOut.isDisplayed());

		logger.debug("All required Elements found");
		logger.debug("About to SignOut...");

		Thread.sleep(2000);

		// signout Approach1

		signOut.click();
		// signout Approach2
		//		Actions mouse=new Actions(driver);
		//		mouse.moveToElement(welcomeBtn).perform();
		//		mouse.moveToElement(signOut).click().perform();
		logger.debug("SignOut successful...");

	}

	@DataProvider
	public Object[][] loginAuthenticationTestDataEbay() {
		String filePath = "C:\\Users\\sapai\\Roopa\\QA\\Practice\\AutomationFramework\\datadriven\\resources\\LoginTestDataEbay.xlsx";
		//	    String testData[][] = ExcelReader.GetExcelData(filePath, "LoginTestData");
		String testData[][] = ExcelReader.GetExcelData(filePath, "LoginTestData", "LoginTC_P002");
		System.out.println("authentication DATA= "+Arrays.deepToString(testData));
		return testData;
	}

	@Test(enabled=false, dataProvider = "loginAuthenticationTestDataEbay")
	public void testLoginEbay(String userName, String passWd, String testType, String testMsg) throws InterruptedException {	
		logger.debug("Data set:  userName = "+userName+" passWd = "+passWd+" testType = "+testType+" testMsg="+testMsg);

		//	driver.get("www.ebay.com");
		driver.get("https://signin.ebay.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("userid")).sendKeys(userName);;
		driver.findElement(By.xpath("//input[@id=\"pass\"]")).sendKeys(passWd);;
		driver.findElement(By.xpath("//button[@id=\"sgnBt\"]")).click();
		WebElement welcomeBtn= driver.findElement(By.xpath("//button[contains(text(),'Hi') and contains(.,'!')][b[contains(.,'Rupa')]]"));
		//button[contains(text(),'Hi') and contains(.,'!')][b[contains(.,'Rupa')]]

		assertTrue(welcomeBtn.isDisplayed());

		logger.debug("All required Elements found");
		logger.debug("About to SignOut...");

		Thread.sleep(2000);

		// signout Approach1
		welcomeBtn.click();
		WebElement signOut= driver.findElement(By.linkText("Sign out"));
		signOut.click();
		// signout Approach2
		//	Actions mouse=new Actions(driver);
		//	mouse.moveToElement(welcomeBtn).perform();
		//	mouse.moveToElement(signOut).click().perform();
		logger.debug("SignOut successful...");

	}


	@BeforeMethod
	public void beforeMethod() {
		setUpDriver();
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}


}
