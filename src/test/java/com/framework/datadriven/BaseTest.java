package com.framework.datadriven;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseTest {
	  protected static  Logger logger = Logger.getLogger(BaseTest.class);
	  protected WebDriver driver;
	  
	  public void setUpDriver() {
		  logger.debug("Setting up Driver ");
		  System.setProperty("webdriver.chrome.driver","C:\\Users\\sapai\\TestingTools\\chromedriver.exe");
		  driver= new ChromeDriver();
		  
		  logger.debug("Driver setup successfully");
	  }
	  
	  public void loggerSample(Integer n, String s) {	
			String  tcName = this.getClass().getMethods().toString();
			System.out.println("Data set n = "+n+"s  = "+s);
			
			logger.debug("login");
			if(logger.isDebugEnabled()){
				logger.debug("This is debug : " + tcName);
			}
			
			if(logger.isInfoEnabled()){
				logger.info("This is info : " + tcName);
			}
			
			logger.warn("This is warn : " + tcName);
			logger.error("This is error : " + tcName);
			logger.fatal("This is fatal : " + tcName);
			
		}
}
