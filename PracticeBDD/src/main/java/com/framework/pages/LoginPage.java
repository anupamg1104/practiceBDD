package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "age")
    WebElement ageField;

    @FindBy(id = "loginBtn")
    WebElement loginBtn;

    @FindBy(id = "togglePassword")
    WebElement togglePasswordBtn;

    @FindBy(id = "successMessage")
    WebElement successMessage;

    @FindBy(id = "continueBtn")
    WebElement continueBtn;

    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void enterAge(String age) {
        ageField.clear();
        ageField.sendKeys(age);
    }

    public void clickLogin() {
        loginBtn.click();
    }

    public void clickTogglePassword() {
        togglePasswordBtn.click();
    }

    public String getSuccessMessage() {
        return successMessage.getText();
    }

    public void clickContinue() {
        continueBtn.click();
    }

    public String getPasswordFieldType() {
        return passwordField.getAttribute("type");
    }
}

