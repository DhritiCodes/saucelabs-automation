package com.swaglabsdemo.pages;

import com.swaglabsdemo.utils.LogUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends SidebarPage {
    Logger logger = LogUtil.getLogger(CartPage.class);

    @FindBy(id="continue-shopping")
    private WebElement continueShoppingBtn;

    @FindBy(id = "checkout")
    private WebElement checkoutBtn;


    public CartPage(WebDriver driver){
        super(driver);
    }

    //getter methods -------------------------------------------------------------------------------------------------

    public List<WebElement> getItemsInCart(){
        return driver.findElements(By.className("cart_item"));
    }

    //action methods -------------------------------------------------------------------------------------------------

    public void removeItemFromCart(String productName){
        String dynamicId = "remove-"+productName.toLowerCase().replace(" ","-");
        driver.findElement(By.id(dynamicId)).click();
        logger.debug("Removed {} from cart",productName);
    }

    public int countOfProductsInCart(){
        int cartSize = getItemsInCart().size();
        logger.debug("Count of products in cart is : {}",cartSize);
        return cartSize;
    }

    public void continueShopping(){
        clickElement(continueShoppingBtn);
    }

    public void checkoutCart(){
        clickElement(checkoutBtn);
        logger.debug("Cart checkout button is clicked.");
    }

    //navigation methods ----------------------------------------------------------------------------------------------

    public CheckoutFormPage redirectFromCartToCheckoutFormPage(){
        logger.debug("Redirected from Cart Page to Cart Checkout Form...");
        return new CheckoutFormPage(driver);
    }

}
