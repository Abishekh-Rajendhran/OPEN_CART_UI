package testScripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;

public class TC002_LoginPage extends BaseTest {

	@Test(groups = {"Sanity", "Master","Regression"})
	public void verifyLogin() {
		try {
			logger.info("TC002_LoginPage Started!...");
			HomePage homePage = new HomePage(driver);
			logger.info("Clicking my account");
			homePage.clickMyAccount();
			logger.info("clicking login link");
			homePage.clickLogin();



			LoginPage loginPage = new LoginPage(driver);
			logger.info("Entering username");
			loginPage.enterUsername(properties.getProperty("username"));
			logger.info("Entering password");
			loginPage.enterPassword(properties.getProperty("password"));
			logger.info("Clicking Login"); loginPage.clickLogin();



			MyAccountPage accountPage = new MyAccountPage(driver);
			Assert.assertEquals(true, accountPage.isAccountPageExists(), "Login Passed!");

		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("TC002_LoginPage Finished!...");

	}

}
