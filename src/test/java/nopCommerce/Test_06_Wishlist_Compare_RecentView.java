package nopCommerce;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGenerator;
import pageObjects.CompareProductsPageObjects;
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import pageObjects.ProductDetailPageObjects;
import pageObjects.ProductPageObjects;
import pageObjects.RecentlyViewedProductsPageObjects;
import pageObjects.WishlistShoppingCartPageObjects;

public class Test_06_Wishlist_Compare_RecentView extends AbstractTest {
	WebDriver driver;
	HomePageObjects homePage;
	LoginPageObjects loginPage;
	ProductDetailPageObjects productDetailPage;
	WishlistShoppingCartPageObjects wishlistShoppingCartPage;
	CompareProductsPageObjects compareProductsPage;
	ProductPageObjects productPage;
	RecentlyViewedProductsPageObjects recentlyViewedProductsPage;

	String productName;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.NOPCOMMERCE_URL);
		homePage = PageGenerator.getHomePage(driver);
//		homePage.clickHeaderLinkText("Log in");
//		loginPage = PageGenerator.getLoginPage(driver);
//		loginPage.inputToLoginTextboxByLabel("Email:", GlobalConstants.NOPCOMMERCE_ACCOUNT);
//		loginPage.inputToLoginTextboxByLabel("Password:", GlobalConstants.NOPCOMMERCE_PASSWORD);
//		loginPage.clickLoginButton();
//		homePage = PageGenerator.getHomePage(driver);
//		homePage.isMyAccountLinkDisplayedOnHeader();	
	}

//	@Test
	public void TC_01_Add_to_Wishlist() {
		productName = homePage.getProductNameByPosition("2");
		productDetailPage = homePage.clickProductItemByPosition("2");
		verifyEquals(productDetailPage.getProductNameText(), productName);
		String productQuantity = productDetailPage.getCurrentProductQuantity();

		productDetailPage.clickButton("Add to wishlist");
		verifyEquals(productDetailPage.getNotificationBarText(), "The product has been added to your wishlist");
		productDetailPage.clickCloseNotificationBar();
		verifyEquals(productDetailPage.getHeaderWishlistQuantityText(), "(" + productQuantity + ")");

		wishlistShoppingCartPage = productDetailPage.clickHeaderWishlistLink();
		verifyEquals(wishlistShoppingCartPage.getPageTitleText(), "Wishlist");
		verifyEquals(wishlistShoppingCartPage.getLinkTextAtRowNumber("Product(s)", "1"), productName);

		wishlistShoppingCartPage.clickWishlistSharingLink();
		verifyEquals(wishlistShoppingCartPage.getPageTitleText(), "Wishlist of");
	}

//	@Test
	public void TC_02_Add_Product_to_Cart_from_Wishlist() {
		wishlistShoppingCartPage.clickHeaderWishlistLink();
		wishlistShoppingCartPage.checkACheckboxAtRowNumber("Add to cart", "1");
		wishlistShoppingCartPage.clickButton("Add to cart");
		verifyEquals(wishlistShoppingCartPage.getPageTitleText(), "Shopping cart");
//		verifyEquals(wishlistShoppingCartPage.getHeaderWishlistQuantityText(), "(0)");
		verifyEquals(wishlistShoppingCartPage.getLinkTextAtRowNumber("Product(s)", "1"), productName);
	}

//	@Test
	public void TC_03_Remove_Product_in_Wishlist() {
//		homePage = wishlistShoppingCartPage.clickHeaderLogo();

		productName = homePage.getProductNameByPosition("3");
		productDetailPage = homePage.clickProductItemByPosition("3");
		verifyEquals(productDetailPage.getProductNameText(), productName);

		productDetailPage.clickButton("Add to wishlist");
		verifyEquals(productDetailPage.getNotificationBarText(), "The product has been added to your wishlist");
		productDetailPage.clickCloseNotificationBar();

		wishlistShoppingCartPage = productDetailPage.clickHeaderWishlistLink();
		verifyEquals(wishlistShoppingCartPage.getPageTitleText(), "Wishlist");
		verifyEquals(wishlistShoppingCartPage.getLinkTextAtRowNumber("Product(s)", "1"), productName);
		wishlistShoppingCartPage.clickButtonAtRowNumber("Remove", "1");
		verifyEquals(wishlistShoppingCartPage.getNoDataText(), "The wishlist is empty!");
	}

