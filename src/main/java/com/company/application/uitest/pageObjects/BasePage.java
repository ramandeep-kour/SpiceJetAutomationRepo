package com.company.application.uitest.pageObjects;


import com.company.application.uitest.utilities.DriverFactory;
import org.jsoup.helper.DataUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class BasePage extends DriverFactory
{

    private static Logger logger = LoggerFactory.getLogger(BasePage.class);    /**************************** Properties ************************/


    private static Wait<WebDriver> wait;
    private static final long DEFAULT_WAIT_TIMEOUT_SECS = 10;
    private static final long WAIT_FOR_PAGE_LOADED_DELAY = 3000;
    private static final long DEFAULT_SLEEP_TIMEOUT_MILLIS = 250;

    /**************************** Constructor ************************/

    public BasePage()
    {
        wait = new WebDriverWait(_driver, DEFAULT_WAIT_TIMEOUT_SECS, DEFAULT_SLEEP_TIMEOUT_MILLIS);
        PageFactory.initElements(_driver, this);
    }

    /**************************** Locators ************************/

    @FindBy(name = "mainFrame")
    private WebElement mainFrame;

    @FindBy(name = "main")
    private WebElement ppmsMainFrame;

    @FindBy(name = "main")
    private WebElement opgMainFrame;

    /**************************** Frame methods ************************/

    /**
     * Switch to main frame
     * @throws Exception
     */
    public void switchToMainFrame() throws Exception
    {
        new WebDriverWait(_driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(mainFrame)).switchTo();
    }

    /**
     * Switch to main frame in PPMS
     * @throws Exception
     */
    public void switchToPPMSMainFrame() throws Exception
    {
        new WebDriverWait(_driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ppmsMainFrame)).switchTo();
    }

    /**
     * Switch to main frame in OPG
     * @throws Exception
     */
    public void switchToOPGMainFrame() throws Exception
    {
        new WebDriverWait(_driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(opgMainFrame)).switchTo();
    }

    /**
     * Switch to default content
     */
    public void switchToDefaultContent()
    {
        // Switch to default content
        _driver.switchTo().defaultContent();
    }

    /**
     * Switch to a frame using the given frame name
     * @param frameName
     */
    public void switchToFrameWithName(String frameName)
    {
        new WebDriverWait(_driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName)).switchTo();
    }

    /**
     * Switch to a frame using the given index
     * @param frameIndex
     */
    public void switchToFrameWithIndex(int frameIndex)
    {
        new WebDriverWait(_driver,20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex)).switchTo();
    }

    /**************************** Object methods ************************/

    public  static void waitForElementToBeClickableSafe(WebElement element) throws Exception {
        waitForElementToBeClickableSafe(element, 10);
    }

    public static void waitForElementToBeClickableSafe(WebElement element, int waitTimeInSeconds) throws Exception
    {
        int count = 0;
        boolean continueOn = false;
        while (!continueOn)
        {
            try
            {
                waitForWebElementToBeClickable(element, waitTimeInSeconds);
                continueOn = true;
            }
            catch (org.openqa.selenium.StaleElementReferenceException e)
            {
                count++;
                if (count == 16)
                    throw new Exception("StaleElementReferenceException not going away when waiting for element to become clickable.");
                // Do not remove
                Thread.sleep(250);
            }
        }
    }

    public static void waitForElementVisible(WebElement webElement) {
        wait.until(visibilityOf(webElement));
    }

    private static void waitForWebElementToBeClickable(WebElement webElement, int waitTimeInSeconds) {
        BasePage basePage = new BasePage();
        Wait<WebDriver> wait_local;
        int sleepTimeOutMillis = 250;
        wait_local = new WebDriverWait(basePage._driver, waitTimeInSeconds, sleepTimeOutMillis);
        wait_local.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static boolean textPresentOnPage(String textToFind) throws Exception {

        BasePage basePage = new BasePage();

        if (!doesElementExist(By.cssSelector("body"), 2000))
            throw new Exception("Cannot find 'body' region on the page");

        boolean textFound = false;
        for (int count = 0; count < 12; count++) {
            textFound = basePage._driver.findElement(By.cssSelector("body")).getText().contains(textToFind);
            if (textFound) {
                break;
            }
            Thread.sleep(250);
        }
        return textFound;
    }

    public static boolean isTextPresentInFrames(String textToFind) throws Exception {

        BasePage basePage = new BasePage();

        boolean textFound = false;
        int size = basePage._driver.findElements(By.tagName("frame")).size();
        for(int i=0; i<size; i++){
            basePage._driver.switchTo().frame(i);
            textFound = basePage._driver.findElement(By.cssSelector("body")).getText().contains(textToFind);
            basePage._driver.switchTo().defaultContent();
            if (textFound) {
                break;
            }
        }
        return textFound;
    }

    public static boolean doesElementExist(By webElementBy, int timeInMilliseconds) throws Exception {

        BasePage basePage = new BasePage();

        // Wait for a maximum specified amount of time for an element to exist and return true or false depending on the outcome

        int timeElapsedInMilliseconds = 0;
        boolean elementFound = false;

        // Unavoidable thread sleep - to give the screen time to update
        Thread.sleep(500);

        while (timeElapsedInMilliseconds < timeInMilliseconds)
        {
            try {
                basePage._driver.findElement(webElementBy);
                elementFound = true;
                //log.info("Element found");
                break;
            }
            catch (NoSuchElementException ex){
                //log.info("Exception caught");
                Thread.sleep(250);
                timeElapsedInMilliseconds += 250;
            }
        }

        return elementFound;
    }

    public static String getTextFrom(WebElement element) {
        return element.getText();
    }

    /**************************** Checkbox operations ************************/

    public static void setCheckBox(WebElement checkbox) {

        // Ensure the checkbox is set regardless of its present state

        int timeInSeconds = 10;
        waitForWebElementToBeClickable(checkbox, timeInSeconds);
        if (!checkbox.isSelected())
        {
            checkbox.click();
        }
    }

    public static void clearCheckBox(WebElement checkbox) {

        // Ensure the checkbox is cleared regardless of its present state

        int timeInSeconds = 2;
        waitForWebElementToBeClickable(checkbox, timeInSeconds);
        if (checkbox.isSelected())
        {
            checkbox.click();
        }
    }

    public static void toggleCheckBox(WebElement checkbox) {
//        int timeInSeconds = 2;
//        waitForWebElementToBeClickable(checkbox, timeInSeconds);
        checkbox.click();
    }

    public static boolean getCheckBoxState(WebElement checkbox) {
        return checkbox.isSelected();
    }

    /**************************** DropDown Operations ************************/

    public static void checkActualDropdownOptionsAgainstExpected(WebElement dropdownListElement, List<String> expectedOptionsList) throws Exception {

        // Set up with regard to the actual options in the drop down
        int actualOptionCount = getActualOptionCount(dropdownListElement);

        // Check we have the expected number of options coming through
        if (actualOptionCount != expectedOptionsList.size())
            throw new Exception("Was expecting " + expectedOptionsList.size() + " options in the drop down box but there are " + actualOptionCount);

        // Get the actual options
        List<String> actualOptionsList = getDropDownOptionsList(dropdownListElement);

        // Check off the expected options against the actual and note any missing
        String missingOptions ="";
        boolean optionFound;
        int numberOfMissingOptions = 0;
        for (String expectedOption : expectedOptionsList){
            optionFound = false;
            //for (int count = 0; count < actualOptionCount; count++) {
            for (String actualOption : actualOptionsList)
            {
                //if (actualDropdownElementSelect.getOptions().get(count).getText().equals(expectedOption)){
                if (actualOption.equals(expectedOption))
                {
                    //logger.debug("Found expectedOption '" + expectedOption + "' at position " + count);
                    optionFound = true;
                    break;
                }
            }
            if (!optionFound){
                missingOptions = missingOptions + expectedOption + ", ";
                numberOfMissingOptions++;
            }
        }

        // Report missing options
        if (!missingOptions.equals("")) {
            String exceptionTextToInsert;
            if (numberOfMissingOptions == 1){
                exceptionTextToInsert = "option is";
            }
            else
            {
                exceptionTextToInsert = "options are";
            }
            String missingOptionsTrimmed = missingOptions.substring(0, missingOptions.length()-2);
            throw new Exception("The following expected " + exceptionTextToInsert + " missing from the drop down box : " + missingOptionsTrimmed);
        }
    }

    public static List<String> getDropDownOptionsList(WebElement dropdownListElement) {

        Select actualDropdownElementSelect = new Select(dropdownListElement);
        int actualOptionCount = actualDropdownElementSelect.getOptions().size();

        List<String> actualOptionsList = new ArrayList<String>();
        for (int count = 0; count < actualOptionCount; count++) {
            String optionText = actualDropdownElementSelect.getOptions().get(count).getText();
            actualOptionsList.add(optionText);
            //logger.debug(count + " : " + optionText);
        }
        return actualOptionsList;
    }

    public static void checkSpecificDropdownOptionAgainstActualOptions(WebElement dropdownListElement, String expectedOption) throws Exception {

        // Check that there is at least one option
        if (getActualOptionCount(dropdownListElement) == 0)
            throw new Exception("The drop downbox box contains no options");

        // Get the actual options
        List<String> actualOptionsList = getDropDownOptionsList(dropdownListElement);

        boolean foundExpectedOption = false;
        for (String actualOption : actualOptionsList){
            if (expectedOption.equals(actualOption)){
                foundExpectedOption = true;
                break;
            }
        }
        if (!foundExpectedOption)
            throw new Exception("The expected option of '" + expectedOption + "' was not found in the dropdown options list");
    }

    public static void selectDropdownOptionByValue(WebElement dropdownListElement, String expectedOption) throws Exception {
        Select actualDropdownElementSelect = new Select(dropdownListElement);
        actualDropdownElementSelect.selectByValue(expectedOption);
    }

    /**************************** Textbox operations ************************/

    public static void enterText(WebElement textboxElement, String textToSend) throws Exception {
        waitForElementToBeClickableSafe(textboxElement);
        sendKeysSafe(textboxElement, textToSend);
    }

    private static void sendKeysSafe(WebElement element, String stringToSend) throws Exception {

        // Keep attempting to sendKeys to an element
        boolean attemptSuccessful = false;
        for (int count = 0; count < 8; count++){
            try {
                waitForWebElementToBeClickable(element, 30);
                element.clear();
                element.sendKeys(stringToSend);
                attemptSuccessful = true;
                break;

            }
            catch (org.openqa.selenium.InvalidElementStateException e){
                //log.info("Invalid Element State Exception Caught\n");
            }

            Thread.sleep(250);
        }
        if (!attemptSuccessful)
            throw new Exception ("Unable to enter text into text box - Invalid state exception thrown for every attempt.");
    }

    public static String getTextFromTextBox(WebElement element) {
        return element.getAttribute("value");
    }

    /**************************** Link clicking ************************/

    // todo - code to click a link, and to wait a couple of seconds for a certain element that should appear. If it doesn't appear click the link again a maximum of three times.
    // Sometimes Webdriver thinks it has clicked on a link but it doesn't seem to be actioned on the page itself.

    /**************************** Element clicking ************************/

    public static void clickOn(WebElement elementToClick) throws Exception {

        boolean success = false;

        for (int i = 0; i < 12; i++) {
            try {
                elementToClick.click();
                success = true;
                break;
            } catch (WebDriverException e) {
                // Do nothing, we'll try again
            }
            Thread.sleep(250);
        }
        if (!success)
            throw new Exception("WebDriver exception thrown every time we attempt to click this element");
    }

    public static void  highlightElement(WebElement elem) {
        BasePage basePage = new BasePage();
        // draw a border around the found element
        if (basePage._driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)basePage._driver).executeScript("arguments[0].style.border='3px solid red'", elem);
        }
    }

    /**************************** Get page source ************************/

    /**
     * Get the source of the current page
     * @return
     * @throws Exception
     */
    public static String getPageSource() throws Exception {
        BasePage basePage = new BasePage();
        return basePage._driver.getPageSource().trim();
    }

    /**
     * Click on a link text
     * @param linkText
     */
    public static void clickOnLinkText(String linkText) {
        BasePage basePage = new BasePage();
        new WebDriverWait(basePage._driver, 20).until(ExpectedConditions.elementToBeClickable(By.linkText(linkText))).click();
    }

    /**************************** Private methods ************************/

    private static int getActualOptionCount(WebElement dropdownListElement) {

        // Get the number of actual options in the dropdown box

        Select actualDropdownElementSelect = new Select(dropdownListElement);
        int actualOptionCount = actualDropdownElementSelect.getOptions().size();
        logger.info("actualOptionCount :" + actualOptionCount);
        return actualOptionCount;
    }

}
