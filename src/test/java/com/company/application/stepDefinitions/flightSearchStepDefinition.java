package com.company.application.stepDefinitions;

import com.company.application.uitest.helperClass.LoginHelperClass;
import com.company.application.uitest.pageObjects.BasePage;
import com.company.application.uitest.pageObjects.SpiceJetSearchPage;
import com.company.application.uitest.utilities.DriverFactory;
import com.company.application.uitest.utilities.PropertyUtilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class flightSearchStepDefinition extends BasePage {

    private LoginHelperClass loginHelper = new LoginHelperClass();
    private PropertyUtilities propertyUtilities = new PropertyUtilities();

    private SpiceJetSearchPage spiceJetSearchPage = new SpiceJetSearchPage();

    public flightSearchStepDefinition() throws IOException {
    }

    @Given("I log into SpiceJet website")
    public void iLogInSpiceJetWebsite() throws IOException {
    loginHelper.goToMainPage();
    }

    @Then("I search for a flight")
    public void iSearchForAFlight() throws Exception {
       spiceJetSearchPage.selectOnRoundTripButton();
       spiceJetSearchPage.enterFromCity();
       spiceJetSearchPage.enterToCity();
       spiceJetSearchPage.selectNoOfAdult();
       spiceJetSearchPage.selectGBPCurrency();
       spiceJetSearchPage.selectFamilyAndFriends();
       spiceJetSearchPage.clickSearchFlight();
    }

    @Then("I should see the search result")
    public void iShouldSeeTheSearchResult() throws Exception {

        assertThat(spiceJetSearchPage.getSearchResult().contains(propertyUtilities.FLIGHT_RESULT));

    }
}
