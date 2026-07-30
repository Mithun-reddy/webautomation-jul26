package tests;


import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;

import driver.DriverFactory;
import listeners.RetryTransformer;
import listeners.TestListener;


@Listeners({ChainTestListener.class ,TestListener.class, RetryTransformer.class})
public class BaseTest {
//	protected static WebDriver driver = null; 
	
	@BeforeTest
	public static void setupBrowser() {
		
	}
	
	@Parameters({"bName"})
	@BeforeMethod
	public static void getDriver(@Optional("chrome") String browserName) {
		DriverFactory.createDriver(browserName);
		WebDriver driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@AfterMethod
	public static void teardownDriver() {
		DriverFactory.quitDriver();
	}

}
