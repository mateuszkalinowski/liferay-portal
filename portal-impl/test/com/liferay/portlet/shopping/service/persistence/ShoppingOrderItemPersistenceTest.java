/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.shopping.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.service.persistence.BasePersistenceTestCase;

import com.liferay.portlet.shopping.NoSuchOrderItemException;
import com.liferay.portlet.shopping.model.ShoppingOrderItem;

import java.util.List;

/**
 * <a href="ShoppingOrderItemPersistenceTest.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class ShoppingOrderItemPersistenceTest extends BasePersistenceTestCase {
	public void setUp() throws Exception {
		super.setUp();

		_persistence = (ShoppingOrderItemPersistence)PortalBeanLocatorUtil.locate(ShoppingOrderItemPersistence.class.getName());
	}

	public void testCreate() throws Exception {
		long pk = nextLong();

		ShoppingOrderItem shoppingOrderItem = _persistence.create(pk);

		assertNotNull(shoppingOrderItem);

		assertEquals(shoppingOrderItem.getPrimaryKey(), pk);
	}

	public void testRemove() throws Exception {
		ShoppingOrderItem newShoppingOrderItem = addShoppingOrderItem();

		_persistence.remove(newShoppingOrderItem);

		ShoppingOrderItem existingShoppingOrderItem = _persistence.fetchByPrimaryKey(newShoppingOrderItem.getPrimaryKey());

		assertNull(existingShoppingOrderItem);
	}

	public void testUpdateNew() throws Exception {
		addShoppingOrderItem();
	}

	public void testUpdateExisting() throws Exception {
		long pk = nextLong();

		ShoppingOrderItem newShoppingOrderItem = _persistence.create(pk);

		newShoppingOrderItem.setOrderId(nextLong());
		newShoppingOrderItem.setItemId(randomString());
		newShoppingOrderItem.setSku(randomString());
		newShoppingOrderItem.setName(randomString());
		newShoppingOrderItem.setDescription(randomString());
		newShoppingOrderItem.setProperties(randomString());
		newShoppingOrderItem.setPrice(nextDouble());
		newShoppingOrderItem.setQuantity(nextInt());
		newShoppingOrderItem.setShippedDate(nextDate());

		_persistence.update(newShoppingOrderItem, false);

		ShoppingOrderItem existingShoppingOrderItem = _persistence.findByPrimaryKey(newShoppingOrderItem.getPrimaryKey());

		assertEquals(existingShoppingOrderItem.getOrderItemId(),
			newShoppingOrderItem.getOrderItemId());
		assertEquals(existingShoppingOrderItem.getOrderId(),
			newShoppingOrderItem.getOrderId());
		assertEquals(existingShoppingOrderItem.getItemId(),
			newShoppingOrderItem.getItemId());
		assertEquals(existingShoppingOrderItem.getSku(),
			newShoppingOrderItem.getSku());
		assertEquals(existingShoppingOrderItem.getName(),
			newShoppingOrderItem.getName());
		assertEquals(existingShoppingOrderItem.getDescription(),
			newShoppingOrderItem.getDescription());
		assertEquals(existingShoppingOrderItem.getProperties(),
			newShoppingOrderItem.getProperties());
		assertEquals(existingShoppingOrderItem.getPrice(),
			newShoppingOrderItem.getPrice());
		assertEquals(existingShoppingOrderItem.getQuantity(),
			newShoppingOrderItem.getQuantity());
		assertEquals(Time.getShortTimestamp(
				existingShoppingOrderItem.getShippedDate()),
			Time.getShortTimestamp(newShoppingOrderItem.getShippedDate()));
	}

	public void testFindByPrimaryKeyExisting() throws Exception {
		ShoppingOrderItem newShoppingOrderItem = addShoppingOrderItem();

		ShoppingOrderItem existingShoppingOrderItem = _persistence.findByPrimaryKey(newShoppingOrderItem.getPrimaryKey());

		assertEquals(existingShoppingOrderItem, newShoppingOrderItem);
	}

	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			fail("Missing entity did not throw NoSuchOrderItemException");
		}
		catch (NoSuchOrderItemException nsee) {
		}
	}

	public void testFetchByPrimaryKeyExisting() throws Exception {
		ShoppingOrderItem newShoppingOrderItem = addShoppingOrderItem();

		ShoppingOrderItem existingShoppingOrderItem = _persistence.fetchByPrimaryKey(newShoppingOrderItem.getPrimaryKey());

		assertEquals(existingShoppingOrderItem, newShoppingOrderItem);
	}

	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = nextLong();

		ShoppingOrderItem missingShoppingOrderItem = _persistence.fetchByPrimaryKey(pk);

		assertNull(missingShoppingOrderItem);
	}

	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ShoppingOrderItem newShoppingOrderItem = addShoppingOrderItem();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ShoppingOrderItem.class,
				ShoppingOrderItem.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("orderItemId",
				newShoppingOrderItem.getOrderItemId()));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		assertEquals(1, result.size());

		ShoppingOrderItem existingShoppingOrderItem = (ShoppingOrderItem)result.get(0);

		assertEquals(existingShoppingOrderItem, newShoppingOrderItem);
	}

	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ShoppingOrderItem.class,
				ShoppingOrderItem.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("orderItemId", nextLong()));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		assertEquals(0, result.size());
	}

	protected ShoppingOrderItem addShoppingOrderItem()
		throws Exception {
		long pk = nextLong();

		ShoppingOrderItem shoppingOrderItem = _persistence.create(pk);

		shoppingOrderItem.setOrderId(nextLong());
		shoppingOrderItem.setItemId(randomString());
		shoppingOrderItem.setSku(randomString());
		shoppingOrderItem.setName(randomString());
		shoppingOrderItem.setDescription(randomString());
		shoppingOrderItem.setProperties(randomString());
		shoppingOrderItem.setPrice(nextDouble());
		shoppingOrderItem.setQuantity(nextInt());
		shoppingOrderItem.setShippedDate(nextDate());

		_persistence.update(shoppingOrderItem, false);

		return shoppingOrderItem;
	}

	private ShoppingOrderItemPersistence _persistence;
}