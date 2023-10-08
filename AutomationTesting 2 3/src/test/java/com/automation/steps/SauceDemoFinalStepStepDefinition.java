package com.automation.steps;

import com.automation.exception.AutomationException;
import com.automation.pageobjects.SauceDemoFinalStep;
import com.automation.utils.DriverUtilsImpl;
import com.automation.utils.TestResultsUtils;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.en.And;


public class SauceDemoFinalStepStepDefinition {
	
	DriverUtilsImpl DUI = new DriverUtilsImpl();
	
	@And("^review information to click on finish button$")
	public void Final_Step() throws AutomationException {
		
		try {
			
			DUI.gClick(SauceDemoFinalStep.finishlocator);
			TestResultsUtils.logger.log(LogStatus.PASS ,"Sucessfully Clicked Finish");
			
		}catch(Throwable e) {
			
			TestResultsUtils.logger.log(LogStatus.FAIL ,"Could Not Click Finish");
		}
		
		
	}
	
			

}
