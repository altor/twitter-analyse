package tools.textCleaner;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class LittleWordsCleanTest extends AbstractCleanTextTest {

	@BeforeClass
	public static void launch(){
		 cleanMethod = new ReplaceStringCleanMethod("(\\p{Space}|^)\\w?\\w?\\w?(\\p{Space}|$)", " ");
	}
	
	@Test
	public void NormalTest() {
		String text = "toto ha titi tata";
		String finalText = "toto titi tata";
		cleanTest(text, finalText);
	}
	
	@Test
	public void beginTest() {
		String text = "ha toto titi tata";
		String finalText = " toto titi tata";
		cleanTest(text, finalText);
	}
	
	@Test
	public void endTest() {
		String text = "toto titi tata ha";
		String finalText = "toto titi tata ";
		cleanTest(text, finalText);
	}
	
	@Test
	public void mulTest() {
		String text = "toto ha titi tata ha";
		String finalText = "toto titi tata ";
		cleanTest(text, finalText);
	}

}
