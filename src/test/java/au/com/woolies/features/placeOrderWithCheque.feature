Feature: Verify that customer is able to place an order via cheque payment

  Background:
    Given I am on the homepage
    And I login using credentials

  @smoke @cheque @singleProduct
  Scenario Outline: Customer should be able to add a product to cart and place an order via cheque payment method
    Given I search for a product and add it to cart
      | productName | colour     | size     | quantity     |
      | <P_Name>    | <P_Colour> | <P_Size> | <P_Quantity> |
    Then I verify that added product is there in cart
      | productName | colour     | size     | quantity     | price     |
      | <P_Name>    | <P_Colour> | <P_Size> | <P_Quantity> | <P_Price> |
    And I navigate to cart and proceed to checkout
    And I add comment on the Address screen and proceed to checkout
      | commentText      |
      | testing checkout |
    And I agree to Terms of Service and proceed to checkout
    Then I select "cheque" payment method and confirm my order

    Examples:
      | P_Name                      | P_Colour  | P_Size  | P_Quantity  | P_Price  |
      | Faded Short Sleeve T-shirts | Orange    | M       | 2           | 16.51    |
