package SHAFT.testing;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.shaft.driver.DriverFactory;
import com.shaft.tools.io.ExcelFileManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
	WebDriver browser;
	ExcelFileManager excelFileTestData;
	int randomInt;
	Random randomGenerator;
	SignUpPage signUpPage;
	LoginPage loginpage;
	MyAccountPage myAccountPage;
	HomePage homePage;
	String reportPath;
	ExtentReports extentreport;

	@Test
	public void Registration() {
		homePage.navigateToSignUpPage();
		signUpPage.fillRegistrationData(excelFileTestData.getCellData("firstName"),
				excelFileTestData.getCellData("lastName"), excelFileTestData.getCellData("email"), randomInt,
				excelFileTestData.getCellData("password"));
		assertEquals(signUpPage.getHiMessageText(), excelFileTestData.getCellData("expectedMessage"));
		System.out.println("Asserting that Customer has Registered successfully.");
		myAccountPage.logout();
	}

	@Test(dependsOnMethods = "Registration")
	public void AddItemToShoppingcart() throws InterruptedException {

		homePage.navigateToLogInPage();
		loginpage.loginToMyAccount(excelFileTestData.getCellData("email"), randomInt,
				excelFileTestData.getCellData("password"));
		myAccountPage.SelecyRequirdSectionAndCategory(
				Integer.parseInt(excelFileTestData.getCellData("requiredSection")),
				excelFileTestData.getCellData("requiredcatagory"));
		myAccountPage.ClickOnViewDetailsForRequiredItem(excelFileTestData.getCellData("itemName"));
		myAccountPage.AddRequiredItemToShoppingCart(excelFileTestData.getCellData("itemColor"),
				excelFileTestData.getCellData("itemsize"));

		assertTrue(myAccountPage.CheckTheGrandTotal(Integer.parseInt(excelFileTestData.getCellData("customerBudget"))));
		System.out.println("Asserting that Grand Total is less than you budget of$"
				+ excelFileTestData.getCellData("customerBudget"));
	}

	@BeforeClass
	public void beforeClase() {
		WebDriverManager.chromedriver().setup();
		browser = new DriverFactory().getDriver();
		excelFileTestData = new ExcelFileManager(System.getProperty("testDataFolderPath") + "ExcelTestData.xlsx");
		randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(1000);
		homePage = new HomePage(browser);
		signUpPage = new SignUpPage(browser);
		loginpage = new LoginPage(browser);
		myAccountPage = new MyAccountPage(browser);
	}

	@BeforeMethod
	public void beforMethod() {
		homePage.navigateToURL();
	}

	@AfterClass
	public void afterClase() {
		// browser.quit();
	}

}
