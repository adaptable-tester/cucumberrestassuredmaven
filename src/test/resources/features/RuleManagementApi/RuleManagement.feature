Feature: Rule Management API

  Scenario Outline: Success <tcNo>
    Given I have access to the Rule Management API
    When I create a "GET" request with uri params "none" query params "<queryParams>" headers "<headers>" with payload "none"
    Then I receive a response with HTTP status code "200" with headers "<headers>" with payload "<expectedPayload>"
    And the response should match the schema "<schemaFile>"

    Examples:
      | tcNo | description                   | queryParams                            | schemaFile            | headers                              |
      | SC01 | mandatory fields              | {"pcode":"SW12"},0                     |                       | 69944d30-7a31-4c07-be36-19bb058b5aa1 |
      | SC02 | mandatory and optional values | {"pcode":"NW10 3EWRE","filter":"A"},10 | addrListSchema.json | 69944d30-7a31-4c07-be36-19bb058b5aa2 |
      | SC03 | min values                    | {"pcode":"NW10 3EWRE","filter":"A"},10 | addrListSchema.json | 69944d30-7a31-4c07-be36-19bb058b5aa3 |
      | SC04 | max values                    | {"pcode":"E1"},100                     | addrListSchema.json | 69944d30-7a31-4c07-be36-19bb058b5aa4 |

  Scenario Outline: Invalid <tcNo>
    Given I have access to the Rule Management API
    When I create a "GET" request with uri params "none" query params "<queryParams>" headers "<headers>" with payload "none"
    Then I receive a response with HTTP status code "400" with headers "<headers>" with payload "<expectedPayload>"
    And the response should match the schema "<schemaFile>"

    Examples:
      | tcNo | description                  | queryParams | schemaFile       | headers                              |
      | IV01 | Missing required queryparams | ,0          | errorSchema.json | 69944d30-7a31-4c07-be36-19bb058b7aaa |
      | IV02 | Incorrect type queryparams   | 123,123     | errorSchema.json | 69944d30-7a31-4c07-be36-19bb058b7bbb |
