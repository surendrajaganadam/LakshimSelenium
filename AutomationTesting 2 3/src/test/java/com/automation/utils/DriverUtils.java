package com.automation.utils;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.automation.exception.*;

/**
 * The Interface DriverUtils.
 */
public interface DriverUtils {

	/** The page title. */
	public String pageTitle = null;

	/** The exception message. */
	public String exceptionMessage = "";

	/**
	 * Checks if is page loaded.
	 */
	public boolean isPageLoaded() throws AutomationException;

	/**
	 * Open page.
	 *
	 * @param webSiteUrl
	 *            the web site url
	 * @throws AutomationException
	 *             the Automation exception
	 */
	public void openPage(String webSiteUrl) throws AutomationException;

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle();

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getURL();

	/**
	 * Send text.
	 *
	 * @param idSelector
	 *            the id selector
	 * @param text
	 *            the text
	 * @return true, if successful
	 * @throws AutomationException
	 *             the Automation exception
	 */
	public boolean sendText(By idSelector, String text) throws AutomationException;

	/**
	 * Checks if is element present.
	 *
	 * @param by
	 *            the by
	 * @return true, if is element present
	 * @throws AutomationException
	 *             the Automation exception
	 */
	public boolean isElementPresent(By by) throws AutomationException;

	/**
	 * Checks if is element present.
	 *
	 * @param _cssSelector
	 *            the css selector
	 * @return true, if is element present
	 * @throws AutomationException
	 *             the Automation exception
	 */
	public boolean isElementPresent(String _cssSelector) throws AutomationException;

	/**
	 * Checks if is element present and displayed.
	 *
	 * @param by
	 *            the by
	 * @return true, if is element present and displayed
	 * @throws AutomationException
	 *             the Automation exception
	 */
	public boolean isElementPresentAndDisplayed(By by) throws AutomationException;

	/**
	 * Gets the web element.
	 *
	 * @param by
	 *            the by
	 * @return the web element
	 * @throws AutomationException
	 *             the Automation exception
	 */
	public WebElement getWebElement(By by) throws AutomationException;

	/**
	 * G get llist.
	 *
	 * @param by
	 *            the by
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	public List<WebElement> gGetLlist(By by) throws Exception;

	/**
	 * G explicit wait.
	 *
	 * @param elementLocator
	 *            the element locator
	 * @param maxTimeOut
	 *            the max time out
	 * @param strConditionMode
	 *            the str condition mode
	 * @param strName
	 *            the str name
	 * @throws Exception
	 *             the exception
	 */
	 public void gExplicitWait(By elementLocator, int maxTimeOut, String
	 strConditionMode, String strName)
	 throws Exception;
	
	
	
	

	/**
	 * G element focus.
	 *
	 * @param by
	 *            the by
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public boolean gElementFocus(By by) throws Exception;

	/**
	 * G click link or button.
	 *
	 * @param strLink
	 *            the str link
	 * @param index
	 *            the index
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public boolean gClickLinkOrButton(By strLink, int index) throws Exception;

	/**
	 * G click.
	 *
	 * @param elementLocator
	 *            the element locator
	 * @return true, if successful
	 * @throws AutomationException
	 *             the Automation exception
	 */
	public boolean gClick(By elementLocator) throws AutomationException;

	/**
	 * G edit textbox.
	 *
	 * @param txtKeywords
	 *            the txt keywords
	 * @param strValue
	 *            the str value
	 * @param index
	 *            the index
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public boolean gEditTextbox(By txtKeywords, String strValue, int index) throws Exception;

	/**
	 * G radio select value.
	 *
	 * @param strName
	 *            the str name
	 * @param strRadioValue
	 *            the str radio value
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public boolean gRadioSelectValue(By strName, String strRadioValue) throws Exception;

	/**
	 * G list get value.
	 *
	 * @param strId
	 *            the str id
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String gListGetValue(By strId) throws Exception;

	/**
	 * G radio get value.
	 *
	 * @param strName
	 *            the str name
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String gRadioGetValue(By strName) throws Exception;

	/**
	 * G check box select.
	 *
	 * @param strId
	 *            the str id
	 * @param strChkBoxValue
	 *            the str chk box value
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public boolean gCheckBoxSelect(By strId, String strChkBoxValue) throws Exception;

	/**
	 * G is displayed.
	 *
	 * @param by
	 *            the by
	 * @throws Exception
	 *             the exception
	 */
	public boolean gIsDisplayed(By by) throws Exception;

