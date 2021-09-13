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
import pageObjects.MyAccountPageObjects;
import pageObjects.ProductDetailPageObjects;

public class Test_03_MyAccount extends AbstractTest {
	WebDriver driver;
	HomePageObjects homePage;
	LoginPageObjects loginPage;
	MyAccountPageObjects myAccountPage;
	ProductDetailPageObjects productDetailPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.NOPCOMMERCE_URL);
		homePage = PageGenerator.getHomePage(driver);
		homePage.clickHeaderLinkText("Log in");
		loginPage = PageGenerator.getLoginPage(driver);
		loginPage.inputToLoginTextboxByLabel("Email:", GlobalConstants.NOPCOMMERCE_ACCOUNT);
		loginPage.inputToLoginTextboxByLabel("Password:", GlobalConstants.NOPCOMMERCE_PASSWORD);
		loginPage.clickLoginButton();
		homePage = PageGenerator.getHomePage(driver);
		homePage.isMyAccountLinkDisplayedOnHeader();
	}

	@Test
	public void TC_01_Customer_Info() {
		homePage.clickHeaderLinkText("My account");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		verifyEquals(myAccountPage.getPageTitleText(), "My account - Customer info");

		String updateGender = "Female";
		String updateFirstName = "Automation";
		String updateLastName = "FC";
		String updateDay = "1";
		String updateMonth = "January";
		String updateYear = "1999";
		String updateEmail = "automationfc.vn@gmail.com";
		String updateCompany = "Automation FC";

		myAccountPage.clickGenderRadioButtonByLabel(updateGender);
		myAccountPage.inputToTextboxByLabel("First name:", updateFirstName);
		myAccountPage.inputToTextboxByLabel("Last name:", updateLastName);
		myAccountPage.inputToTextboxByLabel("Email:", updateEmail);
		myAccountPage.inputToTextboxByLabel("Company name:", updateCompany);
		myAccountPage.selectDateOfBirthOptionByLabel("Day", updateDay);
		myAccountPage.selectDateOfBirthOptionByLabel("Month", updateMonth);
		myAccountPage.selectDateOfBirthOptionByLabel("Year", updateYear);

		myAccountPage.clickSubmitFormButton("Save");

		verifyEquals(myAccountPage.getCurrentGenderChecked(), updateGender);
		verifyEquals(myAccountPage.getInputTextValueByLabel("First name:"), updateFirstName);
		verifyEquals(myAccountPage.getInputTextValueByLabel("Last name:"), updateLastName);
		verifyEquals(myAccountPage.getInputTextValueByLabel("Email:"), updateEmail);
		verifyEquals(myAccountPage.getInputTextValueByLabel("Company name:"), updateCompany);
		verifyEquals(myAccountPage.getCurrentDateOfBirthOption("Day"), updateDay);
		verifyEquals(myAccountPage.getCurrentDateOfBirthOption("Month"), updateMonth);
		verifyEquals(myAccountPage.getCurrentDateOfBirthOption("Year"), updateYear);
	}

	@Test
	public void TC_02_Addresses() {
		myAccountPage.clickDynamicMyAccountMenu("Addresses");
		verifyEquals(myAccountPage.getPageTitleText(), "My account - Addresses");

		myAccountPage.clickButtonByText("Add new");
		verifyEquals(myAccountPage.getPageTitleText(), "My account - Add new address");

		String addressFirstName = "Automation";
		String addressLastName = "FC";
		String addressEmail = "automationfc.vn@gmail.com";
		String addressCompany = "Automation FC";
		String addressCountry = "Viet Nam";
		String addressState = "Other";
		String addressCity = "Da Nang";
		String addressAddress1 = "123/04 Le Lai";
		String addressAddress2 = "234/05 Hai Phong";
		String addressZipCode = "550000";
		String addressPhoneNumber = "0123456789";
		String addressFaxNumber = "0987654321";

		myAccountPage.inputToTextboxByLabel("First name:", addressFirstName);
		myAccountPage.inputToTextboxByLabel("Last name:", addressLastName);
		myAccountPage.inputToTextboxByLabel("Email:", addressEmail);
		myAccountPage.inputToTextboxByLabel("Company:", addressCompany);
		myAccountPage.selectOptionByLabel("Country:", addressCountry);
		myAccountPage.selectOptionByLabel("State / province:", addressState);
		myAccountPage.inputToTextboxByLabel("City:", addressCity);
		myAccountPage.inputToTextboxByLabel("Address 1:", addressAddress1);
		myAccountPage.inputToTextboxByLabel("Address 2:", addressAddress2);
		myAccountPage.inputToTextboxByLabel("Zip / postal code:", addressZipCode);
		myAccountPage.inputToTextboxByLabel("Phone number:", addressPhoneNumber);
		myAccountPage.inputToTextboxByLabel("Fax number:", addressFaxNumber);

		myAccountPage.clickSubmitFormButton("Save");
		verifyEquals(myAccountPage.getPageTitleText(), "My account - Addresses");

		String countAddress = Integer.toString(myAccountPage.countCurrentAddress());

		verifyEquals(myAccountPage.getAddressInfoByPositionAndName(countAddress, "name"),
				addressFirstName + " " + addressLastName);
		verifyTrue(myAccountPage.getAddressInfoByPositionAndName(countAddress, "email").contains(addressEmail));
		verifyTrue(myAccountPage.getAddressInfoByPositionAndName(countAddress, "phone").contains(addressPhoneNumber));
		verifyTrue(myAccountPage.getAddressInfoByPositionAndName(countAddress, "fax").contains(addressFaxNumber));
		verifyEquals(myAccountPage.getAddressInfoByPositionAndName(countAddress, "company"), addressCompany);
		verifyEquals(myAccountPage.getAddressInfoByPositionAndName(countAddress, "address1"), addressAddress1);
		verifyEquals(myAccountPage.getAddressInfoByPositionAndName(countAddress, "address2"), addressAddress2);
		verifyEquals(myAccountPage.getAddressInfoByPositionAndName(countAddress, "city-state-zip"),
				addressCity + ", " + addressZipCode);
		verifyEquals(myAccountPage.getAddressInfoByPositionAndName(countAddress, "country"), addressCountry);
	}

	@Test
	public void TC_03_Change_Password() {
		String newPassword = "abcxyz";

		myAccountPage.clickDynamicMyAccountMenu("Change password");

		myAccountPage.inputToTextboxByLabel("Old password:", GlobalConstants.NOPCOMMERCE_PASSWORD);
		myAccountPage.inputToTextboxByLabel("New password:", newPassword);
		myAccountPage.inputToTextboxByLabel("Confirm password:", newPassword);

		myAccountPage.clickSubmitFormButton("Change password");

		verifyEquals(myAccountPage.getNotificationBarText(), "Password was changed");
		myAccountPage.clickCloseNotificationBar();

		myAccountPage.clickHeaderLinkText("Log out");
		homePage = PageGenerator.getHomePage(driver);

		homePage.clickHeaderLinkText("Log in");
		loginPage = PageGenerator.getLoginPage(driver);

		loginPage.inputToLoginTextboxByLabel("Email:", GlobalConstants.NOPCOMMERCE_ACCOUNT);
		loginPage.inputToLoginTextboxByLabel("Password:", GlobalConstants.NOPCOMMERCE_PASSWORD);
		loginPage.clickLoginButton();

		verifyEquals(loginPage.getLoginErrorText(), "Login was unsuccessful. Please correct the errors and try again."
				+ "\n" + "The credentials provided are incorrect");

		loginPage.inputToLoginTextboxByLabel("Password:", newPassword);
		loginPage.clickLoginButton();
		homePage = PageGenerator.getHomePage(driver);
		homePage.isMyAccountLinkDisplayedOnHeader();
	}

	@Test
	public void TC_04_My_Product_Reviews() {
		String ReviewTitle = "Test Review Title";
		String ReviewText = "Test Review Text";

		String productName = homePage.getProductNameByPosition("1");
		productDetailPage = homePage.clickProductItemByPosition("1");
		verifyEquals(productDetailPage.getProductNameText(), productName);

		productDetailPage.clickAddYourReviewLink();
		productDetailPage.inputToTextboxByLabel("Review title:", ReviewTitle);
		productDetailPage.inputToTextareaByLabel("Review text:", ReviewText);
		productDetailPage.clickSubmitFormButton("Submit review");
		verifyEquals(productDetailPage.getReviewResultText(), "Product review is successfully added.");

		homePage.clickHeaderLinkText("My account");
		myAccountPage = PageGenerator.getMyAccountPage(driver);
		myAccountPage.clickDynamicMyAccountMenu("My product reviews");
		verifyEquals(myAccountPage.getPageTitleText(), "My account - My product reviews");

		verifyEquals(myAccountPage.getReviewTitleByPosition("1"), ReviewTitle);
		verifyEquals(myAccountPage.getReviewTextByPosition("1"), ReviewText);
	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}