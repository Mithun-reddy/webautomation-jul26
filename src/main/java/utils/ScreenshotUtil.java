package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.text.DateFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import driver.DriverFactory;
import pages.LoginPage;

public final class ScreenshotUtil {
	private static final Logger LOG = LogManager.getLogger(ScreenshotUtil.class);
	private ScreenshotUtil()
	{
		
	}
	
	public static File captureScreenshot() throws IOException {
		String date = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
		Path target = Path.of("src/main/resources/screenshots","_"+date+".png");
		if(DriverFactory.getDriver()== null) {
			return null;
		} else {
			Files.createDirectories(target.getParent());
			File source = ((TakesScreenshot)DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, target.toFile());
			return target.toFile();
		}
	}
	
	/**
     * Locates an element in the current thread's browser and captures only that element.
     *
     * @param locator locator for the target element
     * @return PNG bytes, or {@code null} when no browser session is available
     */
    public static byte[] captureElementBytes(WebElement element) {
        if (DriverFactory.getDriver() == null) {
            LOG.warn("Element screenshot capture skipped because no browser session is available");
            return null;
        }
        LOG.debug("Capturing element screenshot");
        return element.getScreenshotAs(OutputType.BYTES);
    }
	
}
