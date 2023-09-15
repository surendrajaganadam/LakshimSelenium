package com.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.codehaus.plexus.util.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.constants.*;
import com.automation.exception.AutomationException;
import com.automation.config.LocalDriverManager;
import com.automation.constants.Constants;
import com.automation.utils.DCTUtils;
import com.automation.utils.TestResultsUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

//import ru.yandex.qatools.ashot.AShot;
//import ru.yandex.qatools.ashot.Screenshot;
//import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import java.net.URL;

import com.automation.config.*;

/**
 * The Class DriverUtilsImpl.
 *
 */

/** The base project path. */
public class DriverUtilsImpl implements DriverUtils {
	public static String baseProjectPath = System.getProperty(Constants.USER_DIR);

	/** The logger. */
	private static final Logger LOG = LoggerFactory.getLogger(DriverUtilsImpl.class);

	/** The configprops. */
	public static PropertyUtils configprops = new PropertyUtils(baseProjectPath.concat(Constants.CONFIG_PROPERTY));

	/** The url. */
	private String URL;

	/** The handle popups. */
	public static String handlePopups = configprops.getProperty(Constants.HANDLE_POPUP);

	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#isPageLoaded()
	 */
	@Override
	public boolean isPageLoaded() throws AutomationException {
		try {
			
			return (LocalDriverManager.getDriver().getTitle().contains(pageTitle));
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}
	}

