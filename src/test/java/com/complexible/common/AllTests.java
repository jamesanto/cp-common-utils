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

package com.complexible.common;

/**
 * <p></p>
 *
 * @author Michael Grove
 * @since   2.3.1
 * @version 2.3.1
 */

import com.complexible.common.base.AllBaseTests;
import com.complexible.common.collect.BigArrayListTest;
import com.complexible.common.io.AllIOTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AllBaseTests.class, AllIOTests.class, BigArrayListTest.class })
public class AllTests {

}
