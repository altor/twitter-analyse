package tools.nGrammeExtractor;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class BiGrammeExtractorTest {

	@Test
	public void simpleTest() {
		
		BiGrammeExtractor extractor = new BiGrammeExtractor();
		
		Map<String, Integer> map = extractor.extract("toto titi tata");
		
		assertEquals(map.get("toto titi"), new Integer(1));
		assertEquals(map.get("titi tata"), new Integer(1));
		assertEquals(map.size(), 2);
	}
	
	@Test
	public void doubleTest() {
		
		BiGrammeExtractor extractor = new BiGrammeExtractor();
		
		Map<String, Integer> map = extractor.extract("toto titi tata toto titi");
		
		assertEquals(map.get("toto titi"), new Integer(2));
		assertEquals(map.get("titi tata"), new Integer(1));
		assertEquals(map.get("tata toto"), new Integer(1));
		assertEquals(map.size(), 3);
	}
}