	public WebElement identifyByAccessibilityId(String locator) throws Exception{
		try {
			
			AndroidDriver driver= (AndroidDriver) LocalDriverManager.getDriver();
			return driver.findElementByAccessibilityId(locator);
			
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#openPage(java.lang.String)
	 */
	@Override
	public void openPage(String webSiteUrl) throws AutomationException {
		try {
			URL = webSiteUrl;
			addPageLoadTimeout();

			LocalDriverManager.getDriver().get(webSiteUrl);
		} catch (TimeoutException timeout) {
			hitEscapeKeyForPopups();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new AutomationException(exception);
		}
	}
	

	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#getTitle()
	 */
	@Override
	public String getTitle() {
		return pageTitle;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#getURL()
	 */
	@Override
	public String getURL() {
		return URL;
	}

	/**
	 * Send text keys to the element that finds by cssSelector. It shortens
	 * "LocalDriverManager.getDriver().findElement(By.cssSelector()).sendKeys()".
	 *
	 * @param idSelector
	 *            the id selector
	 * @param text
	 *            the text
	 * @return true, if successful
	 * @throws AutomationException
	 *             the automation exception
	 */
	@Override
	public boolean sendText(By idSelector, String text) throws AutomationException {
		try{
			if(LocalDriverManager.getDriver().findElement(idSelector).isEnabled()){
			LocalDriverManager.getDriver().findElement(idSelector).clear();
			LocalDriverManager.getDriver().findElement(idSelector).sendKeys(text);
			return true;
			}
			else{
				return false;
			}
		}catch(Throwable t){
			LOG.error("Printing error: " + t.getMessage());
			throw new AutomationException("Send Text error");
		}
	}

	/**
	 * Send text keys to the element that finds by cssSelector. It shortens
	 * "LocalDriverManager.getDriver().findElement(By.cssSelector()).sendKeys()".
	 *
	 * @param by
	 *            the by
	 * @return true, if is element present
	 * @throws AutomationException
	 */

	/** Is the Element in page. */
	@Override
	public boolean isElementPresent(By by) throws AutomationException {
		try {
			LocalDriverManager.getDriver().findElement(by);// if it does not
			// find the element
			// throw
			// NoSuchElementException, thus returns
			// false.
			return true;

		} catch (Exception exception) {
			//throw new AutomationException(exception);
			return false;
		}
	}
	
	public void gAcceptAlert() throws AutomationException {
		try {
			
			LocalDriverManager.getDriver().switchTo().alert().accept();
					
		}catch (Throwable t) {
			LOG.error("Printing error : " + t.getMessage());
			throw new AutomationException("gAlertAccept Exception");
		}
	}
	
	public void gDismissAlert() throws AutomationException {
		try {
			
			LocalDriverManager.getDriver().switchTo().alert().dismiss();
					
		}catch (Throwable t) {
			LOG.error("Printing error : " + t.getMessage());
			throw new AutomationException("gAlertDismiss Exception");
		}
	}

	/**
	 * Number of elements present.
	 *
	 * @param by
	 *            the by
	 * @return the int
	 * @throws AutomationException
	 *             the automation exception
	 */
	@Override
	public int numberOfElementsPresent(By by) throws AutomationException {
		try {
			return LocalDriverManager.getDriver().findElements(by).size();
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}
	}

	/**
	 * Send text keys to the element that finds by cssSelector. It shortens
	 * "LocalDriverManager.getDriver().findElement if the element is not present
	 * on the page then returns true
	 *
	 * @param by
	 *            the by
	 * @return true, if is element not present
	 * @throws AutomationException
	 *             the automation exception
	 */

	/** Is the Element in page. */
	@Override
	public boolean isElementNotPresent(By by) throws AutomationException {
		// if it does not find the element then
		try {
			if (LocalDriverManager.getDriver().findElements(by).size() < 1) {
				return true;
			} else {
				return false;
			}

		} catch (Exception exception) {
			LOG.error("Not Entered");
			return false;
		}
	}

	/**
	 * Is the Element present in the DOM.
	 *
	 * @param _cssSelector
	 *            element locater
	 * @return WebElement
	 * @throws AutomationException
	 *             the automation exception
	 */

	@Override
	public boolean isElementPresent(String _cssSelector) throws AutomationException {
		try {
			LocalDriverManager.getDriver().findElement(By.cssSelector(_cssSelector));
			return true;

		} catch (NoSuchElementException exception) {
			throw new AutomationException(exception);
		}
	}

	/**
	 * Checks if the element is in the DOM and displayed.
	 *
	 * @param by
	 *            - selector to find the element
	 * @return true or false
	 * @throws AutomationException
	 *             the automation exception
	 */
	@Override
	public boolean isElementPresentAndDisplayed(By by) throws AutomationException {
		try {
			return LocalDriverManager.getDriver().findElement(by).isDisplayed();
		} catch (NoSuchElementException exception) {
			throw new AutomationException(exception);
		}
	}

	/**
	 * Returns the first WebElement using the given method. It shortens
	 * "LocalDriverManager.getDriver().findElement(By)".
	 *
	 * @param by
	 *            element locater.
	 * @return the first WebElement
	 * @throws AutomationException
	 *             the automation exception
	 */
	@Override
	public WebElement getWebElement(By by) throws AutomationException {
		try {
			return LocalDriverManager.getDriver().findElement(by);
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}
	}

	// ***********************************************************************************************************************
	// 'Function Name : gGetLlist
	// 'Script Description : Returns the List of a given WebElement
	// 'Input Parameters :
	// 'Return Value :

	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#gGetLlist(org.openqa.selenium.By)
	 */
	@Override
	public List<WebElement> gGetLlist(By wElement) throws AutomationException {

		try {
			List<WebElement> strList = LocalDriverManager.getDriver().findElements(wElement);
			return strList;

		} catch (Exception exception) {

			throw new AutomationException(exception);
		}

	}

	/**
	 * Gets the web elements count.
	 *
	 * @param wElement
	 *            the w element
	 * @return the web elements count
	 * @throws AutomationException
	 *             the automation exception
	 */
	public int getWebElementsCount(By wElement) throws AutomationException {

		try {
			int count = LocalDriverManager.getDriver().findElements(wElement).size();
			return count;

		} catch (Exception exception) {

			throw new AutomationException(exception);
		}

	}

	// ***********************************************************************************************************************
	// 'Function Name : gExplictWait
	// 'Script Description : To wait for a webelement in a application
	// 'Input Parameters : 1. elementLocator - Locator(By) of Element to be wait
	// for
	// 2. maxTimeOut - Integer value of max time out in second
	// 3. strConditionMode - Condition mode to apply on wait (possible values
	// are ,VISIBILITY,INVISIBILITY ,FRAME ,PRESENCE )
	// 4. strFrameLocator - Frame ID(String) , when strConditionMode selected as
	// FRAME
	// 'Return Value :

	/**
	 * The Enum conditionalWait.
	 */
	public enum conditionalWait {

		/** The visibility. */
		VISIBILITY,
		/** The invisibility. */
		INVISIBILITY,
		/** The frame. */
		FRAME,
		/** The presence. */
		PRESENCE,
		/** The popupalert. */
		POPUPALERT;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.automation.util.DriverUtils#gExplicitWait(org.openqa.selenium.By,
	 * int, java.lang.String, java.lang.String)
	 */
	
	@Override
	public void gExplicitWait(By elementLocator, int maxTimeOut,
String strConditionMode, String strName) throws AutomationException {
	 
	try {
	 
	 conditionalWait mode =
	 conditionalWait.valueOf(strConditionMode.toUpperCase()); 
	//  Reporter.log("'gExplicitWait' function called for "+ 	  strConditionMode);
	 
	 switch (mode) { case VISIBILITY: (new
	 WebDriverWait(LocalDriverManager.getDriver(), maxTimeOut))
	 .until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
	 break;
	 
	 case INVISIBILITY: (new WebDriverWait(LocalDriverManager.getDriver(),
	 maxTimeOut))
	 .until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
	 break;
	 
	 case FRAME: //
	 // Reporter.log("gExplicitWait is called to wait for element :"+ //	  strName); 
	 (new WebDriverWait(LocalDriverManager.getDriver(), maxTimeOut))
	 .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(strName)); 
	 new	 WebDriverWait(LocalDriverManager.getDriver(), 10).until(
	 ExpectedConditions.visibilityOfElementLocated(By.xpath(
	 "//*[contains(text(),'Test')]"))); break;
	 
	 case PRESENCE: (new WebDriverWait(LocalDriverManager.getDriver(),
	 maxTimeOut))
	 .until(ExpectedConditions.presenceOfElementLocated(elementLocator));
	 break;
	 
	 case POPUPALERT: List<WebElement> popupElements =
	 LocalDriverManager.getDriver().findElements(elementLocator); int count =
	 popupElements.size(); if (count > 0) { (new
	 WebDriverWait(LocalDriverManager.getDriver(), maxTimeOut))
	 .until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
	 for (int j = 0; j < count; j++) { popupElements.get(j).click();
	 Thread.sleep(1500); } break; } } } catch (Exception exception) { throw
	 new AutomationException(exception); }
	 
	 }

	// '**************************************************************************************************************
	// 'Function Name : gElementFocus
	// 'Script Description : To Focus on a particular Element
	// 'Input Parameters : strXpath - Xpath of the Element to be focused.
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.automation.util.DriverUtils#gElementFocus(org.openqa.selenium.By)
	 */
	// 'Return Value : true/false
	@Override
	public boolean gElementFocus(By wElement) throws AutomationException {

		try {
			// create a WebElement for a given object using it's Xpath/Id
			WebElement strElement = LocalDriverManager.getDriver().findElement(wElement);

			// Focus on given element
			new Actions(LocalDriverManager.getDriver()).moveToElement(strElement).perform();

			return true;

		} catch (Exception exception) {

			throw new AutomationException(exception);
		}
	}

	// '***********************************************************************************************************************
	// 'Function Name : gClickLinkOrButton
	// 'Script Description : To perform link click based on class name and text
	// properties
	// 'Input Parameters :
	// ' 1. strLink - Id/Name/linktext/Xpath of the link/button
	// 2. index - position of the link that you want to click(Ex: 0,1)
	// 'Return Value : true

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.automation.util.DriverUtils#gClickLinkOrButton(org.openqa.selenium.
	 * By, int)
	 */
	@Override
	public boolean gClickLinkOrButton(By strLink, int index) throws AutomationException {

		try {
			List<WebElement> lstLinks = LocalDriverManager.getDriver().findElements(strLink);
			addPageLoadTimeout();
			lstLinks.get(index).click();
			Thread.sleep(2000);
			return true;

		} catch (Exception exception) {
			hitEscapeKeyForPopups();
			throw new AutomationException(exception);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#gClick(org.openqa.selenium.By)
	 */
	/*
	 * *************************************************************************
	 * *********************************************** Function Name: click on
	 * any element
	 *
	 * Description : To perform click operation on button or link and etc
	 */
	@Override
	public boolean gClick(By elementLocator) throws AutomationException {
		try {
			LOG.info("in gclick ::{}", elementLocator);
			if (LocalDriverManager.getDriver().findElement(elementLocator).isEnabled()) {
				LocalDriverManager.getDriver().manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
				LocalDriverManager.getDriver().findElement(elementLocator).click();
				Thread.sleep(5000);
				hitEscapeKeyForPopups();
				return true;
			} else {
				LOG.info("Element is not enabled::{}", elementLocator);
				return false;
			}
		} catch (Throwable t) {
			LOG.error("Printing error : " + t.getMessage());
			throw new AutomationException("gClick Exception");
		}
	}

	/*
	 * *************************************************************************
	 * *********************************************** Function Name: click on
	 * any element using javascript executor
	 *
	 * Description : To perform click operation on button or link and etc
	 */
	public boolean jClick(By elementLocator) throws AutomationException {
		try {
			if (LocalDriverManager.getDriver().findElement(elementLocator).isEnabled()) {
				LOG.info(LocalDriverManager.getDriver().findElement(elementLocator).getText());
				WebElement element = LocalDriverManager.getDriver().findElement(elementLocator);
				JavascriptExecutor executor = (JavascriptExecutor) LocalDriverManager.getDriver();
				executor.executeScript("arguments[0].click();", element);
				return true;
			} else {
				return false;
			}
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}
	}

	/**
	 * G click.
	 *
	 * @param elementLocator
	 *            the element locator
	 * @param text
	 *            the text
	 * @return true, if successful
	 * @throws AutomationException
	 *             the automation exception
	 */
	public boolean gClick(By elementLocator, String text) throws AutomationException {
		try {
			String xPath = elementLocator.toString();
			if (xPath.contains("dummy")) {
				xPath = buildXpath(xPath, text);
			} else {
				return false;
			}
			By element = By.xpath(xPath);
			if (LocalDriverManager.getDriver().findElement(element).isEnabled()) {
				LocalDriverManager.getDriver().findElement(element).click();
				Thread.sleep(5000);
				hitEscapeKeyForPopups();
				LOG.info("after escape");
				return true;
			} else {
				return false;
			}
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}
	}

	/**
	 * J click.
	 *
	 * @param elementLocator
	 *            the element locator
	 * @param text
	 *            the text
	 * @return true, if successful
	 * @throws AutomationException
	 *             the automation exception
	 */
	public boolean jClick(By elementLocator, String text) throws AutomationException {
		try {
			String xPath = elementLocator.toString();
			if (xPath.contains("dummy")) {
				xPath = buildXpath(xPath, text);
			} else {
				return false;
			}
			By element = By.xpath(xPath);
			LOG.info("dummy" + element);
			if (LocalDriverManager.getDriver().findElement(element).isEnabled()) {
				WebElement jelement = LocalDriverManager.getDriver().findElement(element);
				JavascriptExecutor executor = (JavascriptExecutor) LocalDriverManager.getDriver();
				executor.executeScript("arguments[0].click();", jelement);
				return true;
			} else {
				return false;
			}
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}
	}

	// '**********************************************************************************************************************
	// 'Function Name : gEditTextbox
	// 'Script Description : To perform enter text in a textbox based on name
	// property
	// 'Input Parameters :
	// ' 1. strName - Name of the Textbox
	// 2. strValue - Value of the Textbox
	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#gEditTextbox(org.openqa.selenium.By,
	 * java.lang.String, int)
	 */
	// 'Return Value : true
	@Override
	public boolean gEditTextbox(By txtKeywords, String strValue, int index) throws AutomationException {

		try {
			List<WebElement> ls = LocalDriverManager.getDriver().findElements(txtKeywords);
			ls.get(index).clear();
			ls.get(index).sendKeys(strValue);
			return true;

		} catch (Exception exception) {

			throw new AutomationException(exception);
		}

	}

	// '**************************************************************************************************************
	// 'Function Name : gRadioSelectValue
	// 'Script Description : To set the value of a Radio Button
	// 'Input Parameters : 1. strName 2. strRadioValue - Value to be selected in
	// the Radiobutton(Ex: 0,1, etc...)
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.automation.util.DriverUtils#gRadioSelectValue(org.openqa.selenium.By,
	 * java.lang.String)
	 */
	// 'Return Value : true
	@Override
	public boolean gRadioSelectValue(By strName, String strRadioValue) throws AutomationException {

		try {
			// List<WebElement> strRadioList =
			// LocalDriverManager.getDriver().findElements(By.name(strName));
			addPageLoadTimeout();
			List<WebElement> strRadioList = LocalDriverManager.getDriver().findElements(strName);
			for (WebElement indRadio : strRadioList) {
				if (strRadioValue.equalsIgnoreCase(indRadio.getAttribute("value"))) {
					indRadio.click();
					break;
				}
			}
			return true;
		} catch (TimeoutException timeout) {
			hitEscapeKeyForPopups();
			return true;
		} catch (Exception exception) {

			throw new AutomationException(exception);
		}
	}

	// '**************************************************************************************************************
	// 'Function Name : gListGetValue
	// 'Script Description : To Get the value in a List box
	// 'Input Parameters : strId - Id property of the List Box
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.automation.util.DriverUtils#gListGetValue(org.openqa.selenium.By)
	 */
	// 'Return Value : strText - Value(Text) of the list box which is selected
	@Override
	public String gListGetValue(By strId) throws AutomationException {
		try {
			String strText = null;
			Select selectListBox = new Select(LocalDriverManager.getDriver().findElement(strId));
			strText = selectListBox.getFirstSelectedOption().getText();
			return strText;
		} catch (Exception exception) {

			throw new AutomationException(exception);
		}
	}

	// '**************************************************************************************************************
	// 'Function Name : gRadioGetValue
	// 'Script Description : To Get the value of a Radio Button
	// 'Input Parameters : strName - Name property of the Radiobutton
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.automation.util.DriverUtils#gRadioGetValue(org.openqa.selenium.By)
	 */
	// 'Return Value : strText - Value of the Radiobutton(Ex: 0,1, etc..)
	@Override
	public String gRadioGetValue(By strName) throws AutomationException {
		try {
			String strText = null;
			List<WebElement> radiostrDocHeader = LocalDriverManager.getDriver().findElements(strName);
			for (WebElement indRadioradiostrDocHeader : radiostrDocHeader) {
				if (indRadioradiostrDocHeader.isSelected() == true) {
					strText = indRadioradiostrDocHeader.getAttribute("value");
					break;
				}
			}

			return strText;

		} catch (Exception exception) {

			throw new AutomationException(exception);
		}
	}

	// '**************************************************************************************************************
	// 'Function Name : gCheckBoxSelect
	// 'Script Description : To Set a value in a CheckBox (On/OFF)
	// 'Input Parameters : 1. strId - ID property of the list box to be selected
	// 2. strChkBoxValue - Value to be selected (ON/OFF)
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.automation.util.DriverUtils#gCheckBoxSelect(org.openqa.selenium.By,
	 * java.lang.String)
	 */
	// 'Return Value : boolean(true/false)
	@Override
	public boolean gCheckBoxSelect(By strId, String strChkBoxValue) throws AutomationException {

		try {

			boolean strFlag = LocalDriverManager.getDriver().findElement(strId).isSelected();
			if ((strFlag == false && strChkBoxValue == "ON") || (strFlag == true && strChkBoxValue == "OFF")) {
				LocalDriverManager.getDriver().findElement(strId).click();
				Thread.sleep(1000);
			}
			return true;

		} catch (Exception exception) {

			throw new AutomationException(exception);
		}
	}
	
