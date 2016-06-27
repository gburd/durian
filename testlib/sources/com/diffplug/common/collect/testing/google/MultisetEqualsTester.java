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
package com.diffplug.common.collect.testing.google;

import static com.diffplug.common.collect.testing.features.CollectionFeature.ALLOWS_NULL_VALUES;
import static com.diffplug.common.collect.testing.features.CollectionSize.ZERO;

import com.diffplug.common.annotations.GwtCompatible;
import com.diffplug.common.collect.testing.features.CollectionFeature;
import com.diffplug.common.collect.testing.features.CollectionSize;
import com.diffplug.common.testing.EqualsTester;

/**
 * Tests for {@code Multiset.equals} and {@code Multiset.hashCode}.
 * 
 * @author Louis Wasserman
 */
@GwtCompatible
public class MultisetEqualsTester<E> extends AbstractMultisetTester<E> {
	public void testEqualsSameContents() {
		new EqualsTester()
				.addEqualityGroup(
						getMultiset(),
						getSubjectGenerator().create(getSampleElements().toArray()))
				.testEquals();
	}

	@CollectionSize.Require(absent = ZERO)
	public void testNotEqualsEmpty() {
		new EqualsTester()
				.addEqualityGroup(getMultiset())
				.addEqualityGroup(getSubjectGenerator().create())
				.testEquals();
	}

	public void testHashCodeMatchesEntrySet() {
		assertEquals(getMultiset().entrySet().hashCode(), getMultiset().hashCode());
	}

	@CollectionSize.Require(absent = ZERO)
	@CollectionFeature.Require(ALLOWS_NULL_VALUES)
	public void testEqualsMultisetWithNullValue() {
		new EqualsTester()
				.addEqualityGroup(getMultiset())
				.addEqualityGroup(
						getSubjectGenerator().create(createArrayWithNullElement()),
						getSubjectGenerator().create(createArrayWithNullElement()))
				.testEquals();
	}
}