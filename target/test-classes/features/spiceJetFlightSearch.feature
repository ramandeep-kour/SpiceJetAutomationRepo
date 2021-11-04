Feature: Search for Flight using Spicejet

  @SmokeTest
  Scenario: Search a flight for round trip
    Given I log into SpiceJet website
    When I search for a flight
    Then I should see the search result






