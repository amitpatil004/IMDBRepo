$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/FeatureCollections/IMDBTopRatedMovies.feature");
formatter.feature({
  "name": "Fetch IMDB Top Rated Indian Movies",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@Login"
    }
  ]
});
formatter.scenario({
  "name": "Verify if user lands on IMDB Home page successfully",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Login"
    },
    {
      "name": "@SmokeTest"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "user opens the \"chrome\" browser",
  "keyword": "Given "
});
formatter.match({
  "location": "IMDBTopRatedMovies.OpenBrowser(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user enters the url \"https://www.imdb.com\"",
  "keyword": "And "
});
formatter.match({
  "location": "IMDBTopRatedMovies.Launchapplication(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user lands on the IMDB Home page",
  "keyword": "Then "
});
formatter.match({
  "location": "IMDBTopRatedMovies.HomePage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user clicks on Top Rated Indian Movies link",
  "keyword": "And "
});
formatter.match({
  "location": "IMDBTopRatedMovies.IndianRatedMovieslink()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user lands on the Top Rated Indian Movies page",
  "keyword": "Then "
});
formatter.match({
  "location": "IMDBTopRatedMovies.TopRatedIndianMoviespage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user reads the Data from the webpage",
  "keyword": "And "
});
formatter.match({
  "location": "IMDBTopRatedMovies.ReadDataFromTable()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Data inserts into the SQLite Database",
  "keyword": "And "
});
formatter.match({
  "location": "IMDBTopRatedMovies.InstertData()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Html page gets open",
  "keyword": "And "
});
formatter.match({
  "location": "IMDBTopRatedMovies.OpenHtmlPage()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});