@cart
Feature: Cart Management

  As a registered user,
  In order to successfully place an order,
  I want to manage my shopping cart by viewing the products,
  removing individual items, clearing the entire cart,
  and proceeding to checkout.

  Background:
    Given the user is logged in
    And the following items are added to cart from home page:
      | Sauce Labs Backpack      |
      | Sauce Labs Onesie        |
      | Sauce Labs Fleece Jacket |
    And the user is on the cart page

  @remove_item
  Scenario: Remove a single item from the cart
    When the user removes a single item:
      | Sauce Labs Fleece Jacket      |
    Then number of items in cart should be 2

  Scenario Outline: Checkout Cart
    When the user clicks on the Checkout button
    Then the user lands on the cart checkout form
    And fills valid "<first_name>" firstName "<last_name>" lastName and <postal_code> postalCode
    And the user clicks on the Continue button
    Then the user lands on the cart checkout overview page
    And on verifying the cart details
    When the user clicks on the finish button
    Then the user lands on the checkout complete page
    And user sees the success message "Thank you for your order!"

    Examples:
    |first_name   |last_name    |postal_code    |
    |John         |Doe          |900909         |



