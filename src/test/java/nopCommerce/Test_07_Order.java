package nopCommerce;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGenerator;
import pageObjects.CheckoutPageObjects;
import pageObjects.HomePageObjects;
import pageObjects.ProductDetailPageObjects;
import pageObjects.ProductPageObjects;
import pageObjects.RegisterPageObjects;
import pageObjects.WishlistShoppingCartPageObjects;

public class Test_07_Order extends AbstractTest {
	WebDriver driver;
	HomePageObjects homePage;
	RegisterPageObjects registerPage;
	ProductPageObjects productPage;
	ProductDetailPageObjects productDetailPage;
	WishlistShoppingCartPageObjects shoppingCartPage;
	CheckoutPageObjects checkoutPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.NOPCOMMERCE_URL);
		homePage = PageGenerator.getHomePage(driver);

		homePage.clickDynamicHeaderLink(driver, "Register");
		registerPage = PageGenerator.getRegisterPage(driver);
		verifyEquals(registerPage.getPageTitleText(), "Register");
		registerPage.inputToDynamicRegisterTextboxByLabel("First name:", "Trung");
		registerPage.inputToDynamicRegisterTextboxByLabel("Last name:", "Dao");
		registerPage.inputToDynamicRegisterTextboxByLabel("Email:", "trung.dao" + randomNumber() + "@gmail.com");
		registerPage.inputToDynamicRegisterTextboxByLabel("Password:", "123456");
		registerPage.inputToDynamicRegisterTextboxByLabel("Confirm password:", "123456");
		registerPage.clickRegisterSubmitButton();
		verifyEquals(registerPage.getResultText(), "Your registration completed");
	}

	String productName = "Build your own computer";

	@Test
	public void TC_01_Add_Product_to_Cart() {
		productPage = registerPage.goToDynamicHeaderSubMenuLink(driver, "Computers", "Desktops");
		productDetailPage = productPage.clickProductLinkByName(productName);
		verifyEquals(productDetailPage.getProductNameText(), productName);

		String attribute1 = "2.5 GHz";
		String attribute2 = "8GB";
		String attribute3 = "400 GB";
		String attribute4 = "Vista Premium";
		String attribute5 = "Microsoft Office";
		String attribute6 = "Acrobat Reader";
		String attribute7 = "Total Commander";

		productDetailPage.selectDropdownListOptionByLabel("Processor", attribute1);
		productDetailPage.selectDropdownListOptionByLabel("RAM", attribute2);
		productDetailPage.clickRadioButtonByLabel(attribute3);
		productDetailPage.clickRadioButtonByLabel(attribute4);
		productDetailPage.checkACheckboxByLabel(attribute5);
		productDetailPage.checkACheckboxByLabel(attribute6);
		productDetailPage.checkACheckboxByLabel(attribute7);
		String productPrice = productDetailPage.getProductPrice();

		productDetailPage.clickButton("Add to cart");
		verifyEquals(productDetailPage.getNotificationBarText(), "The product has been added to your shopping cart");
		productDetailPage.clickCloseNotificationBar();

		verifyEquals(productDetailPage.getHeaderShoppingCartQuantityText(driver), "(1)");
		productDetailPage.hoverMouseToHeaderShoppingCartLink(driver);
		verifyEquals(productDetailPage.getCountTextInCartPopup(driver), "There are 1 item(s) in your cart.");
		verifyEquals(productDetailPage.getProductNameInCartPopup(driver), productName);
		verifyTrue(productDetailPage.getProductAttributeTextInCartPopup(driver).contains(attribute1));
		verifyTrue(productDetailPage.getProductAttributeTextInCartPopup(driver).contains(attribute2));
		verifyTrue(productDetailPage.getProductAttributeTextInCartPopup(driver).contains(attribute3));
		verifyTrue(productDetailPage.getProductAttributeTextInCartPopup(driver).contains(attribute4));
		verifyTrue(productDetailPage.getProductAttributeTextInCartPopup(driver).contains(attribute5));
		verifyTrue(productDetailPage.getProductAttributeTextInCartPopup(driver).contains(attribute6));
		verifyTrue(productDetailPage.getProductAttributeTextInCartPopup(driver).contains(attribute7));
		verifyEquals(productDetailPage.getTotalPriceInCartPopup(driver), productPrice);
	}

	@Test
	public void TC_02_Edit_Product_in_Shopping_Cart() {
		shoppingCartPage = productDetailPage.clickHeaderShoppingCartLink(driver);
		verifyEquals(shoppingCartPage.getPageTitleText(), "Shopping cart");

		String attribute1 = "2.2 GHz";
		String attribute2 = "4GB";
		String attribute3 = "320 GB";
		String attribute4 = "Vista Home";
		String attribute5 = "Microsoft Office";
		String attribute6 = "Acrobat Reader";
		String attribute7 = "Total Commander";

		productDetailPage = shoppingCartPage.clickEditProductLink(productName);
		productDetailPage.selectDropdownListOptionByLabel("Processor", attribute1);
		productDetailPage.selectDropdownListOptionByLabel("RAM", attribute2);
		productDetailPage.clickRadioButtonByLabel(attribute3);
		productDetailPage.clickRadioButtonByLabel(attribute4);
		productDetailPage.checkACheckboxByLabel(attribute5);
		productDetailPage.uncheckACheckboxByLabel(attribute6);
		productDetailPage.uncheckACheckboxByLabel(attribute7);
		productDetailPage.inputToProductQuantity("2");
		productDetailPage.clickButton("Update");
		verifyTrue(productDetailPage.findProductPriceByText("$1,320.00"));

		verifyEquals(productDetailPage.getNotificationBarText(), "The product has been added to your shopping cart");
		productDetailPage.clickCloseNotificationBar();

		verifyEquals(productDetailPage.getHeaderShoppingCartQuantityText(driver), "(2)");
		productDetailPage.hoverMouseToHeaderShoppingCartLink(driver);
		verifyEquals(productDetailPage.getCountTextInCartPopup(driver), "There are 2 item(s) in your cart.");
		verifyEquals(productDetailPage.getProductNameInCartPopup(driver), productName);
		verifyTrue(productDetailPage.getProductAttributeTextInCartPopup(driver).contains(attribute1));
		verifyTrue(productDetailPage.getProductAttributeTextInCartPopup(driver).contains(attribute2));
		verifyTrue(productDetailPage.getProductAttributeTextInCartPopup(driver).contains(attribute3));
		verifyTrue(productDetailPage.getProductAttributeTextInCartPopup(driver).contains(attribute4));
		verifyTrue(productDetailPage.getProductAttributeTextInCartPopup(driver).contains(attribute5));
		verifyEquals(productDetailPage.getTotalPriceInCartPopup(driver), "$2,640.00");
	}

	@Test
	public void TC_03_Remove_from_Cart() {
		shoppingCartPage = productDetailPage.clickHeaderShoppingCartLink(driver);
		verifyEquals(shoppingCartPage.getPageTitleText(), "Shopping cart");

		shoppingCartPage.clickButtonAtRowNumber("Remove", "1");
		verifyEquals(shoppingCartPage.getNoDataText(), "Your Shopping Cart is empty!");
		verifyEquals(shoppingCartPage.getHeaderShoppingCartQuantityText(driver), "(0)");
		shoppingCartPage.hoverMouseToHeaderShoppingCartLink(driver);
		verifyEquals(shoppingCartPage.getCountTextInCartPopup(driver), "You have no items in your shopping cart.");
	}

	@Test
	public void TC_04_Update_Shopping_Cart() {
		productPage = registerPage.goToDynamicHeaderSubMenuLink(driver, "Computers", "Desktops");
		verifyEquals(productPage.getPageTitleText(), "Desktops");

		String productName = "Lenovo IdeaCentre 600 All-in-One PC";
		productDetailPage = productPage.clickDynamicNopCommerceProductLink(driver, productName);
		verifyEquals(productDetailPage.getProductNameText(), productName);

		productDetailPage.clickButton("Add to cart");
		verifyEquals(productDetailPage.getNotificationBarText(), "The product has been added to your shopping cart");
		productDetailPage.clickCloseNotificationBar();

		shoppingCartPage = productDetailPage.clickHeaderShoppingCartLink(driver);
		verifyEquals(shoppingCartPage.getPageTitleText(), "Shopping cart");
		verifyTrue(shoppingCartPage.isLoadingIconUndisplayed());

		shoppingCartPage.inputToTextboxAtRowNumber("5", "Qty.", "1");
		shoppingCartPage.clickButton("Update shopping cart");
		verifyTrue(shoppingCartPage.isLoadingIconUndisplayed());
		verifyEquals(shoppingCartPage.getTextAtRowNumber("Total", "1"), "$2,500.00");
	}

	@Test
	public void TC_05_Checkout_Order() {
		productPage = registerPage.goToDynamicHeaderSubMenuLink(driver, "Computers", "Notebooks");
		verifyEquals(productPage.getPageTitleText(), "Notebooks");

		String productName = "Apple MacBook Pro 13-inch";
		productDetailPage = productPage.clickDynamicNopCommerceProductLink(driver, productName);
		verifyEquals(productDetailPage.getProductNameText(), productName);

		productDetailPage.clickButton("Add to cart");
		verifyEquals(productDetailPage.getNotificationBarText(), "The product has been added to your shopping cart");
		productDetailPage.clickCloseNotificationBar();

		shoppingCartPage = productDetailPage.clickHeaderShoppingCartLink(driver);
		verifyEquals(shoppingCartPage.getPageTitleText(), "Shopping cart");
		verifyTrue(shoppingCartPage.isLoadingIconUndisplayed());

		shoppingCartPage.chooseGiftWrappingOption("No");
		verifyTrue(shoppingCartPage.getCheckoutAttributeText().contains("Gift wrapping: No"));

		shoppingCartPage.checkTermOfServiceCheckbox();
		shoppingCartPage.clickButton("Checkout");
		checkoutPage = PageGenerator.getCheckoutPage(driver);
		verifyEquals(checkoutPage.getPageTitleText(), "Checkout");

		verifyEquals(checkoutPage.getActiveTabTitleText(), "Billing address");
		checkoutPage.clickDynamicCheckboxByLabel(driver, "uncheck", "Ship to the same address");
		checkoutPage.inputToDynamicTextboxByLabel(driver, "First name:", "Automation");
		checkoutPage.inputToDynamicTextboxByLabel(driver, "Last name:", "Test");
		checkoutPage.inputToDynamicTextboxByLabel(driver, "Email:", "automationfc.vn@gmail.com");
		checkoutPage.selectDynamicOptionByLabel(driver, "Country:", "Viet Nam");
		checkoutPage.selectDynamicOptionByLabel(driver, "State / province:", "Other");
		checkoutPage.inputToDynamicTextboxByLabel(driver, "City:", "Hanoi");
		checkoutPage.inputToDynamicTextboxByLabel(driver, "Address 1:", "123 Lo Duc");
		checkoutPage.inputToDynamicTextboxByLabel(driver, "Zip / postal code:", "100000");
		checkoutPage.inputToDynamicTextboxByLabel(driver, "Phone number:", "0123456789");
		checkoutPage.clickContinueButton();

		verifyEquals(checkoutPage.getActiveTabTitleText(), "Shipping address");
		checkoutPage.clickContinueButton();

		verifyEquals(checkoutPage.getActiveTabTitleText(), "Shipping method");
		checkoutPage.clickContinueButton();

		verifyEquals(checkoutPage.getActiveTabTitleText(), "Payment method");
		checkoutPage.clickContinueButton();

		verifyEquals(checkoutPage.getActiveTabTitleText(), "Payment information");
		verifyTrue(checkoutPage.isPaymentInformationDisplay());
		checkoutPage.clickContinueButton();

		verifyEquals(checkoutPage.getActiveTabTitleText(), "Confirm order");
		productDetailPage.clickButton("Confirm");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}