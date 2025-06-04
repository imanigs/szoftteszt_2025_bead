# !!!!
# Will fail when started headless
  # Because chrome 'change your password' popup will come up, no matter the settings
  # Passes if we cancel the popup..

Feature: Checking password complexity validation

  Background:
    Given the home page is opened
    And we are logged in as default user
    And we open the 'My Profile' page

  Scenario Outline: Entering weak passwords
    Given we are on the 'My Profile' page
    And we type 'New Password': '<new>'
    And we type 'Confirm New Password': '<new>'
    Then the password strength is shown as '<strength>'
    Examples:
    |new         |strength    |
    |word        |Weak        |
    |password    |Moderate    |
    |paSswOrd    |Strong      |
    |paSsw0rd01  |Very Strong |
    |paSsw0rd01? |Excellent   |