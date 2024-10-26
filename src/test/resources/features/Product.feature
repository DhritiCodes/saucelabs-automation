Feature: Product Management

  In order to manage products
  As a registered user
  I want to be able to view products
  add them to cart
  and sort them according to filters

  Background:
    Given the user is logged in and the homepage is displayed

  @smoke
  Scenario: Verify all product details are displayed correctly
    When the product list is not empty
    Then each product should have a valid title and description and a non-negative-price
    And valid image and add_to_cart button must be displayed

  Scenario: Add multiple products to the cart and verify cart updates
    When the user adds the following products to cart:
      | Sauce Labs Backpack      |
      | Sauce Labs Onesie        |
      |Sauce Labs Fleece Jacket  |
    Then the Add to cart button changes to Remove button for added products
    And the cart icon displays correct quantity
#    When the user navigates to the cart page
#    Then the cart displays the following items with the correct quantities:
#      | product name            | quantity |
#      | Sauce Labs Backpack     | 1       |
#      | Sauce Labs Onesie      | 1       |




