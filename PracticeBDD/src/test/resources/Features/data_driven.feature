Feature: Data Driven Testing with Scenario Outline, Background and Multiple Sources

  Background:
    Given User is on the login page

  @scenarioOutline
  Scenario Outline: Login with multiple credentials
    When User enters username "<username>" and password "<password>"
    Then Login should be "<status>"

    Examples:
      | username  | password  | status  |
      | user1     | pass1     | success |
      | user2     | wrongpass | failure |
      | admin     | admin123  | success |

  @csvData
  Scenario: Validate URLs from CSV file
    Given User loads URLs from CSV file
    Then User validates status code is 200

  @dbData
  Scenario: Validate Article URLs from Database
    Given User connects to database and fetches article URLs
    Then User validates each URL status code is 200
