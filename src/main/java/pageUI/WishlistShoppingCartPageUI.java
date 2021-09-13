package pageUI;

public class WishlistShoppingCartPageUI {
	public static final String NO_DATA_TEXT = "//div[@class='no-data']";

	public static final String DYNAMIC_COLUMN_POSITION = "//th[contains(text(),'%s')]/preceding-sibling::th";
	public static final String DYNAMIC_TEXT_BY_ROW_AND_COLUMN_INDEX = "//tr[%s]/td[%s]/span";
	public static final String DYNAMIC_LINK_BY_ROW_AND_COLUMN_INDEX = "//tr[%s]/td[%s]/a";
	public static final String DYNAMIC_BUTTON_BY_ROW_AND_COLUMN_INDEX = "//tr[%s]/td[%s]/button";
	public static final String DYNAMIC_INPUT_BY_ROW_AND_COLUMN_INDEX = "//tr[%s]/td[%s]/input";

	public static final String WISHLIST_SHARING_LINK = "//a[@class='share-link']";
	public static final String EDIT_PRODUCT_LINK_BY_NAME = "//a[@class='product-name'][text()='%s']/following-sibling::div/a[text()='Edit']";

	public static final String LOADING_ICON = "//div[@class='ajax-loading-block-window']";
	
	public static final String GIFT_WRAPPING_DROPDOWN_LIST = "//label[contains(text(),'Gift wrapping')]/parent::dt/following-sibling::dd/select";
	public static final String GIFT_WRAPPING_DROPDOWN_LIST_OPTION = "//label[contains(text(),'Gift wrapping')]/parent::dt/following-sibling::dd/select/option[contains(text(),'%s')]";
	
	public static final String CHECKOUT_ATTRIBUTE = "//div[@class='selected-checkout-attributes']";
	
	public static final String TERM_OF_SERVICE_CHECKBOX = "//input[@id='termsofservice']";
}