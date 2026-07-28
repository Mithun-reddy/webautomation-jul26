package listeners;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.chaintest.plugins.ChainTestListener;

import utils.ScreenshotUtil;

public class TestListener implements ITestListener {
	private static final Logger LOG = LogManager.getLogger(TestListener.class);
	@Override
	public void onTestStart(ITestResult result) {
		LOG.info("Test is starting",result.getTestName());
		report("Started: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		LOG.info("Test is PASSED",result.getTestName());
		report("TEST PASSED: " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		LOG.info("Test is FAILED",result.getTestName());
		report("TEST FAILED: " + result.getName());
		try {
			ChainTestListener.embed(ScreenshotUtil.captureScreenshot(), "image/png");
			report("Thread - "+Thread.currentThread().getId());
			report("Screenshot captured for the failed test");
			LOG.info("Screenshot captured during test",result.getTestName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void report(String message) {
		LOG.info(message);
		ChainTestListener.log(message);
	}

}
