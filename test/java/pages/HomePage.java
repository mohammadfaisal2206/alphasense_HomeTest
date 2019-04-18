package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertTrue;

public class HomePage extends LoadableComponent<HomePage> {

	private WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void load() {
	}

	@Override
	protected void isLoaded() throws Error {
			assertTrue(driver.getCurrentUrl().contains("automation"), "Not on correct page");
	}
	
	@FindBy (name = "search_query")
	public WebElement searchBoxElement;
	
	@FindBy (className = "login")
	public WebElement signInLink;
	
	public Boolean isSearchBoxVisible() {
		return searchBoxElement.isDisplayed();
	}
	
	public SignInPage clickSignInLink() {
		signInLink.click();
		return new SignInPage(this.driver, this);
	}
	

}
