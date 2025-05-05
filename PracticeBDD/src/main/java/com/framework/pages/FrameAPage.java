package com.framework.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FrameAPage {
    WebDriver driver;

    @FindBy(id = "linkListA")
    WebElement linkList;

    @FindBy(xpath = "//button[text()='Up']")
    WebElement btnUp;

    @FindBy(xpath = "//button[text()='Down']")
    WebElement btnDown;

    @FindBy(xpath = "//button[text()='Left']")
    WebElement btnLeft;

    @FindBy(xpath = "//button[text()='Right']")
    WebElement btnRight;

    @FindBy(xpath = "//button[text()='Go']")
    WebElement btnGo;

    public FrameAPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void pressDown() {
        btnDown.click();
    }

    public void pressRight() {
        btnRight.click();
    }

    public void verifySelectedLink(String expected) {
        WebElement selected = driver.findElement(By.cssSelector(".active"));
        if (!selected.getText().equals(expected)) {
            throw new AssertionError("Expected: " + expected + " but was: " + selected.getText());
        }
    }

    public void clickFrameCLink() {
        // Example: locate the Frame C clickable area and click it
    	driver.switchTo().frame("frameA");
		driver.switchTo().frame("frameB");
		driver.switchTo().frame("frameC");
        WebElement frameCLink = driver.findElement(By.xpath("//button"));
        frameCLink.click();
    }
}

