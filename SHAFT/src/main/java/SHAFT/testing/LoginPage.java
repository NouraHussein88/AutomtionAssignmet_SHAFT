package SHAFT.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;

import io.qameta.allure.Step;

public class LoginPage {
	WebDriver browser;

	// constructor
	public LoginPage(WebDriver browser) {
		this.browser = browser;
	}

	// Element Locators
	By email = By.id("email");
	By password = By.id("pass");
	By loginButton = By.id("send2");

	// Actions
	@Step("UserLogin by with Data -->Email: [{Email}]+ [{randomInt}]@gmail.com,Password: [{Password}]")
	public void loginToMyAccount(String Email, int randomInt, String Password) {
		ElementActions.type(browser, email, Email + randomInt + "@gmail.com");
		ElementActions.type(browser, password, Password);
		ElementActions.click(browser, loginButton);
	}
}
