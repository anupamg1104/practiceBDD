Feature: Data Handling Techniques for Automation

  Background:
    Given the browser is launched and navigated to the base URL

  Scenario Outline: Validate SEO parameters for different page URLs
    When user opens the page with URL "<pageUrl>"
    Then the page should have valid SEO tags like title and meta description
    And status code of the page should be 200

    Examples:
      | pageUrl                             |
      | https://www.news18.com/tech         |
      | https://www.news18.com/sports       |

  Scenario: Validate URLs fetched from MySQL database
    Given user fetches URLs from the database
    When user opens each URL
    Then the title and canonical should be present
    And invalid URLs should be written to CSV

  Scenario: Validate article API and UI content consistency
    Given user fetches article list from API
    When user navigates to each article on UI
    Then article headline from API should match with UI

  Scenario: Validate URLs from CSV file
    Given user loads URLs from a CSV file
    When user opens each page
    Then the response code should be 200
    And log any URL failing validation to CSV
