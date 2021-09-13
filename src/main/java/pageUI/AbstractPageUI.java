package pageUI;

public class AbstractPageUI {
	public static final String HEADER_LOGO = "//div[@class='header']//div[@class='header-logo']";
	public static final String DYNAMIC_HEADER_LINK = "//div[@class='header']//a[text()='%s']";
	public static final String HEADER_WISHLIST_LINK = "//div[@class='header']//span[@class='wishlist-label']";
	public static final String HEADER_WISHLIST_QUANTITY_TEXT = "//div[@class='header']//span[@class='wishlist-qty']";
	public static final String HEADER_SHOPPING_CART_LINK = "//div[@class='header']//li[@id='topcartlink']/a";
	public static final String HEADER_SHOPPING_CART_QUANTITY_TEXT = "//div[@class='header']//li[@id='topcartlink']//span[@class='cart-qty']";

	public static final String DYNAMIC_FOOTER_LINK = "//div[@class='footer']//a[text()='%s']";

	public static final String DYNAMIC_MY_ACCOUNT_MENU = "//div[@class='listbox']//a[text()='%s']";

	public static final String DYNAMIC_PRODUCT_LINK = "//h2[@class='product-title']/a[text()='%s']";

	// New
	public static final String PAGE_TITLE_TEXT = "//div[@class='page-title']/h1";
	public static final String SUBMIT_FORM_BUTTON_BY_TEXT = "//form//button[@type='submit'][text()='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_LABEL = "//label[text()='%s']/following-sibling::input";
	public static final String DYNAMIC_TEXTAREA_BY_LABEL = "//label[text()='%s']/following-sibling::textarea";
	public static final String DYNAMIC_DROPDOWN_LIST_BY_LABEL = "//label[text()='%s']/following-sibling::select";
	public static final String DYNAMIC_DROPDOWN_OPTION_BY_LABEL = "//label[text()='%s']/following-sibling::select/option[text()='%s']";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "//button[contains(text(),'%s')]";
	public static final String DYNAMIC_RADIO_BUTTON_BY_LABEL = "//label[text()='%s']/preceding-sibling::input[@type='radio']";
	public static final String DYNAMIC_CHECKBOX_BY_LABEL = "//label[text()='%s']/preceding-sibling::input[@type='checkbox']";

	public static final String DYNAMIC_HEADER_MENU_LINK = "//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]";
	public static final String DYNAMIC_HEADER_SUBMENU_LINK = "//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]/following-sibling::ul//a[contains(text(),'%s')]";

	public static final String DYNAMIC_LINK_BY_TEXT = "//a[text()='Add your review']";

	public static final String NOTIFICATION_BAR_TEXT = "//div[@class='bar-notification %s']//p";
	public static final String NOTIFICATION_BAR_CLOSE_ICON = "//div[@id='bar-notification']//span[@class='close']";

	// Shopping cart popup
	public static final String COUNT_TEXT_IN_CART_POPUP = "//div[@id='flyout-cart']//div[@class='count']";
	public static final String PRODUCT_NAME_IN_CART_POPUP = "//div[@id='flyout-cart']//div[@class='name']/a";
	public static final String PRODUCT_ATTRIBUTE_IN_CART_POPUP = "//div[@id='flyout-cart']//div[@class='product']/div[@class='attributes']";
	public static final String TOTAL_PRICE_IN_CART_POPUP = "//div[@id='flyout-cart']//div[@class='totals']/strong";
}