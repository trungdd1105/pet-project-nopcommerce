package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGenerator;
import pageUI.ProductPageUI;

public class ProductPageObjects extends AbstractPage {
	WebDriver driver;

	public ProductPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitleText() {
		return getPageTitleText(driver);
	}
	
	public void selectSortByOption(String option) {
		waitForElementClickable(driver, ProductPageUI.SORT_BY_DROPDOWN);
		selectItemInDropdown(driver, ProductPageUI.SORT_BY_DROPDOWN, option);
		waitForElementInvisible(driver, ProductPageUI.AJAX_LOADER_ICON);
	}

	public void selectDisplayOption(String option) {
		waitForElementClickable(driver, ProductPageUI.DISPLAY_DROPDOWN);
		selectItemInDropdown(driver, ProductPageUI.DISPLAY_DROPDOWN, option);
		waitForElementInvisible(driver, ProductPageUI.AJAX_LOADER_ICON);
	}
	
	public boolean isProductNameSortedAscending() {
		return isDataSortedAscending(driver, ProductPageUI.PRODUCT_ITEM_LINKS);
	}
	
	public boolean isProductNameSortedDescending() {
		return isDataSortedDescending(driver, ProductPageUI.PRODUCT_ITEM_LINKS);
	}
	
	public boolean isProductPriceSortedLowToHigh() {
		return isPriceSortedAscending(driver, ProductPageUI.PRODUCT_ITEM_PRICE);
	}
	
	public boolean isProductPriceSortedHighToLow() {
		return isPriceSortedAscending(driver, ProductPageUI.PRODUCT_ITEM_PRICE);
	}

	public int countProductItemNumber() {
		waitForElementsVisible(driver, ProductPageUI.PRODUCT_ITEMS);
		return countElementNumber(driver, ProductPageUI.PRODUCT_ITEMS);
	}

	public boolean isPagingBarUndisplayed() {
		return isElementUndisplayed(driver, ProductPageUI.PAGING_BAR);
	}

	public boolean isNextPageIconDisplayed() {
		waitForElementVisible(driver, ProductPageUI.NEXT_PAGE_ICON);
		return isElementDisplayed(driver, ProductPageUI.NEXT_PAGE_ICON);
	}

	public boolean isPreviousPageIconDisplayed() {
		waitForElementVisible(driver, ProductPageUI.PREVIOUS_PAGE_ICON);
		return isElementDisplayed(driver, ProductPageUI.PREVIOUS_PAGE_ICON);
	}

	public String getCurrentPage() {
		waitForElementVisible(driver, ProductPageUI.CURRENT_PAGE_NUMBER);
		return getElementText(driver, ProductPageUI.CURRENT_PAGE_NUMBER);
	}

	public void clickNextPageIcon() {
		waitForElementClickable(driver, ProductPageUI.NEXT_PAGE_ICON);
		clickElement(driver, ProductPageUI.NEXT_PAGE_ICON);
		waitForElementInvisible(driver, ProductPageUI.AJAX_LOADER_ICON);
	}
	
	public String getProductNameTextByPosition(String position) {
		waitForElementVisible(driver, ProductPageUI.PRODUCT_LINK_BY_POSITION, position);
		return getElementText(driver, ProductPageUI.PRODUCT_LINK_BY_POSITION, position);
	}
	
	public void clickProductNameTextByPosition(String position) {
		waitForElementVisible(driver, ProductPageUI.PRODUCT_LINK_BY_POSITION, position);
		clickElement(driver, ProductPageUI.PRODUCT_LINK_BY_POSITION, position);
	}
	
	public ProductDetailPageObjects clickProductLinkByName(String name) {
		waitForElementVisible(driver, ProductPageUI.PRODUCT_LINK_BY_NAME, name);
		clickElement(driver, ProductPageUI.PRODUCT_LINK_BY_NAME, name);
		return PageGenerator.getProductDetailPage(driver);
	}
}