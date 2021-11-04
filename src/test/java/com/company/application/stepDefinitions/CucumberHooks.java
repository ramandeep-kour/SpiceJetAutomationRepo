package com.company.application.stepDefinitions;

import java.io.IOException;

import com.company.application.uitest.utilities.PropertyUtilities;
import com.company.application.uitest.utilities.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class CucumberHooks
{

    private WebDriver driver;
    private DriverFactory driverFactory;
    private PropertyUtilities propertyUtils;


    @Before()
    public void getProperty() throws IOException {

        driverFactory = new DriverFactory();
        driverFactory.init_driver( "normal");
    }

    @After(order = 0)
    public void quitBrowser() {
       //driver.quit();
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenShotName = scenario.getName().replaceAll(" ", "_");
            byte [] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenShotName);
        }
    }
}
