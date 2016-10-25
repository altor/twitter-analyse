package controller.textCleaner;

import static org.junit.Assert.*;

import org.junit.Test;

public abstract class AbstractCleanTextTest {
	
	protected CleanMethod cleanMethod;
	
	@Test
	public void EmptyTest() {
		String text = "toto titi tata";
		String finalText = "toto titi tata";
		cleanTest(text, finalText);
	}
	
	
	public void cleanTest(String text, String finalText){
		assertEquals(cleanMethod.clean(text), finalText);
	}
}
