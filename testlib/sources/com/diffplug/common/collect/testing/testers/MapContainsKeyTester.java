/*
 * Original Guava code is copyright (C) 2015 The Guava Authors.
 * Modifications from Guava are copyright (C) 2016 DiffPlug.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.diffplug.common.collect.testing.testers;

import static com.diffplug.common.collect.testing.features.CollectionSize.ZERO;
import static com.diffplug.common.collect.testing.features.MapFeature.ALLOWS_NULL_KEYS;
import static com.diffplug.common.collect.testing.features.MapFeature.ALLOWS_NULL_KEY_QUERIES;

import com.diffplug.common.annotations.GwtCompatible;
import com.diffplug.common.collect.testing.AbstractMapTester;
import com.diffplug.common.collect.testing.WrongType;
import com.diffplug.common.collect.testing.features.CollectionSize;
import com.diffplug.common.collect.testing.features.MapFeature;

/**
 * A generic JUnit test which tests {@code containsKey()} operations on a map.
 * Can't be invoked directly; please see
 * {@link com.diffplug.common.collect.testing.MapTestSuiteBuilder}.
 *
 * @author George van den Driessche
 */
@GwtCompatible
public class MapContainsKeyTester<K, V> extends AbstractMapTester<K, V> {
	@CollectionSize.Require(absent = ZERO)
	public void testContains_yes() {
		assertTrue("containsKey(present) should return true",
				getMap().containsKey(k0()));
	}

	public void testContains_no() {
		assertFalse("containsKey(notPresent) should return false",
				getMap().containsKey(k3()));
	}

	@MapFeature.Require(ALLOWS_NULL_KEY_QUERIES)
	public void testContains_nullNotContainedButAllowed() {
		assertFalse("containsKey(null) should return false",
				getMap().containsKey(null));
	}

	@MapFeature.Require(absent = ALLOWS_NULL_KEY_QUERIES)
	public void testContains_nullNotContainedAndUnsupported() {
		expectNullKeyMissingWhenNullKeysUnsupported(
				"containsKey(null) should return false or throw");
	}

	@MapFeature.Require(ALLOWS_NULL_KEYS)
	@CollectionSize.Require(absent = ZERO)
	public void testContains_nonNullWhenNullContained() {
		initMapWithNullKey();
		assertFalse("containsKey(notPresent) should return false",
				getMap().containsKey(k3()));
	}

	@MapFeature.Require(ALLOWS_NULL_KEYS)
	@CollectionSize.Require(absent = ZERO)
	public void testContains_nullContained() {
		initMapWithNullKey();
		assertTrue("containsKey(null) should return true",
				getMap().containsKey(null));
	}

	public void testContains_wrongType() {
		try {
			//noinspection SuspiciousMethodCalls
			assertFalse("containsKey(wrongType) should return false or throw",
					getMap().containsKey(WrongType.VALUE));
		} catch (ClassCastException tolerated) {}
	}
}