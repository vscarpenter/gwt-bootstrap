package com.github.gwtbootstrap.client.ui.constants;

//@formatter:off
/**
 * New Html5 attributes for &lt;input&gt;.
 * <ul>
 * 	<li>autocomplete</li>
 * 	<li>autofocus</li>
 * 	<li>form</li>
 * 	<li>formaction</li>
 * 	<li>formenctype</li>
 * 	<li>formmethod</li>
 * 	<li>formnovalidate</li>
 * 	<li>formtarget</li>
 * 	<li>height and width</li>
 * 	<li>list</li>
 * 	<li>min and max</li>
 * 	<li>multiple</li>
 * 	<li>pattern (regexp)</li>
 * 	<li>placeholder</li>
 * 	<li>required</li>
 * 	<li>step</li>
 * </ul>
 * 
 * @since 2.2.1.0
 * @author ivangsa
 *
 */
//@formatter:on
public enum InputAttributeType { 

    AUTOCOMPLETE("autocomplete"),
    AUTOFOCUS("autofocus"),
    FORM("form"),
    FORMACTION("formaction"),
    FORMENCTYPE("formenctype"),
    FORMMETHOD("formmethod"),
    FORMNOVALIDATE("formnovalidate"),
    FORMTARGET("formtarget"),
    HEIGHT("height"),
    WIDTH("width"),
    LIST("list"),
    MIN("min"),
    MAX("max"),
    MULTIPLE("multiple"),
    PATTERN("pattern"),
    PLACEHOLDER("placeholder"),
    REQUIRED("required"),
    STEP("step");
	
	private String value;

	/**
	 * @param string
	 */
	private InputAttributeType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}