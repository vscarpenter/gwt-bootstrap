package com.github.gwtbootstrap.client.ui.constants;

/**
 * New Html5 form attributes.
 * 
 * @since 2.2.1.0
 * @author ivangsa
 */
public enum FormAttributeType { 

	NOVALIDATE("novalidate"),
    AUTOCOMPLETE("autocomplete");
	
	private String value;

	/**
	 * @param string
	 */
	private FormAttributeType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}