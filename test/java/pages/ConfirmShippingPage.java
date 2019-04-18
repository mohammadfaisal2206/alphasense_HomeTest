package pages;

import org.openqa.selenium.WebDriver;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import utility.UserInformation;

public class ConfirmShippingPage extends LoadableComponent<ConfirmShippingPage>{

	private WebDriver driver;
	@SuppressWarnings("unused")
	private LoadableComponent<ConfirmAddressesPage> confirmAddressPage;
	UserInformation userInformation = UserInformation.getInstance();
	
	public ConfirmShippingPage(WebDriver driver, LoadableComponent<ConfirmAddressesPage> confirmAddressPage) {
		this.driver = driver;
		this.confirmAddressPage = confirmAddressPage;
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void load() {
	}

	@Override
	protected void isLoaded() throws Error {
		assertTrue(shippingOptionElement.isDisplayed(), "Not on Confirm shipping page");
	}
	
	@FindBy(css = ".delivery_option_radio .checked")
	public WebElement shippingOptionElement;
	
	@FindBy(css = "#cgv")
	public WebElement termsOfServiceCheckboxElement;
	
	@FindBy(name = "processCarrier")
	public WebElement processCarrier;
	
	public boolean isDefaultShippingSelected() {
		return shippingOptionElement.isSelected();
	}

	public void selectDefaultShipping() {
		shippingOptionElement.click();
	}
	
	public void agreeTermsOfService() {
		if(!termsOfServiceCheckboxElement.isSelected())
			termsOfServiceCheckboxElement.click();
	}

	public ConfirmPaymentPage next() {
		processCarrier.click();
		return new ConfirmPaymentPage(driver, this);
	}
}
