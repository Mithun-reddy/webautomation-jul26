package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.oauth.SalesforceAuth;

import config.ConfigManager;
import driver.DriverFactory;

public class LoginPage extends BasePage {
	private static final Logger LOG = LogManager.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// page objects -

	@FindBy(id = "Login")
	public WebElement loginButton;

	@FindBy(id = "username")
	public WebElement username;

	@FindBy(id = "password")
	public WebElement password;

	@FindBy(id = "rememberUn")
	public WebElement rememberMeCheckbox;

	@FindBy(id = "forgot_password_link")
	public WebElement forgotPasswordLink;

	@FindBy(id = "error")
	public WebElement errorMessage;

	public void enterUsername(String userID) {
		if (username.isDisplayed()) {
			username.sendKeys(userID);
			LOG.info("Entered username");
		}
	}

	public void enterPassword(String pass) {
		if (password.isDisplayed()) {
			password.sendKeys(pass);
			LOG.info("Entered password (check config)");
		}
	}

	public String getUsername() {
		if (username.isDisplayed()) {
			LOG.info("Attribute value fetched is: ",username.getAttribute("value"));
			return username.getAttribute("value");
		} else {
			return "";
		}
	}

	public String getPassword() {
		if (password.isDisplayed()) {
			return password.getAttribute("value");
		} else {
			return "";
		}
	}

	public void clearPassword() {
		if (password.isDisplayed()) {
			password.clear();
		}
	}

	/**
	 * 
	 */
	public void selectRememberMeCheckbox() {
		if (!rememberMeCheckbox.isSelected()) {
			rememberMeCheckbox.click();
		} else {
			LOG.info("Checkbox is already selected");
		}
	}

	/**
	 * THis function will fetch the error message if login fails
	 * 
	 * @return
	 */
	public String getLoginErrorMessage() {
		if (errorMessage.isDisplayed()) {
			return errorMessage.getText();
		} else
			return "";
	}

	public void clickForgotPassword() {
		forgotPasswordLink.click();
	}

	public HomePageLightningXp loginToApp() throws Exception {
		SalesforceAuth login = new SalesforceAuth(ConfigManager.get("consumer.key"),
				ConfigManager.get("consumer.secret"), false);
		String url = login.start();
		WebDriver driver = DriverFactory.getDriver();
		driver.navigate().to(url);
		return new HomePageLightningXp(driver);
	}
}
