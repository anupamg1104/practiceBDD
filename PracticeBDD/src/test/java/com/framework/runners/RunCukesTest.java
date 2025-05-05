//package com.framework.runners;
//
//import java.io.*;
//import java.lang.reflect.Field;
//import java.util.*;
//
//import org.testng.annotations.*;
//
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentReporter;
//
//import com.google.common.collect.ArrayListMultimap;
//import com.google.common.collect.Multimap;
//
//
//import cucumber.api.*;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.Scenario;
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//
//import org.apache.commons.lang3.reflect.FieldUtils;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.*;
//
//@CucumberOptions(
//    features = "classpath:features",
//    plugin = {
//        "pretty",
//        "html:target/cucumber-html-report",
//        "json:target/cucumber.json",
//        "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-html-report/index.html"
//    },
//    tags = "@CovidTracker",
//    monochrome = true
//)
//public class RunCukesTest extends AbstractTestNGCucumberTests {
//    static ExtentReporter extent;
//    public static ExtentTest logger;
//    String imagePath, pathForLogger, testCaseDescription;
//    public Scenario scenario;
//    public FileInputStream fis;
//    public XSSFWorkbook workbook;
//    public XSSFSheet sheet;
//    public XSSFRow row;
//    public XSSFCell cell;
//
//    private static final String NEW_LINE = "\n";
//    private static final String HTML_FILE_EXTENSION = ".html";
//    private static final String TEMP_FILE_EXTENSION = ".tmp";
//    private static final String HTML_SNNIPET_1 = "<!DOCTYPE html><html><head><title>";
//    private static final String HTML_SNNIPET_2 = "<table cellspacing='0' cellpadding='0' table-layout= 'fixed' width='100%' border='1'>";
//    private static final String HTML_SNNIPET_3 = "</table></body></html>";
//    private static final String HTML_TR_S = "<tr>";
//    private static final String HTML_TR_E = "</tr>";
//    private static final String HTML_TD_S = "<td align='center' valign='top' style='border-left: solid 1px'>";
//    private static final String HTML_TD_S_C = "<td align='center' valign='top' style='color:red;font-weight: bold;' width='33.33%'  style='font-size:25px'>";
//    private static final String HTML_TD_S_2 = "<td align='center' valign='top' style='color:black;' width='33.33%' style='font-size:25px'>";
//    private static final String HTML_TD_E = "</td>";
//    private static final String excelSheetPath = System.getProperty("user.dir") + "\\ExecutionReport.xlsx";
//    private static String[] sheetnames;
//    public static Multimap<String, String> failureReason = ArrayListMultimap.create();
//
//    @Before
//    public void beforeMethod(Scenario scenario) {
//        this.scenario = scenario;
//        testCaseDescription = scenario.getName().contains("_") ? scenario.getName().split("_")[1] : scenario.getName();
//        System.out.println("CURRENTLY EXECUTING TC IS: " + testCaseDescription);
//        logger = extent.startTest(testCaseDescription);
//        LogUtil.infoLog(getClass(), "\n+----------------------------------------------------------------------------------------------------------------------------+");
//        LogUtil.infoLog(getClass(), "Test Started: " + scenario.getName());
//        LogUtil.infoLog("Test Environment", "Test is executed in Environment: " + GlobalUtil.getCommonSettings().getExecutionEnv());
//        LogUtil.infoLog("TestStarted", "Test is started with browser: " + GlobalUtil.getCommonSettings().getBrowser());
//
//        ExtentProperties.INSTANCE.setReportPath("output/ExecutionReport.html");
//    }
//
//    @After
//    public void afterMethod() throws Exception {
//        if (scenario.isFailed()) {
//            try {
//                String scFileName = "ScreenShot_" + System.currentTimeMillis();
//                String screenshotFilePath = ConfigReader.getValue("screenshotPath") + "/" + scFileName + ".png";
//                imagePath = HTMLReportUtil.testFailTakeScreenshot(screenshotFilePath);
//                pathForLogger = logger.addScreenCapture(imagePath);
//                logger.log(LogStatus.FAIL, HTMLReportUtil.failStringRedColor("Failed at point: " + pathForLogger));
//                scenario.write("Current Page URL is " + GlobalUtil.getDriver().getCurrentUrl());
//                byte[] screenshot = KeywordUtil.takeScreenshot(screenshotFilePath);
//                scenario.embed(screenshot, "image/png");
//                Reporter.addScreenCaptureFromPath(imagePath);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                fis = new FileInputStream(excelSheetPath);
//                workbook = new XSSFWorkbook(fis);
//                if (workbook.getNumberOfSheets() == 0) workbook.createSheet("Report");
//                sheet = workbook.getSheet("Report");
//                row = sheet.createRow(0);
//                row.createCell(0).setCellValue("Failed Scenario");
//                row.createCell(1).setCellValue("Status");
//                row.createCell(2).setCellValue("Platform");
//                row.createCell(3).setCellValue("Failure Reason");
//                row = sheet.createRow(sheet.getLastRowNum() + 1);
//                row.createCell(0).setCellValue(scenario.getName());
//                row.createCell(1).setCellValue(scenario.getStatus());
//                row.createCell(2).setCellValue(GlobalUtil.applicationType.equalsIgnoreCase("amp") ? "amp" : GlobalUtil.platformName);
//                row.createCell(3).setCellValue(logError(scenario));
//                failureReason.put(scenario.getName(), logError(scenario));
//                FileOutputStream fos = new FileOutputStream(excelSheetPath);
//                workbook.write(fos);
//                fos.close();
//            }
//        } else {
//            LogUtil.infoLog("TestEnded", "Test has ended closing browser: " + GlobalUtil.getCommonSettings().getBrowser());
//        }
//        extent.endTest(logger);
//    }
//
//    @BeforeClass
//    public void onStart() {
//        try {
//            extent = new ExtentReports(System.getProperty("user.dir") + ConfigReader.getValue("extentReportPath"));
//            extent.loadConfig(new File(System.getProperty("user.dir") + ConfigReader.getValue("extentConfigPath")));
//            GlobalUtil.setCommonSettings(ExcelDataUtil.getCommonSettings());
//
//            String browser = Optional.ofNullable(GlobalUtil.getCommonSettings().getBrowser()).orElse(ConfigReader.getValue("defaultBrowser"));
//            String executionEnv = Optional.ofNullable(GlobalUtil.getCommonSettings().getExecutionEnv()).orElse(ConfigReader.getValue("defaultExecutionEnvironment"));
//            String url = Optional.ofNullable(GlobalUtil.getCommonSettings().getUrl()).orElse(ConfigReader.getValue("BASE_URL"));
//            GlobalUtil.getCommonSettings().setUrl(url);
//
//            LogUtil.infoLog(getClass(), "\n\n+===========================================================================================================+");
//            LogUtil.infoLog(getClass(), " Suite started at " + new Date());
//            LogUtil.infoLog(getClass(), "Suite Execution For Web application on environment : " + executionEnv);
//            LogUtil.infoLog(getClass(), "\n\n+===========================================================================================================+");
//
//            new File("htmlemail.html").delete();
//            File fileName = new File(excelSheetPath);
//            if (fileName.exists()) fileName.delete();
//            FileOutputStream fileOut = new FileOutputStream(fileName);
//            Workbook wb = new XSSFWorkbook();
//            wb.createSheet("Report");
//            wb.write(fileOut);
//            fileOut.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            LogUtil.errorLog(getClass(), "Common Settings not properly set may not run the scripts properly");
//        }
//    }
//
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.features();
//    }
//
//    @AfterClass
//    public void onFinish() {
//        LogUtil.infoLog(getClass(), " suite finished at " + new Date());
//        LogUtil.infoLog(getClass(), "\n\n+===========================================================================================================+");
//        // Remaining code for generating HTML report should be continued here
//    }
//
//    private String logError(Scenario scenario) {
//        try {
//            Field field = FieldUtils.getField(scenario.getClass(), "delegate", true);
//            ScenarioImpl scenarioImpl = (ScenarioImpl) field.get(scenario);
//            Result result = scenarioImpl.getResult();
//            return result.getErrorMessage();
//        } catch (Exception e) {
//            return "Could not retrieve error message.";
//        }
//    }
//}
