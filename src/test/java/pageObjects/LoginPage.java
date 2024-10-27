package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	
	}
	
	
	@FindBy(id = "input-email")
	WebElement txtUserName;
	
	@FindBy(id = "input-password")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement clkLogin;
	
	public void enterUsername(String userName) {
		txtUserName.sendKeys(userName);
	}
	
	public void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clickLogin() {
		clkLogin.click();
	}
	
	
	

}
