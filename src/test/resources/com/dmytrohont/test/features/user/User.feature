Feature: User API Tests

  @Positive
  Scenario Outline: Retrieve a user
    When I send a GET request to the 'User' endpoint with the Id <id>
    Then the response status is 200
    And the response contains field "id" with the integer value <id>
    Examples:
      | id |
      | 3  |

  @Negative
  Scenario: Retrieve user with invalid id
    When I send a GET request to the 'User' endpoint with invalid Id 0
    Then the response status is 404
    And the response contains field "title" with value "Not Found"

  @Positive
  @Smoke
  Scenario: Create user and check the response
    Given I have a unique user generated
    When I send a POST request to create a user
    Then the success response contains userId value

# User creation validation tests would generally follow the same principles as the tests implemented for Post creation.
#
# POSITIVE TESTS:
# - Field value validation: Valid values should be verified for each field. For string fields, this includes boundary cases
#   (min/max length), special characters, and formats that match the email regex:
#   ^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$ — covering typical emails (e.g., test@test.com), emails with subdomains,
#   and special character usage (e.g., user.name+tag@sub.domain.co.uk).
# - Numeric fields: Should be tested with boundary values, zero, and valid mid-range values.
# - Field sets: Valid user creation should be tested with different field combinations, such as:
#   a) only required fields
#   b) all possible fields populated
#
# NEGATIVE TESTS:
# - Each type of validation rule violation should be covered for every field. This includes:
#   - Missing required fields
#   - Exceeding min/max length constraints
#   - Invalid formats (e.g., malformed email addresses)
#   - Invalid data types (e.g., string instead of number)
#   - Empty strings where not allowed
#
# OTHER TEST TYPES TO CONSIDER:
# - Duplicate data handling (e.g., user with an email that already exists)
# - SQL injection or other malicious input strings (basic security validation)
# - Case sensitivity checks where applicable
# - Whitespace trimming and normalization behavior
#
# NOTE:
# The current implementation focuses primarily on demonstrating the usage of REST Assured and Cucumber for structuring API tests.

  @Positive
    @Smoke
  Scenario Outline: Update user role
    When I send a PUT request to update a user role with id <id> and role <role>
    Then the response status is <responseStatus>
    And the response contains field "message" with value "User updated"

    Examples:
      | id | role | responseStatus |
      | 3  | 1    | 200            |
      | 4  | 1000 | 200            |
      | 4  | 0    | 200            |
      | 3  | -1   | 200            |

  @Positive
  Scenario Outline: Update user role and check updated user
    When I send a PUT request to update a user role with id <id> and role <role>
    And I send a GET request to the 'User' endpoint with the Id <id>
    Then the response contains field "role" with the integer value <role>
    Examples:
      | id | role |
      | 5  | 2    |
      | 5  | 3    |

  @Negative
  Scenario Outline: Update user role with invalid Id
    When I send a PUT request to update a user role with id <id> and role <role>
    Then the validationErrors field in the response contains "<validationErrors>" values
    Examples:
      | id    | role | validationErrors               |
      | 0     | 4    | 'Id' must be greater than '0'. |
      | 10000 | 3    | User doeasn't exist            |