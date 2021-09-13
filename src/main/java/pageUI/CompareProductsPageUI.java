package pageUI;

public class CompareProductsPageUI {
	public static final String REMOVE_BUTTONS = "//tr[@class='remove-product']//button";
	public static final String PRODUCT_NAMES = "//tr[@class='product-name']//a";
	public static final String PRICE_TEXT_BY_INDEX = "//tr[@class='product-price'] /td[%s]";
	public static final String DYNAMIC_COLUMN_POSITION = "//tr[@class='product-name']//a[text()='%s']/parent::td/preceding-sibling::td";

	public static final String CLEAR_LIST_BUTTON_LINK = "//a[@class='clear-list']";
	public static final String NO_DATA_TEXT = "//div[@class='no-data']";
}