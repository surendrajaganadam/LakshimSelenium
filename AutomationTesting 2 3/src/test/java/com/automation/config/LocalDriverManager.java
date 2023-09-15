package com.automation.config;

import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;

/**
 * The Class LocalDriverManager.
 */
public class LocalDriverManager {

	/** The web driver. */
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	private static ThreadLocal<AppiumDriver> AppiumDriver = new ThreadLocal<AppiumDriver>();

	/**
	 * Gets the driver.
	 *
	 * @return the driver
	 * 
	 */
	public static WebDriver getDriver() {
		return webDriver.get();
	}
	
	public static void setWebDriver(WebDriver driver) {
		webDriver.set(driver);
	}
	
	
	
	
	public static AppiumDriver getAppiumDriver() {
		return AppiumDriver.get();
	}

	/**
	 * Sets the web driver.
	 *
	 * @param driver
	 *            the new web driver
	 */
	
	static void setAppiumDriver(AppiumDriver driver) {
		AppiumDriver.set(driver);
	}
	
	
	
}