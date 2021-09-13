package nopCommerce;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGenerator;
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;

public class Test_02_Login extends AbstractTest {
	WebDriver driver;
	HomePageObjects homePage;
	LoginPageObjects loginPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.NOPCOMMERCE_URL);
		homePage = PageGenerator.getHomePage(driver);
		homePage.clickDynamicHeaderLink(driver, "Log in");
		loginPage = PageGenerator.getLoginPage(driver);
		verifyEquals(loginPage.getPageTitleText(), "Welcome, Please Sign In!");
	}

	@Test
	public void TC_01_Empty_Data() {
		loginPage.clickLoginButton();
		verifyEquals(loginPage.getEmailErrorText(), "Please enter your email");
	}

	@Test
	public void TC_02_Invalid_Email() {
		loginPage.inputToLoginTextboxByLabel("Email:", "123");
		loginPage.clickLoginButton();
		verifyEquals(loginPage.getEmailErrorText(), "Wrong email");
	}

	@Test
	public void TC_03_Not_Exist_Email() {
		loginPage.inputToLoginTextboxByLabel("Email:", "abc@xyz.com");
		loginPage.clickLoginButton();
		verifyEquals(loginPage.getLoginErrorText(), "Login was unsuccessful. Please correct the errors and try again."
				+ "\n" + "No customer account found");
	}

	@Test
	public void TC_04_Empty_Password() {
		loginPage.inputToLoginTextboxByLabel("Email:", GlobalConstants.NOPCOMMERCE_ACCOUNT);
		loginPage.clickLoginButton();
		verifyEquals(loginPage.getLoginErrorText(), "Login was unsuccessful. Please correct the errors and try again."
				+ "\n" + "The credentials provided are incorrect");
	}

	@Test
	public void TC_05_Wrong_Password() {
		loginPage.inputToLoginTextboxByLabel("Email:", GlobalConstants.NOPCOMMERCE_ACCOUNT);
		loginPage.inputToLoginTextboxByLabel("Password:", "123456789");
		loginPage.clickLoginButton();
		verifyEquals(loginPage.getLoginErrorText(), "Login was unsuccessful. Please correct the errors and try again."
				+ "\n" + "The credentials provided are incorrect");
	}

	@Test
	public void TC_06_Register_Successfully() {
		loginPage.inputToLoginTextboxByLabel("Email:", GlobalConstants.NOPCOMMERCE_ACCOUNT);
		loginPage.inputToLoginTextboxByLabel("Password:", GlobalConstants.NOPCOMMERCE_PASSWORD);
		loginPage.clickLoginButton();
		homePage = PageGenerator.getHomePage(driver);
		homePage.isMyAccountLinkDisplayedOnHeader();
	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}