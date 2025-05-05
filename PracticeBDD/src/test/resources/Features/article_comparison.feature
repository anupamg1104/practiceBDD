Feature: Article Comparison across UI, API, XML
  Scenario: Compare article content from different sources
    Given I get article data from UI, API, and XML
    Then verify all three are consistent