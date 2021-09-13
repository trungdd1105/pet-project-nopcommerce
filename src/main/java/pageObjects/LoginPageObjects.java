package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.LoginPageUI;

public class LoginPageObjects extends AbstractPage {
	WebDriver driver;

	public LoginPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitleText() {
		return getPageTitleText(driver);
	}

	public void inputToLoginTextboxByLabel(String labelName, String inputValue) {
		inputToDynamicTextboxByLabel(driver, labelName, inputValue);
	}
	
	public void clickLoginButton() {
		clickSubmitFormButtonByText(driver, "Log in");
	}

	public String getEmailErrorText() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_TEXT);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_TEXT);
	}

	public String getLoginErrorText() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_ERROR_TEXT);
		return getElementText(driver, LoginPageUI.LOGIN_ERROR_TEXT);
	}
}