@CreateUser
Feature: Create User Tests

  Scenario: Create user and check the response
    Given I have a unique user generated
    When I send a POST request to create a user
    Then I receive the success response with userId value

