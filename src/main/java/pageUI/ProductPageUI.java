package pageUI;

public class ProductPageUI {
	public static final String SORT_BY_DROPDOWN = "//select[@id='products-orderby']";
	public static final String DISPLAY_DROPDOWN = "//select[@id='products-pagesize']";
	public static final String AJAX_LOADER_ICON = "//div[@class='ajax-products-busy']";

	public static final String PRODUCT_ITEMS = "//div[@class='item-box']";
	public static final String PRODUCT_ITEM_LINKS = "//div[@class='item-box']//h2/a";
	public static final String PRODUCT_LINK_BY_POSITION = "//div[@class='item-box'][%s]//h2/a";
	public static final String PRODUCT_LINK_BY_NAME = "//div[@class='item-box']//h2/a[text()='%s']";
	public static final String PRODUCT_ITEM_PRICE = "//div[@class='item-box']//span[contains(@class,'price')]";

	public static final String PAGING_BAR = "//div[@class='pager']";
	public static final String NEXT_PAGE_ICON = "//li[@class='next-page']";
	public static final String PREVIOUS_PAGE_ICON = "//li[@class='previous-page']";
	public static final String CURRENT_PAGE_NUMBER = "//li[@class='current-page']/span";
}