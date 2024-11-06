@cart
Feature: Cart Management

  In order to successfully place an order,
  As a registered user,
  I want to manage my shopping cart by viewing the products,
  removing individual items, clearing the entire cart,
  and proceeding to checkout.

  Background:
    Given the user is logged in
    And the following items are added to cart:
      | Sauce Labs Backpack      |
      | Sauce Labs Onesie        |
      | Sauce Labs Fleece Jacket |

  Scenario: Remove a single item from the cart
    When the user removes a single item:
      | Sauce Labs Backpack      |
    Then number of items in cart should be 2
#
#  Scenario: Checkout Cart
#    When the user clicks on the Checkout button
#    Then the user lands on the cart checkout form
#    And fills valid details:
#      | First_Name | Last_name | 900100 |
#    Then the user lands on the cart checkout overview page
#    And on verifying the cart details
#    When the user clicks on the finish button
#    Then the user lands on the checkout complete page showing success message



