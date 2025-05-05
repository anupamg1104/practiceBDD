package com.framework.stepdefinitions;


import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import com.framework.pages.FrameAPage;
import com.framework.pages.LoginPage1;
import com.framework.driver.DriverFactory;

public class FrameStepDefinitions {

    WebDriver driver = DriverFactory.getDriver();
    FrameAPage frameA = new FrameAPage(driver);
    LoginPage1 loginPage1 = new LoginPage1(driver);

    @Given("the user opens the main application")
    public void openApplication() {
        driver.get("file:///C:/Users/shubh/Desktop/HTML%20CODE%20FOR%20SELENIUM/multiple%20frame%20with%20login/updated%20-Final/main.html");
    }

    @When("the user navigates to Frame A")
    public void switchToFrameA() {
        driver.switchTo().frame("frameA");
    }

    @When("the user presses the Down button twice")
    public void pressDownButtonTwice() {
        frameA.pressDown();
        frameA.pressDown();
    }

    @When("the user presses the Right button")
    public void pressRightButton() {
        frameA.pressRight();
    }

    @Then("the selected link should be {string}")
    public void verifySelectedLink(String expectedLinkText) {
        frameA.verifySelectedLink(expectedLinkText);
    }

    @Given("the user clicks on Frame C link and switches to the new tab")
    public void clickFrameCLink() {
        frameA.clickFrameCLink();
        DriverFactory.switchToNewTab();
    }

    @When("the user enters username {string}")
    public void enterUsername(String username) {
    	loginPage1.enterUsername(username);
    }

    @When("the user enters password {string}")
    public void enterPassword(String password) {
    	loginPage1.enterPassword(password);
    }

    @When("the user enters age {string}")
    public void enterAge(String age) {
    	loginPage1.enterAge(age);
    }

    @When("the user clicks the login button")
    public void clickLoginButton() {
    	loginPage1.clickLogin();
    }

    @Then("the message {string} should be displayed")
    public void verifySuccessMessage(String expectedMessage) {
    	loginPage1.verifyLoginMessage(expectedMessage);
    }

    @Then("the login form fields should be cleared")
    public void verifyFieldsAreCleared() {
    	loginPage1.verifyFieldsCleared();
    }
}
