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
package com.github.gwtbootstrap.client.ui.base;

import java.util.List;

import com.github.gwtbootstrap.client.ui.constants.AlternateSize;
import com.github.gwtbootstrap.client.ui.constants.Constants;
import com.github.gwtbootstrap.client.ui.constants.ControlGroupType;
import com.github.gwtbootstrap.client.ui.constants.Device;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.ui.Widget;

/**
 * A ValueBoxBase extending for Bootstarp style.
 * 
 * <pre>
 * It is GWT's {@link com.google.gwt.user.client.ui.ValueBoxBase} extending for Bootstrap style.
 * </pre>
 * @since 2.0.4.0
 * 
 * @author ohashi keisuke
 * @see com.google.gwt.user.client.ui.ValueBoxBase
 * @param <T> the value type
 */
public class ValueBoxBase<T> extends com.google.gwt.user.client.ui.ValueBoxBase<T> implements HasPlaceholder, HasAlternateSize, IsSearchQuery, HasSize, HasId, IsResponsive , HasStyle, HasEditorErrors<T>{

	/** placeholderHelper */
	private PlaceholderHelper placeholderHelper = GWT.create(PlaceholderHelper.class);

	/** Widget for control decoration on <code>EditorError</code>s */
	private Widget controlGroup;// could be a ControlGroup widget
	/** Widget where <code>EditorError</code>s messages will be placed */
	private Widget errorLabel;// could be a HelpInline widget

	/**
	 * Creates a value box that wraps the given browser element handle. This is
	 * only used by subclasses.
	 * 
	 * @param elem
	 *            the browser element to wrap
	 */
	protected ValueBoxBase(Element elem,
		Renderer<T> renderer,
		Parser<T> parser) {
		super(elem, renderer, parser);
	}

	/**
	 * 
	 * @see com.google.gwt.editor.client.HasEditorErrors#showErrors(java.util.List)
	 */
	@Override
	public void showErrors(List<EditorError> errors) {
		Widget decoratedWidget = controlGroup != null? controlGroup : this;
		if(errors != null && !errors.isEmpty()) {
			StyleHelper.addStyle(decoratedWidget, ControlGroupType.ERROR);
			SafeHtmlBuilder sb = new SafeHtmlBuilder();
			for (EditorError error : errors) {
				if(error.getEditor() == this) {
					error.setConsumed(true);
					sb.appendEscaped(error.getMessage());
					sb.appendHtmlConstant("<br />");
				}
			}
			setErrorLabelText(sb.toSafeHtml().asString());
		} else {
			StyleHelper.removeStyle(decoratedWidget, ControlGroupType.ERROR);
			setErrorLabelText("");
		}
	}
	
	@Override
	public void setValue(T value) {
		showErrors(null);//clear errors on value change
		super.setValue(value);
	}
	
	@Override
	public void setValue(T value, boolean fireEvents) {
		showErrors(null);//clear errors on value change
		super.setValue(value, fireEvents);
	}
	
	/**
	 * The widget that will be decorated on <code>EditorError</code>s will be added de <code>ControlGroupType.ERROR</code> style.
	 * It can be a ControlGroup or any widget.
	 * @param controlGroup
	 */
	public void setControlGroup(Widget controlGroup) {
		this.controlGroup = controlGroup;
	}
	/**
	 * Widget where <code>EditorError</code>s messages will be placed.
	 * It can be a HelpBlock or any other widget.
	 * @param errorLabel
	 */
	public void setErrorLabel(Widget errorLabel) {
		this.errorLabel = errorLabel;
	}

	/**
	 * Sets the content of the <code>EditorError</code>s messages inside de <code>errorLabel</code>.
	 * This implementation uses {@link Element#setInnerHTML(String)} to set the content.
	 * @param errorMessage
	 */
	protected void setErrorLabelText(String errorMessage) {
		if(errorLabel != null) {
			errorLabel.getElement().setInnerHTML(errorMessage);
		}
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPlaceholder(String placeholder) {
		placeholderHelper.setPlaceholer(getElement(), placeholder);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPlaceholder() {
		return placeholderHelper.getPlaceholder(getElement());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSearchQuery(boolean searchQuery) {
		SearchQueryStyleHelper.setSearchQuery(this, searchQuery);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSearchQuery() {
		return SearchQueryStyleHelper.isSearchQuery(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAlternateSize(AlternateSize size) {
		StyleHelper.changeStyle(this, size, AlternateSize.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSize(int size) {
		SizeHelper.setSize(this, size);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return getElement().getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(String id) {
		getElement().setId(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		if (enabled) {
			removeStyleName(Constants.DISABLED);
            getElement().removeAttribute("disabled");
		} else {
			addStyleName(Constants.DISABLED);
			getElement().setAttribute("disabled", "");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setShowOn(Device device) {
		ResponsiveHelper.setShowOn(this, device);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHideOn(Device device) {
		ResponsiveHelper.setHideOn(this, device);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setStyle(Style style) {
		StyleHelper.setStyle(this, style);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addStyle(Style style) {
		StyleHelper.addStyle(this, style);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeStyle(Style style) {
		StyleHelper.removeStyle(this, style);
		
	}
}
