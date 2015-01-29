/*
 * Copyright (c) 2005-2012 Clark & Parsia, LLC. <http://www.clarkparsia.com>
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

package com.complexible.common.io.block;

/**
 * Enumeration of brackets that are commonly used as {@link BlockMarker block markers}.
 * 
 * @author Evren Sirin
 */
public enum Bracket implements BlockMarker {
	SQUARE("[", "]"), CURLY("{", "}"), PARENTHESIS("(", ")"), ANGLE("<", ">");
	
	private final String start;
	
	private final String end;
	
	private Bracket(String start, String end) {
	    this.start = start;
	    this.end = end;
    }

	public String getBegin() {
		return start;
	}
	
	public String getEnd() {
		return end;
	}
}
