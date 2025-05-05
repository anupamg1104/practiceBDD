package com.framework.runners;

import com.aventstack.extentreports.ExtentTest;
import com.framework.utils.ExcelUtil;
import com.framework.utils.ExtentManager;
import com.framework.utils.HTMLReportUtil;
import com.framework.utils.LogUtility;
import com.framework.utils.ScreenshotUtil;

import io.cucumber.java.*;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;



@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.cucumber.stepdef", "com.cucumber.utility"},
        plugin = {"pretty", "html:target/cucumber-html-report", "json:target/cucumber.json"},
        tags = "@Smoke"
)
public class RunCukesTest1 extends AbstractTestNGCucumberTests {

    private static final String FAILED_SCENARIO_EXCEL = "target/FailedScenarios.xlsx";
    private static final String SUMMARY_HTML = "target/ExecutionSummary.html";

    @Before
    public void beforeScenario(Scenario scenario) {
        setScenarioName(scenario.getName());
        ExtentTest test = ExtentManager.getInstance().createTest(scenario.getName());
        setExtentTest(test);
        LogUtility.logInfo("Starting Scenario: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(getDriver(), scenario.getName());
            getExtentTest().fail("Scenario Failed")
                    .addScreenCaptureFromPath(screenshotPath);
            ExcelUtil.logFailureToExcel(FAILED_SCENARIO_EXCEL, scenario.getName(), getDriverName(), getExecutionEnvironment());
        } else {
            getExtentTest().pass("Scenario Passed");
        }

        ExtentManager.flushReports();
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        ExtentManager.initReports("target/ExtentReport.html", "Automation Suite");
        ExcelUtil.initExcelReport(FAILED_SCENARIO_EXCEL);
        LogUtility.logInfo("Test Suite Started");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        HTMLReportUtil.generateSummaryHTML(FAILED_SCENARIO_EXCEL, SUMMARY_HTML);
        LogUtility.logInfo("Test Suite Ended - Summary Report Generated at: " + SUMMARY_HTML);
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
    
    
    
    private static final ThreadLocal<String> scenarioName = new ThreadLocal<>();

    public static void setScenarioName(String name) {
        scenarioName.set(name);
    }

    public static String getScenarioName() {
        return scenarioName.get();
    }

    public static WebDriver getDriver() {
    	WebDriver driver=null;
        return driver; // Replace with your WebDriver logic
    }

    public static String getDriverName() {
        return "Chrome";
    }

    public static String getExecutionEnvironment() {
        return "QA";
    }

    public static void setExtentTest(ExtentTest test) {
        ExtentManager.setExtentTest(test);
    }

    public static ExtentTest getExtentTest() {
        return ExtentManager.getExtentTest();
    }

}

