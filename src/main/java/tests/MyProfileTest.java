package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import config.ConfigManager;
import config.RetryAnalyzer;
import constants.FileConstants;
import driver.DriverFactory;
import pages.HomePageLightningXp;
import pages.LoginPage;
import pages.MyProfilePage;
import pages.NavigationLinkMenu;

public class MyProfileTest extends BaseTest {
	
	@Test(groups = {"regression", "smoke"})
	public static void myProfile_TC6() throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		LoginPage lp = new LoginPage(driver);
		HomePageLightningXp hpLx = lp.loginToApp();
		driver.navigate().refresh();
		hpLx.clickOnProfileIcon();
		hpLx.waitForSpinnerToDisapear(driver);
		hpLx.switchToSFClassic();
		
		NavigationLinkMenu menu = new NavigationLinkMenu(driver);
		
		String[] actualUserMenuValues = menu.getUserMenuOptions().toArray(new String[0]);
		String[] expectedUserMenuValues = ConfigManager.get("usermenu.options").split(",");
		Assert.assertEquals(actualUserMenuValues, expectedUserMenuValues);
		
		MyProfilePage myProfile = menu.navigateToMyProfilePage();
		
		Assert.assertTrue(myProfile.isProfilePageLoaded(driver), "Failed to load profile page");
		
		myProfile.waitAndClickOnEditIcon(driver);
		
		Assert.assertTrue(myProfile.verifyEditProfilePopUpIsVisible(driver), "Failed to load Edit profile window");
		
		Assert.assertTrue(myProfile.verifyChangeLastNameInAboutTab(driver, ConfigManager.get("user.lastname")), "Failed to update last name");
		
		Assert.assertTrue(myProfile.verifyCreatePost(driver, "Hello"), "Failed to create post");
		
		Assert.assertTrue(myProfile.verifyFileUpload(driver, FileConstants.FILE_UPLOAD_PATH), "Failed to upload file");
		
		Assert.assertTrue(myProfile.verifyAddPhoto(driver, FileConstants.TEST_IMAGE_UPLOAD_PATH), "Failed to add profile photo");
		
		
		
		
	}

}
