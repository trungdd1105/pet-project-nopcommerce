package pageUI;

public class RecentlyViewedProductsPageUI {
	public static final String PAGE_TITLE_TEXT = "//div[@class='page-title']/h1";
	public static final String PRODUCT_ITEM_BOXES = "//div[@class='item-box']";
	public static final String PRODUCT_NAMES = "//div[@class='item-box']//h2[@class='product-title']/a";
	public static final String PRODUCT_NAME_BY_INDEX = "//div[@class='item-box'][%s]//h2[@class='product-title']/a";
	public static final String FIND_PRODUCT_BY_TITLE = "//div[@class='item-box'][1]//h2[@class='product-title']/a[text()='%s']";
}