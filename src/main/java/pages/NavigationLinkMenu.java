package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import driver.DriverFactory;

public class NavigationLinkMenu extends BasePage {

	public NavigationLinkMenu(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "userNavLabel")
	public WebElement userNavLabel;

	@FindBy(linkText = "My Profile")
	public WebElement myProfile;

	@FindBy(css = "[id='userNav-menuItems'] > a")
	public List<WebElement> usermenuOptions;
	
	public MyProfilePage navigateToMyProfilePage() {
		if (userNavLabel.isDisplayed()) {
			myProfile.click();
		} else {

		}
		
		return new MyProfilePage(DriverFactory.getDriver());
	}
	
	public ArrayList<String> getUserMenuOptions() {
		if(!(usermenuOptions.get(1).isDisplayed())) {
			userNavLabel.click();
		} 
		ArrayList<String> menuOptions = new ArrayList<String>();
		for(WebElement element: usermenuOptions) {
			menuOptions.add(element.getText());
		}
		return menuOptions;
	}
	

}
