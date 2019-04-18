package utility;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

public class TestContext {

	public static ITestContext seTestContext(ITestContext iTestContext, WebDriver driver) {
		iTestContext.setAttribute("driver", driver);
		return iTestContext;
	}
}