	/**
	 * G web element get texts.
	 *
	 * @param wElement
	 *            the w element
	 * @param index
	 *            the index
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String gWebElementGetTexts(By wElement, int index) throws Exception;

	/**
	 * G web element get text.
	 *
	 * @param wElement
	 *            the w element
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String gWebElementGetText(By wElement) throws Exception;

	/**
	 * G list select.
	 *
	 * @param lstPracArea
	 *            the lst prac area
	 * @param lstValue
	 *            the lst value
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public boolean gListSelect(By lstPracArea, String lstValue) throws Exception;

	/**
	 * G verify text.
	 *
	 * @param wElement
	 *            the w element
	 * @param strVerifyText
	 *            the str verify text
	 * @throws Exception
	 *             the exception
	 */
	public void gVerifyText(By wElement, String strVerifyText) throws Exception;

	/**
	 * G verify contains text.
	 *
	 * @param strToCompare
	 *            the str to compare
	 * @param strVerifyText
	 *            the str verify text
	 * @throws Exception
	 *             the exception
	 */
	public void gVerifyContainsText(String strToCompare, String strVerifyText) throws Exception;

	/**
	 * G verify attribute value.
	 *
	 * @param webElement
	 *            the web element
	 * @param strProperty
	 *            the str property
	 * @param strText
	 *            the str text
	 * @return the boolean
	 * @throws Exception
	 *             the exception
	 */
	public Boolean gVerifyAttributeValue(By webElement, String strProperty, String strText) throws Exception;

	/**
	 * Checks if is selected.
	 *
	 * @param by
	 *            the by
	 * @param strSelection
	 *            the str selection
	 * @return true, if is selected
	 * @throws Exception
	 *             the exception
	 */
	public boolean isSelected(By by, String strSelection) throws Exception;

	/**
	 * Checks if is enabled.
	 *
	 * @param by
	 *            the by
	 * @param strSelection
	 *            the str selection
	 * @return true, if is enabled
	 * @throws Exception
	 *             the exception
	 */
	public boolean isEnabled(By by, String strSelection) throws Exception;

	/**
	 * G switch frame.
	 *
	 * @param strFrameid
	 *            the str frameid
	 * @param strFrameType
	 *            the str frame type
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public boolean gSwitchFrame(String strFrameid, String strFrameType) throws Exception;

	/**
	 * G verify close window.
	 *
	 * @param searchText
	 *            the search text
	 * @param searchFlag
	 *            the search flag
	 * @throws Exception
	 *             the exception
	 */
	public void gVerifyCloseWindow(String searchText, String searchFlag) throws Exception;

	/**
	 * G get attribute value.
	 *
	 * @param webElement
	 *            the web element
	 * @param strProperty
	 *            the str property
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String gGetAttributeValue(By webElement, String strProperty) throws Exception;

	/**
	 * G edit textbox with out clear.
	 *
	 * @param txtKeywords
	 *            the txt keywords
	 * @param strValue
	 *            the str value
	 * @param index
	 *            the index
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public boolean gEditTextboxWithOutClear(By txtKeywords, String strValue, int index) throws Exception;

	/**
	 * G get all options.
	 *
	 * @param strWebElement
	 *            the str web element
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	public List<WebElement> gGetAllOptions(By strWebElement) throws Exception;

	/**
	 * G scroll page.
	 *
	 * @param scrollX
	 *            the scroll X
	 * @param scrollY
	 *            the scroll Y
	 * @throws Exception
	 *             the exception
	 */
	public void gScrollPageDown() throws Exception;

