package controller.textCleaner;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class CleanArobaseTest extends AbstractCleanTextTest {

	@BeforeClass
	 public void launch(){
		 cleanMethod = new CleanMethod("@\\p{ASCII}[^\\p{Space}]*", "");
	 }
	
	@Test
	public void NormalTest() {
		String text = "@toto titi tata";
		String finalText = " titi tata";
		cleanTest(text, finalText);
	}
}
