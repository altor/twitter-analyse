package tools.nGrammeExtractor;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class UniAndBiGrammeExtractorTest {

	@Test
	public void simpleTest() {
		
		UniAndBiGrammeExtractor extractor = new UniAndBiGrammeExtractor();
		
		Map<String, Integer> map = extractor.extract("toto titi tata");
		assertEquals(map.get("toto"), new Integer(1));
		assertEquals(map.get("titi"), new Integer(1));
		assertEquals(map.get("tata"), new Integer(1));
		assertEquals(map.get("toto titi"), new Integer(1));
		assertEquals(map.get("titi tata"), new Integer(1));
		assertEquals(map.size(), 5);
	}
	
	@Test
	public void doubleTest() {
		
		UniAndBiGrammeExtractor extractor = new UniAndBiGrammeExtractor();
		
		Map<String, Integer> map = extractor.extract("toto titi tata toto titi");
		
		assertEquals(map.get("toto titi"), new Integer(2));
		assertEquals(map.get("titi tata"), new Integer(1));
		assertEquals(map.get("tata toto"), new Integer(1));
		assertEquals(map.get("toto"), new Integer(2));
		assertEquals(map.get("titi"), new Integer(2));
		assertEquals(map.get("tata"), new Integer(1));
		assertEquals(map.size(), 6);
	}

}
