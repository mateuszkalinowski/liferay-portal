/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.asset.display.internal.asset.display.contributor;

import com.liferay.asset.display.contributor.AssetDisplayContributorField;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(
	property = "model.class.name=com.liferay.asset.kernel.model.AssetEntry",
	service = AssetDisplayContributorField.class
)
public class AssetEntryAuthorNameAssetDisplayContributorField
	implements AssetDisplayContributorField<AssetEntry> {

	@Override
	public String getKey() {
		return "authorName";
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			locale, getClass());

		return LanguageUtil.get(resourceBundle, "author-name");
	}

	@Override
	public String getType() {
		return "text";
	}

	@Override
	public String getValue(AssetEntry assetEntry, Locale locale) {
		User user = _userLocalService.fetchUser(assetEntry.getUserId());

		if (user != null) {
			return user.getFullName();
		}

		return StringPool.BLANK;
	}

	@Reference
	private UserLocalService _userLocalService;

}