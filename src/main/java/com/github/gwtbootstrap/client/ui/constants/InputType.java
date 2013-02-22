package com.github.gwtbootstrap.client.ui.constants;

/**
 * New Html5 input types.
 * 
 * @since 2.2.1.0
 * @author ivangsa
 *
 */
public enum InputType {

	// Html4
	TEXT("text"),
	PASSWORD("password"),
	BUTTON("button"),
	IMAGE("image"),
	CHECKBOX("checkbox"),
	RADIO("radio"),
	FILE("file"),
	HIDDEN("hidden"),
	RESET("reset"),
	SUBMIT("submit"),

	// Html5
	COLOR("color"),
	DATE("date"),
	DATETIME("datetime"),
	DATETIME_LOCAL("datetime-local"),
	EMAIL("email"),
	MONTH("month"),
	NUMBER("number"),
	RANGE("range"),
	SEARCH("search"),
	TEL("tel"),
	TIME("time"),
	URL("url"),
	WEEK("week");
	
	public static final String ATTRIBUTE_NAME = "type";
	
	private String value;

	/**
	 * @param string
	 */
	private InputType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}