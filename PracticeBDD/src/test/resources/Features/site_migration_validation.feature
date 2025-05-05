Feature: Site Migration Validation
  Scenario: Validate URLs before and after migration
    Given I have old and new URLs
    When I compare both
    Then validate content consistency