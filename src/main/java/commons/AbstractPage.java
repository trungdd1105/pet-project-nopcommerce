package commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.HomePageObjects;
import pageObjects.ProductDetailPageObjects;
import pageObjects.ProductPageObjects;
import pageObjects.WishlistShoppingCartPageObjects;
import pageUI.AbstractPageUI;

public abstract class AbstractPage {
	public void openUrl(WebDriver driver, String urlValue) {
		driver.get(urlValue);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void sendkeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public String getTextInAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void waitForAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public By byXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement findElementByXpath(WebDriver driver, String locator) {
		return driver.findElement(byXpath(locator));
	}

	public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
		return driver.findElements(byXpath(locator));
	}

	public String castObject(String locator, String... values) {
		return String.format(locator, (Object[]) values);
	}

	public void clickElement(WebDriver driver, String locator) {
		sleepInMiliSecond(200);
		findElementByXpath(driver, locator).click();
	}

	public void clickElement(WebDriver driver, String locator, String... values) {
		sleepInMiliSecond(200);
		findElementByXpath(driver, castObject(locator, values)).click();
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		element = findElementByXpath(driver, locator);
		element.clear();
		element.sendKeys(value);
	}

	public void sendkeyToElement(WebDriver driver, String locator, String inputValue, String... values) {
		element = findElementByXpath(driver, castObject(locator, values));
		element.clear();
		element.sendKeys(inputValue);
	}

