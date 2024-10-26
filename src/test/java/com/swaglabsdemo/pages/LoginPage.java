package com.swaglabsdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void sendUsername(String name){
        setData(usernameField, name);
    }

    public void sendPassword(String password){
        setData(passwordField, password);
    }

    public void clickLoginButton(){
        clickElement(loginButton);
    }

    public ProductPage redirectToHomePage() {
        return new ProductPage(driver);
    }

}
