package com.swaglabsdemo.stepdefinitions;

import com.swaglabsdemo.Constants;
import com.swaglabsdemo.hooks.DriverHook;
import com.swaglabsdemo.pages.ProductPage;
import com.swaglabsdemo.pages.LoginPage;
//import com.swaglabsdemo.pages.SidebarPage;
import com.swaglabsdemo.utils.TestUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.lang.constant.Constable;
import java.util.List;

public class ProductSteps {

    private static final Logger logger = LogManager.getLogger(ProductSteps.class);
    WebDriver driver = DriverHook.getDriver();
    LoginPage loginPage;
    ProductPage productPage;
//    SidebarPage sidebarPage;
    List<String> productsAddedToCart;

    public ProductSteps(){
        logger.debug("Authenticating User...");
        TestUtil.authenticateUser(driver);
        logger.debug("User Login is Successful");
        productPage = new ProductPage(driver);
    }

    @Given("the user is logged in and the homepage is displayed")
    public void the_user_is_logged_in_and_the_homepage_is_displayed() {
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = Constants.PRODUCTPAGE_URL;
        Assert.assertEquals(actualUrl, expectedUrl, "URL's do not match");
        logger.debug("User is logged in and the homepage is displayed.");
    }

    @When("the product list is not empty")
    public void theProductListIsNotEmpty() {
        boolean productsDisplayed = productPage.areProductsDisplayed();
        Assert.assertTrue(productsDisplayed, "Products are not displayed !!!");
        logger.debug("Product list displayed status: " + productsDisplayed);
    }

    @Then("each product should have a valid title and description and a non-negative-price")
    public void each_product_should_have_a_valid_title_and_description_and_a_non_negative_price() {
        productPage.getProductTitles().forEach(title -> {
            Assert.assertNotNull(title, "Product Title Cannot be Null");
            Assert.assertFalse(title.isBlank(), "Product Title cannot be empty.");
            logger.debug("Valid product title: " + title);
        });

        productPage.getProductDescriptions().forEach(description -> {
            Assert.assertNotNull(description, "Product Description cannot be null");
            Assert.assertFalse(description.isBlank(), "Product Description cannot be empty");
            logger.debug("Valid product description: " + description);
        });

        productPage.getProductPrices().forEach(price -> {
            Assert.assertNotNull(price, "Product Price cannot be null");
            Assert.assertTrue(price > 0, "Product Price must be greater than 0");
            logger.debug("Valid product price: " + price);
        });
    }

    @Then("valid image and add_to_cart button must be displayed")
    public void valid_image_and_add_to_cart_button_must_be_displayed() {
        List<String> invalidImageUrls = productPage.getInvalidProductImageUrls();
        Assert.assertTrue(invalidImageUrls.isEmpty(), "Some images are invalid: " + invalidImageUrls);
        logger.debug("All product images are valid. Invalid URLs: " + invalidImageUrls);

        boolean allButtonsDisplayed = productPage.areAllAddToCartButtonsDisplayed();
        Assert.assertTrue(allButtonsDisplayed, "Add to cart button is not displayed or is disabled.");
        logger.debug("All add to cart buttons are displayed and enabled: " + allButtonsDisplayed);
    }

    @When("the user adds the following products to cart:")
    public void the_user_clicks_on_the_button_for(DataTable productsTable) {
        productsAddedToCart = productsTable.asList();
        logger.debug("Adding products to cart: " + productsAddedToCart);
        for (String product : productsAddedToCart) {
            productPage.addProductToCart(product);
        }
    }

    @Then("the Add to cart button changes to Remove button for added products")
    public void the_add_to_cart_button_changes_to_remove_button_for_added_products() {
        for (String product : productsAddedToCart) {
            Assert.assertTrue(productPage.isRemoveBtnVisible(product), "Remove button is not visible for " + product);
        }
    }

    @Then("the cart icon displays correct quantity")
    public void the_cart_icon_displays_correct_quantity() {
        int actualQuantity = productPage.getCartQuantity();
        int expectedQuantity = productsAddedToCart.size();
        Assert.assertEquals(actualQuantity, expectedQuantity, "Quantities do not match");
        logger.info("Cart icon quantity check: expected {}, found {}", expectedQuantity, actualQuantity);
    }

//    @When("the user navigates to the cart page")
//    public void the_user_navigates_to_the_cart_page(){
//
//    }
//
//    @Then("the cart displays the following items with the correct quantities:")
//    public void the_cart_displays_the_following_items_with_the_correct_quantities(io.cucumber.datatable.DataTable dataTable) {
//
//    }

    @When("the user filters the products based on the {string} criteria")
    public void the_user_filters_the_products_based_on_the_criteria(String filterCriteria) {
        productPage.selectFilterOption(filterCriteria);

    }

    @Then("the products should be filtered according to the {string} criteria")
    public void the_products_should_be_filtered_according_to_the_criteria(String filterCriteria) {
        productPage.assertProductOrder(filterCriteria);
    }

}
