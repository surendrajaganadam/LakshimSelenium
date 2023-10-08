package com.automation.pageobjects;

import org.openqa.selenium.By;

import com.automation.constants.Constants;
import com.automation.utils.PropertyUtils;


public class SauceDemoCart {
	
public static String baseprojectlocation = System.getProperty(Constants.USER_DIR);
	
	public static String SauceDemoCartlocator = "\\src\\\\test\\\\resources\\\\pageobjects\\\\SauceDemoCart.property";
	
	public static PropertyUtils pr = new PropertyUtils(baseprojectlocation+Constants.SauceDemoCartlocator);
	
	public static String Checkoutfield = pr.getProperty("saucedemo_checkout_field");
	public static By Checkoutlocator = By.id(Checkoutfield);
	
	//public By Checkoutlocator = BY.id(pr.getProperty("saucedemo_checkout_field"));
}