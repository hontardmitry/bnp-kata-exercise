Feature: API Healthcheck Tests

  @Positive
  @Smoke
  Scenario: Check User Administration is alive
    When I send a GET request to the "/User/GetUserAdministration" endpoint
    Then the response status is 200
    And the response contains not empty list of entities