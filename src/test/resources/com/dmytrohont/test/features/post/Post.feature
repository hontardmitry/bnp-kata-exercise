Feature: Post endpoint tests

  @Positive
  @Smoke
  Scenario: Retrieve posts.
    When I send a GET request to the 'Post' endpoint
    Then the response contains a list of posts with not empty values for each field

  @Positive
    @Smoke
  Scenario: Create a post and check that values are correctly saved
    Given the request body is loaded from "post.json"
    When I send a POST request to create a post with stored post body
    Then the success response contains postId value
    And I retrieve a post by stored id
    Then the retrieved post has the same title and content as the request body

  @Validation
    @Positive
  Scenario Outline: Create a post. Title validations. Valid values
    When I send a POST request to create a post with title "<title>" and content "default content"
    Then the success field in the response contains <isSuccess> value
    And the validationErrors field in the response is empty
    @MinValue
    Examples:
      | title | isSuccess |
      | min   | true      |
    @MaxValue
    Examples:
      | title                     | isSuccess |
      | 25 characters long title. | true      |

  @Validation
    @Negative
  Scenario Outline: Create a post. Title validations. Invalid values
    When I send a POST request to create a post with title "<title>" and content "default content"
    Then the success field in the response contains <isSuccess> value
    And the validationErrors field in the response contains "<validationErrors>" values
    @MoreThanMaxValue
    Examples:
      | title                      | isSuccess | validationErrors                                                                 |
      | 26 characters long title.1 | false     | The length of 'Title' must be 25 characters or fewer. You entered 26 characters. |
    @LessThanMinValue
    Examples:
      | title | isSuccess | validationErrors                                                               |
      |       | false     | The length of 'Title' must be at least 3 characters. You entered 0 characters. |
      | 0     | false     | The length of 'Title' must be at least 3 characters. You entered 1 characters. |
      | ab    | false     | The length of 'Title' must be at least 3 characters. You entered 2 characters. |
#    For content field the approach in testing would be the same

  @Validation
    @Negative
  Scenario Outline: Create a post. Content and title validations. Violation of several validations simultaneously.
    When I send a POST request to create a post with title "<title>" and content "<content>"
    Then the success field in the response contains <isSuccess> value
    And the validationErrors field in the response contains "<validationErrors>" values
    @LessThanMinValue
    Examples:
      | content | title | isSuccess | validationErrors                                                                                                                                                 |
      |         |       | false     | The length of 'Content' must be at least 3 characters. You entered 0 characters.; The length of 'Title' must be at least 3 characters. You entered 0 characters. |
