Feature: Load Time Tracking

  Scenario: Track page load time
    Given I launch the website
    When I capture load times
    Then validate load time is within threshold
