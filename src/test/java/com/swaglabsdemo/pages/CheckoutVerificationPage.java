package com.swaglabsdemo.pages;

import com.swaglabsdemo.utils.LogUtil;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.EnsuresKeyForIf;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutVerificationPage extends SidebarPage{

    private static Logger logger = LogUtil.getLogger(CheckoutVerificationPage.class);
    public CheckoutVerificationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(className = "summary_subtotal_label")
    private WebElement summarySubTotal;

    @FindBy(className = "summary_tax_label")
    private WebElement summaryTax;

    @FindBy(className = "summary_total_label")
    private WebElement summaryTotal;

    @FindBy(id="finish")
    private WebElement finishCheckoutBtn;

    //getter methods -------------------------------------------------------------------------------------------------

    public List<WebElement> getCartItemsOnVerificationPage(){
        return driver.findElements(By.className("cart_item"));
    }

    public List<String> getCartItemsTitleOnVerificationPage(){
        List<WebElement> cartItems = getCartItemsOnVerificationPage();
        return  cartItems.stream()
                .map(cartItem -> cartItem.findElement(By.className("inventory_item_name")).getText())
                .collect(Collectors.toList());
    }

    public Double getCartItemsTotalPriceOnVerificationPage(){
        List<WebElement> cartItems = getCartItemsOnVerificationPage();
        return cartItems.stream()
                .map(cartItem -> cartItem.findElement(By.className("inventory_item_price")).getText())
                .map(price -> price.replace("$", ""))
                .map(Double::parseDouble)
                .reduce(0.0, Double::sum);
    }

    public Double getSummarySubTotal(){
        return Double.parseDouble(summarySubTotal.getText().replace("Item total: $",""));
    }

    public  Double getSummaryTax(){
        return Double.parseDouble(summarySubTotal.getText().replace("Tax: $",""));
    }

    public  Double getSummaryTotal(){
        return Double.parseDouble(summarySubTotal.getText().replace("Total: $",""));
    }

    //action methods -------------------------------------------------------------------------------------------------

    public void finishCheckout(){
        logger.debug("Finishing checkout process..");
        clickElement(finishCheckoutBtn);
    }

    //navigation methods ----------------------------------------------------------------------------------------------

    public CheckoutCompletePage redirectFromCheckoutVerificationToCompletePage(){
        return new CheckoutCompletePage(driver);
    }

}
