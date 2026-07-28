package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import config.ConfigManager;
import driver.DriverFactory;
import listeners.TestListener;
import pages.LoginPage;

//@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
	private static final Logger LOG = LogManager.getLogger(LoginTest.class);
	

	@Test
	public static void loginErrorMessage_TC01() {
		WebDriver driver = DriverFactory.getDriver();
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
	public static void userMenu_TC05() throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp();
		driver.navigate().refresh();
		
	}
}
