package com.company.application.uitest.pageObjects;

import com.company.application.uitest.utilities.DriverFactory;
import com.company.application.uitest.utilities.PropertyUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SpiceJetSearchPage extends BasePage{


    private PropertyUtilities propertyUtilities;
    private static Logger logger = LoggerFactory.getLogger(SpiceJetSearchPage.class);

    public SpiceJetSearchPage()
    {
        PageFactory.initElements(_driver, this);
    }

    /**************************** Locators ************************/
    @FindBy(xpath = "//div[@data-testid=\"round-trip-radio-button\"]")
    private WebElement roundTrip;


    @FindBy(xpath = "//div[@data-testid='to-testID-origin']//*[local-name()='input']")
    private WebElement fromCity;

    @FindBy(xpath = "//div[@data-testid='to-testID-destination']//*[local-name()='input']")
    private WebElement toCity;

    @FindBy(xpath = "//div[text()='1 Adult']")
    private WebElement selectPassenger;

    //div[text()='1 Adult']
    @FindBy(xpath = "//div[@data-testid='Adult-testID-plus-one-cta']")
    private WebElement selectAdult;

    @FindBy(xpath= "//div[@data-testid='home-page-flight-cta']")
    private WebElement searchFlight;

    @FindBy(xpath = "//div[@data-testid='departure-date-dropdown-label-test-id']")
    private WebElement clickDepartureDate;

    @FindBy(xpath = "//div[@data-testid='undefined-calendar-day-26']")
    private WebElement selectDepartureDate;

    @FindBy(xpath = "//div[@data-testid='return-date-dropdown-label-test-id']")
    private WebElement selectReturnDate;

    @FindBy(xpath = "//div[@data-testid='undefined-calendar-day-29']")
    private WebElement clickReturnDate;

    @FindBy(xpath = "//div[@class='css-76zvg2 r-homxoj r-adyw6z r-1kfrs79']")
    private WebElement clickFamilyAndFriends;

    @FindBy(xpath = "//div[@class='css-76zvg2 r-homxoj r-adyw6z r-1kfrs79']")
    private WebElement getFlightResult;

    @FindBy(xpath = "css-76zvg2 css-bfa6kz r-homxoj r-ubezar")
    private WebElement clickCurrency;

    @FindBy(xpath = "//div[text()='GBP']")
    private WebElement selectGbpCurrency;

    public void selectOnRoundTripButton() throws Exception {
        clickOn(roundTrip);
    }

    public void enterFromCity() throws Exception {
        enterText(fromCity,"DEL");
    }

    public void enterToCity() throws Exception {
        enterText(toCity,"HYD");
    }
    public void selectNoOfAdult() throws Exception {
        clickOn(selectPassenger);
        clickOn(selectAdult);
    }

    public void selectDepartureDate() throws Exception {
        clickOn(clickDepartureDate);
        clickOn(selectDepartureDate);
    }

    public void selectReturnDate() throws Exception {
        clickOn(clickReturnDate);
        clickOn(selectDepartureDate);
    }

    public void selectGBPCurrency() throws Exception {
        clickOn(clickCurrency);
        clickOn(selectGbpCurrency);
    }

    public void selectFamilyAndFriends() throws Exception {
            clickOn(clickFamilyAndFriends);
    }

    public void clickSearchFlight() throws Exception {
        clickOn(searchFlight);
    }


    public String getSearchResult() throws Exception {
        String result = getFlightResult.getText();
        logger.info("Flight result is:" + result);
        return result;
    }
}
