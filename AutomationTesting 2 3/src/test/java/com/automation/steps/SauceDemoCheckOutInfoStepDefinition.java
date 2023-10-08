package com.automation.steps;

import com.automation.exception.AutomationException;
import com.automation.pageobjects.SauceDemoCheckOutInfo;
import com.automation.utils.DriverUtilsImpl;
import com.automation.utils.TestResultsUtils;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.en.And;

public class SauceDemoCheckOutInfoStepDefinition {
	
	DriverUtilsImpl DUI = new DriverUtilsImpl();
	
	@And("^give checkout information to click on continue button$")
	public void checkout_info() throws Exception {
		
		try {
			
			DUI.sendText(SauceDemoCheckOutInfo.Flocator, "Priya");
			DUI.sendText(SauceDemoCheckOutInfo.Llocator, "Kumar");
			DUI.sendText(SauceDemoCheckOutInfo.Plocator, "85021");
			DUI.gClick(SauceDemoCheckOutInfo.Clocator);
			TestResultsUtils.logger.log(LogStatus.PASS ,"Sucessfully Filled In Information");
			
		}catch(Throwable e) {
			
			TestResultsUtils.logger.log(LogStatus.FAIL ,"Not Able To Fill In Information");
			
		}
		
		
		
		
	}

}