//	@Test
	public void TC_04_Add_Product_to_Compare() {
//		homePage = wishlistShoppingCartPage.clickHeaderLogo();
		
		String product_01_name = homePage.getProductNameByPosition("1");
		String product_01_price = homePage.getProductPriceByPosition("1");
		String product_02_name = homePage.getProductNameByPosition("2");
		String product_02_price = homePage.getProductPriceByPosition("2");

		homePage.clickProductButtonByPositionAndText("1", "Add to compare list");
		verifyEquals(homePage.getNotificationBarText(), "The product has been added to your product comparison");
		homePage.clickCloseNotificationBar();

		homePage.clickProductButtonByPositionAndText("2", "Add to compare list");
		verifyEquals(homePage.getNotificationBarText(), "The product has been added to your product comparison");
		homePage.clickCloseNotificationBar();

		homePage.clickDynamicFooterLink(driver, "Compare products list");
		compareProductsPage = PageGenerator.getCompareProductsPage(driver);
		verifyEquals(compareProductsPage.getPageTitleText(), "Compare products");
		verifyEquals(compareProductsPage.countRemoveButtonNumber(), 2);
		verifyTrue(compareProductsPage.getProductNames().contains(product_01_name));
		verifyTrue(compareProductsPage.getProductNames().contains(product_02_name));
		verifyEquals(compareProductsPage.getPriceTextByProduct(product_01_name), product_01_price);
		verifyEquals(compareProductsPage.getPriceTextByProduct(product_02_name), product_02_price);

		compareProductsPage.clickClearListButton();
		verifyEquals(compareProductsPage.getNoDataText(), "You have no items to compare.");
	}

	@Test
	public void TC_05_Recently_Viewed_Products() {
//		homePage = compareProductsPage.clickHeaderLogo(driver);
		
		productPage = homePage.goToHeaderSubMenuLink("Computers ", "Notebooks ");
		verifyEquals(productPage.getPageTitleText(), "Notebooks");

		String product_01_name = productPage.getProductNameTextByPosition("1");
		String product_02_name = productPage.getProductNameTextByPosition("2");
		String product_03_name = productPage.getProductNameTextByPosition("3");
		String product_04_name = productPage.getProductNameTextByPosition("4");
		String product_05_name = productPage.getProductNameTextByPosition("5");

		productPage.clickProductNameTextByPosition("1");
		navigateBack();
		verifyEquals(productPage.getPageTitleText(), "Notebooks");
		productPage.clickProductNameTextByPosition("2");
		navigateBack();
		verifyEquals(productPage.getPageTitleText(), "Notebooks");
		productPage.clickProductNameTextByPosition("3");
		navigateBack();
		verifyEquals(productPage.getPageTitleText(), "Notebooks");
		productPage.clickProductNameTextByPosition("4");
		navigateBack();
		verifyEquals(productPage.getPageTitleText(), "Notebooks");
		productPage.clickProductNameTextByPosition("5");
		navigateBack();
		verifyEquals(productPage.getPageTitleText(), "Notebooks");

		productPage.clickDynamicFooterLink(driver, "Recently viewed products");
		recentlyViewedProductsPage = PageGenerator.getRecentlyViewedProductsPage(driver);
		verifyEquals(recentlyViewedProductsPage.countDisplayProductNumber(), 3);
		verifyEquals(recentlyViewedProductsPage.getProductNameByIndex("1"), product_05_name);
		verifyEquals(recentlyViewedProductsPage.getProductNameByIndex("2"), product_04_name);
		verifyEquals(recentlyViewedProductsPage.getProductNameByIndex("3"), product_03_name);
		verifyFalse(recentlyViewedProductsPage.getListProductNames().contains(product_02_name));
		verifyFalse(recentlyViewedProductsPage.getListProductNames().contains(product_01_name));
	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}