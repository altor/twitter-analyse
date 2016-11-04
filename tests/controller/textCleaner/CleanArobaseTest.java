package controller.textCleaner;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import tools.textCleaner.ReplaceStringCleanMethod;

public class CleanArobaseTest extends AbstractCleanTextTest {

	@BeforeClass
	public static void launch(){
		 cleanMethod = new ReplaceStringCleanMethod("@\\p{ASCII}[^\\p{Space}]*", "");
	}
	
	@Test
	public void NormalTest() {
		String text = "@toto titi tata";
		String finalText = " titi tata";
		cleanTest(text, finalText);
	}
}
