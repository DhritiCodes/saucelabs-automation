package com.swaglabsdemo.stepdefinitions;

import com.swaglabsdemo.hooks.DriverHook;
import com.swaglabsdemo.pages.LoginPage;
import com.swaglabsdemo.pages.ProductPage;
//import com.swaglabsdemo.pages.SidebarPage;
import com.swaglabsdemo.utils.TestUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CartSteps {

    private static final Logger logger = LogManager.getLogger(CartSteps.class);
    WebDriver driver = DriverHook.getDriver();
    LoginPage loginPage;
    ProductPage productPage;
//    SidebarPage sidebarPage;
    List<String> productsAddedToCart;

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        logger.debug("Authenticating User...");
        TestUtil.authenticateUser(driver);
        logger.debug("User Login is Successful");
        productPage = new ProductPage(driver);
    }

    @And("the following items are added to cart:")
    public void theFollowingItemsAreAddedToCart(DataTable productsTable) {
        productsAddedToCart = productsTable.asList();
        logger.debug("Adding products to cart: " + productsAddedToCart);
        for (String product : productsAddedToCart) {
            productPage.addProductToCart(product);
        }
    }

    @When("the user removes a single item:")
    public void the_user_removes_a_single_item(DataTable products) {
//        cartPage = productPage.
//        List<String> productsRemoved = products.asList();
//        for(var prod : productsRemoved){
//
//        }
    }

    @Then("number of items in cart should be {int}")
    public void number_of_items_in_cart_should_be(Integer num) {

    }
}
