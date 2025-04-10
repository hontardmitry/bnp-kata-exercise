Feature: API Healthcheck Tests

  @Positive
  @Smoke
  Scenario: Check User Administration is alive
    When I send a GET request to "/User/GetUserAdministration"
    Then the response status should be 200
    And the response should contain not empty list of entities