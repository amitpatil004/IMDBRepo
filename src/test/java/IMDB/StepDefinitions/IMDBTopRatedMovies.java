package IMDB.StepDefinitions;

import java.awt.Dimension;
import java.lang.*;
import java.net.UnknownHostException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.text.MessageFormat;
import java.sql.*;
import org.apache.commons.io.FileUtils;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import DataModels.Movies;
import IMDB.Config.Config;
import IMDB.Config.ConfigFileReader;
import IMDB.Config.Constants;
import IMDB.Config.DatabaseListner;
import IMDB.Config.GeckoConfiguration;
import IMDB.Config.HTMLReportGenerator;
import IMDB.Config.Helper;
import IMDB.Config.TakeScreenShot;
import IMDB.Config.baseclass;
import IMDB.PageObjects.IMDBPageObjects;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class IMDBTopRatedMovies {
	// page object initialization
	IMDBPageObjects IMDBPageObjectsobj = null;
	public static WebDriver driver = null;
	final static Logger logger = Logger.getLogger(IMDBTopRatedMovies.class);
	public List<Movies> allMovies = new ArrayList<Movies>();
	ConfigFileReader configFileReader;
	int counter = 1;

	@Given("^user opens the \"([^\"]*)\" browser$")
	public void OpenBrowser(String BrowserName) {
		//Read the configuration file.
		configFileReader = new ConfigFileReader();
		
		if (BrowserName.equalsIgnoreCase(Constants.FIREFOX)) {
			baseclass.driver = new FirefoxDriver();
		}

		if (BrowserName.equalsIgnoreCase(Constants.CHROME)) {
			//System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\chromedriver.exe");
			File file = new File(configFileReader.getChromeDriverPath());
			System.setProperty(Constants.CHROME_WEB_DRIVER, file.getAbsolutePath());
			baseclass.driver = new ChromeDriver();
		}
	}

	@And("^user enters the url \"(.*)\"$")
	public void Launchapplication(String URL) throws IOException {
		baseclass.driver.get(URL);
		baseclass.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		baseclass.wait = new WebDriverWait(baseclass.driver, 30);
		baseclass.driver.manage().window().maximize();
		String message = "Step:" + (counter++) + " PASSED: user enter the url as " + URL;
		logger.info(message);
		HTMLReportGenerator.StepDetails(Constants.PASS, "Launchapplication", message,
				TakeScreenShot.TakeScreenShot(Config.getImageFilePath(), baseclass.driver));
	}

	@Then("^user lands on the IMDB Home page$")
	public void HomePage() throws IOException {
		try {
			Thread.sleep(1000);
			IMDBPageObjectsobj = new IMDBPageObjects(baseclass.driver);
			String message = "Step:" + (counter++) + " PASSED- user is on the IMDB Home Page";
			logger.info(message);
			HTMLReportGenerator.StepDetails(Constants.PASS, "HomePage", message,
					TakeScreenShot.TakeScreenShot(Config.getImageFilePath(), baseclass.driver));
		} catch (Exception ex) {
			String message = "Step:" + (counter++)
					+ " FAILED- user is NOT on the application login page\nException Details: "
					+ ex.getLocalizedMessage();
			logger.info(message);
			HTMLReportGenerator.StepDetails(Constants.FAIL, "HomePage", message,
					TakeScreenShot.TakeScreenShot(Config.getImageFilePath(), baseclass.driver));
			Assert.assertTrue(false);
		}
	}

	@And("^user clicks on Top Rated Indian Movies link$")
	public void IndianRatedMovieslink() throws IOException {
		try {
			Thread.sleep(1000);
			IMDBPageObjectsobj.indian_rated_movies.click();
			String message = "Step:" + (counter++) + " PASSED- user clicks on IndianRatedMovies";
			logger.info(message);
			HTMLReportGenerator.StepDetails(Constants.PASS, "IndianRatedMovies", message,
					TakeScreenShot.TakeScreenShot(Config.getImageFilePath(), baseclass.driver));
		} catch (Exception e) {
			String message = "Step:" + (counter++)
					+ "FAILED- user is failed to click on IndianRatedMovies link\nException Details:"
					+ e.getLocalizedMessage();
			logger.info(message);
			HTMLReportGenerator.StepDetails(Constants.FAIL, "IndianRatedMovies", message,
					TakeScreenShot.TakeScreenShot(Config.getImageFilePath(), baseclass.driver));
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Then("^user lands on the Top Rated Indian Movies page$")
	public void TopRatedIndianMoviespage() throws IOException {
		try {
			Thread.sleep(1000);
			IMDBPageObjectsobj.india_spotlight.isDisplayed();
			String message = "Step:" + (counter++) + " PASSED- user clicks on IndianRatedMovies";
			logger.info(message);
			HTMLReportGenerator.StepDetails(Constants.PASS, "TopRatedIndianMoviespage", message,
					TakeScreenShot.TakeScreenShot(Config.getImageFilePath(), baseclass.driver));
		} catch (Exception e) {
			String message = "Step:" + (counter++)
					+ "FAILED- user is failed to click on TopRatedIndianMoviespage link\nException Details:"
					+ e.getLocalizedMessage();
			logger.info(message);
			HTMLReportGenerator.StepDetails(Constants.FAIL, "TopRatedIndianMoviespage", message,
					TakeScreenShot.TakeScreenShot(Config.getImageFilePath(), baseclass.driver));
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@And("^user reads the Data from the webpage$")
	public void ReadDataFromTable() throws InterruptedException {
		Thread.sleep(1000);
		int total_rows = IMDBPageObjectsobj.Top_Rated_Indian_Movies_table_rows.size();
		int column_per_rows = IMDBPageObjectsobj.Top_Rated_Indian_Movies_table_column.size();
		String test = ".//table[@class=" + "''" + "chart full-width" + "''" + "]/tbody/tr[{0}]/td[{1}]";

		for (int i = 1; i <= total_rows; i++) {
			Movies movie = new Movies();
			for (int j = 1; j < column_per_rows; j++) {
				MessageFormat mf = new MessageFormat(test);
				String Path = mf.format(new Object[] { i, j });
				WebElement data = baseclass.driver.findElement(By.xpath(Path));

				if (data.getText() != null && data.getText().trim().length() > 0) {
					if (j == 2) {
						movie.rank_Title = data.getText();
					}
					if (j == 3) {
						movie.rating = data.getText();
					}
				}
			}
			allMovies.add(movie);
		}
	}

	@And("^Data inserts into the SQLite Database$")
	public void InstertData() {

		if (allMovies != null && allMovies.size() > 0) {
			DatabaseListner.insert(allMovies);
		}
	}

	@And("^Html page gets open$")
	public void OpenHtmlPage() throws IOException {
		String workingDir = System.getProperty("user.dir");
		File htmlTemplateFile = new File(workingDir + "\\src\\test\\java\\HTMLView\\Movies.html");
		String htmlString = FileUtils.readFileToString(htmlTemplateFile);
		String htmlTable = Helper.BuildHTML(allMovies);
		htmlString = htmlString.replace("$table", htmlTable);
		File newHtmlFile = new File(workingDir + "\\src\\test\\java\\HTMLView\\Movies1.html");
		FileUtils.writeStringToFile(newHtmlFile, htmlString);
		baseclass.driver.get(workingDir + "\\src\\test\\java\\HTMLView\\Movies1.html");
	}

	@Before()
	public void BeforeEveryScenario(Scenario scenario) throws UnknownHostException {
		counter = 1;
		if (Config.listofsuite == null) {
			Config.listofsuite = scenario.getSourceTagNames();
			HTMLReportGenerator.TestSuiteStart("E:\\smoke.html", "Test");
		}
		HTMLReportGenerator.TestSuiteStart("E:\\smoke.html", "Test1");
		HTMLReportGenerator.TestCaseStart(scenario.getName(), scenario.getLines().toString());
		System.out.println("new scenario is started " + scenario.getName());
	}

	@After()
	public void AfterEveryScenario(Scenario scenario) {
		counter = 0;
		baseclass.driver.close();
		HTMLReportGenerator.TestCaseEnd();
		HTMLReportGenerator.TestSuiteEnd();
	}
}
