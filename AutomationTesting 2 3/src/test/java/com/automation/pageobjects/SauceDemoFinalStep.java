package com.automation.pageobjects;

import org.openqa.selenium.By;

import com.automation.constants.Constants;
import com.automation.utils.PropertyUtils;

public class SauceDemoFinalStep {
	
	
public static String baseprojectlocation = System.getProperty(Constants.USER_DIR);
	
	public static String SauceDemoFinalSteplocator = "\\src\\\\test\\\\resources\\\\pageobjects\\\\SauceDemoFinalStep.property";
	
	public static PropertyUtils pr = new PropertyUtils(baseprojectlocation+Constants.SauceDemoFinalSteplocator);
	
	public static String finish = pr.getProperty("saucedemo_finish_field");
	public static  By finishlocator = By.id(finish);
	
	//public By finishlocator = By.id(pr.getProperty("saucedemo_finish_field=finish");
	
}
	
	