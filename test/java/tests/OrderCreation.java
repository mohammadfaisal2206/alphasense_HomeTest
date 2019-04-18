package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.ConfirmAddressesPage;
import pages.ConfirmPaymentPage;
import pages.ConfirmShippingPage;
import pages.FinalConfirmationOnOrderPage;
import pages.HomePage;
import pages.OrderCompletionPage;
import pages.OrderConfirmationPage;
import pages.ProductConfirmationPage;
import pages.SearchResultsPage;
import pages.SignInPage;
import pages.UserAccountPage;
import utility.UserInformation;
import static models.DressCategory.PRINTED_SUMMER_DRESS;
import static org.testng.Assert.assertTrue;

public class OrderCreation extends Base{

	private HomePage homePage;
	private SignInPage signInPage;
	private UserAccountPage userAccountPage;
	private SearchResultsPage searchResultsPage;
	private ProductConfirmationPage productConfirmationPage;
	private OrderConfirmationPage orderConfirmationPage;
	private ConfirmAddressesPage confirmAddressesPage;
	private ConfirmShippingPage confirmShippingPage;
	private ConfirmPaymentPage confirmPaymentPage;
	private FinalConfirmationOnOrderPage finalConfirmationOnOrderPage;
	private OrderCompletionPage orderCompletionPage;
	private static UserInformation userInformation = UserInformation.getInstance();
	
	@BeforeClass(description = "Method Level Setup!")
    public void methodLevelSetup() {
        	homePage = new HomePage(driver).get();
    }
	
	@Test
	public void purchaseProduct() throws InterruptedException {
		
		signInPage = homePage.clickSignInLink().get();
		
		userAccountPage = signInPage.signInWithRegisteredAccount(userInformation.getEmail(), userInformation.getPassword()).get();
		
		searchResultsPage = userAccountPage.searchFor(PRINTED_SUMMER_DRESS).get();
		
		assertTrue((searchResultsPage.getResultCount() > 0), "No Results found");
		
		productConfirmationPage = searchResultsPage.addToCart(PRINTED_SUMMER_DRESS).get();
		
		assertTrue(productConfirmationPage.isCorrectProductInformationDisplayedOnConfirmationDialogue(PRINTED_SUMMER_DRESS), "Product info not correct");
		
		orderConfirmationPage = productConfirmationPage.proceedToCheckout().get();
		
		assertTrue(orderConfirmationPage.isProductStillInStock(), "product out of stock");
		
		assertTrue(orderConfirmationPage.isCorrectProductQuantityAdded(), "wrong qty added");
		
		assertTrue(orderConfirmationPage.areDeliveryDetailsCorrect(), "wrong address details");
		
		confirmAddressesPage = orderConfirmationPage.checkout().get();
		
		assertTrue(confirmAddressesPage.isCorrectEmailSelected(), "Wrong email selected");
		
		confirmShippingPage = confirmAddressesPage.processAddress().get();
		
		if(!confirmShippingPage.isDefaultShippingSelected())
			confirmShippingPage.selectDefaultShipping();
		
		confirmShippingPage.agreeTermsOfService();
		
		confirmPaymentPage = confirmShippingPage.next().get();
		
		finalConfirmationOnOrderPage = confirmPaymentPage.payByCheck().get();
		
		orderCompletionPage = finalConfirmationOnOrderPage.submitOrder().get();
		
		assertTrue(orderCompletionPage.isOrderSuccessfullyPlaced(), "Order completion failed");
	}
	
}
