package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//i[@class='fa fa-user']")
	WebElement myAccount;

	@FindBy(linkText = "Register")
	WebElement register;

	@FindBy(linkText = "Login")
	WebElement login;

	public void clickMyAccount() {
		myAccount.click();
		
//		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//		jsExecutor.executeScript("arguments[0].click()", myAccount);
//		
//		Actions actions = new Actions(driver);
//		actions.click(myAccount).build().perform();
		
	}

	public void clickRegister() {
     	register.click();
     	
//		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//		jsExecutor.executeScript("arguments[0].click()", myAccount);
		
//		Actions actions = new Actions(driver);
//		actions.click(register).build().perform();
	}
	
	public void clickLogin() {
     	login.click();
     	
//		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//		jsExecutor.executeScript("arguments[0].click()", myAccount);
		
//		Actions actions = new Actions(driver);
//		actions.click(register).build().perform();
	}
	
}
