package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import com.aventstack.extentreports.ExtentReports;

import models.DressCategory;
import pages.HomePage;
import pages.SearchResultsPage;
import pages.SignInPage;
import pages.UserAccountPage;
import utility.ExtentManager;
import utility.UserInformation;
import static models.ProductCategory.DRESS;

public class SearchProductCategory extends Base{
	
	private HomePage homePage;
	private static UserInformation userInformation = UserInformation.getInstance();
	ExtentReports test = ExtentManager.getInstance();
	private SignInPage signInPage;
	private UserAccountPage userAccountPage;
	private SearchResultsPage searchResultsPage;
	
	@BeforeClass(description = "Method Level Setup!")
    public void methodLevelSetup() {
        	homePage = new HomePage(driver).get();
    }
	
	@Test
	public void searchProductCategory() {
			signInPage = homePage.clickSignInLink();
			userAccountPage = signInPage.signInWithRegisteredAccount(userInformation.getEmail(), userInformation.getPassword());
			searchResultsPage = userAccountPage.enterSearchText(DRESS.name());
			assertTrue(DressCategory.values().length > searchResultsPage.getResultCount(), "Wrong number of results shown");
	}
	
}
