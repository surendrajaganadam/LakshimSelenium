package com.automation.steps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.config.LocalDriverManager;
import com.automation.config.SeleniumNGSuite;
import com.automation.exception.AutomationException;
import com.automation.pageobjects.LoginPage;
import com.automation.utils.DriverUtilsImpl;
import com.automation.utils.LocalTestDataManager;
//import com.automation.utils.DriverUtilsImpl;
import com.automation.utils.TestDataUtils;
import com.automation.utils.TestResultsUtils;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import cucumber.api.DataTable;
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
public class LoginPageStepDefinition extends TestDataUtils{

	DriverUtilsImpl reusablemethods = new DriverUtilsImpl();
	/** The logger. */
	private static final Logger LOG = LoggerFactory.getLogger(LoginPageStepDefinition.class);

	public static TestResultsUtils resultsUtils = new TestResultsUtils();
	 
//	   @Given("^user is on login screen of saucedemo$")
//	    public void user_is_on_login_screen_of_saucedemo() throws Throwable {
//	  
//		   SeleniumNGSuite obj = new SeleniumNGSuite();
//		   obj.setUpSuite("Web");
//	    }

	@Given("^user is on sauceD login screen$")
	public void user_is_on_logn_screen() throws Throwable {
		
		SeleniumNGSuite obj = new SeleniumNGSuite();
		obj.setUpSuite("Web");
		
	}
	
	
	@When("^user Enters name nad password$")
	public void enter_Credentails() throws Exception {
		try {
		reusablemethods.sendText(LoginPage.unameLocator	, "usernrame");
		reusablemethods.sendText(LoginPage.pswdLocator, "test1234");
		resultsUtils.logger.log(LogStatus.PASS, "successfully Entered credentails");
	}catch(Throwable e) {
		resultsUtils.logger.log(LogStatus.FAIL, "UNable to enter credentails");
	}
	}
	
	
	@Then("^click on login button$")
	public void click_login_btn() throws Exception {
		reusablemethods.gClick(LoginPage.loginLocator);	
	}
	
	
	    }

	 
	
	

	/*
	
	  @Given("^user is on SF Login screen$")
	    public void user_is_on_sf_login_screen() throws Throwable {
	     try{
	    	 SeleniumNGSuite obj = new SeleniumNGSuite();
			  obj.setUpSuite("Web");
				resultsUtils.logger.log(LogStatus.PASS, "successfully opened the app");
			   
	     }catch(Throwable e){
	    	 resultsUtils.logger.log(LogStatus.FAIL, "unable to open the app");
			  
	     }
		  
	    }
	
	*/
