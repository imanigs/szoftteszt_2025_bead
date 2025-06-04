Feature: Changes in the cart

  Background:
    Given the home page is opened
    And we navigate to 'Categories'
    And we navigate to 'Hand Tools'
    And we filter 'Wrench'
    And '3' tools are shown
    And we view the 'Adjustable Wrench'
    And the 'Add to Cart' button is pressed
    And the 'Cart' success message is 'Product added to shopping cart.'
    And we wait for message to disappear
    And we navigate to 'Cart'

  Scenario Outline: Changing item quantity in the cart
      Given we type 'Quantity': '<quantity>'
      Then the 'Cart' success message is 'Product quantity updated.'
      And we wait for message to disappear
      And the total price is '<price>'
      And the 'Delete Product' button is pressed
      And the 'Cart' success message is 'Product deleted.'
      And we wait for message to disappear
      Examples:
      |quantity |price   |
      |2        |$40.66  |
      |3        |$60.99	 |
      |9        |$182.97 |
      |7        |$142.31 |