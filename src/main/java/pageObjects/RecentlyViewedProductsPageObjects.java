package pageObjects;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.RecentlyViewedProductsPageUI;

public class RecentlyViewedProductsPageObjects extends AbstractPage {
	WebDriver driver;

	public RecentlyViewedProductsPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitleText() {
		return getPageTitleText(driver);
	}

	public int countDisplayProductNumber() {
		waitForElementsVisible(driver, RecentlyViewedProductsPageUI.PRODUCT_ITEM_BOXES);
		return countElementNumber(driver, RecentlyViewedProductsPageUI.PRODUCT_ITEM_BOXES);
	}

	public String getProductNameByIndex(String value) {
		waitForElementVisible(driver, RecentlyViewedProductsPageUI.PRODUCT_NAME_BY_INDEX, value);
		return getElementText(driver, RecentlyViewedProductsPageUI.PRODUCT_NAME_BY_INDEX, value);
	}

	public ArrayList<String> getListProductNames() {
		waitForElementsVisible(driver, RecentlyViewedProductsPageUI.PRODUCT_NAMES);
		int number = countElementNumber(driver, RecentlyViewedProductsPageUI.PRODUCT_NAMES);
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 1; i <= number; i++) {
			list.add(getElementText(driver, "(" + RecentlyViewedProductsPageUI.PRODUCT_NAMES + ")[" + i + "]"));
		}
		return list;
	}
}