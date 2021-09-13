package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.MyAccountPageUI;

public class MyAccountPageObjects extends AbstractPage {
	WebDriver driver;

	public MyAccountPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	// Commons
	public void clickHeaderLinkText(String linkText) {
		clickDynamicHeaderLink(driver, linkText);
	}

	public String getPageTitleText() {
		return getPageTitleText(driver);
	}

	public void clickDynamicMyAccountMenu(String menu) {
		clickDynamicMyAccountMenu(driver, menu);
	}

	public void inputToTextboxByLabel(String labelName, String inputValue) {
		inputToDynamicTextboxByLabel(driver, labelName, inputValue);
	}

	public void selectOptionByLabel(String label, String value) {
		selectDynamicOptionByLabel(driver, label, value);
	}

	public void clickButtonByText(String buttonText) {
		clickDynamicButtonByText(driver, buttonText);
	}

	public void clickSubmitFormButton(String buttonText) {
		clickSubmitFormButtonByText(driver, buttonText);
	}

	// Customer info
	public void clickGenderRadioButtonByLabel(String label) {
		clickDynamicRadioByLabel(driver, "radio", label);
	}

	public void selectDateOfBirthOptionByLabel(String label, String value) {
		waitForElementClickable(driver, MyAccountPageUI.DOB_DROPDOWN_LIST, label);
		clickElement(driver, MyAccountPageUI.DOB_DROPDOWN_LIST, label);
		clickElement(driver, MyAccountPageUI.DOB_DROPDOWN_OPTION, label, value);
	}

	public String getCurrentGenderChecked() {
		return getElementText(driver, MyAccountPageUI.CURRENT_GENDER_CHECKED);
	}

	public String getInputTextValueByLabel(String label) {
		waitForElementVisible(driver, MyAccountPageUI.INPUT_TEXT_VALUE_BY_LABEL, label);
		return getElementAttribute(driver, MyAccountPageUI.INPUT_TEXT_VALUE_BY_LABEL, "value", label);
	}

	public String getCurrentDateOfBirthOption(String type) {
		waitForElementVisible(driver, MyAccountPageUI.CURREBT_DOB_OPTION_SELECTED, type);
		return getElementText(driver, MyAccountPageUI.CURREBT_DOB_OPTION_SELECTED, type);
	}

	// Addresses
	public int countCurrentAddress() {
		waitForElementsVisible(driver, MyAccountPageUI.ADDRESS_SECTION);
		return countElementNumber(driver, MyAccountPageUI.ADDRESS_SECTION);
	}

	public String getAddressInfoByPositionAndName(String position, String name) {
		return getElementText(driver, MyAccountPageUI.ADDRESS_INFO_BY_POSITION_BY_NAME, position,
				name);
	}
	
	// Change password
	public String getNotificationBarText() {
		return getNotificationBarText(driver, "success");
	}
	
	public void clickCloseNotificationBar() {
		clickCloseNotificationBar(driver);
	}	

	// My product reviews
	public String getReviewTitleByPosition(String position) {
		waitForElementVisible(driver, MyAccountPageUI.REVIEW_TITLE_TEXT, position);
		return getElementText(driver, MyAccountPageUI.REVIEW_TITLE_TEXT, position);
	}

	public String getReviewTextByPosition(String position) {
		waitForElementVisible(driver, MyAccountPageUI.REVIEW_TEXT_TEXT, position);
		return getElementText(driver, MyAccountPageUI.REVIEW_TEXT_TEXT, position);
	}
}