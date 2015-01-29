/*
 * Copyright (c) 2009-2012 Clark & Parsia, LLC. <http://www.clarkparsia.com>
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

import com.google.common.base.Function;
import com.google.common.base.Functions;

/**
 * <p>Additional utility methods for working with Guava Functions</p>
 *
 * @author  Michael Grove
 * @since   2.0.1
 * @version 3.0
 */
public final class Functions2 {

	/**
	 * Return a Function that will cast the parameter of the Function to the specified output type.
	 *
	 * @param theClass the class type to cast to
	 *
	 * @return a function that will perform the cast
	 */
	public static <I, O> Function<I,O> cast(final Class<O> theClass) {
		return new CastFunction<I,O>(theClass);
	}

	private static class CastFunction<I, O> implements Function<I, O> {

		private final Class<O> mClass;

		public CastFunction(final Class<O> theClass) {
			mClass = theClass;
		}

		/**
		 * @inheritDoc
		 */
        @Override
		public O apply(final I theInput) {
			return mClass.cast(theInput);
		}
	}

	/**
	 * <p>String based functions.</p>
	 *
	 * @author  Michael Grove
	 * @since   3.0
	 * @version 3.1.3
	 */
	public final static class Strings {
		public static Function<String, String> substring(final int theStart) {
			return new Substring(theStart);
		}

		public static Function<String, String> substring(final int theStart, final int theEnd) {
			return new Substring(theStart, theEnd);
		}

		public static Function<String, String> substringAfter(final String theString) {
			return new Substring(-1, theString);
		}

		public static Function<String, String> replace(final String theReplace, final String theReplaceWith) {
			return new Replace(theReplace, theReplaceWith);
		}

		/**
		 * Return a function that will create sub strings of the inputs beginning at the starting index and
		 * ending with the starting location of the occurrence of the specified string.  If the specified string does
		 * not exist, then the ending index of the input string will be used.  For example, with the input
		 * "This is a sentence." substringUntil(0, " ") will return "This"
		 *
		 * @param theStart	the starting index
		 * @param theString	the string to indexOf to obtain the end index.
		 * @return			the function which will perform the desired substring
		 */
		public static Function<String, String> substringUntil(final int theStart, final String theString) {
			return new Substring(theStart, theString);
		}

		public static Function<String, String> substringUntil(final String theString) {
			return new Substring(0, theString);
		}

		public static Function<String, String> prefix(final String thePrefix) {
			return new Prefix(thePrefix);
		}

		public static Function<String, String> postfix(final String thePostfix) {
			return new Postfix(thePostfix);
		}

		private static final class Prefix implements Function<String, String> {
			private Function<?, String> mFunc;

			public Prefix(final String thePrefix) {
				this(Functions.constant(thePrefix));
			}

			public Prefix(final Function<?, String> theFunc) {
				mFunc = theFunc;
			}

			@Override
			public String apply(final String input) {
				return String.format("%s%s", mFunc.apply(null), input);
			}
		}

		private static final class Postfix implements Function<String, String> {
			private Function<?, String> mFunc;

			public Postfix(final String thePrefix) {
				this(Functions.constant(thePrefix));
			}

			public Postfix(final Function<?, String> theFunc) {
				mFunc = theFunc;
			}

			@Override
			public String apply(final String input) {
				return String.format("%s%s", input, mFunc.apply(null));
			}
		}

		private static class Replace implements Function<String, String> {
			private final String mReplace;
			private final String mReplaceWith;

			private Replace(final String theReplace, final String theReplaceWith) {
				mReplace = theReplace;
				mReplaceWith = theReplaceWith;
			}

			@Override
			public String apply(final String theString) {
				return theString.replace(mReplace, mReplaceWith);
			}
		}

		private static class Substring implements Function<String, String> {
			private final int mStart;
			private final int mEnd;
			private final String mStringForIndexOf;

			private Substring(final int theStart, final String theStringForIndexOf) {
				mStart = theStart;
				mEnd = -1;
				mStringForIndexOf = theStringForIndexOf;
			}

			private Substring(final int theStart) {
				this(theStart, -1);
			}

			private Substring(final int theStart, final int theEnd) {
				mStart = theStart;
				mEnd = theEnd;
				mStringForIndexOf = null;
			}

			public String apply(final String theString) {
				if (mStringForIndexOf != null) {
					return mStart == -1
						   ? theString.substring(theString.indexOf(mStringForIndexOf)+mStringForIndexOf.length())
						   : theString.substring(mStart, theString.indexOf(mStringForIndexOf)+mStringForIndexOf.length());
				}
				else if (mEnd == -1) {
					return theString.substring(mStart);
				}
				else {
					return theString.substring(mStart, mEnd);
				}
			}
		}
	}
}