	public String getElementText(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).getText();
	}

	public String getElementText(WebDriver driver, String locator, String... values) {
		return findElementByXpath(driver, castObject(locator, values)).getText();
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return findElementByXpath(driver, locator).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... values) {
		return findElementByXpath(driver, castObject(locator, values)).getAttribute(attributeName);
	}

	public boolean isAttribtuePresent(WebDriver driver, String locator, String attributeName) {
		Boolean result = false;
		element = findElementByXpath(driver, locator);
		try {
			String value = element.getAttribute(attributeName);
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
		}

		return result;
	}

	public boolean isAttribtuePresent(WebDriver driver, String locator, String attributeName, String... values) {
		Boolean result = false;
		element = findElementByXpath(driver, castObject(locator, values));
		try {
			String value = element.getAttribute(attributeName);
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
		}

		return result;
	}

	public void selectItemInDropdown(WebDriver driver, String locator, String option) {
		select = new Select(findElementByXpath(driver, locator));
		select.selectByVisibleText(option);
	}
	
	public void selectItemInDropdown(WebDriver driver, String locator, String option, String... values) {
		select = new Select(findElementByXpath(driver, castObject(locator, values)));
		select.selectByVisibleText(option);
	}

	public String getSelectedItemInDropdown(WebDriver driver, String locator) {
		select = new Select(findElementByXpath(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String allItemXpath,
			String expectedItem) {
		element = findElementByXpath(driver, parentLocator);

		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click()", element);
		sleepInSecond(1);

		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(allItemXpath)));

		elements = findElementsByXpath(driver, allItemXpath);
		for (WebElement item : elements) {
			if (item.getText().equals(expectedItem)) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(1);
					jsExecutor.executeScript("arguments[0].click()", item);
				}
				sleepInSecond(1);
				break;
			}
		}
	}

	public boolean isDataSortedAscending(WebDriver driver, String locator) {
		ArrayList<String> arrayList = new ArrayList<String>();
		List<WebElement> elementList = findElementsByXpath(driver, locator);
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		ArrayList<String> sortedList = new ArrayList<String>();
		for (String child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		return sortedList.equals(arrayList);
	}

	public boolean isDataSortedDescending(WebDriver driver, String locator) {
		ArrayList<String> arrayList = new ArrayList<String>();
		List<WebElement> elementList = findElementsByXpath(driver, locator);
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		ArrayList<String> sortedList = new ArrayList<String>();
		for (String child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		Collections.reverse(arrayList);
		return sortedList.equals(arrayList);
	}

	public boolean isPriceSortedAscending(WebDriver driver, String locator) {
		ArrayList<Float> arrayList = new ArrayList<Float>();
		List<WebElement> elementList = findElementsByXpath(driver, locator);
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").replace(",", "").trim()));
		}
		ArrayList<Float> sortedList = new ArrayList<Float>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		return sortedList.equals(arrayList);
	}

	public boolean isPriceSortedDescending(WebDriver driver, String locator) {
		ArrayList<Float> arrayList = new ArrayList<Float>();
		List<WebElement> elementList = findElementsByXpath(driver, locator);
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").replace(",", "").trim()));
		}
		ArrayList<Float> sortedList = new ArrayList<Float>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(arrayList);
		Collections.reverse(arrayList);
		return sortedList.equals(arrayList);
	}

	public int countElementNumber(WebDriver driver, String locator) {
		elements = findElementsByXpath(driver, locator);
		return elements.size();
	}

	public int countElementNumber(WebDriver driver, String locator, String... values) {
		elements = findElementsByXpath(driver, castObject(locator, values));
		return elements.size();
	}

	public void checkACheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkACheckbox(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, castObject(locator, values));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckACheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void uncheckACheckbox(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, castObject(locator, values));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			return findElementByXpath(driver, locator).isDisplayed();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
		return findElementByXpath(driver, castObject(locator, values)).isDisplayed();
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = findElementsByXpath(driver, locator);

		if (elements.size() == 0) {
			System.out.println("Element is not in DOM");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element is in DOM but not displayed");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else {
			System.out.println("Element is in DOM and displayed");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator, String... values) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = findElementsByXpath(driver, castObject(locator, values));

		if (elements.size() == 0) {
			System.out.println("Element is not in DOM");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element is in DOM but not displayed");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else {
			System.out.println("Element is in DOM and displayed");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isSelected();
	}

	public void swtichToIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(findElementByXpath(driver, locator));
	}

	public void swtichToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(findElementByXpath(driver, locator)).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locator, String... values) {
		action = new Actions(driver);
		action.moveToElement(findElementByXpath(driver, castObject(locator, values))).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(findElementByXpath(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(findElementByXpath(driver, locator)).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, locator), key).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key, String... values) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, castObject(locator, values)), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaSript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaSript);
	}

	public boolean verifyTextInInnerText(WebDriver driver, String expectedText) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0]");
		return textActual.equals(expectedText);
	}

	public void scrollToTopPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollTo(0,0)");
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public boolean waitForJStoLoad(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 3px solid red; border-style: dashed");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click()", findElementByXpath(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", findElementByXpath(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')",
				findElementByXpath(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "')",
				findElementByXpath(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove, String values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "')",
				findElementByXpath(driver, castObject(locator, values)));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth !=\'undefined\' && arguments[0].naturalWidth > 0",
				findElementByXpath(driver, locator));
		if (status) {
			return true;
		}
		return false;
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castObject(locator, values))));
	}

	public void waitForElementsVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(locator)));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
	}

	public void waitForElementsInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		elements = findElementsByXpath(driver, locator);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(castObject(locator, values))));
	}
	
	public void waitForElementStale(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		element = findElementByXpath(driver, locator);
		explicitWait.until(ExpectedConditions.stalenessOf(element));
	}
	
	public void waitForElementStale(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		element = findElementByXpath(driver, castObject(locator, values));
		explicitWait.until(ExpectedConditions.stalenessOf(element));
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void sleepInMiliSecond(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	// ======================== Start NopCommerce ========================
	public void clickDynamicHeaderLink(WebDriver driver, String linkText) {
		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_HEADER_LINK, linkText);
		clickElement(driver, AbstractPageUI.DYNAMIC_HEADER_LINK, linkText);
	}

	public void clickDynamicFooterLink(WebDriver driver, String linkText) {
		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_FOOTER_LINK, linkText);
		clickElement(driver, AbstractPageUI.DYNAMIC_FOOTER_LINK, linkText);
	}

	public void clickDynamicMyAccountMenu(WebDriver driver, String pageName) {
		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_MY_ACCOUNT_MENU, pageName);
		clickElement(driver, AbstractPageUI.DYNAMIC_MY_ACCOUNT_MENU, pageName);
	}

	public ProductPageObjects goToDynamicHeaderSubMenuLink(WebDriver driver, String menu, String submenu) {
		hoverMouseToElement(driver, AbstractPageUI.DYNAMIC_HEADER_MENU_LINK, menu);
		clickElement(driver, AbstractPageUI.DYNAMIC_HEADER_SUBMENU_LINK, menu, submenu);
		return PageGenerator.getProductPage(driver);
	}

	public void clickDynamicLinkByText(WebDriver driver, String text) {
		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_LINK_BY_TEXT, text);
		clickElement(driver, AbstractPageUI.DYNAMIC_LINK_BY_TEXT, text);
	}

	public ProductDetailPageObjects clickDynamicNopCommerceProductLink(WebDriver driver, String productName) {
		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_PRODUCT_LINK, productName);
		clickElement(driver, AbstractPageUI.DYNAMIC_PRODUCT_LINK, productName);
		return PageGenerator.getProductDetailPage(driver);
	}

	public WishlistShoppingCartPageObjects clickHeaderWishlistLink(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.HEADER_WISHLIST_LINK);
		clickElement(driver, AbstractPageUI.HEADER_WISHLIST_LINK);
		return PageGenerator.getWishlistShoppingCartPage(driver);
	}

	public String getHeaderWishlistQuantityText(WebDriver driver) {
		waitForElementVisible(driver, AbstractPageUI.HEADER_WISHLIST_QUANTITY_TEXT);
		return getElementText(driver, AbstractPageUI.HEADER_WISHLIST_QUANTITY_TEXT);
	}

	public String getHeaderShoppingCartQuantityText(WebDriver driver) {
		waitForElementVisible(driver, AbstractPageUI.HEADER_SHOPPING_CART_QUANTITY_TEXT);
		return getElementText(driver, AbstractPageUI.HEADER_SHOPPING_CART_QUANTITY_TEXT);
	}
	
	// Shopping cart
	public void hoverMouseToHeaderShoppingCartLink(WebDriver driver) {
		waitForElementVisible(driver, AbstractPageUI.HEADER_SHOPPING_CART_LINK);
		hoverMouseToElement(driver, AbstractPageUI.HEADER_SHOPPING_CART_LINK);
	}

	public String getCountTextInCartPopup(WebDriver driver) {
		waitForElementVisible(driver, AbstractPageUI.COUNT_TEXT_IN_CART_POPUP);
		return getElementText(driver, AbstractPageUI.COUNT_TEXT_IN_CART_POPUP);
	}
	
	public String getProductNameInCartPopup(WebDriver driver) {
		waitForElementVisible(driver, AbstractPageUI.PRODUCT_NAME_IN_CART_POPUP);
		return getElementText(driver, AbstractPageUI.PRODUCT_NAME_IN_CART_POPUP);
	}
	
	public String getProductAttributeTextInCartPopup(WebDriver driver) {
		waitForElementVisible(driver, AbstractPageUI.PRODUCT_ATTRIBUTE_IN_CART_POPUP);
		return getElementText(driver, AbstractPageUI.PRODUCT_ATTRIBUTE_IN_CART_POPUP);
	}

	public String getTotalPriceInCartPopup(WebDriver driver) {
		waitForElementVisible(driver, AbstractPageUI.TOTAL_PRICE_IN_CART_POPUP);
		return getElementText(driver, AbstractPageUI.TOTAL_PRICE_IN_CART_POPUP);
	}

	public WishlistShoppingCartPageObjects clickHeaderShoppingCartLink(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.HEADER_SHOPPING_CART_LINK);
		clickElement(driver, AbstractPageUI.HEADER_SHOPPING_CART_LINK);
		return PageGenerator.getWishlistShoppingCartPage(driver);
	}

	public void inputToDynamicTextboxByLabel(WebDriver driver, String labelName, String inputValue) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_LABEL, labelName);
		clickElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_LABEL, labelName);
		sendkeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_BY_LABEL, inputValue, labelName);
	}

	public void inputToDynamicTextareaByLabel(WebDriver driver, String labelName, String inputValue) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTAREA_BY_LABEL, labelName);
		clickElement(driver, AbstractPageUI.DYNAMIC_TEXTAREA_BY_LABEL, labelName);
		sendkeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTAREA_BY_LABEL, inputValue, labelName);
	}

	public void selectDynamicOptionByLabel(WebDriver driver, String label, String value) {
		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_DROPDOWN_LIST_BY_LABEL, label);
		clickElement(driver, AbstractPageUI.DYNAMIC_DROPDOWN_LIST_BY_LABEL, label);
		clickElement(driver, AbstractPageUI.DYNAMIC_DROPDOWN_OPTION_BY_LABEL, label, value);
	}

	public void clickSubmitFormButtonByText(WebDriver driver, String valueName) {
		waitForElementClickable(driver, AbstractPageUI.SUBMIT_FORM_BUTTON_BY_TEXT, valueName);
		clickElement(driver, AbstractPageUI.SUBMIT_FORM_BUTTON_BY_TEXT, valueName);
	}

	public String getPageTitleText(WebDriver driver) {
		waitForElementVisible(driver, AbstractPageUI.PAGE_TITLE_TEXT);
		return getElementText(driver, AbstractPageUI.PAGE_TITLE_TEXT);
	}

	public void clickDynamicButtonByText(WebDriver driver, String buttonText) {
		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickElement(driver, AbstractPageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
	}

	public void clickDynamicRadioByLabel(WebDriver driver, String inputType, String label) {
		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, label, inputType);
		clickElement(driver, AbstractPageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, label, inputType);
	}

	public void clickDynamicCheckboxByLabel(WebDriver driver, String action, String label) {
		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_CHECKBOX_BY_LABEL, label);
		if (action == "check") {
			checkACheckbox(driver, AbstractPageUI.DYNAMIC_CHECKBOX_BY_LABEL, label);
		} else if (action == "uncheck") {
			uncheckACheckbox(driver, AbstractPageUI.DYNAMIC_CHECKBOX_BY_LABEL, label);
		}
	}

	public String getNotificationBarText(WebDriver driver, String notiType) {
		waitForElementVisible(driver, AbstractPageUI.NOTIFICATION_BAR_TEXT, notiType);
		return getElementText(driver, AbstractPageUI.NOTIFICATION_BAR_TEXT, notiType);
	}

	public void clickCloseNotificationBar(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.NOTIFICATION_BAR_CLOSE_ICON);
		clickElement(driver, AbstractPageUI.NOTIFICATION_BAR_CLOSE_ICON);
		waitForElementStale(driver, AbstractPageUI.NOTIFICATION_BAR_CLOSE_ICON);
	}

	public HomePageObjects clickHeaderLogo(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.NOTIFICATION_BAR_CLOSE_ICON);
		clickElement(driver, AbstractPageUI.NOTIFICATION_BAR_CLOSE_ICON);
		return PageGenerator.getHomePage(driver);
	}

	// ======================== End NopCommerce ========================

	private Select select;
	private Actions action;
	private WebElement element;
	private List<WebElement> elements;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
}