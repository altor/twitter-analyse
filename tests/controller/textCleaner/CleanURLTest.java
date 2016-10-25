package controller.textCleaner;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class CleanURLTest extends AbstractCleanTextTest {

	 @BeforeClass
	 public static void launch(){
		 cleanMethod = new CleanMethod("(https?://([-\\w\\.]+)+(/([\\w/_\\.]*(\\?\\S+)?(#\\S+)?)?)?)", "");
	 }
	
	@Test
	public void NormalTest() {
		String text = "http://twitter4j.org/javadoc/twitter4j/Status.html#isRetweet-- toto titi tata";
		String finalText = " toto titi tata";
		cleanTest(text, finalText);
		
		text = "https://t.co/Ft0IyD0XiZ toto titi tata";
		finalText = " toto titi tata";
		cleanTest(text, finalText);
	}
	
	@Test
	public void httpsTest() {
		String text = "https://twitter4j.org/javadoc/twitter4j/Status.html#isRetweet-- toto titi tata";
		String finalText = " toto titi tata";
		cleanTest(text, finalText);
	}
}
