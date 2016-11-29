package tools.textCleaner;

import static org.junit.Assert.*;

import org.junit.Test;

import tools.textCleaner.CleanMethod;
import tools.textCleaner.SupprimeDeterminant;

public class SupprimeDeterminantTest {

	private String clean(String txt){
		CleanMethod cleanBeginEnd = new ReplaceStringCleanMethod(" $|^ ", "");
		CleanMethod cleanSpace = new ReplaceStringCleanMethod("\\s\\s|\u00A0", " ");
		CleanMethod cm = new SupprimeDeterminant();

		return cleanBeginEnd.clean(cleanSpace.clean(cm.clean(txt)));
		
	}
	
	@Test
	public void test() {
		CleanMethod cm = new SupprimeDeterminant();
		
		assertEquals(clean("manger des pates"), "manger pate");
		assertEquals(clean("manger des pates des"), "manger pate");
		assertEquals(clean("manger des pates au fromage"), "manger pate fromage");
		assertEquals(clean("manger des pates au fromage et a la tomate"), "manger pate fromage tomate");
		assertEquals(clean("manger des pates au fromage et a la tomate et a la bolognaise"), "manger pate fromage tomate bolognaise");
	
	}

}