	//Mouse hover by our own
	public boolean jMouseHover(WebElement MenuElement) throws AutomationException {
		try {
			Actions builder = new Actions(LocalDriverManager.getDriver());
			//WebElement MenuElement = LocalDriverManager.getDriver().findElement(strXpath);
			builder.moveToElement(MenuElement).build().perform();
			Thread.sleep(2000);
			return true;
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}
	}

	// '**************************************************************************************************************
	// 'Function Name : gMouseHover
	// 'Script Description : To Hover the Mouse on a given link
	// 'Input Parameters : 1. strXpath - ID property of the link to hover
	// 'Return Value : boolean(true/false)

	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#gMouseHover(org.openqa.selenium.By)
	 */
	@Override
	public boolean gMouseHover(By strXpath) throws AutomationException {
		try {
			Actions builder = new Actions(LocalDriverManager.getDriver());
			WebElement MenuElement = LocalDriverManager.getDriver().findElement(strXpath);
			builder.moveToElement(MenuElement).build().perform();
			Thread.sleep(2000);
			return true;
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}
	}

	// '**************************************************************************************************************
	// 'Function Name : gIsDisplayed
	// 'Script Description : To Verfiy the Object is Displayed or not
	// 'Input Parameters : WebElement (Object)
	// 'Return Value :

	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#gIsDisplayed(org.openqa.selenium.By)
	 */
	@Override
	public boolean gIsDisplayed(By wElement) throws AutomationException {

		try {
			LocalDriverManager.getDriver().findElement(wElement).isDisplayed();
			return true;
		} catch (Exception exception) {
			throw new AutomationException(exception);

		}
		

	}
	
