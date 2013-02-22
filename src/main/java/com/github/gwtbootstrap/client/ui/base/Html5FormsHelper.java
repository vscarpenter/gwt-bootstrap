/**
 * 
 */
package com.github.gwtbootstrap.client.ui.base;

import com.github.gwtbootstrap.client.ui.constants.Constants;
import com.google.gwt.dom.client.Element;
import com.google.gwt.text.client.IntegerParser;

import static com.github.gwtbootstrap.client.ui.constants.InputAttributeType.*;
import static com.github.gwtbootstrap.client.ui.constants.FormAttributeType.NOVALIDATE;
/**
 * 
 *
<p>New attributes for &lt;form&gt;:</p>
<ul>
	<li>autocomplete</li>
	<li>novalidate</li>
</ul>
<p>New attributes for &lt;input&gt;:</p>
<ul>
	<li>autocomplete</li>
	<li>autofocus</li>
	<li>form</li>
	<li>formaction</li>
	<li>formenctype</li>
	<li>formmethod</li>
	<li>formnovalidate</li>
	<li>formtarget</li>
	<li>height and width</li>
	<li>list</li>
	<li>min and max</li>
	<li>multiple</li>
	<li>pattern (regexp)</li>
	<li>placeholder</li>
	<li>required</li>
	<li>step</li>
</ul>
 * @author ivangsa
 *
 */
public class Html5FormsHelper {

	private Element element;
	
	/**
	 * @param element
	 */
	public Html5FormsHelper(Element element) {
		super();
		this.element = element;
	}

	public Element getElement() {
		return element;
	}
	
	public void setElement(Element element) {
		this.element = element;
	}	

	public void setNovalidate(boolean novalidate) {
		AttributeHelper.setAttribute(getElement(), NOVALIDATE.getValue(), novalidate);
	}

	public void setAutocomplete(boolean autocomplete) {
		AttributeHelper.setAttribute(getElement(), AUTOCOMPLETE.getValue(), autocomplete);
	}

	public void setAutofocus(boolean autofocus) {
		AttributeHelper.setAttribute(getElement(), AUTOFOCUS.getValue(), autofocus);
	}

	public void setForm(String form) {
		AttributeHelper.setAttribute(getElement(), FORM.getValue(), form);
	}

	public void setFormaction(String formaction) {
		AttributeHelper.setAttribute(getElement(), FORMACTION.getValue(), formaction);
	}

	public void setFormenctype(String formenctype) {
		AttributeHelper.setAttribute(getElement(), FORMENCTYPE.getValue(), formenctype);
	}

	public void setFormmethod(String formmethod) {
		AttributeHelper.setAttribute(getElement(), FORMMETHOD.getValue(), formmethod);
	}

	public void setFormnovalidate(boolean formnovalidate) {
		AttributeHelper.setAttribute(getElement(), FORMNOVALIDATE.getValue(), formnovalidate);
	}

	public void setFormtarget(String formtarget) {
		AttributeHelper.setAttribute(getElement(), FORMTARGET.getValue(), formtarget);
	}

	public void setHeight(Integer height) {
		AttributeHelper.setAttribute(getElement(), HEIGHT.getValue(), height);
	}

	public void setWidth(Integer width) {
		AttributeHelper.setAttribute(getElement(), WIDTH.getValue(), width);
	}

	public void setList(String list) {
		AttributeHelper.setAttribute(getElement(), LIST.getValue(), list);
	}

	public void setMin(Integer min) {
		AttributeHelper.setAttribute(getElement(), MIN.getValue(), min);
	}

	public void setMax(Integer max) {
		AttributeHelper.setAttribute(getElement(), MAX.getValue(), max);
	}

	public void setMultiple(boolean multiple) {
		AttributeHelper.setAttribute(getElement(), MULTIPLE.getValue(), multiple);
	}

	public void setPattern(String pattern) {
		AttributeHelper.setAttribute(getElement(), PATTERN.getValue(), pattern);
	}

	public void setPlaceholder(String placeholder) {
		AttributeHelper.setAttribute(getElement(), PLACEHOLDER.getValue(), placeholder);
	}

	public void setRequired(boolean required) {
		AttributeHelper.setAttribute(getElement(), REQUIRED.getValue(), required);
	}

	public void setStep(Integer step) {
		AttributeHelper.setAttribute(getElement(), STEP.getValue(), step);
	}

	public boolean isNovalidate() {
		return AttributeHelper.getAttributeAsBoolean(getElement(), NOVALIDATE.getValue());
	}

	public boolean isAutocomplete() {
		return AttributeHelper.getAttributeAsBoolean(getElement(), AUTOCOMPLETE.getValue());
	}

	public boolean isAutofocus() {
		return AttributeHelper.getAttributeAsBoolean(getElement(), AUTOFOCUS.getValue());
	}

	public String getForm() {
		return AttributeHelper.getAttribute(getElement(), FORM.getValue());
	}

	public String getFormaction() {
		return AttributeHelper.getAttribute(getElement(), FORMACTION.getValue());
	}

	public String getFormenctype() {
		return AttributeHelper.getAttribute(getElement(), FORMENCTYPE.getValue());
	}

	public String getFormmethod() {
		return AttributeHelper.getAttribute(getElement(), FORMMETHOD.getValue());
	}

	public boolean isFormnovalidate() {
		return AttributeHelper.getAttributeAsBoolean(getElement(), FORMNOVALIDATE.getValue());
	}

	public String getFormtarget() {
		return AttributeHelper.getAttribute(getElement(), FORMTARGET.getValue());
	}

	public Integer getHeight() {
		return AttributeHelper.getAttributeAsInteger(getElement(), HEIGHT.getValue());
	}

	public Integer getWidth() {
		return AttributeHelper.getAttributeAsInteger(getElement(), WIDTH.getValue());
	}

	public String getList() {
		return AttributeHelper.getAttribute(getElement(), AUTOFOCUS.getValue());
	}

	public Integer getMin() {
		return AttributeHelper.getAttributeAsInteger(getElement(), MIN.getValue());
	}

	public Integer getMax() {
		return AttributeHelper.getAttributeAsInteger(getElement(), MAX.getValue());
	}

	public boolean isMultiple() {
		return AttributeHelper.getAttributeAsBoolean(getElement(), MULTIPLE.getValue());
	}

	public String getPattern() {
		return AttributeHelper.getAttribute(getElement(), PATTERN.getValue());
	}

	public String getPlaceholder() {
		return AttributeHelper.getAttribute(getElement(), PLACEHOLDER.getValue());
	}

	public boolean isRequired() {
		return AttributeHelper.getAttributeAsBoolean(getElement(), REQUIRED.getValue());
	}

	public Integer getStep() {
		return AttributeHelper.getAttributeAsInteger(getElement(), STEP.getValue());
	}
	
}
