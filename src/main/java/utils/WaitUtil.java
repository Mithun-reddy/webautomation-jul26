package utils;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;

public class WaitUtil {
	
	public boolean waitForElementToBeClicked(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver()	, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean waitForElementToBeVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver()	, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean waitForElementVisiblity(WebElement elementToWait, WebDriver driver) {
		boolean isElementVisible = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOf(elementToWait));
			isElementVisible = true;
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
		return isElementVisible;
	}
	
	public static boolean waitForElementInVisiblity(WebElement elementToWait, WebDriver driver) {
		boolean isElementVisible = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.invisibilityOf(elementToWait));
			isElementVisible = true;
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
		return isElementVisible;
	}
	
	public static boolean waitForTextToBePresent(WebElement elementToWait, WebDriver driver, String text) {
		boolean isElementTextVisible = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.textToBePresentInElement(elementToWait, text));
			isElementTextVisible = true;
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
		return isElementTextVisible;
	}
	
	public WebElement fluentlyWait(WebElement element) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(DriverFactory.getDriver())
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(100))
				.ignoring(NoSuchElementException.class);
		
		WebElement ele = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return element;
			}
		});
		return ele;
	}
	
}
