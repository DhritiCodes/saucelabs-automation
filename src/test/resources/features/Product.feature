@product
Feature: Product Management

  In order to manage products
  As a registered user
  I want to be able to view products
  add them to cart
  and sort them according to filters

  Background:
    Given the user is logged in and the homepage is displayed

  @smoke @positive @verify_products
  Scenario: Verify product details
    When the product list is not empty
    Then each product should have a valid title and description and a non-negative-price
    And valid image and add_to_cart button must be displayed

  @positive @add_to_cart
  Scenario: Add multiple products to cart and verify cart quantity on homepage
    When the user adds the following products to cart:
      | Sauce Labs Backpack      |
      | Sauce Labs Onesie        |
      |Sauce Labs Fleece Jacket  |
    Then the Add to cart button changes to Remove button for added products
    And the cart icon displays correct quantity

  @positive @filter_product
  Scenario Outline: Filter Product
    When the user filters the products based on the "<criteria>" criteria
    Then the products should be filtered according to the "<criteria>" criteria
    Examples:
      | criteria            |
      | Price (low to high) |
      | Name (A to Z) |
      | Name (Z to A) |
      | Price (high to low) |




