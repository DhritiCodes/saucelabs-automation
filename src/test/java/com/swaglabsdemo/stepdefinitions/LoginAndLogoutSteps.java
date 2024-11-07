package com.swaglabsdemo.stepdefinitions;

import com.swaglabsdemo.Constants;
import com.swaglabsdemo.hooks.DriverHook;
import com.swaglabsdemo.utils.TestUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.swaglabsdemo.pages.ProductPage;
import com.swaglabsdemo.pages.LoginPage;

public class LoginAndLogoutSteps {

    private static final Logger logger = LogManager.getLogger(ProductSteps.class);
    WebDriver driver = DriverHook.getDriver();
    LoginPage loginPage;
    ProductPage productPage;

    public LoginAndLogoutSteps() {
        loginPage = new LoginPage(driver);
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        driver.get(Constants.LOGINPAGE_URL);
    }

    @When("the user enters {string} and {string}")
    public void the_user_enters_a_valid_username_and_password(String username, String password) {
        loginPage.sendUsername(username);
        loginPage.sendPassword(password);
    }

    @When("the user clicks the submit button")
    public void the_user_clicks_the_submit_button() {
        loginPage.clickLoginButton();
    }

    @Then("the user is successfully logged in and the homepage is displayed")
    public void the_user_is_successfully_logged_in_and_homepage_is_displayed() {
        productPage = loginPage.redirectToHomePage();

        String expectedUrl = Constants.PRODUCTPAGE_URL;
        String actualUrl = productPage.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URLs do not match");
    }

    @Then("the user can successfully log out")
    public void the_user_can_successfully_log_out() {
        loginPage = TestUtil.logoutUser(driver);

        String expectedUrl = Constants.LOGINPAGE_URL;
        String actualUrl = loginPage.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl,"Expected URLs do not match.");
    }
}
