package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.AccountCreationPage;
import pages.HomePage;
import pages.SignInPage;
import pages.UserAccountPage;
import utility.UserInformation;

import static org.testng.Assert.assertEquals;
public class CreateUserAccount extends Base{

	private HomePage homePage;
	private static UserInformation userInformation = UserInformation.getInstance();
	
	private SignInPage signInPage;
	private AccountCreationPage accountCreationPage;
	private UserAccountPage userAccountPage;
	
	@BeforeClass(description = "Method Level Setup!")
    public void methodLevelSetup() {
        homePage = new HomePage(driver).get();
    }
	 
	@Test
	public void createNewUserAccount() {
			signInPage = homePage.clickSignInLink();
			signInPage.enterEmail(userInformation.getEmail());
			accountCreationPage = signInPage.submit();
			assertEquals(accountCreationPage.getEmailContent(), userInformation.getEmail(),  "Email not populated correctly");
			userAccountPage = accountCreationPage.fillForm(userInformation);
			userAccountPage.signOut();
	}
	
	@Test
	public void checkThatUserCanLoginSuccessfullyAfterAccountCreation() {
		signInPage = homePage.clickSignInLink();
		userAccountPage = signInPage.signInWithRegisteredAccount(userInformation.getEmail(), userInformation.getPassword());
		assertEquals(userAccountPage.userAccountInfo.getText(), userInformation.getFirstName() + " " + userInformation.getLastName(), "Could not login");
		userAccountPage.signOut();
	}
	
}