	// **************************************************************************************************************
				// 'Function Name : gScrollPageintoviewanelement
				// 'Script Description : Scrolling the page to a webelement
				// 'Input Parameters : webelement
				/*
				 * (non-Javadoc)
				 *
				 * @see com.automation.util.DriverUtils#gScrollPage(int, int)
				 */
			
			public void gScrollPageToElement(By ElementLocator) throws AutomationException{
				 try {
					 WebElement scrollToObj=LocalDriverManager.getDriver().findElement(ElementLocator);
					 ((JavascriptExecutor) LocalDriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", scrollToObj);
					 Thread.sleep(1500);
				} catch (Exception exception) {

					throw new AutomationException();
				}

			}

	// '**************************************************************************************************************
	// 'Function Name : gWebElementGetText
	// 'Script Description : To Get the Text of any web element (ex: link, Page
	// title)
	// 'Input Parameters : WebElement (Object)
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.automation.util.DriverUtils#gWebElementGetTexts(org.openqa.selenium.
	 * By, int)
	 */
	// 'Return Value : true/false
	@Override
	public String gWebElementGetTexts(By wElement, int index) throws AutomationException {
		// TODO check
		try {
			List<WebElement> lstWebElement = LocalDriverManager.getDriver().findElements(wElement);
			if (lstWebElement.size() > 0) {
				String strText = lstWebElement.get(index).getText();
				return strText;
			} else {
				throw new Exception();
			}

		} catch (Exception exception) {

			throw new AutomationException(exception);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.automation.util.DriverUtils#gWebElementGetText(org.openqa.selenium.
	 * By)
	 */
	@Override
	public String gWebElementGetText(By wElement) throws AutomationException {
		try {
			WebElement element = LocalDriverManager.getDriver().findElement(wElement);
			String text = element.getText();
			return text;
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}

	}

	// '**************************************************************************************************************
	// 'Function Name : gListSelect
	// 'Script Description : Select the given value from a list box
	// 'Input Parameters : listName(Name of the list box), listValue(Value to be
	// selected)
	// 'Return Value : true/false

	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#gListSelect(org.openqa.selenium.By,
	 * java.lang.String)
	 */
	
	@Override
	public boolean gListSelect(By lstPracArea, String lstValue) throws AutomationException {

		try {
			new Select(LocalDriverManager.getDriver().findElement(lstPracArea)).selectByVisibleText(lstValue);
			Thread.sleep(3000);
			return true;

		} catch (Exception exception) {
			throw new AutomationException(exception);
		}
	}

