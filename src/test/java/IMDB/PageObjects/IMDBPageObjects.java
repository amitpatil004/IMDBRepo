package IMDB.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class IMDBPageObjects {

	WebDriver localdriverobject;

	public IMDBPageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.localdriverobject = driver;
		System.out.println("INSIDE LoginPage CONSTRUCTOR$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("driver is: " + driver);

	}

	@FindBy(how = How.XPATH, using = "//a[@id='nblogin']")
	public WebElement email_Address_Text_Box;

	@FindBy(how = How.XPATH, using = "//a[text()=' Top Rated Indian Movies ']")
	public WebElement indian_rated_movies;

	@FindBy(how = How.XPATH, using = "//a[text()='Top Rated Indian Movies']")
	public WebElement india_spotlight;

	@FindBy(how = How.XPATH, using = ".//table[@class='chart full-width']/tbody/tr")
	public List<WebElement> Top_Rated_Indian_Movies_table_rows;

	@FindBy(how = How.XPATH, using = ".//table[@class='chart full-width']/tbody/tr[1]/td")
	public List<WebElement> Top_Rated_Indian_Movies_table_column;

	@FindBy(how = How.XPATH, using = ".//table[@class='chart full-width']/tbody/tr{0}]/td{1}")
	public String Top_Rated_Indian_Movies_table_data;

}
