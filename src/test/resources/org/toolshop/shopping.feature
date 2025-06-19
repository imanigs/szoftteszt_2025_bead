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

  Scenario Outline: Putting items in the cart and seeing correct price
    Given we navigate to 'Categories'
    And we navigate to '<category_1>'
    And we filter '<filter_1>'
    And '<count_1>' tools are shown
    And we view the '<item_1>'
    And the 'Add to Cart' button is pressed
    And the 'Cart' success message is 'Product added to shopping cart.'
    And we wait for message to disappear
    And we navigate to 'Categories'
    And we navigate to '<category_2>'
    And we filter '<filter_2>'
    And '<count_2>' tools are shown
    And we view the '<item_2>'
    And the 'Add to Cart' button is pressed
    And the 'Cart' success message is 'Product added to shopping cart.'
    And we wait for message to disappear
    And we navigate to 'Cart'
    Then the total price is '<price>'
    And the 'Remove item' button is pressed
    And the 'Remove item' button is pressed
    Examples:
    |category_1 |category_2 |filter_1 |filter_2 |count_1 |count_2 |item_1               |item_2                    |price  |
    |Hand Tools |Hand Tools |Chisels  |Chisels  |3       |3       |Wood Carving Chisels |Swiss Woodcarving Chisels |$68.19 |
    |Hand Tools |Hand Tools |Chisels  |Wrench   |3       |3       |Wood Carving Chisels |Adjustable Wrench         |$65.56 |
    |Hand Tools |Hand Tools |Wrench   |Chisels  |3       |3       |Adjustable Wrench    |Chisels Set               |$33.29 |
    |Hand Tools |Hand Tools |Chisels  |Chisels  |3       |3       |Chisels Set          |Swiss Woodcarving Chisels |$35.92 |