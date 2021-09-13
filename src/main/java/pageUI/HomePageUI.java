package pageUI;

public class HomePageUI {
	public static final String LOGIN_LINK = "//a[@class='ico-login']";
	public static final String MY_ACCOUNT_LINK = "//a[@class='ico-account']";

	public static final String DYNAMIC_PRODUCT_PRICE_TEXT = "//a[text()='%s']/parent::h2/following-sibling::div//span[@class='price actual-price']";
	public static final String DYNAMIC_ADD_TO_COMPARE_LIST_BUTTON = "//a[text()='%s']/parent::h2//following-sibling::div[@class='add-info']//input[@value='Add to compare list']";

	public static final String SUCCESS_NOTIFICATION_BAR = "//div[@class='bar-notification success']";
	public static final String CLOSE_SUCCESS_NOTIFICATION_BUTTON = "//div[@id='bar-notification']//span[@class='close']";
	
	// Product grid
	public static final String PRODUCT_LINK_BY_POSITION = "//div[contains(@class,'product-grid')]//div[@class='item-box'][%s]//h2/a";
	public static final String PRODUCT_PRICE_BY_POSITION = "//div[contains(@class,'product-grid')]//div[@class='item-box'][%s]//span[contains(@class,'price')]";
	public static final String PRODUCT_BUTTON_BY_POSITION_AND_TEXT = "//div[contains(@class,'product-grid')]//div[@class='item-box'][%s]//button[text()='%s']";
}