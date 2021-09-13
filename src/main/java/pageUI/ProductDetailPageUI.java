package pageUI;

public class ProductDetailPageUI {
	public static final String PRODUCT_NAME_TEXT = "//div[@class='product-name']/h1";
	public static final String PRODUCT_CURRENT_QUANTIY = "//div[@class='add-to-cart']//input";
	
	public static final String REVIEW_TITLE_TEXTBOX = "//input[@id='AddProductReview_Title']";
	public static final String REVIEW_TEXT_TEXTBOX = "//textarea[@id='AddProductReview_ReviewText']";
	public static final String SUBMIT_REVIEW_BUTTON = "//input[@value='Submit review']";
	public static final String BODY_RESULT_TEXT = "//div[@class='result']";
	public static final String PRODUCT_PRICE = "//div[@class='product-price']/span";
	public static final String PRODUCT_PRICE_BY_TEXT = "//div[@class='product-price']/span[text()='%s']";

	public static final String RADIO_BUTTON_BY_LABEL = "//label[contains(text(),'%s')]/preceding-sibling::input[@type='radio']";
	public static final String CHECKBOX_BY_LABEL = "//label[contains(text(),'%s')]/preceding-sibling::input[@type='checkbox']";
	
	public static final String PRODUCT_ATTRIBUTE_LABEL_BY_TEXT = "//div[@class='attributes']//dt/label[contains(text(),'%s')]/parent::dt";
	public static final String PRODUCT_DROPDOWN_LIST_BY_ID = "//div[@class='attributes']//select[@id='%s']";
	public static final String PRODUCT_DROPDOWN_OPTION_BY_TEXT = "//div[@class='attributes']//select[@id='%s']/option[contains(text(),'%s')]";
}