package com.swaglabsdemo.utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotUtil {
    private static final Logger logger = LogUtil.getLogger(ScreenShotUtil.class);

    public static void captureScreenshot(WebDriver driver, String screenshotName){
        String timestamp = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(srcFile, new File("target/screenshots/"+screenshotName+"_"+timestamp+".png"));
        }catch (IOException e){
            logger.error("Error in taking screenshot "+ e);
        }
    }
}
