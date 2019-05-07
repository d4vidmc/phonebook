
Feature: Register

  Scenario: Register new account
    Given the register form page
    When creates a new user account as:
      | user.name     | ABC                |
      | user.email    | ass@mailinator.com |
      | user.password | picapiedra          |
    Then validates "user.name" on header menu
