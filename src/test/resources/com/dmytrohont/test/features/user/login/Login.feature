Feature: Login endpoint tests

  @Positive
  Scenario: Login with valid credentials
    Given following user credentials:
      | environment | login        | password       |
      | prod        | string123456 | string12341234 |
      | test        | testEnvLogin | testEnvPass    |
    When I send a POST request to the 'Login' endpoint with stored login and password
    Then the response contains the token value

  @Negative
  Scenario: Attempt to login with invalid credentials
    When I send a POST request to the 'Login' endpoint with login "login" and password "test"
    Then the token value is blank in the response