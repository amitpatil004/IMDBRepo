package IMDB.Config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GeckoConfiguration {

	private static WebDriver driver = null;

	public static WebDriver Geckotest(String DriverType) {

		if (DriverType == "Chrome") {
			System.setProperty("webdriver.chrome.driver", "C:\\TestLibs\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", "C:/TestLibs/16/geckodriver.exe");

			if (driver == null)
				driver = new ChromeDriver();
		} else if (DriverType == "Firefox") {
			System.setProperty("webdriver.gecko.driver", "C:/TestLibs/16/geckodriver.exe");
			if (driver == null)
				driver = new FirefoxDriver();

		}
		return driver;
	}
}
