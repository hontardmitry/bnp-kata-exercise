Feature: Post endpoint tests

  @Positive
  Scenario: Create a post
    Given the request body is loaded from "post.json"
    When I send a POST request to create a post with stored post body
    Then the success response contains postId value

    Scenario: Create a post. Title validations
      When I send a POST request to create a post with title "<title>" and content "default content"
