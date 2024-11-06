package com.swaglabsdemo.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.sql.DriverManager;

@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"com.swaglabsdemo.stepdefinitions","com.swaglabsdemo.hooks"},
        plugin = {"pretty",
                "html:target/cucumber-reports.html",
//                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = false,
        dryRun = false
        ,tags = "@login_logout"
)

public class TestRunner extends AbstractTestNGCucumberTests {
    private static String browserName;

    @Parameters("browserName")
    @BeforeClass
    public void setUp(String browserName) {
        this.browserName = browserName;
    }

    public static String getBrowser(){
        return browserName;
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios(){
        return super.scenarios();
    }


}
