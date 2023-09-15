package com.automation.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.constants.Constants;
import com.automation.exception.*;
import com.automation.utils.PropertyUtils;

/**
 * The Class SeleniumNGSuite.
 */
public class SeleniumNGSuite {

	/** The logger. */
	private static final Logger LOG = LoggerFactory.getLogger(SeleniumNGSuite.class);

	/** The base project path. */
	public static String baseProjectPath = System.getProperty(Constants.USER_DIR);

	/** The configprops. */
	public static PropertyUtils configprops = new PropertyUtils(baseProjectPath.concat(Constants.CONFIG_PROPERTY));

	/** The browser type. */
	public static String browserType = configprops.getProperty("browser_name");

	/** The url. */
	public static String url = configprops.getProperty(Constants.URL);

	/** The platform name. */
	public static String platformName = configprops.getProperty("platform_name");
	
	/** The device name. */
	public static String deviceName = configprops.getProperty("device_name");
	
	/** The platform version. */
	public static String platformVersion = configprops.getProperty("platform_version");
	
	/** The device url. */
	public static String deviceURL = configprops.getProperty("device_URL");
	
	
	
	/** The current suite. */
	public static String currentSuite = "";

	/** The creating object to DriverConfig. */
	public DriverConfig config = new DriverConfig();
	
	

	/**
	 * Sets the up suite.
	 *
	 * @throws Throwable
	 *             the throwable
	 */
	public void setUpSuite(String executionMode) throws Throwable {
		switch(executionMode){
			case "Web":
				config.setUp(browserType, url);
				break;
				
			case "Mobile":
				config.setUp(platformName,platformVersion,deviceName,browserType,deviceURL);
				break;
				
		}
		
	}

	/**
	 * Tear down.
	 *
	*/
	public void tearDown() throws AutomationException {

		try { 
				LocalDriverManager.getDriver().quit();
			LOG.info("Successfully closed the browser ");
		}catch (Exception exception) {
			LOG.error("Error in closing the browser:: {}", exception.getMessage());
			exception.printStackTrace();
			throw new AutomationException(exception);
		}

	}
	
	
}