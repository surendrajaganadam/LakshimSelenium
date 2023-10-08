package com.automation.pageobjects;

import org.openqa.selenium.By;

import com.automation.constants.Constants;
import com.automation.utils.PropertyUtils;

public class SauceDemoCheckOutInfo {
	
	
public static String baseprojectlocation = System.getProperty(Constants.USER_DIR);
	
	public static String SauceDemoCheckOutlocator = "\\src\\\\test\\\\resources\\\\pageobjects\\\\SauceDemoCheckoutInfo.property";
	
	public static PropertyUtils pr = new PropertyUtils(baseprojectlocation+Constants.SauceDemoCheckOutlocator);
	
	public static String firstname = pr.getProperty("saucedemo_firstname_field");
	public static By Flocator = By.id(firstname);
	
	//public By Flocator = By.id(pr.getProperty("saucedemo_firstname_field"));
	
	public static String lname = pr.getProperty("saucedemo_lastname_field");
	public static By Llocator = By.id(lname);
	
	//public By Llocator = By.id(pr.getProperty("saucedemo_lastname_field"));
	
	public static String postalcode = pr.getProperty("saucedemo_postalcode_field");
	public static By Plocator = By.id(postalcode);
	
	//public By Plocator = By.id(pr.getProperty("saucedemo_postal_field"));
	
	public static String cont = pr.getProperty("saucedemo_continue_field");
	public static By Clocator = By.id(cont);
	
	
	//public By Clocator = By.id(pr.getProperty("saucedemo_continue_field"));
	
	
}
	