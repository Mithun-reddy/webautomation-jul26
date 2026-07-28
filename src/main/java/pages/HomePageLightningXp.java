package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.WaitUtil;

public class HomePageLightningXp extends BasePage {

	public HomePageLightningXp(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//img[@class=\"icon noicon\"]/parent::span")
	public WebElement myProfileIcon;
	
	
	@FindBy(linkText = "Switch to Salesforce Classic")
	public WebElement salesforceClassic;
	
	
	@FindBy(className = "forceInlineSpinner")
	public WebElement menuSpinner;
	
	
	public void clickOnProfileIcon() {
		if(myProfileIcon.isDisplayed()) {
			myProfileIcon.click();
		}
	}
	
	public boolean waitForSpinnerToDisapear(WebDriver driver) {
		if(WaitUtil.waitForElementVisiblity(menuSpinner, driver)) {
			if(WaitUtil.waitForElementInVisiblity(menuSpinner, driver)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public  void switchToSFClassic() {
		if(salesforceClassic.isDisplayed()) {
			salesforceClassic.click();
		}
	}

}
