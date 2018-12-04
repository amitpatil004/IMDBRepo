#Author:  Amit Patil
#Keywords Summary :
#Feature: List of scenarios for Login feature
#Date Created: 24/11/2018
@Login
Feature: Fetch IMDB Top Rated Indian Movies

  @SmokeTest
  Scenario: Verify if user lands on IMDB Home page successfully
    Given user opens the "chrome" browser
    And user enters the url "https://www.imdb.com"
    Then user lands on the IMDB Home page
    And user clicks on Top Rated Indian Movies link
    Then user lands on the Top Rated Indian Movies page
    And user reads the Data from the webpage
    And Data inserts into the SQLite Database
    And Html page gets open
