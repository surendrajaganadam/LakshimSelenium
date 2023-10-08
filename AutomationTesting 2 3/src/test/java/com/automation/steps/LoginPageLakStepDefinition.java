package com.automation.steps;

import com.automation.config.SeleniumNGSuite;
import com.automation.exception.AutomationException;
import com.automation.pageobjects.LoginPagelak;
import com.automation.utils.DriverUtilsImpl;
import com.automation.utils.TestResultsUtils;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.en.Given;

public class LoginPageLakStepDefinition {
	
	DriverUtilsImpl DUI = new DriverUtilsImpl();

	
	@Given("^User is on SauceDemo login page$")
	public void SauceDemo_login() throws Exception {
		try {
			SeleniumNGSuite obj = new SeleniumNGSuite();
			obj.setUpSuite("Web");
			DUI.sendText(LoginPagelak.unamelocator, "standard_user");
			DUI.sendText(LoginPagelak.Upwdlocator, "secret_sauce");
			DUI.gClick(LoginPagelak.lbuttonlocator);
			
			TestResultsUtils.logger.log(LogStatus.PASS, "Sucessfully logged in");
			
		}catch(Throwable e) {
			
			TestResultsUtils.logger.log(LogStatus.FAIL, "Login Failed");
			
		}
		
		
		
		
		
		
	}
	
		
	}

