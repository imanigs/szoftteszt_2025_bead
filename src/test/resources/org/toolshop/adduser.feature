Feature: Adding a new user as an admin

    Background:
      Given the home page is opened
      And we are logged in as admin
      And we open the 'Admin' page
      And we navigate to 'Main Menu'
      And we navigate to 'Users'
      And we navigate to 'Add User'

    Scenario Outline: Leaving blank a mandatory field
      Given we are on the 'Add User' page
      And the 'First Name' is set to '<first_name>'
      And the 'Last Name' is set to '<last_name>'
      And the 'Date of birth' is set to '<birthday>'
      And the 'Street' is set to '<street>'
      And the 'City' is set to '<city>'
      And the 'Country' is set to '<country>'
      And the 'Email' is set to '<email>'
      And the 'Enabled' is checked
      And the 'Password' is set to '<password>'
      And the 'Add User' form is sent
      Then on the '<field>' field the '<message>' error is shown
      Examples:
      |first_name |last_name |birthday   |street |city |country |email            |password   |field         |message                   |
      |           |Tester    |01-01-2000 |Test   |Test |Romania |tester@test.test |PASS*word1 |First Name    |First Name is required    |
      |Tester     |          |01-01-2000 |Test   |Test |Romania |tester@test.test |PASS*word1 |Last Name     |Last Name is required     |
      |Tester     |Tester    |           |Test   |Test |Romania |tester@test.test |PASS*word1 |Date of birth |Date of birth is required |
      |Tester     |Tester    |01-01-2000 |       |Test |Romania |tester@test.test |PASS*word1 |Street        |Street is required        |

  Scenario: Filling all the fields with acceptable data
    Given we are on the 'Add User' page
    And the 'First Name' is set to 'Tester'
    And the 'Last Name' is set to 'Tester'
    And the 'Date of birth' is set to '01-01-2000'
    And the 'Street' is set to 'Test'
    And the 'Postal code' is set to 'test'
    And the 'City' is set to 'Test'
    And the 'State' is set to 'test'
    And the 'Country' is set to 'Romania'
    And the 'Phone' is set to 'test'
    And the 'Email' is set to 'tester@test.test'
    And the 'Enabled' is checked
    And the 'Failed login attempts' is set to '0'
    And the 'Password' is set to 'PASS*word1'
    And the 'Add User' form is sent
    Then the 'User' success message is 'User saved!/A customer with this email address already exists.'
    # the page database refreshes after some time, then the user can be created
  # but if we run the test multiple times in a short time, we expect that the user
  # already exists. The credentials are acceptable anyways