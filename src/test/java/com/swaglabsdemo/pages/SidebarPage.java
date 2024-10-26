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

    @CacheLookup
    @FindBy(id = "react-burger-menu-btn")
    private WebElement openSideMenuBtn;

    @FindBy(id = "inventory_sidebar_link")
    @CacheLookup
    private WebElement allItemsSidebarBtn;

    @FindBy(id = "about_sidebar_link")
    @CacheLookup
    private WebElement aboutSidebarBtn;

    @FindBy(xpath="//a[text()='Logout']")
    private WebElement logoutSideBarBtn;

    public WebElement getLogoutSideBarBtn() {
        return logoutSideBarBtn;
    }

    @CacheLookup
    @FindBy(id = "reset_sidebar_link")
    private WebElement resetSidebarLink;

    @CacheLookup
    @FindBys({@FindBy(css = ".bm-item-list"),@FindBy(tagName = "a")})
    private List<WebElement> sidebarMenuItems;

    public void openSideMenu(){
        clickElement(openSideMenuBtn);
    }

    public void clickAllItems(){
        clickElement(allItemsSidebarBtn);
    }

    public void clickLogout(){
        clickElement(logoutSideBarBtn);
    }

    public LoginPage redirectToLoginPage(){
        return new LoginPage(driver);
    }

    public void clickAbout(){
        clickElement(aboutSidebarBtn);
    }

    public void clickReset(){
        resetSidebarLink.click();
        clickElement(resetSidebarLink);
    }

}
