package com.swaglabsdemo.pages;

import com.swaglabsdemo.utils.LogUtil;
import com.swaglabsdemo.utils.WaitUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;
    public WebDriverWait wait;
    private static final Logger logger = LogUtil.getLogger(BasePage.class);

    protected BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //1. Common BASE Methods --------------------------------------------------------------------

    public String getCurrentUrl(){
        String url = driver.getCurrentUrl();
        logger.debug("Retrieved current URL: {}", url);
        return url;
    }

    public void clickElement(WebElement element){
        try {
            WebElement clickableElement = WaitUtil.waitForElementToBeClickable(driver, element);
            clickableElement.click();
            logger.info("Clicked on {} successfully.",element);
        } catch (Exception e) {
            logger.error("Failed to click on the {} due to: {}",element, e.getMessage());
            throw e;
        }
    }

    public void setData(WebElement element, String data){
        try {
            WebElement visibleElement = WaitUtil.waitForElementToBeVisible(driver, element);
            visibleElement.sendKeys(data);
            logger.info("Set data '{}', on {} successfully.", data,element);
        } catch (Exception e) {
            logger.error("Failed to set data on the {} due to: {}",element, e.getMessage());
            throw e;
        }
    }



}
