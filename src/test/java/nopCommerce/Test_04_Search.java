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
import pageObjects.SearchPageObjects;

public class Test_04_Search extends AbstractTest {
	WebDriver driver;
	HomePageObjects homePage;
	SearchPageObjects searchPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.NOPCOMMERCE_URL);
		homePage = PageGenerator.getHomePage(driver);
		homePage.clickDynamicFooterLink(driver, "Search");
		searchPage = PageGenerator.getSearchPage(driver);
	}

	@Test
	public void TC_01_Empty_Data() {
		searchPage.clickSearchButton();
		verifyEquals(searchPage.getSearchResultWarningText(), "Search term minimum length is 3 characters");
	}

	@Test
	public void TC_02_Data_Not_Exist() {
		searchPage.inputToTextboxByLabel("Search keyword:", "Macbook Pro 2050");
		searchPage.clickSearchButton();
		verifyEquals(searchPage.getSearchNoResultText(), "No products were found that matched your criteria.");
	}

	@Test
	public void TC_03_Relative_Search() {
		searchPage.inputToTextboxByLabel("Search keyword:", "Lenovo");
		searchPage.clickSearchButton();
		verifyEquals(searchPage.countSearchItemNumber(), 2);
		verifyTrue(searchPage.getSearchItemTitles().contains("Lenovo IdeaCentre 600 All-in-One PC"));
		verifyTrue(searchPage.getSearchItemTitles().contains("Lenovo Thinkpad X1 Carbon Laptop"));
	}

	@Test
	public void TC_04_Absolute_Search() {
		searchPage.inputToTextboxByLabel("Search keyword:", "Thinkpad X1 Carbon");
		searchPage.clickSearchButton();
		verifyEquals(searchPage.countSearchItemNumber(), 1);
		verifyTrue(searchPage.getSearchItemTitles().contains("Lenovo Thinkpad X1 Carbon Laptop"));
	}

	@Test
	public void TC_05_Advanced_Search_with_Parent_Category() {
		searchPage.inputToTextboxByLabel("Search keyword:", "Apple Macbook Pro");
		searchPage.clickCheckboxByLabel("check", "Advanced search");
		searchPage.selectOptionByLabel("Category:", "Computers");
		searchPage.clickSearchButton();
		verifyEquals(searchPage.getSearchNoResultText(), "No products were found that matched your criteria.");
	}

	@Test
	public void TC_06_Advanced_Search_with_Sub_Category() {
		searchPage.inputToTextboxByLabel("Search keyword:", "Apple Macbook Pro");
		searchPage.clickCheckboxByLabel("check", "Advanced search");
		searchPage.selectOptionByLabel("Category:", "Computers");
		searchPage.clickCheckboxByLabel("check", "Automatically search sub categories");
		searchPage.clickSearchButton();
		verifyEquals(searchPage.countSearchItemNumber(), 1);
		verifyTrue(searchPage.getSearchItemTitles().contains("Apple MacBook Pro 13-inch"));
	}

	@Test
	public void TC_07_Advanced_Search_with_Incorrect_Manufaturer() {
		searchPage.inputToTextboxByLabel("Search keyword:", "Apple Macbook Pro");
		searchPage.clickCheckboxByLabel("check", "Advanced search");
		searchPage.selectOptionByLabel("Category:", "Computers");
		searchPage.clickCheckboxByLabel("check", "Automatically search sub categories");
		searchPage.selectOptionByLabel("Manufacturer:", "HP");
		searchPage.clickSearchButton();
		verifyEquals(searchPage.getSearchNoResultText(), "No products were found that matched your criteria.");
	}

	@Test
	public void TC_08_Advanced_Search_with_Correct_Manufaturer() {
		searchPage.inputToTextboxByLabel("Search keyword:", "Apple Macbook Pro");
		searchPage.clickCheckboxByLabel("check", "Advanced search");
		searchPage.selectOptionByLabel("Category:", "Computers");
		searchPage.clickCheckboxByLabel("check", "Automatically search sub categories");
		searchPage.selectOptionByLabel("Manufacturer:", "Apple");
		searchPage.clickSearchButton();
		verifyEquals(searchPage.countSearchItemNumber(), 1);
		verifyTrue(searchPage.getSearchItemTitles().contains("Apple MacBook Pro 13-inch"));
	}

//	@Test
	public void TC_09_Advanced_Search_within_Price_Range() {
		searchPage.inputToTextboxByLabel("Search keyword:", "Apple Macbook Pro");
		searchPage.clickCheckboxByLabel("check", "Advanced search");
		searchPage.selectOptionByLabel("Category:", "Computers");
		searchPage.clickCheckboxByLabel("check", "Automatically search sub categories");
		searchPage.selectOptionByLabel("Manufacturer:", "Apple");
		searchPage.inputToPriceFromTextbox("1000");
		searchPage.inputToPriceToTextbox("2000");
		searchPage.clickSearchButton();
		verifyEquals(searchPage.countSearchItemNumber(), 1);
		verifyTrue(searchPage.getSearchItemTitles().contains("Apple MacBook Pro 13-inch"));
	}

//	@Test
	public void TC_10_Advanced_Search_with_Price_Range_lower_than_Product_Price() {
		searchPage.inputToTextboxByLabel("Search keyword:", "Apple Macbook Pro");
		searchPage.clickCheckboxByLabel("check", "Advanced search");
		searchPage.selectOptionByLabel("Category:", "Computers");
		searchPage.clickCheckboxByLabel("check", "Automatically search sub categories");
		searchPage.selectOptionByLabel("Manufacturer:", "Apple");
		searchPage.inputToPriceFromTextbox("1000");
		searchPage.inputToPriceToTextbox("1700");
		searchPage.clickSearchButton();
		verifyEquals(searchPage.getSearchNoResultText(), "No products were found that matched your criteria.");
	}

//	@Test
	public void TC_11_Advanced_Search_with_Price_Range_higher_than_Product_Price() {
		searchPage.inputToTextboxByLabel("Search keyword:", "Apple Macbook Pro");
		searchPage.clickCheckboxByLabel("check", "Advanced search");
		searchPage.selectOptionByLabel("Category:", "Computers");
		searchPage.clickCheckboxByLabel("check", "Automatically search sub categories");
		searchPage.selectOptionByLabel("Manufacturer:", "Apple");
		searchPage.inputToPriceFromTextbox("1900");
		searchPage.inputToPriceToTextbox("5000");
		searchPage.clickSearchButton();
		verifyEquals(searchPage.getSearchNoResultText(), "No products were found that matched your criteria.");
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}
}