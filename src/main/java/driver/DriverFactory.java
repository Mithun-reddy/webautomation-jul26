package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import config.ConfigManager;

public final class DriverFactory {

	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<WebDriver>();

	private DriverFactory() {

	}

	public static void createDriver() {
		String browser = ConfigManager.get("browser").toLowerCase();
		boolean headless = ConfigManager.getBoolean("headless");
		WebDriver driver = null;
		switch (browser) {
		case "chrome":
			driver = new ChromeDriver(options(new ChromeOptions(), headless));
			break;
		case "safari":
			driver = new SafariDriver(options(new SafariOptions(), headless));
			break;
		case "firefox":
			driver = new FirefoxDriver(options(new FirefoxOptions(), headless));
			break;
		default:
			System.out.println("Config does not contain a valid browser name defaulting to chrome browser");
			driver = new ChromeDriver();
			break;
		}
		DRIVER.set(driver);
	}
	
	public static WebDriver getDriver() {
		return DRIVER.get();
	}
	
	public static void quitDriver() {
		if(DRIVER.get()!= null) {
			DRIVER.get().quit();
			DRIVER.remove();
		}
	}
	
	private static ChromeOptions options(ChromeOptions options, boolean headless) {
		if(headless) {
			options.addArguments("--headless");
		} 
		options.addArguments("--disable-notifications");
		return options;
	}
	
	private static FirefoxOptions options(FirefoxOptions options, boolean headless) {
		if(headless) {
			options.addArguments("--headless");
		} 
		options.addArguments("--disable-notifications");
		return options;
	}
	
	private static SafariOptions options(SafariOptions options, boolean headless) {
		if(headless) {
			options.setCapability("", headless);
		} 
		options.setCapability("--disable-notifications", headless);
		return options;
	}

}
