package ca.oson.json;

import org.junit.Before;

import ca.oson.json.Oson.JSON_PROCESSOR;
import junit.framework.TestCase;

public abstract class TestCaseBase extends TestCase {
	protected Oson oson;
	public static JSON_PROCESSOR processor = JSON_PROCESSOR.OSON;
	
   @Before 
   public void setUp() {
	   oson = new Oson().setJsonProcessor(processor);
   }
   
   
   //tearDown used to close the connection or clean up activities
   public void tearDown() {
   }
}