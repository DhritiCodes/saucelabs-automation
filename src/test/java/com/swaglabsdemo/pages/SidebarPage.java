package com.swaglabsdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class SidebarPage extends BasePage{

    public SidebarPage(WebDriver driver){
        super(driver);
    }

    // SIDEBAR Elements -----------------------------------------------------------

    @CacheLookup
    @FindBy(id = "react-burger-menu-btn")
    private WebElement openSideMenuBtn;

    @CacheLookup
    @FindBys({@FindBy(css = ".bm-item-list"),@FindBy(tagName = "a")})
    private List<WebElement> sidebarMenuItems;

    @FindBy(id = "inventory_sidebar_link")
    @CacheLookup
    private WebElement allItemsSidebarBtn;

    @FindBy(id = "about_sidebar_link")
    @CacheLookup
    private WebElement aboutSidebarBtn;

    @FindBy(xpath="//a[text()='Logout']")
    private WebElement logoutSideBarBtn;

    @CacheLookup
    @FindBy(id = "reset_sidebar_link")
    private WebElement resetSidebarLink;

    @CacheLookup
    @FindBy(id = "react-burger-menu-btn")
    private WebElement closeSidebarBtn;

    //CART Element -------------------------------------------------------------------------

    @CacheLookup
    @FindBy(className = "shopping_cart_link")
    private WebElement cartBtn;

    //2. NAVBAR Methods -------------------------------------------------------------------

    //2a. navbar-sidebar methods

    public void openSideMenu(){
        clickElement(openSideMenuBtn);
    }

    public void closeSideMenu(){
        clickElement(closeSidebarBtn);
    }

    public void clickLogout(){
        clickElement(logoutSideBarBtn);
    }

    public LoginPage redirectToLoginPage(){
        return new LoginPage(driver);
    }

    public void clickReset(){
        clickElement(resetSidebarLink);
    }

    //2b. navbar-cart methods

    public CartPage redirectToCartPage(){
        return new CartPage(driver);
    }

}
