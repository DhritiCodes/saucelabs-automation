package com.swaglabsdemo.utils;

import com.swaglabsdemo.Constants;
import com.swaglabsdemo.config.ConfigReader;
import com.swaglabsdemo.pages.LoginPage;
import com.swaglabsdemo.pages.ProductPage;
import com.swaglabsdemo.pages.SidebarPage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class TestUtil {

    private static Logger logger = LogUtil.getLogger(TestUtil.class);

    public static void authenticateUser(WebDriver driver){
        driver.get(Constants.LOGINPAGE_URL);
        logger.debug("Driver is getting url :"+Constants.LOGINPAGE_URL);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.sendUsername(ConfigReader.getValue("username")).sendPassword(ConfigReader.getValue("password")).clickLoginButton();
    }

    public static LoginPage logoutUser(WebDriver driver){
        driver.get(Constants.PRODUCTPAGE_URL);

        ProductPage productPage = new ProductPage(driver);

        productPage.openSideMenu();
        productPage.clickLogout();

        return productPage.redirectToLoginPage();
    }

    public static void resetAppState(WebDriver driver){

        SidebarPage sidebarPage = new SidebarPage(driver);
        sidebarPage.openSideMenu();
        sidebarPage.clickReset();
        sidebarPage.closeSideMenu();

        //reload page
        ((JavascriptExecutor) driver).executeScript("location.reload();");
//        driver.navigate().refresh();
    }
}
