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

import com.github.gwtbootstrap.client.ui.base.DivWidget;
import com.github.gwtbootstrap.client.ui.base.StyleHelper;
import com.github.gwtbootstrap.client.ui.constants.Constants;
import com.github.gwtbootstrap.client.ui.constants.WellSize;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Widget;

//@formatter:off
/**
 * {@link NavList} in a {@link Well}.
 * 
 * @since 2.0.4.0
 * 
 * @author Dominik Mayer
 */
// @formatter:on
public class WellNavList extends DivWidget {

	private NavList navList = new NavList();

	/**
	 * Creates an empty list.
	 */
	public WellNavList() {
		setStyleName(Constants.WELL);
		super.add(navList);
		setStyle();
	}

	private void setStyle() {
		Style style = getElement().getStyle();
		style.setPaddingTop(8, Unit.PX);
		style.setPaddingBottom(8, Unit.PX);
		style.setPaddingLeft(0, Unit.PX);
		style.setPaddingRight(0, Unit.PX);
	}

	// TODO: Restrict type of widgets that can be added?
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(Widget child) {
		navList.add(child);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(Widget w, int beforeIndex) {
		navList.insert(w, beforeIndex);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		navList.clear();
	}

	/**
	 * Set well size
	 * 
	 * @param size
	 *            size
	 */
	public void setSize(WellSize size) {
		StyleHelper.changeStyle(this, size, WellSize.class);
	}

}