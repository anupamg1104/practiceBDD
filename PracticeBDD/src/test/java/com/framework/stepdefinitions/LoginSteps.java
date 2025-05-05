package com.framework.stepdefinitions;

import io.cucumber.java.en.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.framework.factory.BrowserFactory;
import com.framework.pages.LoginPage;
import com.framework.utils.ConfigReader;
import com.framework.utils.LoggerUtility;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.Set;

public class LoginSteps {
	WebDriver driver;
	WebDriverWait wait;
	LoginPage loginPage;
	String mainWindow, newTab;
	Logger logger = LoggerUtility.getLogger(DataDrivenSteps.class);
	SoftAssert softAssert = new SoftAssert();


	@Given("I open the index page")
	public void i_open_the_index_page() {
		driver = BrowserFactory.getDriver();
		driver.manage().window().maximize();
		String url = ConfigReader.getProperty("login.frame");
		driver.get(url);
		//        driver = new ChromeDriver();
		//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//        driver.get("file:///C:/Users/shubh/Desktop/HTML%20CODE%20FOR%20SELENIUM/multiple%20frame%20with%20login/Clear%20all%20the%20input%20fields%20%205/main.html");
		mainWindow = driver.getWindowHandle();
	}

	@Then("I should see Frame A, Frame B inside it, and Frame C inside B")
	public void verify_nested_frames() {
		driver.switchTo().frame("frameA");
		driver.switchTo().frame("frameB");
		driver.switchTo().frame("frameC");
		assertTrue(driver.findElement(By.tagName("button")).isDisplayed());
		driver.switchTo().defaultContent();
	}

	@Given("I navigate to Frame C")
	public void i_navigate_to_frame_c() {
		driver.switchTo().frame("frameA");
		driver.switchTo().frame("frameB");
		driver.switchTo().frame("frameC");
	}

	@When("I click the {string} button")
	public void i_click_the_button(String btnText) {
		driver.findElement(By.xpath("//button[contains(text(),'" + btnText + "')]")).click();
		Set<String> allWindows = driver.getWindowHandles();
		for (String win : allWindows) {
			if (!win.equals(mainWindow)) newTab = win;
		}
		driver.switchTo().window(newTab);
	}

	@Then("A new tab should open with the login page")
	public void new_tab_should_open_with_login() {
		assertTrue(driver.getCurrentUrl().contains("login.html"));
	}

	@Given("I am on the login page")
	public void i_am_on_the_login_page() {
		if (driver == null) {
			driver = new ChromeDriver();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.get("file:///path/to/login.html");
		}
		loginPage = new LoginPage(driver);
	}

	@When("I enter email {string}, password {string}, and age {string}")
	public void enter_login_details(String email, String password, String age) {
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
		loginPage.enterAge(age);
	}

	@When("I click on login")
	public void i_click_on_login() {
		loginPage.clickLogin();
	}

	@Then("I should see {string}")
	public void validate_result(String result) {
		try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			String alertText = alert.getText();
			alert.accept();
			assertTrue(alertText.toLowerCase().contains("invalid") || alertText.toLowerCase().contains("underage"));
		} catch (TimeoutException e) {
			assertEquals(result, loginPage.getSuccessMessage().trim());
		} finally {
			driver.quit();
		}
	}

	@When("I click on toggle password visibility")
	public void i_click_on_toggle_password_visibility() {
		loginPage.clickTogglePassword();
	}

	@Then("The password input should toggle its visibility")
	public void toggle_visibility_check() {
		String type = loginPage.getPasswordFieldType();
		assertTrue(type.equals("password") || type.equals("text"));
	}

	@When("I login with valid credentials")
	public void login_with_valid_credentials() {
		loginPage.enterEmail("test@example.com");
		loginPage.enterPassword("Test@1234");
		loginPage.enterAge("18");
		loginPage.clickLogin();
	}

	@When("I click the continue button")
	public void i_click_the_continue_button() {
		loginPage.clickContinue();
	}

	@Then("I should proceed to the next page")
	public void i_should_proceed_to_next_page() {
		// Adjust as per your flow
		assertTrue(driver.getCurrentUrl().contains("nextStep.html"));
		driver.quit();
	}


//	@Then("Frame A should have background color {string}")
//	public void verify_frame_a_color(String expectedColor) {
//		driver.switchTo().frame("frameA");
//		String bgColor = driver.findElement(By.tagName("body")).getCssValue("background-color");
//		logger.info("Frame page color : "+bgColor.toString());
//		assertTrue(bgColor.contains(expectedColor));
//		driver.switchTo().defaultContent();
//	}
//
//	@Then("Frame B should have background color {string}")
//	public void verify_frame_b_color(String expectedColor) {
//		driver.switchTo().frame("frameA");
//		driver.switchTo().frame("frameB");
//		String bgColor = driver.findElement(By.tagName("body")).getCssValue("background-color");
//		logger.info("Frame page color : "+bgColor);
//		assertTrue(bgColor.contains(expectedColor));
//		driver.switchTo().defaultContent();
//	}
//
//	@Then("Frame C should have background color {string}")
//	public void verify_frame_c_color(String expectedColor) {
//		driver.switchTo().frame("frameA");
//		driver.switchTo().frame("frameB");
//		driver.switchTo().frame("frameC");
//		String bgColor = driver.findElement(By.tagName("body")).getCssValue("background-color");
//		logger.info("Frame page color : "+bgColor);
//		assertTrue(bgColor.contains(expectedColor));
//		driver.switchTo().defaultContent();
//	}
	
	 @Then("Frame A should have background color {string}")
	    public void verify_frame_a_color(String expectedColor) {
	        driver.switchTo().frame("frameA");
	        String bgColor = driver.findElement(By.tagName("body")).getCssValue("background-color");
	        logger.info("Frame page color : "+Color.fromString(bgColor).asHex());
	        softAssert.assertTrue(bgColor.contains(expectedColor), "Frame A color mismatch. Found: " + bgColor);
	        driver.switchTo().defaultContent();
	    }

	    @Then("Frame B should have background color {string}")
	    public void verify_frame_b_color(String expectedColor) {
	        driver.switchTo().frame("frameA");
	        driver.switchTo().frame("frameB");
	        String bgColor = driver.findElement(By.tagName("body")).getCssValue("background-color");
	        softAssert.assertTrue(bgColor.contains(expectedColor), "Frame B color mismatch. Found: " + bgColor);
	        driver.switchTo().defaultContent();
	    }

	    @Then("Frame C should have background color {string}")
	    public void verify_frame_c_color(String expectedColor) {
	        driver.switchTo().frame("frameA");
	        driver.switchTo().frame("frameB");
	        driver.switchTo().frame("frameC");
	        String bgColor = driver.findElement(By.tagName("body")).getCssValue("background-color");
	        softAssert.assertTrue(bgColor.contains(expectedColor), "Frame C color mismatch. Found: " + bgColor);
	        driver.switchTo().defaultContent();
	    }

	    @Then("Verify all soft assertions")
	    public void assert_all_soft_assertions() {
	        softAssert.assertAll();
	    }

}
