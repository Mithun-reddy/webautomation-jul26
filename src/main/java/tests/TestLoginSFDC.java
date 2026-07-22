package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.salesforce.oauth.SalesforceAuth;

import driver.DriverFactory;
import pages.LoginPage;

public class TestLoginSFDC {
	
	public static void main(String[] args) throws Exception {
		SalesforceAuth sfdc = new SalesforceAuth("3MVG9HtWXcDGV.nE1Nul.Lp1lcWv9nNxykC3JB4.0UCC_1l1mZ6lFGgQJyd.l3AZm7oR_ydMQQmTWeliG_Xtr", "0BDB3806AB2AA1D1484CD91BF179108CB05438E1D99EA324A721A7F018EC6832", false);
		System.out.println(sfdc.start());
		
		WebDriver driver = DriverFactory.getDriver();
		
		LoginPage lp = new LoginPage(driver);
		
		lp.enterUsername("");
		lp.enterPassword("");
		
	}

}
