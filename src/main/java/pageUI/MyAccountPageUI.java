package pageUI;

public class MyAccountPageUI {
	// Customer info
	public static final String DOB_DROPDOWN_LIST = "//select[@name='DateOfBirth%s']";
	public static final String DOB_DROPDOWN_OPTION = "//select[@name='DateOfBirth%s']/option[text()='%s']";

	public static final String CURRENT_GENDER_CHECKED = "//input[@name='Gender'][@checked]/following-sibling::label";
	public static final String INPUT_TEXT_VALUE_BY_LABEL = "//label[text()='%s']/following-sibling::input";
	public static final String CURREBT_DOB_OPTION_SELECTED = "//select[@name='DateOfBirth%s']/option[@selected]";

	// Addresses
	public static final String ADDRESS_SECTION = "//div[contains(@class, 'address-item')]";
	public static final String ADDRESS_INFO_BY_POSITION_BY_NAME = "//div[contains(@class, 'address-item')][%s]//li[@class='%s']";

	// My product reviews
	public static final String REVIEW_TITLE_TEXT = "//div[@class='product-review-item'][%s]//div[@class='review-title']/strong";
	public static final String REVIEW_TEXT_TEXT = "//div[@class='product-review-item'][%s]//div[@class='review-text']";

}