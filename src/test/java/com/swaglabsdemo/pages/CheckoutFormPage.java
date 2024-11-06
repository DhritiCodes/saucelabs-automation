package com.swaglabsdemo.pages;

import com.swaglabsdemo.utils.LogUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutFormPage extends BasePage{

    private final static Logger logger = LogUtil.getLogger(ProductPage.class);

    public CheckoutFormPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="first-name")
    private WebElement firstNameField;

    @FindBy(id="last-name")
    private WebElement lastNameField;

    @FindBy(id="postal-code")
    private WebElement postalCodeField;

    @FindBy(id="continue")
    private WebElement continueCheckoutBtn;


}
