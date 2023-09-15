package com.automation.pageobjects;

import org.openqa.selenium.By;

import com.automation.constants.Constants;
import com.automation.utils.PropertyUtils;

public class LoginPage {
	//
	
	public static String basePath = System.getProperty(Constants.USER_DIR);
	
	public static PropertyUtils pr = new PropertyUtils(basePath+Constants.KRISHNALOGIN_PAGE_PROPERTY);
	
	public static String uname = pr.getProperty("username_textField");
	public static By unameLocator = By.id(uname);
	
	public static String pswd = pr.getProperty("password_textField");
	public static By pswdLocator = By.id(pswd);
	
	
	public static String login = pr.getProperty("login_Button");
	public static By loginLocator = By.id(login);
	
	

}
