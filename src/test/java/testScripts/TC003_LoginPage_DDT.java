package testScripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;
import utilities.DataProviders;

public class TC003_LoginPage_DDT extends BaseTest {

	@Test(groups = "Master", dataProvider = "loginData", dataProviderClass = DataProviders.class)
	public void verify_Login_DDT(String email, String password, String exp) {

		try {
			logger.info("TC003_LoginPage_DDT");
			HomePage homePage = new HomePage(driver);
			logger.info("Clicking my account");
			homePage.clickMyAccount();
			logger.info("clicking login link");
			homePage.clickLogin();

			LoginPage loginPage = new LoginPage(driver);
			logger.info("Entering username");
			loginPage.enterUsername(email);
			logger.info("Entering password");
			loginPage.enterPassword(password);
			logger.info("Clicking Login");
			loginPage.clickLogin();

			MyAccountPage accountPage = new MyAccountPage(driver);

			if (exp.equalsIgnoreCase("valid")) {
				if (accountPage.isAccountPageExists()==true) {
					
					accountPage.clickLogout();
					Assert.assertTrue(true);

				} else {
					
					Assert.assertTrue(false);
				}
				
				
			} if (exp.equalsIgnoreCase("invalid")) {
				if (accountPage.isAccountPageExists()==true) {
					
					accountPage.clickLogout();
					Assert.assertTrue(false);
					
				} 
				else {
					Assert.assertTrue(true);
				}
			} 

		} catch (Exception e) {
			Assert.fail();
		}


		logger.info("TC003_LoginPage_DDT Finished!...");

	}

}
