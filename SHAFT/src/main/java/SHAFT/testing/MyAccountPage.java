package SHAFT.testing;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;

import com.shaft.gui.element.ElementActions;

import io.qameta.allure.Step;

public class MyAccountPage {
	WebDriver browser;

	// constructor
	public MyAccountPage(WebDriver browser) {
		this.browser = browser;
	}

	// Element Locators
	private By sectionName(int requiredSection) {
		return By.xpath("//*[@class='nav-primary']/li["+requiredSection+"]/a");
	}

	private By categoryName(String requiredcatagory) {
		return By.xpath("//li[\" + requiredcatagory + \"]/ul/li");
	}

	private By colorLocator = By.id("attribute92");
	private By sizeLocator = By.id("attribute180");
	private By productsGrid = By.xpath("//ul[contains(@class,'products-grid')]/li/div[@class='product-info']/h2/a");
	private By viewDetailsButton = By.xpath("//*[@class='actions']/a");
	private By addToCart=By.cssSelector("button[title='Add to Cart']");
	private By accountLink = By.className("skip-account");
	private By logOut = By.xpath("//a[contains(.,'Log Out')]");

	// Actions
	@Step("Select Required Category []and Required Section[]")
	public void SelecyRequirdSectionAndCategory(int requiredSection, String requiredcatagory) {
		// Select Required Section
		ElementActions.hover(browser, sectionName(requiredSection));
		System.out.println("Selected Section is ["+ ElementActions.getText(browser,sectionName(requiredSection))+"]");
		// Select Required Category
		List<WebElement> allCategory = browser.findElements(categoryName(requiredcatagory));
		for (WebElement category : allCategory) {
			if (category.getText().trim().contains(requiredcatagory)) {
				System.out.println("Selected Category is [" + category.getText() + "]");
				category.click();
				break;
			}
		}
	}
@Step("Click On View Details button For Required Item")
	public void ClickOnViewDetailsForRequiredItem(String itemName) {
		// Select Required item
		List<WebElement> allitems = browser.findElements(productsGrid);
		for (WebElement item : allitems) {
			if (item.getText().contains(itemName)) {
				System.out.println("Selected Item is[" + item.getText() + "]");
				ElementActions.click(browser, RelativeLocator.with(viewDetailsButton).below(item));
				break;
			}
		}
	}
@Step("Add Required Item To Shopping Cart")
	public void AddRequiredItemToShoppingCart(String color, String size) {
		// Select item color
		Select selectColor = new Select(browser.findElement(colorLocator));
		selectColor.selectByVisibleText(color);
		System.out.println("Item color is[" + color + "]");

		// Select item size
		Select selectSize = new Select(browser.findElement(sizeLocator));
		selectSize.selectByVisibleText(size);
		System.out.println("Item size is[" + size + "]");

		// Add item to cart
		browser.findElement(addToCart).click();
		//ElementActions.click(browser, addToCart);
		System.out.println("Item added successfully to your shopping card.");
	}
@Step("Check TheGrand Total less than user budget")
	public boolean CheckTheGrandTotal(int customerBudget) {
		WebElement totalCard = browser
				.findElement(By.xpath("//*[@class='a-right'][contains(.,'Grand Total')]/following-sibling::td"));
		System.out.println("The grand total is [" + totalCard.getText() + "]");
		Integer totalAmmount = Integer
				.parseInt(totalCard.getText().trim().replace("$", " ").replace(".", " ").split(" ")[1]);
		if (totalAmmount < customerBudget)
			return true;
		else
			return false;
	}

	public void RemoveFromCard() {

	}
	@Step("Logout from my account.")
	public void logout() {
		
		browser.findElement(accountLink).click();
		browser.findElement(logOut).click();
	}
}
