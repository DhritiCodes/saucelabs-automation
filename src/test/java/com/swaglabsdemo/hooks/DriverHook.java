package com.swaglabsdemo.hooks;

import com.swaglabsdemo.utils.LogUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverHook {
    public static WebDriver driver;
    private static final Logger logger = LogUtil.getLogger(DriverHook.class);

//    @Before("@testhook")
    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("Driver setup completed.");
    }

    public static WebDriver getDriver(){
        return driver;
    }

//    @After("@testhook")
    @After
    public void tearDown(){
        driver.quit();
        logger.info("Driver teardown completed.");
    }
}
