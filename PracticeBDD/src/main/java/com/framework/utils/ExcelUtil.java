package com.framework.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtil {

    private static Workbook workbook;
    private static Sheet sheet;

    public static void initExcelReport(String excelPath) {
        try {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("FailedScenarios");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Scenario Name");
            header.createCell(1).setCellValue("Browser");
            header.createCell(2).setCellValue("Environment");
            header.createCell(3).setCellValue("Timestamp");

            try (FileOutputStream out = new FileOutputStream(excelPath)) {
                workbook.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logFailureToExcel(String excelPath, String scenarioName, String browser, String env) {
        try (FileInputStream fis = new FileInputStream(excelPath)) {
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet("FailedScenarios");

            int lastRow = sheet.getLastRowNum();
            Row row = sheet.createRow(lastRow + 1);
            row.createCell(0).setCellValue(scenarioName);
            row.createCell(1).setCellValue(browser);
            row.createCell(2).setCellValue(env);
            row.createCell(3).setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            try (FileOutputStream out = new FileOutputStream(excelPath)) {
                workbook.write(out);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

