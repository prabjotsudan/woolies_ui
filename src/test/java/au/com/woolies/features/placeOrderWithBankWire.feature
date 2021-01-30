Feature: Verify that customer is able to place an order via bank wire payment method

  Background:
    Given I am on the homepage
    And I login using credentials

  @smoke @bankWire @twoProducts
  Scenario Outline: Customer should be able to add 2 products to cart and place an order
    Given I search for a product and add it to cart
      | productName | colour      | size      | quantity      |
      | <P1_Name>   | <P1_Colour> | <P1_Size> | <P1_Quantity> |
    Then I verify that added product is there in cart
      | productName | colour      | size      | quantity      | price      |
      | <P1_Name>   | <P1_Colour> | <P1_Size> | <P1_Quantity> | <P1_Price> |
    And I search for a product and add it to cart
      | productName | colour      | size      | quantity      |
      | <P2_Name>   | <P2_Colour> | <P2_Size> | <P2_Quantity> |
    Then I verify that added product is there in cart
      | productName | colour      | size      | quantity      | price      |
      | <P2_Name>   | <P2_Colour> | <P2_Size> | <P2_Quantity> | <P2_Price> |
    And I navigate to cart and proceed to checkout
    And I add comment on the Address screen and proceed to checkout
      | commentText      |
      | testing checkout |
    And I agree to Terms of Service and proceed to checkout
    Then I select "bankwire" payment method and confirm my order

    Examples:
      | P1_Name                     | P1_Colour | P1_Size | P1_Quantity | P1_Price | P2_Name | P2_Colour | P2_Size | P2_Quantity | P2_Price |
      | Faded Short Sleeve T-shirts | Orange    | M       | 2           | 16.51    | Blouse  | White     | M       | 3           | 27.00    |
