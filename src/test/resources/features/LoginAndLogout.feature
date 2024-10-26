Feature: User Login
  In order to access their account
  As a registered user
  They want to be able to log in with valid credentials and log out

  Background:
    Given the user is on the login page

  Scenario Outline: Successful login and logout
    When the user enters a valid "<username>" username and "<password>" password
    And the user clicks the submit button
    Then the user is successfully logged in and the homepage is displayed
    Then the user can successfully log out

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |
#    | problem_user  | secret_sauce |
#    | error_user    | secret_sauce |
#    | visual_user   | secret_sauce |
