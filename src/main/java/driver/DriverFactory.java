package driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import config.ConfigManager;
import tests.LoginTest;

public final class DriverFactory {

	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<WebDriver>();
	private static final Logger LOG = LogManager.getLogger(DriverFactory.class);
	
	private DriverFactory() {

	}

	public static void createDriver() {
		LOG.info("Creating driver");
		String browser = ConfigManager.get("browser").toLowerCase();
		LOG.info("browser being configured", browser);
		boolean headless = ConfigManager.getBoolean("headless");
		LOG.info("is headless browser ", headless);
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
			LOG.warn("check configuration browser name is missing and defaulting to chrome browser ");
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
	
	//TRACE: DEtailed steps 
	//DEBUG: Calculated values, input values
	//INFO : normal application started
//	WARN: Retry
//	ERROR: Failure
//	FATAL: Application cannot run

}
