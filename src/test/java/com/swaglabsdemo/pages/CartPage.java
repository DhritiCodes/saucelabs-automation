package com.swaglabsdemo.pages;

import com.swaglabsdemo.utils.LogUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
    Logger logger = LogUtil.getLogger(CartPage.class);

    @FindBy(id="continue-shopping")
    private WebElement continueShoppingBtn;

    @FindBy(id = "checkout")
    private WebElement checkoutBtn;

    public CartPage(WebDriver driver){
        super(driver);
    }

    public void removeItemFromCart(String productName){
        String dynamicId = "remove-"+productName.toLowerCase().replace(" ","-");
        driver.findElement(By.id(dynamicId)).click();
        logger.debug("Removed {} from cart",productName);
    }

    public void continueShopping(){
        clickElement(continueShoppingBtn);
    }

    public void checkoutCart(){
        clickElement(checkoutBtn);
    }


}
