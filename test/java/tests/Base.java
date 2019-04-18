package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import utility.TestContext;
import utility.WebDriverFactory;

import static org.testng.Assert.fail;

public class Base extends WebDriverFactory{

	public WebDriver driver;
	@SuppressWarnings("unused")
	private ITestContext testContext;
	
	@Parameters({"browser", "url"})
	@BeforeClass (alwaysRun = true, description = "One time initialization code")
	public void setup(String browser, String url, ITestContext testContext){
		try {
			driver = WebDriverFactory.createWebDriver(browser);
			testContext = TestContext.seTestContext(testContext, driver);
			driver.navigate().to(url);
			driver.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@AfterClass (alwaysRun = true, description = "Method to take care of final cleanup activities, liking shutting down browsers or closing any open streams")
	public void tearDown(){
		try {
			if(driver != null)
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
