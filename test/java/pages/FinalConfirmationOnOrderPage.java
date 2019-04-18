package pages;

import org.openqa.selenium.WebDriver;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class FinalConfirmationOnOrderPage extends LoadableComponent<FinalConfirmationOnOrderPage>{

	private WebDriver driver;
	@SuppressWarnings("unused")
	private LoadableComponent<ConfirmPaymentPage> confirmPaymentPage;
	
	@Override
	protected void load() {
	}

	@Override
	protected void isLoaded() throws Error {
		System.out.println("Is on FinalConfirmationOnOrderPage");
		assertTrue(submitButtonElement.isDisplayed(), "Submit button not displayed");
	}
	
	@FindBy(css = ".cart_navigation button")
	public WebElement submitButtonElement;
	
	public FinalConfirmationOnOrderPage(WebDriver driver, LoadableComponent<ConfirmPaymentPage> confirmPaymentPage) {
		this.driver = driver;
		this.confirmPaymentPage = confirmPaymentPage;
		PageFactory.initElements(driver, this);
	}
	
	public OrderCompletionPage submitOrder() {
		submitButtonElement.click();
		return new OrderCompletionPage(driver, this);
	}

}
