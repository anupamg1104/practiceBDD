package com.framework.driver;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

	private static WebDriver driver;

	public static WebDriver getDriver() {
		if (driver == null) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public static void switchToNewTab() {
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> iterator = windowHandles.iterator();
		String current = driver.getWindowHandle();
		while (iterator.hasNext()) {
			String handle = iterator.next();
			if (!handle.equals(current)) {
				driver.switchTo().window(handle);
				break;
			}
		}
	}
	
	
	
	private static void killBrowserProcesses() {
        String[] processes = {"chromedriver.exe", "chrome.exe"};
        for (String process : processes) {
            try {
                Runtime.getRuntime().exec("taskkill /F /IM " + process);
                System.out.println(process + " processes terminated (if running).");
            } catch (IOException e) {
                System.out.println("Error terminating " + process + ": " + e.getMessage());
            }
        }
    }
}
