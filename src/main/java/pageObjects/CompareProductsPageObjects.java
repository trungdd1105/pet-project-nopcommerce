package pageObjects;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.CompareProductsPageUI;

public class CompareProductsPageObjects extends AbstractPage {
	WebDriver driver;

	public CompareProductsPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitleText() {
		return getPageTitleText(driver);
	}

	public int countRemoveButtonNumber() {
		waitForElementsVisible(driver, CompareProductsPageUI.REMOVE_BUTTONS);
		return countElementNumber(driver, CompareProductsPageUI.REMOVE_BUTTONS);
	}

	public ArrayList<String> getProductNames() {
		waitForElementsVisible(driver, CompareProductsPageUI.PRODUCT_NAMES);
		int number = countElementNumber(driver, CompareProductsPageUI.PRODUCT_NAMES);
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 1; i <= number; i++) {
			list.add(getElementText(driver, "(" + CompareProductsPageUI.PRODUCT_NAMES + ")[" + i + "]"));
		}
		return list;
	}

	public String getPriceTextByProduct(String columnName) {
		waitForElementVisible(driver, CompareProductsPageUI.DYNAMIC_COLUMN_POSITION, columnName);
		int columnPosition = countElementNumber(driver, CompareProductsPageUI.DYNAMIC_COLUMN_POSITION, columnName) + 1;
		waitForElementVisible(driver, CompareProductsPageUI.PRICE_TEXT_BY_INDEX, Integer.toString(columnPosition));
		return getElementText(driver, CompareProductsPageUI.PRICE_TEXT_BY_INDEX, Integer.toString(columnPosition));
	}

	public void clickClearListButton() {
		waitForElementClickable(driver, CompareProductsPageUI.CLEAR_LIST_BUTTON_LINK);
		clickElement(driver, CompareProductsPageUI.CLEAR_LIST_BUTTON_LINK);
	}

	public String getNoDataText() {
		waitForElementVisible(driver, CompareProductsPageUI.NO_DATA_TEXT);
		return getElementText(driver, CompareProductsPageUI.NO_DATA_TEXT);
	}
}