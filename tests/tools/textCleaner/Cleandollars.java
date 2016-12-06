package tools.textCleaner;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import tools.textCleaner.ReplaceStringCleanMethod;

public class Cleandollars extends AbstractCleanTextTest {

	
	

	@BeforeClass
	public static void launch(){
		 cleanMethod = new ReplaceStringCleanMethod("[-#@$€\n()0-9+&@/%?=~_!:,\\.;\"*><^…]|RT", "");
	}
	@Test
	public void test() {
		String text = "$18 dollars";
		String finalText = " dollars";
		cleanTest(text, finalText);
	}
	
	public void test1() {
		String text = "€18 euros";
		String finalText = "euros";
		cleanTest(text, finalText);
    }
}
