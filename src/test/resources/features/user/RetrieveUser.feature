Feature: User API Tests

  @RetrieveUser
  Scenario Outline: Retrieve a user
    When I send a GET request to "/User/{id}" with the Id <id>
    Then the response status should be 200
    And the response should contain field "id" with the integer value <id>
    Examples:
      | id |
      | 3  |

  Scenario Outline: Retrieve a user
    When I send a GET request to "/User/{id}" with the Id <id>
    Then the response status should be 200
    And the response should contain field "id" with the integer value <id>
    Examples:
      | id |
      | 3  |