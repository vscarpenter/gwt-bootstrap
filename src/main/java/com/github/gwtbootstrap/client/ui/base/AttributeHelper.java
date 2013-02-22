/**
 * 
 */
package com.github.gwtbootstrap.client.ui.base;

import com.google.gwt.dom.client.Element;

/**
 * @author ivangsa
 *
 */
public class AttributeHelper {

	public static String getAttribute(Element element, String attribute) {
		return element.hasAttribute(attribute) 
				? element.getAttribute(attribute)
				: "";		
	}
	
	public static Integer getAttributeAsInteger(Element element, String attribute) {
		String value = getAttribute(element, attribute);
		if(isNaN(value)) {
			return null;
		} else {
			return Integer.parseInt(value);
		}
	}
	
	public static boolean getAttributeAsBoolean(Element element, String attribute) {
		String value = getAttribute(element, attribute);
		return value != null && ("on".equals(value) || value.equalsIgnoreCase(attribute));
	}
	
	
	public static void setAttribute(Element element, String attribute, String value) {
		if (value == null || value.trim().isEmpty()) {
			element.removeAttribute(attribute);
			return;
		}
		element.setAttribute(attribute, value);		
	}
	
	public static void setAttribute(Element element, String attribute, boolean value) {
		if (value) {
			element.setAttribute(attribute, attribute);		
		}else {
			element.removeAttribute(attribute);
		}
	}
	
	public static void setAttribute(Element element, String attribute, Integer value) {
		if (value != null) {
			element.setAttribute(attribute, String.valueOf(value));		
		}else {
			element.removeAttribute(attribute);
		}
	}

	
	public static final native boolean isNaN(String value) /*-{ return value != null || isNaN(value); }-*/;
}
