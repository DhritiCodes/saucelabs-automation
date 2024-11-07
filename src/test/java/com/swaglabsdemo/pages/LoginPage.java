package com.swaglabsdemo.pages;

import com.swaglabsdemo.utils.LogUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    private static final Logger logger = LogUtil.getLogger(LoginPage.class);

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver){
        super(driver);
    }

    //chaining methods ------------------------------------------------------------------------------------------------

    public LoginPage sendUsername(String name){
        setData(usernameField, name);
        return this;
    }

    public LoginPage sendPassword(String password){
        setData(passwordField, password);
        return this;
    }

    public LoginPage clickLoginButton(){
        clickElement(loginButton);
        return this;
    }

    //navigation methods ----------------------------------------------------------------------------------------------

    public ProductPage redirectToHomePage() {
        logger.debug("Redirected to home page successfully, ProductPage object created.");
        return new ProductPage(driver);
    }

}
