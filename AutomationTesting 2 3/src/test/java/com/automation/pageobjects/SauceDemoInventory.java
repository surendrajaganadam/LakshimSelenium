package com.automation.pageobjects;

import org.openqa.selenium.By;

import com.automation.constants.Constants;
import com.automation.utils.PropertyUtils;

public class SauceDemoInventory {
	
	
public static String baseprojectlocation = System.getProperty(Constants.USER_DIR);
	
	public static String SauceDemolocator = "\\src\\\\test\\\\resources\\\\pageobjects\\\\SauceDemoInventory.property";
	
	public static PropertyUtils pr = new PropertyUtils(baseprojectlocation+Constants.SauceDemolocator);
	
	public static String backpack = pr.getProperty("saucedemo_inventorypg_add_field");
	public static By Blocator = By.id(backpack);
	
	//public By Blocator = By.id(pr.getProperty("saucedemo_inventorypg_add_field"));
	
	public static String  Tshirt = pr.getProperty("saucedemo_inventorypg_add_Tshirt_field");
	public static By Tlocator = By.id(Tshirt);
	
	//public By Tlocator = By.id(pr.getProperty("saucedemo_inventorypg_add_Tshirt_field"));
	
	public static String cart = pr.getProperty("saucedemo_cart");
	public static By Clocator = By.id(cart);
	
	//public By clocator = By.id(pr.getProperty("saucedemo_cart"));
	
	
}