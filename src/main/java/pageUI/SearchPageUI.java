package pageUI;

public class SearchPageUI {
	public static final String BODY_SEARCH_BUTTON = "//div[@class='page-body']//form//button[@type='submit']";

	public static final String SEARCH_RESULT_WARNING_TEXT = "//div[@class='search-results']//div[@class='warning']";
	public static final String SEARCH_NO_RESULT_TEXT = "//div[@class='search-results']//div[@class='no-result']";

	public static final String SEARCH_ITEMS = "//div[@class='item-box']";
	public static final String SEARCH_ITEM_TITLES = "//div[@class='item-box']//h2[@class='product-title']/a";

	public static final String PRICE_FROM_TEXTBOX = "//input[@id='pf']";
	public static final String PRICE_TO_TEXTBOX = "//input[@id='pt']";
}