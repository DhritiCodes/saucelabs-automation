package com.swaglabsdemo.utils;

import com.swaglabsdemo.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {

    public static WebElement waitForElementToBeVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.SHORT_TIMEOUT));
        return wait.until( elem -> element.isDisplayed() ? element : null);
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.SHORT_TIMEOUT));
        return wait.until( elem -> (element.isDisplayed() && element.isEnabled()) ? element : null);
    }

    public static WebElement waitForElementToBeSelectable(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_TIMEOUT));
        return wait.until(elem -> (element.isDisplayed() && element.isEnabled()) ? element : null);
    }
}
