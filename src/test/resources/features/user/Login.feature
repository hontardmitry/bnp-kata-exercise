Feature: Login endpoint tests

  Scenario: Login with valid credentials
    When I send a POST request to Login with login "string123456" and password "string12341234"
    Then I receive the token value in the response

  Scenario: Login with invalid credentials
    When I send a POST request to Login with login "login" and password "test"
    Then I receive the response with empty value for the token field