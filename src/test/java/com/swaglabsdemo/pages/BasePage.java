package com.swaglabsdemo.pages;

import com.swaglabsdemo.utils.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;
    public WebDriverWait wait;

    protected BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
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


    //1. Common BASE Methods --------------------------------------------------------------------

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public void clickElement(WebElement element){
        WaitUtil.waitForElementToBeClickable(driver,element).click();
    }

    public void setData(WebElement element, String data){
        WaitUtil.waitForElementToBeVisible(driver,element).sendKeys(data);
    }

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
