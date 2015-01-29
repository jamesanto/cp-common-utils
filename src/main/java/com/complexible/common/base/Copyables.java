/*
 * Copyright (c) 2005-2011 Clark & Parsia, LLC. <http://www.clarkparsia.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.complexible.common.base;

import java.util.Collection;
import java.util.Arrays;

import com.google.common.base.Optional;

/**
 * <p>Utility methods for copying collections of {@link Copyable} objects.</p>
 *
 * @author  Michael Grove
 * @since   2.1
 * @version 2.3.1
 */
public final class Copyables {

	/**
	 * Private constructor
	 */
	private Copyables() {
	}

	/**
	 * Perform a copy of a collection.  Creates a new Collection of the same type as the original (via <code>getClass().newInstance()</code>)
	 * and {@link Copyable#copy copies} each element from the source into the new collection.
	 *
	 * @param theToCopy	the collection to copy
	 * @return			the copy of the collection
	 *
	 * @throws IllegalArgumentException if the new collection could not be created
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Copyable<T>, C extends Collection<T>> C copy(final C theToCopy) {
		try {
			C aCopy = (C) theToCopy.getClass().newInstance();
			
			for (T aObj : theToCopy) {
				aCopy.add(aObj.copy());
			}

			return aCopy;
		}
		catch (InstantiationException e) {
			throw new IllegalArgumentException(e);
		}
		catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Perform a copy of an array.  Creates a new array and {@link Copyable#copy copies} each element from the
	 * source into the new array.
	 *
	 * @param theArray	the array to copy
	 * @return			the copy
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Copyable<T>> T[] copy(final T[] theArray) {
		T[] aArray = Arrays.copyOf(theArray, theArray.length);

		for (int i = 0; i < theArray.length; i++) {
			aArray[i] = theArray[i].copy();
		}

		return aArray;
	}

	/**
	 * {@link Copyable#copy Copies} the object if it is an instance of Copyable, otherwise returns the object unchanged.
	 *
	 * @param theObject the object to try to copy.
	 * @return			the possibly copied object
	 */
	@SuppressWarnings("unchecked")
	public static <T> T copy(final T theObject) {
        if (theObject == null) {
            return null;
        }
		else if (theObject instanceof Copyable) {
			return (T) ((Copyable)theObject).copy();
		}
		else {
			return theObject;
		}
	}

    /**
     * If the {@link Optional} has ({@link Optional#isPresent isPresent a value} a {@link Copyable#copy} is made of the object.
     * Otherwise, with an absent value, the Optional is returned as-is
     *
     * @param theObj    the object to copy
     * @param <T>       the object's type
     * @return          an Optional which contains a copy of the object if a value is present on the optional.
     */
    public static <T> Optional<T> copy(final Optional<T> theObj) {
        if (theObj.isPresent()) {
            return Optional.of(copy(theObj.get()));
        }
        else {
            // no value, no need to make a copy
            return theObj;
        }
    }
}
