/*
 * Copyright (c) 2005-2014 Clark & Parsia, LLC. <http://www.clarkparsia.com>
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

package com.complexible.common.iterations;

/**
 * <p>Interface for marking something that can be iterated over via an {@link Iteration}, analogous to {@link Iterable}</p>
 *
 * @author  Michael Grove
 * @author  Fernando Hernandez
 *
 * @since   2.0
 * @version 4.0
 */
public interface SupportsIteration<T, E extends Exception> {

	/**
	 * Return an Iteration over this object.
     *
	 * @return      the iteration
     * @throws E    if there was an error creating the Iteration
	 */
	public Iteration<T,E> iteration() throws E;
}
