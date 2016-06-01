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

import static com.diffplug.common.collect.testing.features.CollectionFeature.KNOWN_ORDER;
import static com.diffplug.common.collect.testing.features.CollectionSize.ONE;
import static com.diffplug.common.collect.testing.features.CollectionSize.SEVERAL;
import static com.diffplug.common.collect.testing.features.CollectionSize.ZERO;

import com.diffplug.common.annotations.GwtCompatible;
import com.diffplug.common.collect.testing.features.CollectionFeature;
import com.diffplug.common.collect.testing.features.CollectionSize;

/**
 * A generic JUnit test which tests {@code peek()} operations on a queue.
 * Can't be invoked directly; please see
 * {@link com.diffplug.common.collect.testing.CollectionTestSuiteBuilder}.
 *
 * @author Jared Levy
 */
@GwtCompatible
public class QueuePeekTester<E> extends AbstractQueueTester<E> {
	@CollectionSize.Require(ZERO)
	public void testPeek_empty() {
		assertNull("emptyQueue.peek() should return null", getQueue().peek());
		expectUnchanged();
	}

	@CollectionSize.Require(ONE)
	public void testPeek_size1() {
		assertEquals("size1Queue.peek() should return first element",
				e0(), getQueue().peek());
		expectUnchanged();
	}

	@CollectionFeature.Require(KNOWN_ORDER)
	@CollectionSize.Require(SEVERAL)
	public void testPeek_sizeMany() {
		assertEquals("sizeManyQueue.peek() should return first element",
				e0(), getQueue().peek());
		expectUnchanged();
	}
}