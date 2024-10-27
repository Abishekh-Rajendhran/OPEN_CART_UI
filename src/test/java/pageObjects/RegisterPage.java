package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{

	public RegisterPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(name = "firstname")
	WebElement txtfirstName;

	@FindBy(name = "lastname")
	WebElement txtlastName;

	@FindBy(name = "email")
	WebElement txtMailId;

	@FindBy(name = "telephone")
	WebElement txtTelephone;

	@FindBy(name = "password")
	WebElement txtPassword;

	@FindBy(name = "confirm")
	WebElement txtConfirmPassword;

	@FindBy(xpath = "//label[text()='Yes']//input")
	WebElement rdbtnSubscribeTrue;

	@FindBy(xpath = "//label[text()='No']//input")
	WebElement rdbtnSubscribeFalse;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkPrivacyPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	public void enterFirstName(String firstName) {
		txtfirstName.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		txtlastName.sendKeys(lastName);
	}

	public void enterMailId(String mailId) {
		txtMailId.sendKeys(mailId);
	}
	public void enterTelephone(String contactNumber) {
		txtTelephone.sendKeys(contactNumber);
	}
	public void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}
	public void enterConfirmPassword(String confirmpassword) {
		txtConfirmPassword.sendKeys(confirmpassword);
	}
	public void clickRadioButton() {
		rdbtnSubscribeTrue.click();
	}
	public void clickPrivacyPolicy() {
		chkPrivacyPolicy.click();
	}

	public void clickContinue() {
		btnContinue.click();
	}
	
	public String captureConfirmationMessage() {
		try {
			return msgConfirmation.getText();
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	

}
