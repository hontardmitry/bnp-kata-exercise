Feature: Post endpoint tests

  Scenario: Create a post
    Given I have a post body from file "post.json"
    When I send a POST request to create a post with stored post body
    Then I receive the success response with postId value