package com.swaglabsdemo.stepdefinitions;

import com.swaglabsdemo.Constants;
import com.swaglabsdemo.hooks.DriverHook;
import com.swaglabsdemo.pages.*;
import com.swaglabsdemo.utils.TestUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;

public class CartSteps {

    private static final Logger logger = LogManager.getLogger(CartSteps.class);
    WebDriver driver = DriverHook.getDriver();
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutFormPage checkoutFormPage;
    CheckoutCompletePage checkoutCompletePage;
    CheckoutVerificationPage checkoutVerificationPage;
    List<String> productsAddedToCart;
    String expectedUrl, actualUrl;

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        logger.debug("Authenticating User...");
        TestUtil.authenticateUser(driver);
        logger.debug("User Login is Successful");
        productPage = new ProductPage(driver);
    }

    @And("the following items are added to cart from home page:")
    public void theFollowingItemsAreAddedToCartFromHomePage(DataTable productsTable) {
        productsAddedToCart = productsTable.asList();
        logger.debug("Adding products to cart: " + productsAddedToCart);
        for (String product : productsAddedToCart) {
            productPage.addProductToCart(product);
        }
    }

    @And("the user is on the cart page")
    public void theUserIsOnTheCartPage(){
        driver.get(Constants.CARTPAGE_URL);//this can be done by alternatively clicking on the cart btn
        cartPage = productPage.redirectToCartPage();
    }

    @When("the user removes a single item:")
    public void the_user_removes_a_single_item(DataTable products) {
        List<String> productsRemoved = products.asList();
        for(var prod : productsRemoved){
            cartPage.removeItemFromCart(prod);
        }
    }

    @Then("number of items in cart should be {int}")
    public void number_of_items_in_cart_should_be(Integer num) {
        int expectedCartSize = num;
        int actualCartSize = cartPage.countOfProductsInCart();
        Assert.assertEquals(actualCartSize,expectedCartSize ,"Actual Cart size "+actualCartSize+" doesn't match with expected cart size "+expectedCartSize);
    }

    @When("the user clicks on the Checkout button")
    public void the_user_clicks_on_the_checkout_button() {
        cartPage.checkoutCart();
    }

    @Then("the user lands on the cart checkout form")
    public void the_user_lands_on_the_cart_checkout_form() {
        checkoutFormPage = cartPage.redirectFromCartToCheckoutFormPage();
        expectedUrl = Constants.CART_CHECKOUT_FORM_URL;
        actualUrl = checkoutFormPage.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl,"URL's dont match expected :"+ expectedUrl+"\n actual "+actualUrl);
    }

    @And("fills valid {string} firstName {string} lastName and {int} postalCode")
    public void fillsValidFirstNameLastNameAndPostalCode(String firstName, String lastName, int postalCode) {
        checkoutFormPage.fillDetails(firstName,lastName,postalCode);
    }

    @And("the user clicks on the Continue button")
    public void the_user_clicks_on_the_continue_button() {
        checkoutFormPage.clickContinueCheckoutBtn();
        checkoutVerificationPage = checkoutFormPage.redirectFromCheckoutFormToVerificationPage();
    }

    @Then("the user lands on the cart checkout overview page")
    public void the_user_lands_on_the_cart_checkout_overview_page() {
        expectedUrl = Constants.CART_VERIFICATION_URL;
        actualUrl = checkoutVerificationPage.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URL's dont match expected :"+ expectedUrl+"\n actual "+actualUrl);
    }

    @Then("on verifying the cart details")
    public void on_verifying_the_cart_details() {
        Double expectedSum = checkoutVerificationPage.getCartItemsTotalPriceOnVerificationPage();
        Double actualSum = checkoutVerificationPage.getSummarySubTotal();
        Assert.assertEquals(actualSum,expectedSum,"Cart products sum don't match on the verification page.");
    }

    @When("the user clicks on the finish button")
    public void the_user_clicks_on_the_finish_button() {
        checkoutVerificationPage.finishCheckout();
        checkoutCompletePage = checkoutVerificationPage.redirectFromCheckoutVerificationToCompletePage();
    }

    @Then("the user lands on the checkout complete page")
    public void the_user_lands_on_the_checkout_complete_page_showing_success_message() {
        expectedUrl = Constants.CHECKOUT_COMPLETE_URL;
        actualUrl = checkoutCompletePage.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URL's dont match expected :"+ expectedUrl+"\n actual "+actualUrl);
    }

    @And("user sees the success message {string}")
    public void user_sees_the_success_message(String successMessage){
        String expectedMessage = successMessage;
        String actualMessage = checkoutCompletePage.getSuccessMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Success Message on the Order Placed page doesn't match.");
    }

}
