Feature: Manage brands

  Background:
    Given the home page is opened
    And we are logged in as admin
    And we open the 'Admin' page
    And we navigate to 'Main Menu'
    And we navigate to 'Brands'

  Scenario: Create a new brand
    Given we navigate to 'Add Brand'
    And we are on the 'Add Brand' page
    And the 'Brand Name' is set to 'Test Brand'
    And the 'Brand Slug' is set to 'test-brand'
    And the 'Create Brand' form is sent
    Then the 'Brand' success message is 'Brand saved!'

  Scenario: Delete brand
    Given the 'Delete brand' button is clicked
    Then the 'Brand deleted.' alert is shown