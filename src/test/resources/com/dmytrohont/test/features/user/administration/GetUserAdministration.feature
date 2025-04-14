Feature: User Administration tests
  @Positive
  @Smoke
  Scenario: Check User Administration is alive
    When I send a GET request to the 'GetUserAdministration' endpoint
    Then the response status is 200
    And the response contains not empty list of entities

  @Positive
  Scenario: Retrieve user administration and check its fields
    When I send a GET request to the 'GetUserAdministration' endpoint
    Then the response contains a list of users with not empty values for fields: id, role, email, name, lastName
    And the response contains a list of users without value for the login and password fields