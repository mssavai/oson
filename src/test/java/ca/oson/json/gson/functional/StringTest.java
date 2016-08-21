package ca.oson.json.gson.functional;

import ca.oson.json.support.TestCaseBase;

import com.google.gson.Gson;

import junit.framework.TestCase;

/**
 * Functional tests for Json serialization and deserialization of strings.
 *
 * @author Inderjeet Singh
 * @author Joel Leitch
 */
public class StringTest extends TestCaseBase {
  private Gson gson;

  @Override
  protected void setUp() {
    super.setUp();
    gson = new Gson();
  }

  public void testStringValueSerialization() throws Exception {
    String value = "someRandomStringValue";
    assertEquals(value, oson.toJson(value));
  }

  public void testStringValueDeserialization() throws Exception {
    String value = "someRandomStringValue";
    String actual = oson.fromJson("\"" + value + "\"", String.class);
    assertEquals(value, actual);
  }

  public void testSingleQuoteInStringSerialization() throws Exception {
    String valueWithQuotes = "beforeQuote'afterQuote";
    String jsonRepresentation = oson.toJson(valueWithQuotes);
    assertEquals(valueWithQuotes, oson.fromJson(jsonRepresentation, String.class));
  }

  public void testEscapedCtrlNInStringSerialization() throws Exception {
    String value = "a\nb";
    String json = oson.toJson(value);
    assertEquals(value, json);
  }

  public void testEscapedCtrlNInStringDeserialization() throws Exception {
    String json = "'a\\nb'";
    String actual = oson.fromJson(json, String.class);
    assertEquals("a\nb", actual);
  }

  public void testEscapedCtrlRInStringSerialization() throws Exception {
    String value = "a\rb";
    String json = oson.toJson(value);
    assertEquals(value, json);
  }

  public void testEscapedCtrlRInStringDeserialization() throws Exception {
    String json = "'a\\rb'";
    String actual = oson.fromJson(json, String.class);
    assertEquals("a\rb", actual);
  }

  public void testEscapedBackslashInStringSerialization() throws Exception {
    String value = "a\\b";
    String json = oson.toJson(value);
    assertEquals(value, json);
  }

  public void testEscapedBackslashInStringDeserialization() throws Exception {
    String actual = oson.fromJson("'a\\\\b'", String.class);
    assertEquals("a\\\\b", actual);
  }

  public void testSingleQuoteInStringDeserialization() throws Exception {
    String value = "beforeQuote'afterQuote";
    String actual = oson.fromJson("\"" + value + "\"", String.class);
    assertEquals(value, actual);
  }

  public void testEscapingQuotesInStringSerialization() throws Exception {
    String valueWithQuotes = "beforeQuote\"afterQuote";
    String jsonRepresentation = oson.toJson(valueWithQuotes);
    String target = oson.fromJson(jsonRepresentation, String.class);
    assertEquals(valueWithQuotes, target);
  }

  public void testEscapingQuotesInStringDeserialization() throws Exception {
    String value = "beforeQuote\\\"afterQuote";
    String actual = oson.fromJson("\"" + value + "\"", String.class);
    String expected = "beforeQuote\"afterQuote";
    assertEquals(value, actual);
  }

  public void testStringValueAsSingleElementArraySerialization() throws Exception {
    String[] target = {"abc"};
    assertEquals("[\"abc\"]", oson.toJson(target));
    assertEquals("[\"abc\"]", oson.toJson(target, String[].class));
  }

  public void testStringWithEscapedSlashDeserialization() {
    String value = "/";
    String json = "'\\/'";
    String actual = oson.fromJson(json, String.class);
    assertEquals(value, actual);
  }

  /**
   * Created in response to http://groups.google.com/group/google-gson/browse_thread/thread/2431d4a3d0d6cb23
   */
  public void testAssignmentCharSerialization() {
    String value = "abc=";
    String json = oson.toJson(value);
    assertEquals(value, json);
  }

  /**
   * Created in response to http://groups.google.com/group/google-gson/browse_thread/thread/2431d4a3d0d6cb23
   */
  public void testAssignmentCharDeserialization() {
    String json = "\"abc=\"";
    String value = oson.fromJson(json, String.class);
    assertEquals("abc=", value);

    json = "'abc\u003d'";
    value = oson.fromJson(json, String.class);
    assertEquals("abc=", value);
  }

  public void testJavascriptKeywordsInStringSerialization() {
    String value = "null true false function";
    String json = oson.toJson(value);
    assertEquals(value, json);
  }

  public void testJavascriptKeywordsInStringDeserialization() {
    String json = "'null true false function'";
    String value = oson.fromJson(json, String.class);
    assertEquals(json.substring(1, json.length() - 1), value);
  }
}
