package com.automation.pageobjects;

import org.openqa.selenium.By;

import com.automation.constants.Constants;
import com.automation.utils.PropertyUtils;

public class LoginPagelak {
	
	
	public static String baseprojectlocation = System.getProperty(Constants.USER_DIR);
	
	public static String loginpagelocator = "\\src\\\\test\\\\resources\\\\pageobjects\\\\LoginPageLak.property";
	
	//public static PropertyUtils pr = new PropertyUtils(baseprojectlocation+loginpagelocator);
	
	public static PropertyUtils pr = new PropertyUtils(baseprojectlocation+Constants.loginpagelocator);
	
	//PropertyUtils pr=new PropertyUtils("C:\\Users\\mvssr\\LakshimSelenium\\AutomationTesting 2 3\\src\\test\\resources\\pageobjects\\LoginPageLak.property");
	 public static String uname = pr.getProperty("saucedemo_username_field");
	public static By unamelocator =By.id(uname);
	
	//or You can write as below by combining both the above sentences
	//public By unamelocator = By.id(pr.getProperty("saucedemo_username_field"));
	
	public static String Upwd = pr.getProperty("saucedemo_password_field");
	public static By Upwdlocator = By.id(Upwd);
	//or You can write as below by combining both the above sentences
	//public By Upwdlocator = By.id(pr.getProperty(saucedemo_password_field");
	
	public static String lbutton = pr.getProperty("saucedemo_login_button");
	public static By lbuttonlocator = By.id(lbutton);
	
	//or You can write as below by combining both the above sentences
	//public By lbuttonlocator = By.id(pr.getProperty(saucedemo_login_button");*/
	
	
	
	
}