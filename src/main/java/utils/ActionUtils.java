package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import pages.LoginPage;

public class ActionUtils {
	private static final Logger LOG = LogManager.getLogger(ActionUtils.class);
	public static void mouseHover(WebDriver driver, WebElement element) {
		try {
			
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			LOG.info("Performed mouse hover on webelement", element);
		} catch(Exception e) {
			e.printStackTrace();
			LOG.warn("FAILED to Perform mouse hover on webelement", element);
		}
		
	}

}
