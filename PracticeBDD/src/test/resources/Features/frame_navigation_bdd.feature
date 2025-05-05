Feature: Frame Navigation and Login Functionality

@framearrow
  Scenario: Verify navigation through links in Frame A using arrow buttons
    Given the user opens the main application
    When the user navigates to Frame A
    And the user presses the Down button twice
    And the user presses the Right button
    Then the selected link should be "Link 6"

@login
  Scenario: Verify successful login with valid credentials and age >= 18
   Given the user opens the main application
    Given the user clicks on Frame C link and switches to the new tab
    When the user enters username "test@example.com"
    And the user enters password "Test@1234"
    And the user enters age "20"
    And the user clicks the login button
    Then the message "Successfully logged in!" should be displayed

@login1
  Scenario: Verify login fails with age below 18 and clears all fields
  Given the user opens the main application
    Given the user clicks on Frame C link and switches to the new tab
    When the user enters username "wrong"
    And the user enters password "wrong"
    And the user enters age "15"
    And the user clicks the login button
    Then the login form fields should be cleared