	/**
	 * G verify text and return.
	 *
	 * @param by
	 *            the by
	 * @param strVerifyText
	 *            the str verify text
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	public boolean gVerifyTextAndReturn(By by, String strVerifyText) throws Exception;

	/**
	 * G nav back browser.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void gNavBackBrowser() throws Exception;

	/**
	 * G page reload.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void gPageReload() throws Exception;

	/**
	 * G list of values.
	 *
	 * @param elementLocator
	 *            the element locator
	 * @return the list
	 * @throws AutomationException
	 *             the Automation exception
	 */
	public List<String> gListOfValues(By elementLocator) throws AutomationException;

	/**
	 * Gets the table data.
	 *
	 * @param tableElementLocator
	 *            the table element locator
	 * @param tableRowLocator
	 *            the table row locator
	 * @param tableDataLocator
	 *            the table data locator
	 * @return the table data
	 * @throws AutomationException
	 *             the Automation exception
	 */
	public String[] getTableData(By tableElementLocator, By tableRowLocator, By tableDataLocator)
			throws AutomationException;

	/**
	 * Gets the html table data.
	 *
	 * @param tableLocator
	 *            the table locator
	 * @return the html table data
	 * @throws AutomationException
	 *             the Automation exception
	 */
	public List<String> getHtmlTableData(By tableLocator) throws AutomationException;

	/**
	 * Gets the list windows.
	 *
	 * @return the list windows
	 */
	public Set<String> getListWindows();

	/**
	 * Switch to new window.
	 *
	 * @return true, if successful
	 * @throws AutomationException
	 *             the Automation exception
	 */
	public boolean switchToNewWindow() throws AutomationException;

	/**
	 * Switch to new window.
	 *
	 * @param newWindowHandle
	 *            the new window handle
	 * @return true, if successful
	 * @throws AutomationException
	 *             the Automation exception
	 */
	public boolean switchToNewWindow(String newWindowHandle) throws AutomationException;

	/**
	 * Send keys action.
	 *
	 * @param Key
	 *            the key
	 * @throws AutomationException
	 *             the Automation exception
	 */
	void sendKeysAction(Keys Key) throws AutomationException;

	/**
	 * Takescreenshot.
	 *
	 * @param scenario
	 *            the scenario
	 * @throws AutomationException
	 *             the Automation exception
	 */
	String takescreenshot(String scenario) throws AutomationException;
	
	/**
	 * Takefullpagescreenshot.
	 *
	 * @param scenario
	 *            the scenario
	 * @throws AutomationException
	 *             the Automation exception
	 */
	String captureFullPageScreenshot(String scenario) throws AutomationException;
	
	
	/**
	 * scroll to specific element
	 * @param by
	 *  		the by
	 * 		
	 * @throws Exception
	 * 				the Exception
	 */
	
	/** takescreenshot of specific element 
	 * 
	 * @param strWebelement
	 * @param scenario
	 * @return
	 * @throws AutomationException
	 */
	
	String capturingSpecificElement(By strWebelement, String scenario) throws AutomationException;

	

	
	public void gScrollPageToElement(By by) throws Exception;

	
	/**
	 * G mouse hover.
	 *
	 * @param strXpath
	 *            the str xpath
	 * @return true, if successful
	 * @throws AutomationException
	 *             the Automation exception
	 */
	boolean gMouseHover(By strXpath) throws AutomationException;

	/**
	 * Adds the page load timeout.
	 */
	void addPageLoadTimeout();

	/**
	 * Hit escape key for popups.
	 */
	void hitEscapeKeyForPopups();

	/**
	 * Checks if is element not present.
	 *
	 * @param by
	 *            the by
	 * @return true, if is element not present
	 * @throws AutomationException
	 *             the Automation exception
	 */
	boolean isElementNotPresent(By by) throws AutomationException;

	/**
	 * Number of elements present.
	 *
	 * @param by
	 *            the by
	 * @return the int
	 * @throws AutomationException
	 *             the Automation exception
	 */
	int numberOfElementsPresent(By by) throws AutomationException;

	String capturescreenshot(String scenario)throws AutomationException;
	
}