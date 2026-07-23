package tests;


import java.sql.Driver;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import config.ConfigManager;
import driver.DriverFactory;

public class BaseTest {
	protected static WebDriver driver = null; 
	
	@BeforeTest
	public static void setupBrowser() {
		DriverFactory.createDriver();
	}
	
	@BeforeMethod
	public static void getDriver() {
		driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@AfterMethod
	public static void teardownDriver() {
		DriverFactory.quitDriver();
	}

}
