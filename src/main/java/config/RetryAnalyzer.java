package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import listeners.TestListener;

public class RetryAnalyzer implements IRetryAnalyzer{
	private static final Logger LOG = LogManager.getLogger(RetryAnalyzer.class);
	private int retryCount = 1;
	private static final int MAX_RETRY_COUNT = 3;
	@Override
	public boolean retry(ITestResult result) {
		LOG.info("RETRIED TEST: Count :",retryCount ,result.getName());
		if(retryCount < MAX_RETRY_COUNT) {
			retryCount++;
			return true;
		}
		return false;
	}

}
