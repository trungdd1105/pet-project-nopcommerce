package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.CheckoutPageObjects;
import pageObjects.CompareProductsPageObjects;
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import pageObjects.MyAccountPageObjects;
import pageObjects.ProductDetailPageObjects;
import pageObjects.ProductPageObjects;
import pageObjects.RecentlyViewedProductsPageObjects;
import pageObjects.RegisterPageObjects;
import pageObjects.SearchPageObjects;
import pageObjects.WishlistShoppingCartPageObjects;

public class PageGenerator {
	public static HomePageObjects getHomePage(WebDriver driver) {
		return new HomePageObjects(driver);
	}

	public static RegisterPageObjects getRegisterPage(WebDriver driver) {
		return new RegisterPageObjects(driver);
	}

	public static LoginPageObjects getLoginPage(WebDriver driver) {
		return new LoginPageObjects(driver);
	}

	public static MyAccountPageObjects getMyAccountPage(WebDriver driver) {
		return new MyAccountPageObjects(driver);
	}

	public static ProductDetailPageObjects getProductDetailPage(WebDriver driver) {
		return new ProductDetailPageObjects(driver);
	}

	public static SearchPageObjects getSearchPage(WebDriver driver) {
		return new SearchPageObjects(driver);
	}

	public static ProductPageObjects getProductPage(WebDriver driver) {
		return new ProductPageObjects(driver);
	}

	public static WishlistShoppingCartPageObjects getWishlistShoppingCartPage(WebDriver driver) {
		return new WishlistShoppingCartPageObjects(driver);
	}

	public static CompareProductsPageObjects getCompareProductsPage(WebDriver driver) {
		return new CompareProductsPageObjects(driver);
	}

	public static RecentlyViewedProductsPageObjects getRecentlyViewedProductsPage(WebDriver driver) {
		return new RecentlyViewedProductsPageObjects(driver);
	}

	public static CheckoutPageObjects getCheckoutPage(WebDriver driver) {
		return new CheckoutPageObjects(driver);
	}
}