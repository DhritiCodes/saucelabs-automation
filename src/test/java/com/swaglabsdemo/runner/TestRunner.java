package com.swaglabsdemo.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/Product.feature",
        glue = {"com.swaglabsdemo.stepdefinitions","com.swaglabsdemo.hooks"}
//        ,tags = "@testhook"
)

public class TestRunner extends AbstractTestNGCucumberTests {

}
