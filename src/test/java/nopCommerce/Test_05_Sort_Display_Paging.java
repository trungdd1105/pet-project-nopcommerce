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
import pageObjects.ProductPageObjects;
import pageObjects.SearchPageObjects;

public class Test_05_Sort_Display_Paging extends AbstractTest {
	WebDriver driver;
	HomePageObjects homePage;
	SearchPageObjects searchPage;
	ProductPageObjects productPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.NOPCOMMERCE_URL);
		homePage = PageGenerator.getHomePage(driver);
		productPage = homePage.goToHeaderSubMenuLink("Computers ", "Notebooks ");
		verifyEquals(productPage.getPageTitleText(), "Notebooks");
	}

	@Test
	public void TC_01_Sort_with_Name_A_to_Z() {
		productPage.selectSortByOption("Name: A to Z");
		verifyTrue(productPage.isProductNameSortedAscending());
	}

	@Test
	public void TC_02_Sort_with_Name_Z_to_A() {
		productPage.selectSortByOption("Name: Z to A");
		verifyTrue(productPage.isProductNameSortedDescending());
	}

	@Test
	public void TC_03_Sort_with_Price_Low_to_High() {
		productPage.selectSortByOption("Price: Low to High");
		verifyTrue(productPage.isProductPriceSortedLowToHigh());
	}

	@Test
	public void TC_04_Sort_with_Price_High_to_Low() {
		productPage.selectSortByOption("Price: High to Low");
		verifyTrue(productPage.isProductPriceSortedHighToLow());
	}

	@Test
	public void TC_05_Display_3_per_Page() {
		productPage.selectDisplayOption("3");
		verifyEquals(productPage.countProductItemNumber(), 3);
		verifyEquals(productPage.getCurrentPage(), "1");
		
		verifyTrue(productPage.isNextPageIconDisplayed());
		productPage.clickNextPageIcon();
		verifyEquals(productPage.getCurrentPage(), "2");
		verifyTrue(productPage.isPreviousPageIconDisplayed());
	}

	@Test
	public void TC_06_Display_6_per_Page() {
		productPage.selectDisplayOption("6");
		verifyTrue(productPage.countProductItemNumber() <= 6);
		verifyTrue(productPage.isPagingBarUndisplayed());
	}

	@Test
	public void TC_07_Display_9_per_Page() {
		productPage.selectDisplayOption("9");
		verifyTrue(productPage.countProductItemNumber() <= 9);
		verifyTrue(productPage.isPagingBarUndisplayed());
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}
}