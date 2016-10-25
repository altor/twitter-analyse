package controller.textCleaner;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CleanBeginEndSpaces extends AbstractCleanTextTest {

	@BeforeClass
	public static void launch() throws Exception {
		 cleanMethod = new CleanMethod(" $|^ ", "");

	}

	@Test
	public void beginSpaceTest() {
		String text = " toto titi";
		String finalText = "toto titi";
		cleanTest(text, finalText);
	}
	
	@Test
	public void endSpaceTest() {
		String text = "toto titi ";
		String finalText = "toto titi";
		cleanTest(text, finalText);
	}
	
	@Test
	public void endBeginSpaceTest() {
		String text = " toto titi ";
		String finalText = "toto titi";
		cleanTest(text, finalText);
	}


}
