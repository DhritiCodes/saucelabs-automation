package com.swaglabsdemo.hooks;

import com.swaglabsdemo.config.DriverManagerConfig;
import com.swaglabsdemo.exceptions.DriverSetupException;
import com.swaglabsdemo.runner.TestRunner;
import com.swaglabsdemo.utils.LogUtil;
import com.swaglabsdemo.utils.ScreenShotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

public class DriverHook {
    private static WebDriver driver;
    private static final Logger logger = LogUtil.getLogger(DriverHook.class);

    @Before
    public void setup(Scenario scenario) throws DriverSetupException{
        try {
            driver = DriverManagerConfig.getDriver(TestRunner.getBrowser());
            driver.manage().window().maximize();
            logger.info("Driver setup for {} is completed.", scenario.getName());
        } catch (Exception e) {
            logger.error("Failed to setup WebDriver for scenario '{}': {}", scenario.getName(), e.getMessage());
            throw new DriverSetupException("Driver setup failure");
        }
    }

    public static WebDriver getDriver(){
        return driver;
    }

    @After
    public void tearDown(Scenario scenario){
        if(DriverManagerConfig.quitDriver()){
            logger.info("Driver teardown for "+scenario.getName()+" is completed.");

        }else{
            logger.info("Driver teardown for "+scenario.getName()+" is incomplete due to failure.");

        }
    }

    @After
    public void takeScreenshotOnFailure(Scenario scenario){
        if(scenario.isFailed()){
            ScreenShotUtil.captureScreenshot(driver, scenario.getName());
            logger.info("Captured screenshot for failed scenario : "+scenario.getName());
        }
    }
}
