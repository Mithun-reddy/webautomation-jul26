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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import driver.DriverFactory;

public final class ScreenshotUtil {
	
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
	
}
