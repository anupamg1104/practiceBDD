Feature: Nested Frame Login Flow

@testFrame
  Scenario: Verify nested frames are loaded correctly
    Given I open the index page
    Then I should see Frame A, Frame B inside it, and Frame C inside B

  Scenario: Open login page from Frame C
    Given I navigate to Frame C
    When I click the "Login Page" button
    Then A new tab should open with the login page

  Scenario Outline: Validate login form inputs
    Given I am on the login page
    When I enter email "<email>", password "<password>", and age "<age>"
    And I click on login
    Then I should see "<result>"

    Examples:
      | email             | password   | age | result                       |
      | test@example.com  | Test@1234  | 18  | Successfully Logged In       |
      | wrong@example.com | Test@1234  | 18  | Invalid credentials alert    |
      | test@example.com  | WrongPass  | 18  | Invalid credentials alert    |
      | test@example.com  | Test@1234  | 17  | Underage alert               |

  Scenario: Toggle password visibility
    Given I am on the login page
    When I click on toggle password visibility
    Then The password input should toggle its visibility

  Scenario: Continue button after successful login
    Given I am on the login page
    When I login with valid credentials
    And I click the continue button
    Then I should proceed to the next page
    
    @framecolor
    Scenario: Verify colors of each frame
  Given I open the index page
  Then Frame A should have background color "red"
  And Frame B should have background color "blue"
  And Frame C should have background color "green"
  And Verify all soft assertions
