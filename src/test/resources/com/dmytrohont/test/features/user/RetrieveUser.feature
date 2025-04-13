Feature: User API Tests

  @Positive
  Scenario Outline: Retrieve a user
    When I send a GET request to the "/User/{id}" endpoint with the Id <id>
    Then the response status is 200
    And the response contains field "id" with the integer value <id>
    Examples:
      | id |
      | 3  |