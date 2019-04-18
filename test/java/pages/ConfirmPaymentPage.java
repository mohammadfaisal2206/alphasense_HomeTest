package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class ConfirmPaymentPage extends LoadableComponent<ConfirmPaymentPage>{

	private WebDriver driver;
	@SuppressWarnings("unused")
	private LoadableComponent<ConfirmShippingPage> confirmShippingPage;
	
	@Override
	protected void load() {
	}

	@Override
	protected void isLoaded() throws Error {
		System.out.println("On confirm Payment page");		
	}
	
	@FindBy(css = ".cheque")
	public WebElement payByChequeElement;
	
	public ConfirmPaymentPage(WebDriver driver, LoadableComponent<ConfirmShippingPage> confirmShippingPage) {
		this.driver = driver;
		this.confirmShippingPage = confirmShippingPage;
		PageFactory.initElements(driver, this);
	}
	
	public FinalConfirmationOnOrderPage payByCheck() {
		payByChequeElement.click();
		return new FinalConfirmationOnOrderPage(driver, this);
	}

}
