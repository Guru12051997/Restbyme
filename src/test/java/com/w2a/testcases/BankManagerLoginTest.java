package com.w2a.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class BankManagerLoginTest extends TestBase {
	
	@Test
	public void loginAsBankManagaer() throws InterruptedException {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		log.debug("Inside Login Test");
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		Thread.sleep(3000);
		
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))),"Login not successful");
		log.debug("Login Successfully Executed"); 
		Reporter.log("Login Successfully Executed");
		
		Reporter.log("<a target =\"_blank\"href=\"C:\\Users\\Guru Prasad Das\\Pictures\\Screenshots\\error.png\">Screenshot</a>");
		
		//target =\"_blank\"---> this is used for open the screenshot in new TAB
	
		Reporter.log("<br>");
		//Reporter.log("<a target =\"_blank\"href=\"C:\\Users\\Guru Prasad Das\\Pictures\\Screenshots\\error.png\"><img\ "C:\\Users\\Guru Prasad Das\\Pictures\\Screenshots\\error.png\" >Screenshot</a>");
		
	
	
	}
	

} 
