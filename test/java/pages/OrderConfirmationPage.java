package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import utility.UserInformation;

import static org.testng.Assert.assertTrue;

import java.util.List;

public class OrderConfirmationPage extends LoadableComponent<OrderConfirmationPage>{

	private WebDriver driver;
	private LoadableComponent<ProductConfirmationPage> productConfirmationPage;
	UserInformation userInformation = UserInformation.getInstance();
	
	public OrderConfirmationPage(WebDriver driver, LoadableComponent<ProductConfirmationPage> productConfirmationPage) {
		this.driver = driver;
		this.productConfirmationPage = productConfirmationPage;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
	}

	@Override
	protected void isLoaded() throws Error {
		assertTrue(driver.getTitle().contains("Order"));
	}
	
	@FindBy (css = "td.cart_avail")
	public WebElement cartAvailButtonElement;
	
	@FindBy (css = ".cart_quantity_input")
	public WebElement cartQtyElement;
	
	@FindBys({
	@FindBy (css = ".order_delivery ul.first_item li")
	})
	public List<WebElement> deliveryAddressElements;
	
	@FindBy (css = ".standard-checkout")
	public WebElement checkoutButtonElement;
	
	public boolean isProductStillInStock() {
		return cartAvailButtonElement.getAttribute("innerText").trim().contains("In stock");
	}
	
	public boolean isCorrectProductQuantityAdded() {
		return cartQtyElement.getAttribute("value").equals("1");
	}
	
	public ConfirmAddressesPage checkout() {
		checkoutButtonElement.click();
		return new ConfirmAddressesPage(driver, this);
	}
	
	public boolean areDeliveryDetailsCorrect() {
		assertTrue(deliveryAddressElements.get(0).findElement(By.className("address_alias")).getAttribute("innerText").trim().equalsIgnoreCase("("+userInformation.getAlias()+")"), "Wrong alias");
		assertTrue(deliveryAddressElements.get(1).findElement(By.className("address_name")).getAttribute("innerText").trim().equals(userInformation.getFirstName()+" "+userInformation.getLastName()), "Wrong name");
		assertTrue(deliveryAddressElements.get(2).findElement(By.className("address_company")).getAttribute("innerText").trim().equals(userInformation.getCompany()), "Wrong company");
		assertTrue(deliveryAddressElements.get(3).findElement(By.className("address_address1")).getAttribute("innerText").trim().equals(userInformation.getAddress()), "Wrong address");
		assertTrue(deliveryAddressElements.get(5).getAttribute("innerText").trim().equals("United States"), "Wrong country");
		assertTrue(deliveryAddressElements.get(6).findElement(By.className("address_phone_mobile")).getAttribute("innerText").trim().equals(userInformation.getMobileNumber()), "Wrong phone");
		return true;
	}

}
