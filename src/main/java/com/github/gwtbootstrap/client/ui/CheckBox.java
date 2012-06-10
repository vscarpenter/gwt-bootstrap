/*
 *  Copyright 2012 GWT-Bootstrap
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.github.gwtbootstrap.client.ui;

import com.github.gwtbootstrap.client.ui.base.HasId;
import com.github.gwtbootstrap.client.ui.base.IsSearchQuery;
import com.github.gwtbootstrap.client.ui.base.SearchQueryStyleHelper;
import com.github.gwtbootstrap.client.ui.constants.Constants;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.i18n.shared.HasDirectionEstimator;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.DirectionalTextHelper;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasDirectionalSafeHtml;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWordWrap;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

/**
 * CheckBox widgets.
 * <p>
 * Re-design for Bootstrap.
 * </p>
 * 
 * @since 2.0.3.0
 * @author ohashi keisuke
 */
public class CheckBox extends ButtonBase implements HasName, HasValue<Boolean>, HasWordWrap, HasDirectionalSafeHtml, HasDirectionEstimator, IsEditor<LeafValueEditor<Boolean>>, IsSearchQuery,HasId {

	public static final DirectionEstimator DEFAULT_DIRECTION_ESTIMATOR = DirectionalTextHelper.DEFAULT_DIRECTION_ESTIMATOR;

	final DirectionalTextHelper directionalTextHelper;

	InputElement inputElem;
	SpanElement spanElem;
	

	private LeafValueEditor<Boolean> editor;

	private boolean valueChangeHandlerInitialized;

	/**
	 * Creates a check box with no label.
	 */
	public CheckBox() {
		this(DOM.createInputCheck());
	}

	/**
	 * Creates a check box with the specified text label.
	 * 
	 * @param label
	 *            the check box's label
	 */
	public CheckBox(SafeHtml label) {
		this(label.asString(), true);
	}

	/**
	 * Creates a check box with the specified text label.
	 * 
	 * @param label
	 *            the check box's label
	 * @param dir
	 *            the text's direction. Note that {@code DEFAULT} means
	 *            direction should be inherited from the widget's parent
	 *            element.
	 */
	public CheckBox(SafeHtml label,
		Direction dir) {
		this();
		setHTML(label, dir);
	}

	/**
	 * Creates a check box with the specified text label.
	 * 
	 * @param label
	 *            the check box's label
	 * @param directionEstimator
	 *            A DirectionEstimator object used for automatic direction
	 *            adjustment. For convenience,
	 *            {@link #DEFAULT_DIRECTION_ESTIMATOR} can be used.
	 */
	public CheckBox(SafeHtml label,
		DirectionEstimator directionEstimator) {
		this();
		setDirectionEstimator(directionEstimator);
		setHTML(label.asString());
	}

	/**
	 * Creates a check box with the specified text label.
	 * 
	 * @param label
	 *            the check box's label
	 */
	public CheckBox(String label) {
		this();
		setText(label);
	}

	/**
	 * Creates a check box with the specified text label.
	 * 
	 * @param label
	 *            the check box's label
	 * @param dir
	 *            the text's direction. Note that {@code DEFAULT} means
	 *            direction should be inherited from the widget's parent
	 *            element.
	 */
	public CheckBox(String label,
		Direction dir) {
		this();
		setText(label, dir);
	}

	/**
	 * Creates a label with the specified text and a default direction
	 * estimator.
	 * 
	 * @param label
	 *            the check box's label
	 * @param directionEstimator
	 *            A DirectionEstimator object used for automatic direction
	 *            adjustment. For convenience,
	 *            {@link #DEFAULT_DIRECTION_ESTIMATOR} can be used.
	 */
	public CheckBox(String label,
		DirectionEstimator directionEstimator) {
		this();
		setDirectionEstimator(directionEstimator);
		setText(label);
	}

	/**
	 * Creates a check box with the specified text label.
	 * 
	 * @param label
	 *            the check box's label
	 * @param asHTML
	 *            <code>true</code> to treat the specified label as html
	 */
	public CheckBox(String label,
		boolean asHTML) {
		this();
		if (asHTML) {
			setHTML(label);
		} else {
			setText(label);
		}
	}

