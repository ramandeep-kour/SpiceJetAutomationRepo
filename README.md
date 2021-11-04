# BDDFunctionalTests
A Maven enabled Java/Webdriver/Cucumber Automated Test Framework for Spicejet. This is a Hybrid framework that uses the benefits of Page Object model and BDD.
This project is compiled using Java 1.8 to keep it inline with the project compiler.

## Pre-requisites
- Install JDK and set [JAVA_HOME]
- Download [Maven](https://maven.apache.org/download.cgi) and [Install](https://maven.apache.org/install.html)
- Download [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows) community edition and setup
- Install and setup [Cucumber IntelliJ Plugin](https://www.jetbrains.com/help/idea/enabling-cucumber-support-in-project.html)
- Familiarise yourself with writing [Gherkin Syntax](https://cucumber.io/docs/gherkin) and [Step Definitions](https://cucumber.io/docs/cucumber/step-definitions)
- Google Chrome version 89

## Setup BDDFunctionalTests project in IntelliJ
- Open IntelliJ as administrator.
- Click files -> open and navigate to the project folder `\\JavaAutomationBDD*` and click ok. You should now see the project loaded in IntelliJ.
- In IntelliJ go to `File -> Project Structure` and click `Modules` in the left-hand corner.
- Click on the `src/main/java` folder and click on `Sources` icon and click Apply.
- Click on the `src/test/java` folder in src/test and click on `Tests` icon and click Apply.
- Click on the `src/test/resources` folder in src/test/ and click on `Resources` icon and click on Apply.
- Click OK to close the window

## Running the Cucumber scenarios
There are two options available to run the scenarios from the feature files
### 1. Run scenarios using TestRunner.java
- Navigate to `src/test/java/TestRunner.java` in IntelliJ.
- Click on the green play icon at the beginning of the `Public class TestRunner` and select `Run as -> Run 'TestRunner'`.
  
This will run the scenarios that are tagged with `@SmokeTest` in chrome browser using default baseurl `https://www.spicejet.com/`.

### 2. Run scenarios from Command line
Open terminal and navigate to the project directory in terminal and run following commands
#### Run scenarios with tag `@SmokeTest
`mvn clean test -Dcucumber.options="src/test/resources" -Dcucumber.filter.tags="@SmokeTest"`

## Writing new features
- This framework enables you to write automation test scripts in [BDD](https://cucumber.io/docs/bdd) approach using [Gherkin](https://cucumber.io/docs/gherkin/reference) syntax.
- Cucumber tests are written in terms of [Features](https://cucumber.io/docs/bdd/who-does-what/#writing-features).
- Each feature consists of one or more [Scenarios](https://cucumber.io/docs/bdd/who-does-what/#scenarios).
- Cucumber feature files goes in the `/src/test/resources` package and should have ".feature" extension.


## Step Definitions
- A Step Definition is a Java method with an expression that links it to one or more Gherkin steps.
- When Cucumber executes a Gherkin step in a scenario, it will look for a matching `step definition` to execute.

## Writing new Step definitions
- Write a Gherkin step in a feature file and run the feature.
- Then Cucumber looks for the associated step definition and if cucumber can't find one, then it will generate a snippet which can be used to create a step definition.
- You can create any required step definition java classes in `/src/test/java/com.company.application.uitest.stepDefinitions` package.

## Creating new Page Objects
- Page object classes are present in `/src/main/java/com.company.application.uitest.pageObjects` package.
- You can create page object classes as and when needed with the locators of the elements on the page.