Feature: Logging in as admin and seeing the admin dashboard

  Background:
    Given the home page is opened

    Scenario: Login as admin account
      Given we navigate to 'Sign in'
      And the 'Email' login field is filled with 'admin@practicesoftwaretesting.com'
      And the 'Password' login field is filled with 'welcome01'
      And the login button is clicked
      Then we are on the 'Admin Dashboard' page