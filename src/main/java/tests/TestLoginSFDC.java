package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.salesforce.oauth.SalesforceAuth;

import driver.DriverFactory;
import pages.LoginPage;
import utils.ScreenshotUtil;

public class TestLoginSFDC {
	
	public static void main(String[] args) throws Exception {
		DriverFactory.createDriver();
		WebDriver driver = DriverFactory.getDriver();
		
		driver.get("https://login.salesforce.com");
		Thread.sleep(3000);
		System.out.println( ScreenshotUtil.captureScreenshot());
		
		LoginPage lp = new LoginPage(driver);
		
		lp.enterUsername("");
		lp.enterPassword("");
		
	}

}
