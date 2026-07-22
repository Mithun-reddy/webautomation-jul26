package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {


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
		}
	}

	public void enterPassword(String pass) {
		if (password.isDisplayed()) {
			password.sendKeys(pass);
		}
	}
	
	public String getUsername() {
		if(username.isDisplayed()) {
			return username.getAttribute("value");
		} else {
			return "";
		}
	}

	public void clearPassword(String pass) {
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
			System.out.println("Checkbox is already selected");
		}
	}

	/**
	 * THis function will fetch the error message if login fails
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
}
