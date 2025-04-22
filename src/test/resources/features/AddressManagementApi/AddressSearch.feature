Feature: Address Management API

  Scenario Outline: Address - Success <tcNo>
    Given I have access to the Address Management API
    When I create a "POST" request with uri params "none" query params "none" headers "<headers>" with payload "<payload>"
    Then I receive a response with HTTP status code "200" with headers "<headers>"
    And the response payload "<expectedPayload>" should match the schema "<schemaFile>"
    Examples:
      | tcNo | description            | payload   | expectedPayload | schemaFile               | headers                                                                               |
      | SC01 | Mand + Optional fields | SC01.json | SC01Resp.json   | createAddressSchema.json | Content-Type=application/json,eventcorrelationid=69944d31-7a31-4c01-be11-19bb058b5aa1 |
      | SC02 | Mand fields only       | SC02.json | SC02Resp.json   | createAddressSchema.json | Content-Type=application/json,eventcorrelationid=69944d32-7a31-4c01-be12-19bb058b5aa2 |
      | SC03 | Min values for props   | SC03.json | SC03Resp.json   | createAddressSchema.json | Content-Type=application/json,eventcorrelationid=69944d33-7a31-4c01-be13-19bb058b5aa3 |
      | SC04 | Max values for props   | SC04.json | SC04Resp.json   | createAddressSchema.json | Content-Type=application/json,eventcorrelationid=69944d34-7a31-4c01-be14-19bb058b5aa4 |

  Scenario Outline: Invalid "<tcNo>"
    Given I have access to the Address Management API
    When I create a "GET" request with uri params "none" query params "<queryParams>" headers "<headers>" with payload "none"
    Then I receive a response with HTTP status code "400" with headers "<headers>" with payload "<expectedPayload>"
    And the response should match the schema "<schemaFile>"
    Examples:
      | tcNo | description                  | queryParams | schemaFile       | headers                              |
      | IV01 | Missing required queryparams | ,0          | errorSchema.json | 69944d30-7a31-4c07-be36-19bb058b7aaa |
      | IV02 | Incorrect type queryparams   | 123,123     | errorSchema.json | 69944d30-7a31-4c07-be36-19bb058b7bbb |


  Scenario Outline: SearchAddress - Success <tcNo>
    Given I have access to the Address Management API
    When I create a "GET" request with uri params "none" query params "<queryParams>" headers "<headers>" with payload "none"
    Then I receive a response with HTTP status code "200" with headers "<headers>" with payload "<expectedPayload>"
    And the response should match the schema "<schemaFile>"

    Examples:
      | tcNo | description                   | queryParams                            | schemaFile          | headers                                                 |
      | SC01 | mandatory fields              | {"pcode":"SW12"},0                     | addrListSchema.json | eventcorrelationid=69944d30-7a31-4c07-be36-19bb058b5aa1 |
      | SC02 | mandatory and optional values | {"pcode":"NW10 3EWRE","filter":"A"},10 | addrListSchema.json | eventcorrelationid=69944d30-7a31-4c07-be36-19bb058b5aa2 |
      | SC03 | min values                    | {"pcode":"NW10 3EWRE","filter":"A"},10 | addrListSchema.json | eventcorrelationid=69944d30-7a31-4c07-be36-19bb058b5aa3 |
      | SC04 | max values                    | {"pcode":"E1"},100                     | addrListSchema.json | eventcorrelationid=69944d30-7a31-4c07-be36-19bb058b5aa4 |

  Scenario Outline: SearchAddress - Invalid <tcNo>
    Given I have access to the Address Management API
    When I create a "GET" request with uri params "none" query params "<queryParams>" headers "<headers>" with payload "none"
    Then I receive a response with HTTP status code "400" with headers "<headers>" with payload "<expectedPayload>"
    And the response should match the schema "<schemaFile>"

    Examples:
      | tcNo | description                  | queryParams | schemaFile       | headers                              |
      | IV01 | Missing required queryparams | ,0          | errorSchema.json | 69944d30-7a31-4c07-be36-19bb058b7aaa |
      | IV02 | Incorrect type queryparams   | 123,123     | errorSchema.json | 69944d30-7a31-4c07-be36-19bb058b7bbb |
