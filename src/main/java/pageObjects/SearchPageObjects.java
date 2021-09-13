package pageObjects;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.SearchPageUI;

public class SearchPageObjects extends AbstractPage {
	WebDriver driver;

	public SearchPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToTextboxByLabel(String labelName, String inputValue) {
		inputToDynamicTextboxByLabel(driver, labelName, inputValue);
	}

	public void clickSearchButton() {
		waitForElementClickable(driver, SearchPageUI.BODY_SEARCH_BUTTON);
		clickElement(driver, SearchPageUI.BODY_SEARCH_BUTTON);
	}

	public String getSearchResultWarningText() {
		waitForElementVisible(driver, SearchPageUI.SEARCH_RESULT_WARNING_TEXT);
		return getElementText(driver, SearchPageUI.SEARCH_RESULT_WARNING_TEXT);
	}

	public String getSearchNoResultText() {
		waitForElementVisible(driver, SearchPageUI.SEARCH_NO_RESULT_TEXT);
		return getElementText(driver, SearchPageUI.SEARCH_NO_RESULT_TEXT);
	}

	public int countSearchItemNumber() {
		waitForElementsVisible(driver, SearchPageUI.SEARCH_ITEMS);
		return countElementNumber(driver, SearchPageUI.SEARCH_ITEMS);
	}

	public ArrayList<String> getSearchItemTitles() {
		waitForElementsVisible(driver, SearchPageUI.SEARCH_ITEM_TITLES);
		int number = countElementNumber(driver, SearchPageUI.SEARCH_ITEM_TITLES);
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 1; i <= number; i++) {
			list.add(getElementText(driver, "(" + SearchPageUI.SEARCH_ITEM_TITLES + ")[" + i + "]"));
		}
		return list;
	}

	public void clickCheckboxByLabel(String action, String label) {
		clickDynamicCheckboxByLabel(driver, action, label);
	}
	
	public void selectOptionByLabel(String label, String value) {
		selectDynamicOptionByLabel(driver, label, value);
	}

	public void inputToPriceFromTextbox(String value) {
		waitForElementVisible(driver, SearchPageUI.PRICE_FROM_TEXTBOX);
		sendkeyToElement(driver, SearchPageUI.PRICE_FROM_TEXTBOX, value);
	}

	public void inputToPriceToTextbox(String value) {
		waitForElementVisible(driver, SearchPageUI.PRICE_TO_TEXTBOX);
		sendkeyToElement(driver, SearchPageUI.PRICE_TO_TEXTBOX, value);
	}
}