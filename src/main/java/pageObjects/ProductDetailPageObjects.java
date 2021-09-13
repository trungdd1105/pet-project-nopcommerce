package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.ProductDetailPageUI;

public class ProductDetailPageObjects extends AbstractPage {
	WebDriver driver;

	public ProductDetailPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	// Commons
	public String getNotificationBarText() {
		return getNotificationBarText(driver, "success");
	}

	public void clickCloseNotificationBar() {
		clickCloseNotificationBar(driver);
	}

	public WishlistShoppingCartPageObjects clickHeaderWishlistLink() {
		return clickHeaderWishlistLink(driver);
	}

	public String getHeaderWishlistQuantityText() {
		return getHeaderWishlistQuantityText(driver);
	}

	public void clickButton(String buttonText) {
		clickDynamicButtonByText(driver, buttonText);
	}

	// Product detail
	public String getProductNameText() {
		waitForElementVisible(driver, ProductDetailPageUI.PRODUCT_NAME_TEXT);
		return getElementText(driver, ProductDetailPageUI.PRODUCT_NAME_TEXT);
	}

	public String getCurrentProductQuantity() {
		waitForElementVisible(driver, ProductDetailPageUI.PRODUCT_CURRENT_QUANTIY);
		return getElementAttribute(driver, ProductDetailPageUI.PRODUCT_CURRENT_QUANTIY, "value");
	}
	
	public void inputToProductQuantity(String inputValue) {
		waitForElementsVisible(driver, ProductDetailPageUI.PRODUCT_CURRENT_QUANTIY);
		sendkeyToElement(driver, ProductDetailPageUI.PRODUCT_CURRENT_QUANTIY, inputValue);
	}

	// Reviews
	public void clickAddYourReviewLink() {
		clickDynamicLinkByText(driver, "Add your review");
	}

	public void inputToTextboxByLabel(String labelName, String inputValue) {
		inputToDynamicTextboxByLabel(driver, labelName, inputValue);
	}

	public void inputToTextareaByLabel(String labelName, String inputValue) {
		inputToDynamicTextareaByLabel(driver, labelName, inputValue);
	}

	public void clickSubmitFormButton(String buttonText) {
		clickSubmitFormButtonByText(driver, buttonText);
	}

	public String getReviewResultText() {
		waitForElementVisible(driver, ProductDetailPageUI.BODY_RESULT_TEXT);
		return getElementText(driver, ProductDetailPageUI.BODY_RESULT_TEXT);
	}

	public void selectDropdownListOptionByLabel(String label, String option) {
		String label_attribute = getElementAttribute(driver, ProductDetailPageUI.PRODUCT_ATTRIBUTE_LABEL_BY_TEXT, "id",
				label);
		String select_id = label_attribute.replace("_label", "");

		waitForElementClickable(driver, ProductDetailPageUI.PRODUCT_DROPDOWN_LIST_BY_ID, select_id);
		clickElement(driver, ProductDetailPageUI.PRODUCT_DROPDOWN_LIST_BY_ID, select_id);
		waitForElementClickable(driver, ProductDetailPageUI.PRODUCT_DROPDOWN_OPTION_BY_TEXT, select_id, option);
		clickElement(driver, ProductDetailPageUI.PRODUCT_DROPDOWN_OPTION_BY_TEXT, select_id, option);
	}

	public void clickRadioButtonByLabel(String label) {
		waitForElementClickable(driver, ProductDetailPageUI.RADIO_BUTTON_BY_LABEL, label);
		clickElement(driver, ProductDetailPageUI.RADIO_BUTTON_BY_LABEL, label);
	}

	public void checkACheckboxByLabel(String label) {
		waitForElementClickable(driver, ProductDetailPageUI.CHECKBOX_BY_LABEL, label);
		checkACheckbox(driver, ProductDetailPageUI.CHECKBOX_BY_LABEL, label);
	}

	public void uncheckACheckboxByLabel(String label) {
		waitForElementClickable(driver, ProductDetailPageUI.CHECKBOX_BY_LABEL, label);
		uncheckACheckbox(driver, ProductDetailPageUI.CHECKBOX_BY_LABEL, label);
	}

	public String getProductPrice() {
		sleepInSecond(3);
		waitForElementVisible(driver, ProductDetailPageUI.PRODUCT_PRICE);
		return getElementText(driver, ProductDetailPageUI.PRODUCT_PRICE);
	}
	
	public boolean findProductPriceByText(String price) {
		waitForElementVisible(driver, ProductDetailPageUI.PRODUCT_PRICE_BY_TEXT, price);
		return isElementDisplayed(driver, ProductDetailPageUI.PRODUCT_PRICE_BY_TEXT, price);
	}
}