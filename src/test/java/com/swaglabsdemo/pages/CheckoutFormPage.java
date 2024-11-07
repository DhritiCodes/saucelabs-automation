package com.swaglabsdemo.pages;

import com.swaglabsdemo.utils.LogUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutFormPage extends SidebarPage{

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

    //action methods -------------------------------------------------------------------------------------------------

    public void fillDetails(String firstName, String lastName, int postalCode){
        setData(firstNameField, firstName);
        setData(lastNameField, lastName);
        setData(postalCodeField, String.valueOf(postalCode));
    }

    public void clickContinueCheckoutBtn(){
        clickElement(continueCheckoutBtn);
        logger.debug("Clicked continue checkout button...");
    }

    // navigation methods ---------------------------------------------------------------------------------------------

    public CheckoutVerificationPage redirectFromCheckoutFormToVerificationPage(){
        return new CheckoutVerificationPage(driver);
    }
}
