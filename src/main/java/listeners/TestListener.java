package listeners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.chaintest.plugins.ChainTestListener;

import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		report("Started: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		report("TEST PASSED: " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		report("TEST FAILED: " + result.getName());
		try {
			ChainTestListener.embed(ScreenshotUtil.captureScreenshot(), "image/png");
			report("Thread - "+Thread.currentThread().getId());
			report("Screenshot captured for the failed test");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void report(String message) {
		ChainTestListener.log(message);
	}

}
