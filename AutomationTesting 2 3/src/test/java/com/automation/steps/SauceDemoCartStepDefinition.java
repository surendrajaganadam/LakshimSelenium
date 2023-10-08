package com.automation.steps;


import com.automation.exception.AutomationException;
import com.automation.pageobjects.LoginPagelak;
import com.automation.pageobjects.SauceDemoCart;
import com.automation.utils.DriverUtilsImpl;
import com.automation.utils.TestResultsUtils;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.en.Then;

public class SauceDemoCartStepDefinition {
	
	DriverUtilsImpl DUI = new DriverUtilsImpl();
	
	
	@Then("^click on checkout button$")
	public void Checkout() throws Exception {
		try {
			
			DUI.gClick(SauceDemoCart.Checkoutlocator);
			TestResultsUtils.logger.log(LogStatus.PASS ,"Sucessfully clicked checkout button");
			
		}catch(Throwable e) {
		
			TestResultsUtils.logger.log(LogStatus.FAIL, "Login Failed");

	
}
	}
}
	
	