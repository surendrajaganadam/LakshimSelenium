package com.automation.steps;

import com.automation.exception.AutomationException;
import com.automation.pageobjects.SauceDemoInventory;
import com.automation.utils.DriverUtilsImpl;
import com.automation.utils.TestResultsUtils;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.en.And;

public class SauceDemoInventoryStepDefinition {
	
	DriverUtilsImpl DUI = new DriverUtilsImpl();
	
	@And("^add products to the cart$")
	public void Add_product() throws Exception {
		try {
		DUI.gClick(SauceDemoInventory.Blocator);
		DUI.gClick(SauceDemoInventory.Tlocator);
		DUI.gClick(SauceDemoInventory.Clocator);
		
		TestResultsUtils.logger.log(LogStatus.PASS ,"Sucessfully Added the Products");
		
		}catch(Throwable e) {
			
			TestResultsUtils.logger.log(LogStatus.FAIL ,"Could Not Add The Products");
			
			
		}
		
		
	}

}
