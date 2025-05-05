package com.framework.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage1 {
    WebDriver driver;

    @FindBy(id = "email")
    WebElement usernameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "age")
    WebElement ageInput;

    @FindBy(xpath = "//button[.='Login']")
    WebElement loginButton;

    @FindBy(id = "successMsg")
    WebElement messageBox;

    public LoginPage1(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void enterAge(String age) {
        ageInput.clear();
        ageInput.sendKeys(age);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void verifyLoginMessage(String expectedMessage) {
        String actualMessage = messageBox.getText().trim();
        Assert.assertTrue(actualMessage.contains(expectedMessage),"Login message doesn't match");
    }

    public void verifyFieldsCleared() {
        Assert.assertTrue(usernameInput.getAttribute("value").isEmpty(),"Username not cleared");
        Assert.assertTrue(passwordInput.getAttribute("value").isEmpty(),"Password not cleared");
        Assert.assertTrue(ageInput.getAttribute("value").isEmpty(),"Age not cleared");
    }
}

