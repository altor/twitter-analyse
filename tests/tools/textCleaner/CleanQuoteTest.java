package tools.textCleaner;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tools.textCleaner.ReplaceStringCleanMethod;

public class CleanQuoteTest extends AbstractCleanTextTest {

	@BeforeClass
	 public static void launch(){
		 cleanMethod = new ReplaceStringCleanMethod("\"\\s*\"", "");
	 }

	@Test
	public void quoteTest() {
		String text = "toto \"\"tata";
		String finalText = "toto tata";
		cleanTest(text, finalText);
	}
	
	
	@Test
	public void quoteAndSpacesTest() {
		String text = "toto \"   \"tata";
		String finalText = "toto tata";
		cleanTest(text, finalText);
	}

}
