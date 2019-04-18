package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import static org.testng.Assert.assertTrue;

import utility.UserInformation;

public class ConfirmAddressesPage extends LoadableComponent<ConfirmAddressesPage>{

	private WebDriver driver;
	@SuppressWarnings("unused")
	private LoadableComponent<OrderConfirmationPage> orderConfirmationPage;
	UserInformation userInformation = UserInformation.getInstance();
	
	public ConfirmAddressesPage(WebDriver driver, LoadableComponent<OrderConfirmationPage> orderConfirmationPage) {
		this.driver = driver;
		this.orderConfirmationPage = orderConfirmationPage;
		PageFactory.initElements(driver, this);
	}

	@FindBy (css = ".step_current")
	public WebElement selectedSection;
	
	@FindBy(css = "select#id_address_delivery")
	public WebElement selectAddressElement;
	
	@FindBy(name = "processAddress")
	public WebElement processAddress;
	
	@Override
	protected void load() {
	}

	@Override
	protected void isLoaded() throws Error {
		assertTrue(selectedSection.getAttribute("innerText").trim().contains("Address"), "Not on correct page");
	}
	
	public boolean isCorrectEmailSelected() {
		Select select = new Select(selectAddressElement);
		return select.getFirstSelectedOption().getAttribute("innerText").trim().equalsIgnoreCase(userInformation.getAlias());
	}
	
	public ConfirmShippingPage processAddress() {
		processAddress.click();
		return new ConfirmShippingPage(driver, this);
	}

}
