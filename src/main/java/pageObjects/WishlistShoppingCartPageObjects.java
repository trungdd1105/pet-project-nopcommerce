package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGenerator;
import pageUI.WishlistShoppingCartPageUI;

public class WishlistShoppingCartPageObjects extends AbstractPage {
	WebDriver driver;

	public WishlistShoppingCartPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	// Commons
	public HomePageObjects clickHeaderLogo() {
		return clickHeaderLogo(driver);
	}
	
	public void clickHeaderWishlistLink () {
		clickHeaderWishlistLink(driver);
	}
	
	public String getHeaderWishlistQuantityText() {
		return getHeaderWishlistQuantityText(driver);
	}
	
	public void clickButton(String buttonText) {
		clickDynamicButtonByText(driver, buttonText);
	}

	// Wishlist page
	public String getPageTitleText() {
		return getPageTitleText(driver);
	}
	
	public void clickWishlistSharingLink() {
		waitForElementClickable(driver, WishlistShoppingCartPageUI.WISHLIST_SHARING_LINK);
		clickElement(driver, WishlistShoppingCartPageUI.WISHLIST_SHARING_LINK);
	}

	public void checkACheckboxAtRowNumber(String columnName, String rowNumber) {
		int columnPosition = countElementNumber(driver, WishlistShoppingCartPageUI.DYNAMIC_COLUMN_POSITION, columnName) + 1;
		String columnPositionString = Integer.toString(columnPosition);
		waitForElementClickable(driver, WishlistShoppingCartPageUI.DYNAMIC_INPUT_BY_ROW_AND_COLUMN_INDEX, rowNumber, columnPositionString);
		checkACheckbox(driver, WishlistShoppingCartPageUI.DYNAMIC_INPUT_BY_ROW_AND_COLUMN_INDEX, rowNumber, columnPositionString);
	}

	public void inputToTextboxAtRowNumber(String inputText, String columnName, String rowNumber) {
		int columnPosition = countElementNumber(driver, WishlistShoppingCartPageUI.DYNAMIC_COLUMN_POSITION, columnName) + 1;
		sendkeyToElement(driver, WishlistShoppingCartPageUI.DYNAMIC_INPUT_BY_ROW_AND_COLUMN_INDEX, inputText, rowNumber, Integer.toString(columnPosition));
	}
	
	public String getTextAtRowNumber(String columnName, String rowNumber) {
		int columnPosition = countElementNumber(driver, WishlistShoppingCartPageUI.DYNAMIC_COLUMN_POSITION, columnName) + 1;
		return getElementText(driver, WishlistShoppingCartPageUI.DYNAMIC_TEXT_BY_ROW_AND_COLUMN_INDEX, rowNumber, Integer.toString(columnPosition));
	}
	
	public String getLinkTextAtRowNumber(String columnName, String rowNumber) {
		int columnPosition = countElementNumber(driver, WishlistShoppingCartPageUI.DYNAMIC_COLUMN_POSITION, columnName) + 1;
		return getElementText(driver, WishlistShoppingCartPageUI.DYNAMIC_LINK_BY_ROW_AND_COLUMN_INDEX, rowNumber, Integer.toString(columnPosition));
	}
	
	public void clickButtonAtRowNumber(String columnName, String rowNumber) {
		int columnPosition = countElementNumber(driver, WishlistShoppingCartPageUI.DYNAMIC_COLUMN_POSITION, columnName) + 1;
		clickElement(driver, WishlistShoppingCartPageUI.DYNAMIC_BUTTON_BY_ROW_AND_COLUMN_INDEX, rowNumber, Integer.toString(columnPosition));
	}

	public String getNoDataText() {
		waitForElementVisible(driver, WishlistShoppingCartPageUI.NO_DATA_TEXT);
		return getElementText(driver, WishlistShoppingCartPageUI.NO_DATA_TEXT);
	}

	// Shopping cart
	public ProductDetailPageObjects clickEditProductLink(String productName) {
		waitForElementClickable(driver, WishlistShoppingCartPageUI.EDIT_PRODUCT_LINK_BY_NAME, productName);
		clickElement(driver, WishlistShoppingCartPageUI.EDIT_PRODUCT_LINK_BY_NAME, productName);
		return PageGenerator.getProductDetailPage(driver);
	}

	public boolean isLoadingIconUndisplayed() {
		waitForElementInvisible(driver, WishlistShoppingCartPageUI.LOADING_ICON);
		return isElementUndisplayed(driver, WishlistShoppingCartPageUI.LOADING_ICON);
	}

	public void chooseGiftWrappingOption(String optionText) {
		waitForElementClickable(driver, WishlistShoppingCartPageUI.GIFT_WRAPPING_DROPDOWN_LIST);
		clickElement(driver, WishlistShoppingCartPageUI.GIFT_WRAPPING_DROPDOWN_LIST);
		waitForElementClickable(driver, WishlistShoppingCartPageUI.GIFT_WRAPPING_DROPDOWN_LIST_OPTION, optionText);
		clickElement(driver, WishlistShoppingCartPageUI.GIFT_WRAPPING_DROPDOWN_LIST_OPTION, optionText);
	}

	public String getCheckoutAttributeText() {
		waitForElementVisible(driver, WishlistShoppingCartPageUI.CHECKOUT_ATTRIBUTE);
		return getElementText(driver, WishlistShoppingCartPageUI.CHECKOUT_ATTRIBUTE);
	}

	public void checkTermOfServiceCheckbox() {
		waitForElementClickable(driver, WishlistShoppingCartPageUI.TERM_OF_SERVICE_CHECKBOX);
		checkACheckbox(driver, WishlistShoppingCartPageUI.TERM_OF_SERVICE_CHECKBOX);
	}
}