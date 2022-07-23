package SHAFT.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;

import io.qameta.allure.Step;

public class SignUpPage {

	WebDriver browser;

	// constructor
	public SignUpPage(WebDriver browser) {
		this.browser = browser;
	}

	// Element Locators
	By firstName = By.id("firstname");
	By lastName = By.id("lastname");
	By emailAddress = By.id("email_address");
	By password = By.id("password");
	By confirmationPassword = By.id("confirmation");
	By signupNews = By.id("is_subscribed");
	By registerButton = By.xpath("//button[@title='Register']");
	By hiMessage=By.xpath("//li[@class='success-msg']//li/span"); 

	// Actions
	@Step("Fill all the registration data")
	public void fillRegistrationData(String firstname, String lastname, String email, int randomInt, String Password) {
		new ElementActions(browser)
		.type(firstName,firstname)
		.type(lastName,lastname)
		.type(emailAddress, email + randomInt + "@gmail.com")
		.type(password, Password)
		.type(confirmationPassword, Password)
		.click(signupNews)
		.click(registerButton);
	}
	@Step("get Hi Message Text")
	public String getHiMessageText() {
		return ElementActions.getText(browser,  hiMessage);
	}
}
