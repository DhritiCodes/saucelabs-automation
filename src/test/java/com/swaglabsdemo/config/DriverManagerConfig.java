package com.swaglabsdemo.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManagerConfig {

    private static ThreadLocal<WebDriver> threadlocalDriver = new ThreadLocal<>();

    private DriverManagerConfig(){}

    public static WebDriver getDriver(String browser){
//        if(threadlocalDriver.get() == null){
//            synchronized (DriverManagerConfig.class){  //slows down execution
                if(threadlocalDriver.get() == null){
                    WebDriver driver;
                    switch (browser.toLowerCase()){
                        case "chrome" -> {
                            WebDriverManager.chromedriver().setup();
                            driver = new ChromeDriver();
                        }
                        case "edge" -> {
                            WebDriverManager.edgedriver().setup();
                            driver = new EdgeDriver();
                        }
                        case "firefox" -> {
                            WebDriverManager.firefoxdriver().setup();
                            driver = new FirefoxDriver();
                        }
                        default -> throw new IllegalArgumentException("Browser not recognized "+browser+". Please specify 'chrome', 'firefox', or 'edge'.");
                    }
                    threadlocalDriver.set(driver);
//                }
//            }
        }
        return threadlocalDriver.get();
    }

    public static boolean quitDriver(){
        if(threadlocalDriver.get() != null) {
            threadlocalDriver.get().quit();
            threadlocalDriver.remove();
            return true;
        }else{
            return false;
        }
    }

}
