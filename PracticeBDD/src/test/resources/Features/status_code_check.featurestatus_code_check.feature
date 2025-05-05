Feature: Status Code Check
  Scenario Outline: Check status codes for multiple endpoints
    Given I have endpoints
    When I hit each endpoint
    Then verify response status codes