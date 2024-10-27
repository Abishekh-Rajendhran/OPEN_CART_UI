package testScripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseTest;

public class TC001_AccountRegistrationPage extends BaseTest{


	@Test(groups = {"Sanity","Master"})
	public void verify_account_registration() {

		try {

			logger.info("TC001_AccountRegistrationPage Started!.....");
			HomePage homePage = new HomePage(driver);

			logger.info("Clicking My Account.....");
			homePage.clickMyAccount();

			logger.info("Clicking Register......");
			homePage.clickRegister();



			RegisterPage registerPage = new RegisterPage(driver);

			registerPage.enterFirstName(randomString().toUpperCase());
			logger.info("Entering FirstName.....");
			registerPage.enterLastName(randomString().toUpperCase());
			logger.info("Entering LastName.....");
			registerPage.enterMailId(randomString()+"@gmail.com");
			logger.info("Entering MailId.....");
			registerPage.enterTelephone(randomNumeric());
			logger.info("Entering telephone number.....");

			String randompassword = randomAlphaNumeric();

			registerPage.enterPassword(randompassword);
			logger.info("Entering password.....");
			registerPage.enterConfirmPassword(randompassword);
			logger.info("ReEnterning the password.....");
			registerPage.clickRadioButton();
			logger.info("Clicking radioButton.....");
			registerPage.clickPrivacyPolicy();
			logger.info("Privacy Policy clicking");
			registerPage.clickContinue();
			logger.info("Clicking Continue");
			
			if (registerPage.captureConfirmationMessage().equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			}
			else {
				logger.error("TC001_AccountRegistrationPage Failed!....");
				logger.debug("debug logs...");
				Assert.assertTrue(false);
			}
		}
		
		catch (Exception e) {
			
			Assert.fail();
	
		}
		

	}
}

