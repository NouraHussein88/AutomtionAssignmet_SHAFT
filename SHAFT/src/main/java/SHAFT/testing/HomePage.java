package SHAFT.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.amazonaws.auth.policy.Action;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.element.ElementActions;

import io.qameta.allure.Step;

public class HomePage {
	WebDriver browser;
	String url = "http://magento-demo.lexiconn.com/";

	// constructor
	public HomePage(WebDriver browser) {
		this.browser = browser;
	}

	// Element Locators
	private By accountLink = By.className("skip-account");
	private By loginLink = By.linkText("Log In");
	private By registerLink = By.linkText("Register");
	private By logOut=By.xpath("//a[contains(.,'Log Out')]");

	// Actions
	public void navigateToURL() {
		System.out.println("Navigate to [" + url + "]...");
		BrowserActions.navigateToURL(browser, url);
	}
	@Step("Go to the register page.")
	public void navigateToSignUpPage() {
		ElementActions.click(browser, accountLink);
		//ElementActions.click(browser, registerLink);

		browser.findElement(registerLink).click();
	}
	@Step("Go To Login Page.")
	public void navigateToLogInPage() {
		browser.findElement(accountLink).click();
		//ElementActions.hover(browser, accountLink);
		browser.findElement(loginLink).click();
	}

}
