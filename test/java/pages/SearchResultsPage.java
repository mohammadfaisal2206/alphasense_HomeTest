package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import models.DressCategory;
import models.ProductCategory;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends LoadableComponent<SearchResultsPage>{
	
	private WebDriver driver;
	private LoadableComponent<?> parent;
	
	public SearchResultsPage(WebDriver driver, LoadableComponent<?> parent) {
		this.driver = driver;
		this.parent = parent;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		
	}

	@Override
	protected void isLoaded() throws Error {
		assertTrue(driver.getTitle().contains("Search"), "Not on search page");
	}

	@FindBy (className = "heading-counter")
	public WebElement searchResultsCount;
	
	@FindBys({
		@FindBy (css = ".product_list.grid.row .product-container")
	})
	public List<WebElement> productElementsList;
	
	public int getResultCount() {
		return Integer.valueOf(searchResultsCount.getAttribute("innerText").trim().split(" results have been found.")[0]);
		
	}
	
	public boolean isProductWithNamePresent(DressCategory category) {
		return getProductElementWithName(category) != null;
	}
	
	public WebElement getProductElementWithName (DressCategory category) {
		WebElement prodList = null; 
		try {
			prodList =	productElementsList.stream().filter(e -> e.findElement(By.className("product-name")).getAttribute("innerText").trim().equals(category.getName())).findFirst().get();
			System.out.println("produList found");
		} catch (NoSuchElementException e) {
			// TODO: handle exception
		}
		return prodList;
	}

	public ProductConfirmationPage addToCart(DressCategory dress) throws InterruptedException {

		WebElement productElement = getProductElementWithName(dress);
		WebElement addToCartElement = productElement.findElement(By.className("ajax_add_to_cart_button"));
		Actions action = new Actions(driver);
		action.moveToElement(productElement).click(addToCartElement).build().perform();
		return new ProductConfirmationPage(driver, this).get();
	}
	
}
