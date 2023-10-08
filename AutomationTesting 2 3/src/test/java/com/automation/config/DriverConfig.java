package com.automation.config;



import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ThreadGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.constants.Constants;
import com.automation.exception.AutomationException;
import com.automation.utils.PropertyUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;


/**
 * The Class DriverConfig.
 *
 */
public class DriverConfig {

	/** The base project path. */
	public static String baseProjectPath = System.getProperty(Constants.USER_DIR);
	
	/** The configprops. */
	public static PropertyUtils configprops = new PropertyUtils(baseProjectPath.concat(Constants.CONFIG_PROPERTY));

	/** The logger. */
	private static final Logger LOG = LoggerFactory.getLogger(DriverConfig.class);
	
	public static File folder;
	
	public DesiredCapabilities capabilities = new DesiredCapabilities();
	
	/**
	 * This method returns the driver object for the given browser type with the
	 * page loaded with the given url.
	 *
	 * @param browserType
	 *   the browser type
	 * @param url
	 *   the url
	 * @throws Throwable
	 *    the throwable
	 */

	public void setUp(String browserType, String url) throws Throwable {
		try {
			WebDriver driver;
			
			switch (browserType) {
			case Constants.MOZILLA_FIREFOX:
				try {
					System.setProperty(Constants.GEKO_DRIVER, baseProjectPath.concat(Constants.GEKO_DRIVER_PATH));
					driver = new FirefoxDriver();
					LocalDriverManager.setWebDriver(driver);
				} catch (Exception exception) {
					exception.printStackTrace();
					LOG.error("Error while loading Firefox driver::{}", exception.getMessage());
				} 
				break;
			case Constants.CHROME:
				try {
					System.setProperty(Constants.CHROME_WEBDRIVER,
							"C:\\Users\\mvssr\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
					driver = new ChromeDriver();
					LocalDriverManager.setWebDriver(driver);
				} catch (Exception exception) {
					LOG.error("Error while loading Chrome driver::{}", exception.getMessage());
				} 
				break;


						}
			Thread.sleep(1000);
			
			LocalDriverManager.getDriver().get(url);
			LocalDriverManager.getDriver().manage().window().maximize();

		} catch (Throwable throwable) {
			LOG.error("Error Occured in Getting the driver object ::{}", throwable.getMessage());
			throw new AutomationException(throwable);
		}
	}

	public void setUp(String platformName, String platformVersion, String deviceName, String browserType, String deviceUrl) throws Throwable {
		try {
			AppiumDriver driver ;
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, Constants.ANDROID_AUTOMATION_NAME);
			capabilities.setCapability(MobileCapabilityType.PLATFORM, platformName);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			capabilities.setCapability(MobileCapabilityType.APP,baseProjectPath.concat(Constants.APK_FILES_PATH));
			driver = new AndroidDriver<MobileElement>(new URL(deviceUrl), capabilities);
			driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
			LocalDriverManager.setWebDriver(driver);
			LocalDriverManager.setAppiumDriver(driver);

		}catch (Throwable throwable) {
		LOG.error("Error Occured in Getting the driver object ::{}", throwable.getMessage());
		throw new AutomationException(throwable);
	}
		
	}
}
