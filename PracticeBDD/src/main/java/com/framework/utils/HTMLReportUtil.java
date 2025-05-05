package com.framework.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.nio.file.Files;

public class HTMLReportUtil {

    public static void generateSummaryHTML(String excelPath, String htmlOutputPath) {
        try (FileInputStream fis = new FileInputStream(excelPath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet("FailedScenarios");
            StringBuilder html = new StringBuilder();
            html.append("<html><head><title>Execution Summary</title></head><body>");
            html.append("<h2>Failed Scenarios</h2>");
            html.append("<table border='1' cellpadding='5' style='border-collapse:collapse;'>");
            html.append("<tr><th>Scenario</th><th>Browser</th><th>Environment</th><th>Timestamp</th></tr>");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                html.append("<tr>");
                for (int j = 0; j < 4; j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    html.append("<td>").append(cell.toString()).append("</td>");
                }
                html.append("</tr>");
            }

            html.append("</table></body></html>");

            Files.write(new File(htmlOutputPath).toPath(), html.toString().getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void generateSummaryHTML1(String excelPath, String outputHtmlPath) {
        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Execution Summary</title></head><body>");
        html.append("<h2>Failed Scenarios Summary</h2>");
        html.append("<table border='1'><tr><th>Scenario Name</th><th>Browser</th><th>Environment</th></tr>");

        try (FileInputStream fis = new FileInputStream(excelPath)) {
            org.apache.poi.ss.usermodel.Workbook workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook(fis);
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.getRow(i);
                html.append("<tr>");
                for (int j = 0; j < 3; j++) {
                    html.append("<td>").append(row.getCell(j)).append("</td>");
                }
                html.append("</tr>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        html.append("</table></body></html>");

        try (FileWriter writer = new FileWriter(outputHtmlPath)) {
            writer.write(html.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

