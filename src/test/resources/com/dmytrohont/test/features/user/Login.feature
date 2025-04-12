Feature: Login endpoint tests

  @Positive
  Scenario: Login with valid credentials
    Given following user credentials:
      | environment | login        | password       |
      | prod        | string123456 | string12341234 |
      | test        | testEnvLogin | testEnvPass    |
    When I send a POST request to Login with stored login and password
    Then I receive the token value in the response

  @Negative
  Scenario: Login with invalid credentials
    When I send a POST request to Login with login "login" and password "test"
    Then I receive the response with empty value for the token field