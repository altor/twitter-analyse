package tools.textCleaner;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.BeforeClass;
import org.junit.Test;

import tools.textCleaner.ReplaceStringCleanMethod;

public class CleanSpaces extends AbstractCleanTextTest {

	
	@BeforeClass
	 public static void launch(){
		 cleanMethod = new ReplaceStringCleanMethod("\\s\\s|\u00A0", " ");
	 }
	
	@Test
	public void NormalTest() {
		String text = "toto  titi";
		String finalText = "toto titi";
		cleanTest(text, finalText);
	}
	@Test
	public void insecentSpaceTest() {
		String text = "YouTube : ";
		String finalText = "YouTube : ";
		cleanTest(text, finalText);
	}
}
