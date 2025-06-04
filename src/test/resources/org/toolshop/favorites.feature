# !!!!
# Will fail when started headless
  # Because chrome 'change your password' popup will come up, no matter the settings
  # Passes if we cancel the popup..
  # Because of the popup, we have to press "Categories"
  # If the popup wouldn't come up, the test would pass on its own

Feature: Marking items as favourite

  Background:
    Given the home page is opened
    And we are logged in as default user

    Scenario Outline: Marking an item as favorite and viewing it in the favorites page
      Given we navigate to 'Categories'
      And we navigate to 'Hand Tools'
      And we filter '<category>'
      And '<number>' tools are shown
      And we view the '<item>'
      And the 'Add to Favorites' button is pressed
      And we wait for message to disappear
      And we navigate to 'Main Menu'
      And we navigate to 'My Favorites'
      Then the '<item>' is in the favorites
      And we delete the favorite item
      Examples:
      |category |number |item                      |
      |Wrench   |3      |Adjustable Wrench         |
      |Chisels  |3      |Wood Carving Chisels      |
      |Chisels  |3      |Swiss Woodcarving Chisels |
      |Chisels  |3      |Chisels Set               |