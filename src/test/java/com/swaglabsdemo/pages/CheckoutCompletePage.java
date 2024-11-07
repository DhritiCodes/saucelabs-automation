package com.swaglabsdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends SidebarPage{

    public CheckoutCompletePage(WebDriver driver){
        super(driver);
    }

    @FindBy(className="complete-header")
    private WebElement orderPlacedMessage;

    @CacheLookup
    @FindBy(className = "shopping_cart_link")
    private WebElement cartBtn;

    //getter methods -----------------------------------------------------------------------------------------------

    public String getSuccessMessage(){
        return orderPlacedMessage.getText();
    }
}
