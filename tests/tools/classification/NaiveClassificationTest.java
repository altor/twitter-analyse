package tools.classification;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NaiveClassificationTest extends AbstractClassificationTest {

	@Before
	public void setUp() throws Exception {
		String [] goodsWords = {"bien", "super", "=\\)", "j'aime bien"};
		String [] badsWords = {"nul", "mauvais", "=\\(", "je n'aime pas"};
		this.classificator = new NaiveClassifitation(goodsWords, badsWords);
	}
	
	@Test
	public void normalPositiveTest(){
		textTest("c'était super", 4);
		textTest("c'était bien", 4);
		textTest("qlksjd =)", 4);
		textTest("ça j'aime bien", 4);
	}
	
	@Test
	public void normalNegativeTest(){
		textTest("c'était nul", 0);
		textTest("c'était mauvais", 0);
		textTest("qslkdj =(", 0);
		textTest("ça je n'aime pas", 0);
	}
	
	@Test
	public void normalNeutreTest(){
		textTest("haha hihi hoho", 2);
		textTest("je aime", 2);
		textTest("aaaaaaaaaaaaa", 2);		
	}

	@Test
	public void positiveWithNegativeWordsTest(){
		textTest("super bien nul", 4);
	}
	
	@Test
	public void negativeWithPositiveWordsTest(){
		textTest("super mauvais nul", 0);
	}
	
	@Test
	public void autantNegatifPositifTest(){
		textTest("super bien nul mauvais", 2);
	}
	
}
