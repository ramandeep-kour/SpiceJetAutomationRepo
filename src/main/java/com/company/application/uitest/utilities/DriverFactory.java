package com.company.application.uitest.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver _driver;

   // public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * Initialise WebDriver with Thread local
     * @param browserMode
     */
    public void init_driver(String browserMode) {

        WebDriverManager.chromedriver().setup();

        //Create Chrome Options
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-gpu");
        //chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--start-maximized");

        if (browserMode.equalsIgnoreCase("headless")) {
            chromeOptions.addArguments("--headless");
        }
        _driver = new ChromeDriver(chromeOptions);
        _driver.manage().deleteAllCookies();

    }

}
