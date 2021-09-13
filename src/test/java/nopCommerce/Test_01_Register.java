package nopCommerce;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGenerator;
import pageObjects.HomePageObjects;
import pageObjects.RegisterPageObjects;

public class Test_01_Register extends AbstractTest {
	WebDriver driver;
	HomePageObjects homePage;
	RegisterPageObjects registerPage;

	String registerPageUrl;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.NOPCOMMERCE_URL);
		homePage = PageGenerator.getHomePage(driver);
		homePage.clickDynamicHeaderLink(driver, "Register");
		registerPage = PageGenerator.getRegisterPage(driver);
		verifyEquals(registerPage.getPageTitleText(), "Register");
		registerPageUrl = registerPage.getCurrentUrl(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
		registerPage.openUrl(driver, registerPageUrl);
		verifyEquals(registerPage.getPageTitleText(), "Register");
	}

	@Test
	public void TC_01_Empty_Data() {
		registerPage.clickRegisterSubmitButton();
		verifyEquals(registerPage.getDynamicErrorText("FirstName"), "First name is required.");
		verifyEquals(registerPage.getDynamicErrorText("LastName"), "Last name is required.");
		verifyEquals(registerPage.getDynamicErrorText("Email"), "Email is required.");
		verifyEquals(registerPage.getDynamicErrorText("Password"), "Password is required.");
		verifyEquals(registerPage.getDynamicErrorText("ConfirmPassword"), "Password is required.");
	}

	@Test
	public void TC_02_Invalid_Email() {
		registerPage.inputToDynamicRegisterTextboxByLabel("Email:", "123");
		registerPage.clickRegisterSubmitButton();
		verifyEquals(registerPage.getDynamicErrorText("Email"), "Wrong email");
	}

	@Test
	public void TC_03_Already_Exist_Email() {
		registerPage.inputToDynamicRegisterTextboxByLabel("First name:", "Trung");
		registerPage.inputToDynamicRegisterTextboxByLabel("Last name:", "Dao");
		registerPage.inputToDynamicRegisterTextboxByLabel("Email:", GlobalConstants.NOPCOMMERCE_ACCOUNT);
		registerPage.inputToDynamicRegisterTextboxByLabel("Password:", GlobalConstants.NOPCOMMERCE_PASSWORD);
		registerPage.inputToDynamicRegisterTextboxByLabel("Confirm password:", GlobalConstants.NOPCOMMERCE_PASSWORD);
		registerPage.clickRegisterSubmitButton();
		verifyEquals(registerPage.getValidationErrorText(), "The specified email already exists");
	}

	@Test
	public void TC_04_Password_Less_Than_6() {
		registerPage.inputToDynamicRegisterTextboxByLabel("Password:", "123");
		registerPage.clickRegisterSubmitButton();
		verifyEquals(registerPage.getDynamicErrorText("Password"),
				"Password must meet the following rules:" + "\n" + "must have at least 6 characters");
	}

	@Test
	public void TC_05_Confirm_Password_Not_Match() {
		registerPage.inputToDynamicRegisterTextboxByLabel("Password:", "123456");
		registerPage.inputToDynamicRegisterTextboxByLabel("Confirm password:", "1234567");
		registerPage.clickRegisterSubmitButton();
		verifyEquals(registerPage.getDynamicErrorText("ConfirmPassword"),
				"The password and confirmation password do not match.");
	}

	@Test
	public void TC_06_Register_Successfully() {
		registerPage.inputToDynamicRegisterTextboxByLabel("First name:", "Trung");
		registerPage.inputToDynamicRegisterTextboxByLabel("Last name:", "Dao");
		registerPage.inputToDynamicRegisterTextboxByLabel("Email:", "trung.dao" + randomNumber() + "@gmail.com");
		registerPage.inputToDynamicRegisterTextboxByLabel("Password:", "123456");
		registerPage.inputToDynamicRegisterTextboxByLabel("Confirm password:", "123456");
		registerPage.clickRegisterSubmitButton();
		verifyEquals(registerPage.getResultText(), "Your registration completed");
	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}