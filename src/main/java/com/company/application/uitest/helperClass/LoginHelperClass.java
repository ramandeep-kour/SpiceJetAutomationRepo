package com.company.application.uitest.helperClass;

import com.company.application.uitest.utilities.DriverFactory;
import com.company.application.uitest.utilities.PropertyUtilities;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class LoginHelperClass  extends DriverFactory{

    private PropertyUtilities propertyUtilities;

    {
        propertyUtilities = new PropertyUtilities();
    }

    public void goToMainPage() throws IOException {
        _driver.get(propertyUtilities.BASE_URL);

    }

}
