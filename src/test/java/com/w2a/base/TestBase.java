package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.apache.log4j.Logger;

import com.w2a.utilities.ExcelReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;

	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;

	@BeforeSuite
	public void setUp() {
		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file Loaded !!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			FileInputStream fis = null;
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file Loaded !!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (config.getProperty("browser").equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.debug("Chrome Launched !!");

			} else if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("Webdriver.geckodriver.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");

			} else if (config.getProperty("browser").equals("IE")) {
				System.setProperty("Webdriver.geckodriver.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDRIVERServer.exe");
			}
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : " + config.getProperty("testsiteurl"));

			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, 5);

		}

	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@AfterSuite
	public void TearDown() {
		if (driver != null) {
			driver.quit();
		}
		log.debug("Test Excecution Completed");
	}
}
