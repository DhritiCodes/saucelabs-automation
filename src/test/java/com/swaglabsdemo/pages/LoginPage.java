package com.swaglabsdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage{
    WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void sendUsername(String name){
        usernameField.sendKeys(name);
//        setData(usernameField, name);
    }

    public void sendPassword(String password){
        passwordField.sendKeys(password);
//        setData(passwordField, password);
    }

    public void clickLoginButton(){
        loginButton.click();
//        clickElement(loginButton);
    }

    public ProductPage redirectToHomePage() {
        return new ProductPage(driver);
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

}
