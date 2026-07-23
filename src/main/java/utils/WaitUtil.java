package utils;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;

public class WaitUtil {
	
	public void waitForElementToBeClicked(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver()	, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForElementToBeVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver()	, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch(Exception e) {
			e.printStackTrace();
		}
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
