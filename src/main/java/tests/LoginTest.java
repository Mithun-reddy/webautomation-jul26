package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import config.ConfigManager;
import pages.LoginPage;

public class LoginTest extends BaseTest {

//	@Test
	public static void loginErrorMessage_TC01() {
		LoginPage lp = new LoginPage(driver);
		driver.get(ConfigManager.get("app.url"));
		lp.enterUsername(ConfigManager.get("sfdc.username"));
		Assert.assertEquals(lp.getUsername(), ConfigManager.get("sfdc.username"));
		lp.clearPassword();
		Assert.assertEquals(lp.getPassword(), "");
		lp.loginButton.click();
		String actualErrorMessage = lp.getLoginErrorMessage();
		Assert.assertEquals(actualErrorMessage, ConfigManager.get("login.error.message"));
	}
	
	@Test
	public static void usermenu() throws Exception {
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp();
		driver.navigate().refresh();
		
	}
}
