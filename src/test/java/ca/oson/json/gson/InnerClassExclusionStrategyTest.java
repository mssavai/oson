/*
 * Copyright (C) 2008 Google Inc.
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

package ca.oson.json.gson;

import ca.oson.json.support.TestCaseBase;

import com.google.gson.internal.Excluder;

import java.lang.reflect.Field;

import junit.framework.TestCase;

/**
 * Unit test for GsonBuilder.EXCLUDE_INNER_CLASSES.
 *
 * @author Joel Leitch
 */
public class InnerClassExclusionStrategyTest extends TestCaseBase {
  public InnerClass innerClass = new InnerClass();
  public StaticNestedClass staticNestedClass = new StaticNestedClass();

  public void testExcludeInnerClassObject() throws Exception {
	String expected = "{\"some\":123}";
    String json = oson.serialize(innerClass);

    assertEquals(expected, json);
  }

  public void testExcludeInnerClassField() throws Exception {
    Field f = getClass().getField("innerClass");
    String json = oson.setLevel(3).serialize(f);
    
    //System.out.println(json);
  }

  public void testIncludeStaticNestedClassObject() throws Exception {
		String expected = "{\"myvale\":4567}";
	    String json = oson.serialize(staticNestedClass);

	    assertEquals(expected, json);
  }

  public void testIncludeStaticNestedClassField() throws Exception {
    Field f = getClass().getField("staticNestedClass");
    String json = oson.setLevel(2).serialize(f);
    
    //System.out.println(json);
  }

  class InnerClass {
	  int some = 123;
  }

  static class StaticNestedClass {
	  int myvale = 4567;
  }
}
