package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGenerator;
import pageUI.HomePageUI;

public class HomePageObjects extends AbstractPage {
	WebDriver driver;

	public HomePageObjects(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getNotificationBarText() {
		return getNotificationBarText(driver, "success");
	}

	public void clickCloseNotificationBar() {
		clickCloseNotificationBar(driver);
	}

	public void clickHeaderLinkText(String linkText) {
		clickDynamicHeaderLink(driver, linkText);
	}

	public boolean isMyAccountLinkDisplayedOnHeader() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	public ProductPageObjects goToHeaderSubMenuLink(String menu, String submenu) {
		return goToDynamicHeaderSubMenuLink(driver, menu, submenu);
	}

	public String getProductNameByPosition(String position) {
		waitForElementVisible(driver, HomePageUI.PRODUCT_LINK_BY_POSITION, position);
		return getElementText(driver, HomePageUI.PRODUCT_LINK_BY_POSITION, position);
	}

	public void clickProductButtonByPositionAndText(String position, String buttonText) {
		waitForElementClickable(driver, HomePageUI.PRODUCT_BUTTON_BY_POSITION_AND_TEXT, position, buttonText);
		clickElement(driver, HomePageUI.PRODUCT_BUTTON_BY_POSITION_AND_TEXT, position, buttonText);
	}
	
	public String getProductPriceByPosition(String position) {
		waitForElementVisible(driver, HomePageUI.PRODUCT_PRICE_BY_POSITION, position);
		return getElementText(driver, HomePageUI.PRODUCT_PRICE_BY_POSITION, position);
	}
	
	public ProductDetailPageObjects clickProductItemByPosition(String position) {
		waitForElementClickable(driver, HomePageUI.PRODUCT_LINK_BY_POSITION, position);
		clickElement(driver, HomePageUI.PRODUCT_LINK_BY_POSITION, position);
		return PageGenerator.getProductDetailPage(driver);
	}
}