	protected CheckBox(Element elem) {
		super(DOM.createLabel());
		
		assert elem.hasAttribute("type") : "The elem should has type attributes";

		//TODO 2012/05/06 ohashi keisuke. ugly code......
		if(Constants.CHECKBOX.toLowerCase().equals(elem.getAttribute("type").toLowerCase())) {
			this.setStyleName(Constants.CHECKBOX);
		} else if(Constants.RADIO.toLowerCase().equals(elem.getAttribute("type").toLowerCase())){
			this.setStyleName(Constants.RADIO);
		}

		inputElem = InputElement.as(elem);
		spanElem = Document.get().createSpanElement();
		
		getElement().appendChild(inputElem);
		getElement().appendChild(spanElem);

		String uid = DOM.createUniqueId();
		inputElem.setPropertyString("id", uid);
		asLabel().setHtmlFor(uid);
		directionalTextHelper = new DirectionalTextHelper(spanElem, false);
		setTabIndex(0);
	}

	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Boolean> handler) {
		// Is this the first value change handler? If so, time to add handlers
		if (!valueChangeHandlerInitialized) {
			ensureDomEventHandlers();
			valueChangeHandlerInitialized = true;
		}
		return addHandler(handler, ValueChangeEvent.getType());
	}

	public LeafValueEditor<Boolean> asEditor() {
		if (editor == null) {
			editor = TakesValueEditor.of(this);
		}
		return editor;
	}

	public DirectionEstimator getDirectionEstimator() {
		return directionalTextHelper.getDirectionEstimator();
	}

	/**
	 * Returns the value property of the input element that backs this widget.
	 * This is the value that will be associated with the CheckBox name and
	 * submitted to the server if a {@link FormPanel} that holds it is submitted
	 * and the box is checked.
	 * <p>
	 * Don't confuse this with {@link #getValue}, which returns true or false if
	 * the widget is checked.
	 */
	public String getFormValue() {
		return inputElem.getValue();
	}

	@Override
	public String getHTML() {
		return directionalTextHelper.getTextOrHtml(true);
	}

	public String getName() {
		return inputElem.getName();
	}

	@Override
	public int getTabIndex() {
		return inputElem.getTabIndex();
	}

	@Override
	public String getText() {
		return directionalTextHelper.getTextOrHtml(false);
	}

	public Direction getTextDirection() {
		return directionalTextHelper.getTextDirection();
	}

	/**
	 * Determines whether this check box is currently checked.
	 * <p>
	 * Note that this <em>does not</em> return the value property of the
	 * checkbox input element wrapped by this widget. For access to that
	 * property, see {@link #getFormValue()}
	 * 
	 * @return <code>true</code> if the check box is checked, false otherwise.
	 *         Will not return null
	 */
	public Boolean getValue() {
		if (isAttached()) {
			return inputElem.isChecked();
		} else {
			return inputElem.isDefaultChecked();
		}
	}

	public boolean getWordWrap() {
		return !getElement().getStyle().getProperty("whiteSpace").equals("nowrap");
	}

	/**
	 * Determines whether this check box is currently checked.
	 * 
	 * @return <code>true</code> if the check box is checked
	 * @deprecated Use {@link #getValue} instead
	 */
	@Deprecated
	public boolean isChecked() {
		// Funny comparison b/c getValue could in theory return null
		return getValue() == true;
	}

	@Override
	public boolean isEnabled() {
		return !inputElem.isDisabled();
	}

	@Override
	public void setAccessKey(char key) {
		inputElem.setAccessKey("" + key);
	}

	/**
	 * Checks or unchecks this check box. Does not fire {@link ValueChangeEvent}
	 * . (If you want the event to fire, use {@link #setValue(Boolean, boolean)}
	 * )
	 * 
	 * @param checked
	 *            <code>true</code> to check the check box.
	 * @deprecated Use {@link #setValue(Boolean)} instead
	 */
	@Deprecated
	public void setChecked(boolean checked) {
		setValue(checked);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * See note at {@link #setDirectionEstimator(DirectionEstimator)}.
	 */
	public void setDirectionEstimator(boolean enabled) {
		directionalTextHelper.setDirectionEstimator(enabled);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Note: DirectionEstimator should be set before the label has any content;
	 * it's highly recommended to set it using a constructor. Reason: if the
	 * label already has non-empty content, this will update its direction
	 * according to the new estimator's result. This may cause flicker, and thus
	 * should be avoided.
	 */
	public void setDirectionEstimator(DirectionEstimator directionEstimator) {
		directionalTextHelper.setDirectionEstimator(directionEstimator);
	}

	@Override
	public void setEnabled(boolean enabled) {
		inputElem.setDisabled(!enabled);
		if (enabled) {
			inputElem.removeClassName(Constants.DISABLED);
		} else {
			inputElem.addClassName(Constants.DISABLED);
		}
	}

	@Override
	public void setFocus(boolean focused) {
		if (focused) {
			inputElem.focus();
		} else {
			inputElem.blur();
		}
	}

	/**
	 * Set the value property on the input element that backs this widget. This
	 * is the value that will be associated with the CheckBox's name and
	 * submitted to the server if a {@link FormPanel} that holds it is submitted
	 * and the box is checked.
	 * <p>
	 * Don't confuse this with {@link #setValue}, which actually checks and
	 * unchecks the box.
	 * 
	 * @param value
	 */
	public void setFormValue(String value) {
		inputElem.setAttribute("value", value);
	}

	public void setHTML(SafeHtml html, Direction dir) {
		directionalTextHelper.setTextOrHtml(html.asString(), dir, true);
	}

	@Override
	public void setHTML(String html) {
		directionalTextHelper.setTextOrHtml(html, true);
	}

	public void setName(String name) {
		inputElem.setName(name);
	}

	@Override
	public void setTabIndex(int index) {
		// Need to guard against call to setTabIndex before inputElem is
		// initialized. This happens because FocusWidget's (a superclass of
		// CheckBox) setElement method calls setTabIndex before inputElem is
		// initialized. See CheckBox's protected constructor for more
		// information.
		if (inputElem != null) {
			inputElem.setTabIndex(index);
		}
	}

	@Override
	public void setText(String text) {
		directionalTextHelper.setTextOrHtml(text, false);
	}

	public void setText(String text, Direction dir) {
		directionalTextHelper.setTextOrHtml(text, dir, false);
	}

	/**
	 * Checks or unchecks the check box.
	 * <p>
	 * Note that this <em>does not</em> set the value property of the checkbox
	 * input element wrapped by this widget. For access to that property, see
	 * {@link #setFormValue(String)}
	 * 
	 * @param value
	 *            true to check, false to uncheck; null value implies false
	 */
	public void setValue(Boolean value) {
		setValue(value, false);
	}

	/**
	 * Checks or unchecks the check box, firing {@link ValueChangeEvent} if
	 * appropriate.
	 * <p>
	 * Note that this <em>does not</em> set the value property of the checkbox
	 * input element wrapped by this widget. For access to that property, see
	 * {@link #setFormValue(String)}
	 * 
	 * @param value
	 *            true to check, false to uncheck; null value implies false
	 * @param fireEvents
	 *            If true, and value has changed, fire a
	 *            {@link ValueChangeEvent}
	 */
	public void setValue(Boolean value, boolean fireEvents) {
		if (value == null) {
			value = Boolean.FALSE;
		}

		Boolean oldValue = getValue();
		inputElem.setChecked(value);
		inputElem.setDefaultChecked(value);
		if (value.equals(oldValue)) {
			return;
		}
		if (fireEvents) {
			ValueChangeEvent.fire(this, value);
		}
	}

	public void setWordWrap(boolean wrap) {
		getElement().getStyle().setProperty("whiteSpace", wrap
																? "normal"
																: "nowrap");
	}

	// Unlike other widgets the CheckBox sinks on its inputElement, not
	// its wrapper
	@Override
	public void sinkEvents(int eventBitsToAdd) {
		if (isOrWasAttached()) {
			Event.sinkEvents(inputElem, eventBitsToAdd | Event.getEventsSunk(inputElem));
		} else {
			super.sinkEvents(eventBitsToAdd);
		}
	}

	protected void ensureDomEventHandlers() {
		addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				// Checkboxes always toggle their value, no need to compare
				// with old value. Radio buttons are not so lucky, see
				// overrides in RadioButton
				ValueChangeEvent.fire(CheckBox.this, getValue());
			}
		});
	}

	/**
	 * <b>Affected Elements:</b>
	 * <ul>
	 * <li>-label = label next to checkbox.</li>
	 * </ul>
	 * 
	 * @see UIObject#onEnsureDebugId(String)
	 */
	@Override
	protected void onEnsureDebugId(String baseID) {
		super.onEnsureDebugId(baseID);
		ensureDebugId(asLabel(), baseID, "label");
		ensureDebugId(inputElem, baseID, "input");
		asLabel().setHtmlFor(inputElem.getId());
	}

	/**
	 * This method is called when a widget is attached to the browser's
	 * document. onAttach needs special handling for the CheckBox case. Must
	 * still call {@link Widget#onAttach()} to preserve the
	 * <code>onAttach</code> contract.
	 */
	@Override
	protected void onLoad() {
		setEventListener(inputElem, this);
	}

	/**
	 * This method is called when a widget is detached from the browser's
	 * document. Overridden because of IE bug that throws away checked state and
	 * in order to clear the event listener off of the <code>inputElem</code>.
	 */
	@Override
	protected void onUnload() {
		// Clear out the inputElem's event listener (breaking the circular
		// reference between it and the widget).
		setEventListener(asOld(inputElem), null);
		setValue(getValue());
	}

	/**
	 * Replace the current input element with a new one. Preserves all state
	 * except for the name property, for nasty reasons related to radio button
	 * grouping. (See implementation of {@link RadioButton#setName}.)
	 * 
	 * @param elem
	 *            the new input element
	 */
	protected void replaceInputElement(Element elem) {
		InputElement newInputElem = InputElement.as(elem);
		// Collect information we need to set
		int tabIndex = getTabIndex();
		boolean checked = getValue();
		boolean enabled = isEnabled();
		String formValue = getFormValue();
		String uid = inputElem.getId();
		String accessKey = inputElem.getAccessKey();
		int sunkEvents = Event.getEventsSunk(inputElem);

		// Clear out the old input element
		setEventListener(asOld(inputElem), null);

		getElement().replaceChild(newInputElem, inputElem);

		// Sink events on the new element
		Event.sinkEvents(elem, Event.getEventsSunk(inputElem));
		Event.sinkEvents(inputElem, 0);
		inputElem = newInputElem;

		// Setup the new element
		Event.sinkEvents(inputElem, sunkEvents);
		inputElem.setId(uid);
		if (!"".equals(accessKey)) {
			inputElem.setAccessKey(accessKey);
		}
		setTabIndex(tabIndex);
		setValue(checked);
		setEnabled(enabled);
		setFormValue(formValue);

		// Set the event listener
		if (isAttached()) {
			setEventListener(asOld(inputElem), this);
		}
	}

	private Element asOld(com.google.gwt.dom.client.Element elem) {
		Element oldSchool = elem.cast();
		return oldSchool;
	}

	private void setEventListener(com.google.gwt.dom.client.Element e, EventListener listener) {
		DOM.setEventListener(asOld(e), listener);
	}

	protected LabelElement asLabel() {
		return LabelElement.as(getElement());
	}

	public void setInline(boolean inline) {
		if(getStyleName().contains(Constants.INLINE)) {
			removeStyleName(Constants.INLINE);
		}
		
		if(inline) {
			addStyleName(Constants.INLINE);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSearchQuery(boolean searchQuery) {
		SearchQueryStyleHelper.setSearchQuery(inputElem, searchQuery);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSearchQuery() {
		return SearchQueryStyleHelper.isSearchQuery(inputElem);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return inputElem.getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(String id) {
		inputElem.setId(id);
		asLabel().setHtmlFor(id);
	}


}