	// ******************************************************************************************************************
	// 'Function Name : gVerifyText
	// 'Script Description : To Verify the Text is present or not in a page.
	// 'Input Parameters : 1. wElement - WebElement of a page to get the text.
	// 2. strVerifyText - Text to be validated.
	// 'Return Value :

	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#gVerifyText(org.openqa.selenium.By,
	 * java.lang.String)
	 */
	@Override
	public void gVerifyText(By wElement, String strVerifyText) throws AutomationException {

		try {
			if (!LocalDriverManager.getDriver().findElement(wElement).getText().trim().contains(strVerifyText)) {
				throw new Exception();
			}

		} catch (Exception exception) {
			throw new AutomationException(exception);
		}
	}

	// ******************************************************************************************************************
	// 'Function Name : gVerifyContainsText
	// 'Script Description : To Verify the Text is present or not in a page.
	// 'Input Parameters : 1. strToCompare - Text to compare.
	// 2. strVerifyText - Text to be validated.
	// 'Return Value :

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.automation.util.DriverUtils#gVerifyContainsText(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void gVerifyContainsText(String strToCompare, String strVerifyText) throws AutomationException {
		try {
			if (!strToCompare.contains(strVerifyText)) {
				throw new Exception();
			}

		} catch (Exception exception) {

			throw new AutomationException(exception);
		}
	}

	// ******************************************************************************************************************
	// 'Function Name : gVerifyAttributeValue
	// 'Script Description : To Verify the Tool Tip of a given Object
	// 'Input Parameters : 1. webElement - Object property
	// 2.strProperty - Property value (Ex: alt, title)
	// 3. strText - Expected Tool Tip
	// 'Return Value :
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.automation.util.DriverUtils#gVerifyAttributeValue(org.openqa.selenium
	 * .By, java.lang.String, java.lang.String)
	 */
	// TODO check
	@Override
	public Boolean gVerifyAttributeValue(By webElement, String strProperty, String strText) throws AutomationException {

		Boolean isAttributePresent = false;
		try {
			String attributevalue = LocalDriverManager.getDriver().findElement(webElement).getAttribute(strProperty);
			if (!attributevalue.trim().equalsIgnoreCase(strText)) {
				throw new Exception();
			} else {
				isAttributePresent = true;
			}

		} catch (Exception exception) {

			throw new AutomationException(exception);
		}
		return isAttributePresent;
	}

	// ***********************************************************************************************************************
	// 'Function Name : gVerifySelectedOrDeselected
	// 'Script Description : To verify webelement/s is selected or deselected
	// 'Input Parameters : wel - WebElement name
	// strSelect - possible parameters("selected", "deselected")
	// 'Return Value : true
	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#isSelected(org.openqa.selenium.By,
	 * java.lang.String)
	 */
	// TODO check
	@Override
	public boolean isSelected(By wel, String strSelection) throws AutomationException {

		try {
			if (strSelection.equalsIgnoreCase("selected")) {
				List<WebElement> lstTaxTypes = gGetLlist(wel);
				for (int i = 0; i <= lstTaxTypes.size() - 1; i++) {
					if (!lstTaxTypes.get(i).isSelected()) {
						throw new Exception();
					}

				}

			}
			if (strSelection.equalsIgnoreCase("deselected")) {
				List<WebElement> lstTaxTypes = gGetLlist(wel);
				for (int i = 0; i <= lstTaxTypes.size() - 1; i++) {
					if (lstTaxTypes.get(i).isSelected()) {
						throw new Exception();
					}

				}
			}

		} catch (Exception exception) {

			throw new AutomationException(exception);
		}
		return true;
	}

	// ***********************************************************************************************************************
	// 'Function Name : gVerifyEnabledOrDisabled
	// 'Script Description : To verify webelement/s is enabled or disabled
	// 'Input Parameters : wel - WebElement name
	// strSelect - possible parameters("enabled", "disabled")
	// 'Return Value : true
	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#isEnabled(org.openqa.selenium.By,
	 * java.lang.String)
	 */
	// TODO check
	@Override

	public boolean isEnabled(By wel, String strSelection) throws AutomationException {

		try {
			if (strSelection.equalsIgnoreCase("enabled")) {
				List<WebElement> lstTaxTypes = gGetLlist(wel);
				for (int i = 0; i <= lstTaxTypes.size() - 1; i++) {
					if (!lstTaxTypes.get(i).isEnabled()) {
						throw new Exception();
					}

				}

			}
			if (strSelection.equalsIgnoreCase("disabled")) {
				List<WebElement> lstTaxTypes = gGetLlist(wel);
				for (int i = 0; i <= lstTaxTypes.size() - 1; i++) {
					if (lstTaxTypes.get(i).isEnabled()) {
						throw new Exception();
					}

				}
			}

		} catch (Exception exception) {

			throw new AutomationException(exception);
		}
		return true;
	}

	
	public boolean isEnabled(By idSelector) throws AutomationException {
		
			if(LocalDriverManager.getDriver().findElement(idSelector).isEnabled()){
			
			return true;
		}else {
			return false;
		}
		
	}
	
