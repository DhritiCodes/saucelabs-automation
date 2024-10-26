package com.swaglabsdemo.stepdefinitions;

import com.swaglabsdemo.hooks.DriverHook;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.swaglabsdemo.pages.ProductPage;
import com.swaglabsdemo.pages.LoginPage;
import com.swaglabsdemo.pages.SidebarPage;

public class LoginAndLogoutSteps {
    WebDriver driver = DriverHook.getDriver();
    LoginPage loginPage;
    ProductPage productPage;
    SidebarPage sidebarPage;

    public LoginAndLogoutSteps() {
        loginPage = new LoginPage(driver);
        sidebarPage = new SidebarPage(driver);
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("the user enters a valid {string} username and {string} password")
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
        Assert.assertEquals(productPage.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "URLs do not match");
    }

    @Then("the user can successfully log out")
    public void the_user_can_successfully_log_out() {
        sidebarPage.openSideMenu();
        sidebarPage.performWait(sidebarPage.getLogoutSideBarBtn(), 5);
        sidebarPage.clickLogout();

        loginPage = sidebarPage.redirectToLoginPage();

        Assert.assertEquals(loginPage.getCurrentUrl(), "https://www.saucedemo.com/", "Expected URLs do not match.");
    }
}
