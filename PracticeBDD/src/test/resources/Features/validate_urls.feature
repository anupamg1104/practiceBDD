Feature: URL Validation
  Scenario Outline: Validate multiple URLs for status code 200
    Given I fetch URL from database
    When I hit the URL
    Then I should get a 200 status code