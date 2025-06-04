Feature: Shopping as a customer

  Background:
    Given the home page is opened

  Scenario Outline: Filtering tool categories
    Given we navigate to 'Categories'
    And we navigate to 'Hand Tools'
    And we filter '<tool>'
    Then '<item_number>' tools are shown
    Examples:
    |tool       |item_number|
    |Chisels    |3          |
    |Wrench     |3          |
    |Hand Saw   |1          |
    |Screwdriver|2          |

  Scenario: Putting items in the cart
    Given we navigate to 'Categories'
    And we navigate to 'Hand Tools'
    And we filter 'Chisels'
    And '3' tools are shown
    And we view the 'Wood Carving Chisels'
    And the 'Add to Cart' button is pressed
    And the 'Cart' success message is 'Product added to shopping cart.'
    And we wait for message to disappear
    And we navigate to 'Categories'
    And we navigate to 'Hand Tools'
    And we filter 'Chisels'
    And '3' tools are shown
    And we view the 'Swiss Woodcarving Chisels'
    And the 'Add to Cart' button is pressed
    And the 'Cart' success message is 'Product added to shopping cart.'
    And we wait for message to disappear
    And we navigate to 'Cart'
    Then the total price is '$68.19'