public boolean isEnabled(String web) throws AutomationException {
		
		boolean value=LocalDriverManager.getDriver().findElement(By.xpath(web)).isEnabled();
		
		if(value) {
			return true;	
		}else {
			return false;
		}
		
		
	}
	
	// '**************************************************************************************************************
	// 'Function Name : gSwitchFrame
	// 'Script Description : To switch one frame to other frame
	// 'Input Parameters : 1. strFrameid-Property of the frame /Id(or) Name of
	// the frame 2. strFrameType -
	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#gSwitchFrame(java.lang.String,
	 * java.lang.String)
	 */
	// 'Return Value : true
	@Override
	public boolean gSwitchFrame(String strFrameid, String strFrameType) throws AutomationException {

		
		try {
			String srtUpperCase = String.valueOf(strFrameType.toUpperCase());
			switch (srtUpperCase) {
			case "INT": // To handle if entered frame Id is number
				int typeInt = Integer.parseInt(strFrameid);
				LocalDriverManager.getDriver().switchTo().frame(typeInt);
				break;
			case "STRING": // To handle if entered frame Id is string
				LocalDriverManager.getDriver().switchTo().frame(strFrameid);
				break;
			case "WEBELEMENT": // To handle if entered frame Id is webelement
				WebElement fr1=LocalDriverManager.getDriver().findElement(By.xpath(strFrameid));
				LocalDriverManager.getDriver().switchTo().frame(fr1);
				break;
			case "DEFAULT": // To switch default frame
				LocalDriverManager.getDriver().switchTo().defaultContent();
				break;
			}
			return true;
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}

	}

	// **************************************************************************************************************
	// 'Function Name : gVerifyCloseWindow
	// 'Script Description : Click on the element in nested iframe.
	// 'Input Parameters : searchText - Search Text
	// searchFlag - Flag to indicate for searching the text
	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#gVerifyCloseWindow(java.lang.String,
	 * java.lang.String)
	 */
	// 'Return Value :
	@Override
	public void gVerifyCloseWindow(String searchText, String searchFlag) throws AutomationException {

		try {
			Set<String> winID = LocalDriverManager.getDriver().getWindowHandles();
			Iterator<String> it = winID.iterator();
			String mainWin = it.next();
			String popUpWin = it.next();

			LocalDriverManager.getDriver().switchTo().window(popUpWin);
			Thread.sleep(2000);
			if (searchFlag.equalsIgnoreCase("yes")) {
				// LocalDriverManager.getDriver().findElement(By.xpath("//*[contains(text(),'"+searchText+"')]")).isDisplayed();
				gIsDisplayed(By.xpath("//*[contains(text(),'" + searchText + "')]"));
			}

			LocalDriverManager.getDriver().close();
			LocalDriverManager.getDriver().switchTo().window(mainWin);

		} catch (InterruptedException exception) {

			throw new AutomationException(exception);
		}
	}

	// ******************************************************************************************************************
	// 'Function Name : gGetAttributeValue
	// 'Script Description : To get text from attribute
	// 'Input Parameters : 1. webElement - Object property
	// 2.strProperty - Property value (Ex: alt, title)
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.automation.util.DriverUtils#gGetAttributeValue(org.openqa.selenium.
	 * By, java.lang.String)
	 */
	// 'Return Value :
	@Override
	public String gGetAttributeValue(By webElement, String strProperty) throws AutomationException {

		try {
			List<WebElement> lstWebElement = LocalDriverManager.getDriver().findElements(webElement);
			if (lstWebElement.size() > 0) {
				String strAttributeValue = LocalDriverManager.getDriver().findElement(webElement)
						.getAttribute(strProperty);
				/*
				 * if(!strAppToolTip.trim().equalsIgnoreCase(strText)){ throw
				 * new Exception();
				 */
				return strAttributeValue;
			} else {
				throw new Exception();
			}

		} catch (Exception exception) {

			throw new AutomationException(exception);
		}

	}

	// '***********************************************************************************************************************
	// 'Function Name : gEditTextboxWithOutClear
	// 'Script Description : To perform enter text in a textbox based on name
	// property
	// 'Input Parameters :
	// ' 1. strName - Name of the Textbox
	// 2. strValue - Value of the Textbox
	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#gEditTextboxWithOutClear(org.openqa.
	 * selenium.By, java.lang.String, int)
	 */
	// 'Return Value : true
	@Override
	public boolean gEditTextboxWithOutClear(By txtKeywords, String strValue, int index) throws AutomationException {

		try {
			List<WebElement> ls = LocalDriverManager.getDriver().findElements(txtKeywords);
			ls.get(index).sendKeys(strValue);
			return true;

		} catch (Exception exception) {

			throw new AutomationException(exception);
		}

	}

	// '**************************************************************************************************************
	// 'Function Name : gGetAllOptions
	// 'Script Description : to get all Options from list box.
	// 'Input Parameters : By lstofOptions (Web element)
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.automation.util.DriverUtils#gGetAllOptions(org.openqa.selenium.By)
	 */
	// 'Return Value : true
	@Override
	public List<WebElement> gGetAllOptions(By strWebElement) throws AutomationException {

		try {
			Select strList = new Select(LocalDriverManager.getDriver().findElement(strWebElement));
			List<WebElement> lstOptions = strList.getOptions();
			return lstOptions;
		} catch (Exception exception) {

			throw new AutomationException(exception);
		}

	}

	// **************************************************************************************************************
	// 'Function Name : gScrollPageDown
	// 'Script Description : Scrolling the page down
	// 'Input Parameters : None
	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#gScrollPage(int, int)
	 */
	// 'Return Value :
	
	public void gScrollPageDown() throws AutomationException {

		try {
			JavascriptExecutor jse = (JavascriptExecutor) LocalDriverManager.getDriver();
			jse.executeScript("window.scrollBy(0,250)", "");
			jse.executeScript("scroll(0, 250);");
			
		} catch (Exception exception) {

			throw new AutomationException(exception);
		}
	}
	
	// **************************************************************************************************************
		// 'Function Name : gScrollPageUp
		// 'Script Description : Scrolling the page down
		// 'Input Parameters : None
		/*
		 * (non-Javadoc)
		 *
		 * @see com.automation.util.DriverUtils#gScrollPage(int, int)
		 */
		// 'Return Value :
		
		public void gScrollPageUp() throws AutomationException {

			try {
				JavascriptExecutor jse = (JavascriptExecutor) LocalDriverManager.getDriver();
				jse.executeScript("window.scrollBy(0,-250)", "");
				jse.executeScript("scroll(0, -250);");
				
			} catch (Exception exception) {

				throw new AutomationException(exception);
			}
		}

	// ******************************************************************************************************************
	// 'Function Name : gValidateTextAndReutrn
	// 'Script Description : To Verify the Text is present or not in a page and
	// return boolean value.
	// 'Input Parameters : 1. wElement - WebElement of a page to get the texto.
	// 2. strVerifyText - Text to be validated.
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.automation.util.DriverUtils#gVerifyTextAndReturn(org.openqa.selenium.
	 * By, java.lang.String)
	 */
	// 'Return Value :
	@Override
	public boolean gVerifyTextAndReturn(By by, String strVerifyText) throws AutomationException {

		try {

			List<WebElement> list = LocalDriverManager.getDriver().findElements(by);
			if (list.size() > 0 && list.get(0).getText().trim().contains(strVerifyText)) {
				return true;
			} else {
				return false;
			}

		} catch (Exception exception) {

			throw new AutomationException(exception);
		}
	}

	// ******************************************************************************************************************
	// 'Function Name : gNavBackBrowser
	// 'Script Description : To navigate back browser
	// 'Input Parameters :
	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#gNavBackBrowser()
	 */
	// 'Return Value :
	@Override
	public void gNavBackBrowser() throws AutomationException {
		try {
			addPageLoadTimeout();
			LocalDriverManager.getDriver().navigate().back();

		} catch (TimeoutException timeout) {
			hitEscapeKeyForPopups();
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}
	}

	// **************************************************************************************************************
	// 'Function Name : gPageReload
	// 'Script Description : Reload the Page.
	// 'Input Parameters :
	//
	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#gPageReload()
	 */
	// 'Return Value :
	@Override

	public void gPageReload() throws AutomationException {

		try {
			LocalDriverManager.getDriver().switchTo().defaultContent();

			addPageLoadTimeout();
			LocalDriverManager.getDriver().navigate().refresh();
			// (or)((JavascriptExecutor)
			// LocalDriverManager.getDriver()).executeScript("location.reload();");

		} catch (TimeoutException timeout) {
			hitEscapeKeyForPopups();
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.automation.util.DriverUtils#gListOfValues(org.openqa.selenium.By)
	 */
	/*
	 * Function Name:gListOfValues
	 *
	 * Description: This will return the values of checkbox/radio buttons/links
	 * and etc
	 */
	@Override
	public List<String> gListOfValues(By elementLocator) throws AutomationException {
		List<String> values = new ArrayList<String>();
		try {
			for (int i = 0; i < LocalDriverManager.getDriver().findElements(elementLocator).size(); i++) {
				values.add(LocalDriverManager.getDriver().findElements(elementLocator).get(i).getText().trim());
			}
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}
		return values;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#getTableData(org.openqa.selenium.By,
	 * org.openqa.selenium.By, org.openqa.selenium.By)
	 */
	/*
	 * Function Name=getTableData
	 *
	 * Description:Used to get the data from html table
	 */
	@Override
	public String[] getTableData(By tableElementLocator, By tableRowLocator, By tableDataLocator)
			throws AutomationException {
		String[] tableData = null;
		try {
			// Get All Items from Cart
			WebElement table_element = LocalDriverManager.getDriver().findElement(tableElementLocator);
			List<WebElement> tr_collection = table_element.findElements(tableRowLocator);
			for (WebElement trElement : tr_collection) {
				List<WebElement> td_collection = trElement.findElements(tableDataLocator);
				for (WebElement tdElement : td_collection) {
					LOG.info("{}", tdElement.getText().split("[\\r\\n]+"));
					tableData = tdElement.getText().split("[\\r\\n]+");
				}
			}
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}
		return tableData;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.automation.util.DriverUtils#getHtmlTableData(org.openqa.selenium.By)
	 */
	/*
	 * Function Name: getHtmlTableData
	 *
	 * Description: Used to get the table data with single parameter
	 */
	@Override

	public List<String> getHtmlTableData(By tableLocator) throws AutomationException {
		
		List<String> tableData = new ArrayList<>();
		try {
			List<WebElement> tbl_element = LocalDriverManager.getDriver().findElements(tableLocator);

			for (int i = 0; i < tbl_element.size(); i++) {
				tableData.add(tbl_element.get(i).getText());
			}
		} catch (Exception exception) {
			throw new AutomationException();
		}
		return tableData;
	}

	/*
	 * Function Name=getListOfWindows
	 *
	 * Description:It will return the list of windows as set object
	 */

	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#getListWindows()
	 */
	@Override
	public Set<String> getListWindows() {
		return LocalDriverManager.getDriver().getWindowHandles();
	}

	/*
	 * Function Name=switchToNewWindow
	 *
	 * Description:It will return the list of windows as set object
	 */

	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#switchToNewWindow()
	 */
	@Override
	public boolean switchToNewWindow() throws AutomationException {
		try {
			addPageLoadTimeout();

			LocalDriverManager.getDriver().switchTo().window(LocalDriverManager.getDriver().getWindowHandle());
			return true;
		} catch (TimeoutException timeout) {
			hitEscapeKeyForPopups();
			return true;
		} catch (Exception exception) {
			throw new AutomationException();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#switchToNewWindow(java.lang.String)
	 */
	/*
	 * Function Name=switchToNewWindow
	 *
	 * Description:It will return the list of windows as set object
	 *
	 */
	@Override
	public boolean switchToNewWindow(String newWindowHandle) throws AutomationException {
		try {
			addPageLoadTimeout();
			LocalDriverManager.getDriver().switchTo().window(newWindowHandle);
			return true;
		} catch (TimeoutException timeout) {
			hitEscapeKeyForPopups();
			return true;
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#takescreenshot(java.lang.String)
	 */
	@Override
	public String takescreenshot(String scenario) throws AutomationException {
		try {
			String startedAt = "";
			String screenshotPath = "";
			// String[] s = DCTUtils.timeStamp().split(Constants.COLON);
			String s = DCTUtils.timeStamp().split(Constants.SPACE)[1];
			s = s.replaceAll(":", " ");
			startedAt = s.split("\\.")[0];
			/*
			 * LOG.info(Arrays.toString(s)); for (int i = 0; i < s.length - 1;
			 * i++) { startedAt = startedAt + "-" + s[i]; }
			 */

			// startedAt = startedAt.substring(1, startedAt.length());
			// startedAt = startedAt.replace(" ", Constants.UNDERSCORE);

			File srcfile = ((TakesScreenshot) LocalDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcfile, new File(TestResultsUtils.screenshotDirectory + "\\sc-" + scenario + startedAt + ".png"));
			//screenshotPath = TestResultsUtils.screenshotDirectory + "\\sc-" + scenario + startedAt + ".png";
			screenshotPath = "\\sc-" + scenario + startedAt + ".png";
			return screenshotPath;

		} catch (IOException exception) {
			throw new AutomationException(exception);
		}
	}
	
	/*****		
	 * encodebase64 screenshots		
	 */		
	@Override		
	public String capturescreenshot(String scenario){		

		File srcfile=((TakesScreenshot) LocalDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);		
		String encodedBase64=null;		
		FileInputStream fisreader=null;		
		try {		
			fisreader = new FileInputStream(srcfile);		
			byte[] bytes = new byte[(int)srcfile.length()];		
			fisreader.read(bytes);		
			encodedBase64 = new String(Base64.encodeBase64(bytes));		
		} catch (FileNotFoundException e) {		
			e.printStackTrace();		
		} catch (IOException e) {		
			e.printStackTrace();		
		}		
		return "data:image/png;base64," +encodedBase64;		

	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.util.DriverUtils#takefullpagescreenshot(java.lang.String)
	 */
	@Override
	
	public String captureFullPageScreenshot(String scenario) throws AutomationException{
		
		try {
			String startedAt = "";
			String screenshotPath = "";
			// String[] s = DCTUtils.timeStamp().split(Constants.COLON);
			String s = DCTUtils.timeStamp().split(Constants.SPACE)[1];
			s = s.replaceAll(":", " ");
			startedAt = s.split("\\.")[0];

			File scrFile = ((TakesScreenshot)LocalDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
			//store screenshot at a specidfied location
			FileUtils.copyFile(scrFile, new File(baseProjectPath+"\\target\\screenshots\\demo.png"));

			
			// screen will be scrolled and captured
		//	Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
		//			.takeScreenshot(LocalDriverManager.getDriver());
//			Screenshot screenshot=new AShot().takeScreenshot(LocalDriverManager.getDriver());
//			
//			ImageIO.write(screenshot.getImage(), "PNG",
//					new File(TestResultsUtils.screenshotDirectory + "\\sc-" + scenario + startedAt + ".png"));
//			
//			screenshotPath = TestResultsUtils.screenshotDirectory + "\\sc-" + scenario + startedAt + ".png";
//			return screenshotPath;

		} catch (Throwable exception) {
			throw new AutomationException(exception);
		}
		return scenario;
	}
	
	/**
	 * @throws AutomationException *********************
	 * 
	 */
	@Override
	public String capturingSpecificElement(By strWebelement, String scenario) throws AutomationException{

		try{
			String startedAt = "";
			String screenshotPath = "";
			// String[] s = DCTUtils.timeStamp().split(Constants.COLON);
			String s = DCTUtils.timeStamp().split(Constants.SPACE)[1];
			s = s.replaceAll(":", " ");
			startedAt = s.split("\\.")[0];
			WebElement	screenshot_element=LocalDriverManager.getDriver().findElement(strWebelement);
//			Screenshot screenshot= new AShot().takeScreenshot(LocalDriverManager.getDriver(), screenshot_element);
//			ImageIO.write(screenshot.getImage(), "PNG",
//					new File(TestResultsUtils.screenshotDirectory + "\\sc-" + scenario + startedAt + ".png"));
//
//			screenshotPath = TestResultsUtils.screenshotDirectory + "\\sc-" + scenario + startedAt + ".png";
			return screenshotPath;

		} catch (Throwable exception) {
			throw new AutomationException(exception);
		}

	}
	
	// **************************************************************************************************************
			// 'Function Name : gScrollPageintoviewanelement
			// 'Script Description : Scrolling the page to a webelement
			// 'Input Parameters : webelement
			/*
			 * (non-Javadoc)
			 *
			 * @see com.automation.util.DriverUtils#gScrollPage(int, int)
			 */
		
		
			


	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.automation.applnlibrary.ReusableMethods#sendKeysAction(org.openqa.
	 * selenium.Keys)
	 *
	 */
	@Override
	public void sendKeysAction(Keys Key) throws AutomationException {
		try {
			Actions action = new Actions(LocalDriverManager.getDriver());
			action.sendKeys(Key);
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.applnlibrary.ReusableMethods#addPageLoadTimeout()
	 * This Method is to override the default pageload timeout of selenium
	 */
	@Override
	public void addPageLoadTimeout() {
		try {
			if (Boolean.valueOf(handlePopups)) {
				LocalDriverManager.getDriver().manage().timeouts().pageLoadTimeout(
						Integer.parseInt(configprops.getProperty(Constants.TIME_OUT_DURATION)), TimeUnit.SECONDS);
			}
		} catch (NumberFormatException exception) {
			LOG.info("Error while resetting the pageload timeout");
		}

	}

	/**
	 * Builds the xpath.
	 *
	 * @param xPath
	 *            the x path
	 * @param text
	 *            the text
	 * @return the string
	 */
	public String buildXpath(String xPath, String text) {
		if (xPath.contains("dummy")) {
			xPath = xPath.replace("dummy", text);
		}
		return xPath;
	}

	/**
	 * Builds the xpath.
	 *
	 * @param elemlocator
	 *            the elemlocator
	 * @param text
	 *            the text
	 * @return the string
	 */
	public String buildXpath(By elemlocator, String text) {
		String xPath = elemlocator.toString();
		if (xPath.contains("dummy")) {
			xPath = xPath.replace("dummy", text);
		}
		xPath = xPath.split("By.xpath:")[1].trim();
		LOG.info("After split" + xPath);
		return xPath;
	}


	
	/*
	 * (non-Javadoc)
	 *
	 * @see com.automation.applnlibrary.ReusableMethods#hitEscapeKeyForPopups()
	 * This method is used to hit escape key for the Windows popup
	 */
	@Override
	public void hitEscapeKeyForPopups() {
		try {
			if (Boolean.valueOf(handlePopups)) {
				sendKeysAction(Keys.ESCAPE);
			}
		} catch (AutomationException exception) {
			LOG.error("Error in hitting Escape key for pupup");
		}

	}

	/*
	 * This methods performs an action on a particular element. Parameter passed
	 * are : key like Enter and xpath of any element
	 */

	public void sendKeysAction(Keys Key, By element) throws AutomationException {
		try {
			Actions actions = new Actions(LocalDriverManager.getDriver());
			Action action = actions.moveToElement(LocalDriverManager.getDriver().findElement(element)).sendKeys(Key)
					.build();
			action.perform();
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}

	}

	/*
	 * This method closes the browser
	 *
	 */
	public void closeTheBrowser() throws AutomationException {
		try {
			LocalDriverManager.getDriver().close();
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}

	}

	/*
	 * Function Name : gDropdownGetValues Script Description Input Parameters :
	 * WebElement Return Value : List
	 */

	public List<String> gDropdownGetValues(By strWebElement) throws AutomationException {

		try {
			Select strList = new Select(LocalDriverManager.getDriver().findElement(strWebElement));
			List<WebElement> lstOptions = strList.getOptions();
			System.out.println("Size of page dropdown: " + lstOptions.size());
			List<String> dropDownValues = new ArrayList<>();
			for (int i = 0; i < lstOptions.size(); i++) {
				dropDownValues.add(lstOptions.get(i).getText());
			}
			return dropDownValues;
		} catch (Exception exception) {

			throw new AutomationException(exception);
		}

	}

	/****
	 * This method takes the input as list of expected values, reads the actual
	 * values send true / false
	 **/
	public boolean verifyWebElementsText(By Element, List<String> expectedvalues) {
		List<WebElement> list = LocalDriverManager.getDriver().findElements(Element);
		ArrayList<String> actualvalues = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String element = list.get(i).getText();
			actualvalues.add(element);
		}
		if (expectedvalues.size() == actualvalues.size()) {
			@SuppressWarnings("rawtypes")
			Collection diff = CollectionUtils.subtract(expectedvalues, actualvalues);
			if (diff.isEmpty()) {
				LOG.info("Scenario::{} - Expected values are equal to Actual values",
						LocalTestDataManager.getScenarioname());
			} else {
				LOG.info("Scenario::{} - Expected values are not equal to Actual values::{}",
						LocalTestDataManager.getScenarioname(), diff);
				return false;
			}
		}
		return true;
	}

	public void sendKeyAction(Keys Key, By element) throws AutomationException {
		try {
			Actions actions = new Actions(LocalDriverManager.getDriver());
			Action action = actions.moveToElement(LocalDriverManager.getDriver().findElement(element)).sendKeys(Key)
					.build();
			action.perform();
		} catch (Exception exception) {
			throw new AutomationException(exception);
		}

	}
	public static boolean getHttpUrlStatus(String url) throws IOException {
	
		boolean result = false;
		try {
			URL urlObj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(3000);
			con.connect();

			int code = con.getResponseCode();
			if (code == 200) {
				result =true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	public String toCamelCase(String init) {
	 if (init==null)
	 return null;

	final StringBuilder ret = new StringBuilder(init.length());

	for (String word : init.split(" ")) {
	if (!word.isEmpty()) {
	ret.append(word.substring(0, 1).toUpperCase());
	ret.append(word.substring(1).toLowerCase());
	}
	 if (!(ret.length()==init.length()))
	 ret.append(" ");
	 }

	 return ret.toString();
	}
	
	
		
	public boolean elementWithVaryingLocators(By... strWebElement) throws Throwable{
	try{	
		LocalDriverManager.getDriver().findElements(new ByAll(strWebElement));
		return true;
	}catch(Exception e){
		e.printStackTrace();
		throw new AutomationException();
	}	
		
	}
	
	public void explicitWaitMethod(By locator, int time){
		WebDriverWait wait= new WebDriverWait(LocalDriverManager.getDriver(),time);
		wait.until(ExpectedConditions.alertIsPresent());
		
	}
	
	public void waitForelementToHidden(By locator, int timeoutInSeconds) throws Throwable{

		String msg = "Wait %ss for [%s] on page [%s]";
		LOG.debug(String.format(msg, timeoutInSeconds, locator, this.getClass().getSimpleName()));
		boolean visible = true;
		for (int i = 0; i < timeoutInSeconds; i++)
		{
			visible = isElementPresent(locator);
			if ( !visible)
			{
				return;
			}
			delay(1);
		}
		throw new ElementNotVisibleException(locator + " is still visible on the Web Page. ");
	} 
	
	public void delay(int timeoutInSeconds)
{
try
{
 TimeUnit.SECONDS.sleep(timeoutInSeconds);
}catch(Throwable e){
	
}
	  }

//	@Override
//	public void gExplicitWait(By elementLocator, int maxTimeOut, String strConditionMode, String strName)
//			throws Exception {
//		// TODO Auto-generated method stub
//		
//	}


	
}