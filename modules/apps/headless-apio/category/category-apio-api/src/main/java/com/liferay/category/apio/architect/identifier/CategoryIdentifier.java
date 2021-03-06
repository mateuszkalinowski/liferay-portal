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

package com.liferay.category.apio.architect.identifier;

import com.liferay.apio.architect.identifier.Identifier;

/**
 * Holds information about a {@code Category} identifier. The internal method
 * {@code com.liferay.asset.kernel.model.AssetCategory#getId()} returns the
 * category's identifier.
 *
 * @author Javier Gamarra
 */
public interface CategoryIdentifier extends Identifier<Long> {
}