package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.CheckoutPageUI;

public class CheckoutPageObjects extends AbstractPage {
	WebDriver driver;

	public CheckoutPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitleText() {
		return getPageTitleText(driver);
	}

	public String getActiveTabTitleText() {
		sleepInSecond(1);
		waitForElementVisible(driver, CheckoutPageUI.ACTIVE_TAB_TITLE_TEXT);
		return getElementText(driver, CheckoutPageUI.ACTIVE_TAB_TITLE_TEXT);
	}

	public void clickContinueButton() {
		waitForElementClickable(driver, CheckoutPageUI.CONTINUE_BUTTON);
		clickElement(driver, CheckoutPageUI.CONTINUE_BUTTON);
	}

	public boolean isPaymentInformationDisplay() {
		waitForElementVisible(driver, CheckoutPageUI.PAYMENT_INFORMATION);
		return isElementDisplayed(driver, CheckoutPageUI.PAYMENT_INFORMATION);
	}
}