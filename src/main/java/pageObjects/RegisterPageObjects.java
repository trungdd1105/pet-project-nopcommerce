package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.RegisterPageUI;

public class RegisterPageObjects extends AbstractPage {
	WebDriver driver;

	public RegisterPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitleText() {
		return getPageTitleText(driver);
	}

	public void clickRegisterSubmitButton() {
		clickSubmitFormButtonByText(driver, "Register");
	}

	public String getDynamicErrorText(String location) {
		waitForElementVisible(driver, RegisterPageUI.DYNAMIC_ERROR_TEXT, location);
		return getElementText(driver, RegisterPageUI.DYNAMIC_ERROR_TEXT, location);
	}

	public void inputToDynamicRegisterTextboxByLabel(String labelName, String inputValue) {
		inputToDynamicTextboxByLabel(driver, labelName, inputValue);
	}

	public String getValidationErrorText() {
		waitForElementVisible(driver, RegisterPageUI.VALIDATION_ERROR_TEXT);
		return getElementText(driver, RegisterPageUI.VALIDATION_ERROR_TEXT);
	}

	public String getResultText() {
		waitForElementVisible(driver, RegisterPageUI.RESULT_TEXT);
		return getElementText(driver, RegisterPageUI.RESULT_TEXT);
	}
}