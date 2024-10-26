package com.swaglabsdemo.utils;

import com.swaglabsdemo.config.ConfigReader;
import com.swaglabsdemo.pages.LoginPage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class AuthenticateUserUtil {

    private static Logger logger = LogUtil.getLogger(AuthenticateUserUtil.class);

    public static void authenticateUser(WebDriver driver){
        driver.get(ConfigReader.getLoginPageUrl());
        logger.debug("Driver is getting url :"+ConfigReader.getLoginPageUrl());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendUsername(ConfigReader.getUsername());
        loginPage.sendPassword(ConfigReader.getPassword());
        loginPage.clickLoginButton();
    }
}
