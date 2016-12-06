package tools.nGrammeExtractor;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class UniGrammeExtractorTest {

	
	@Test
	public void simpleTest() {
		
		UniGrammeExtractor extractor = new UniGrammeExtractor();
		
		Map<String, Integer> map = extractor.extract("toto titi tata");
		
		assertEquals(map.get("toto"), new Integer(1));
		assertEquals(map.get("titi"), new Integer(1));
		assertEquals(map.get("tata"), new Integer(1));
		assertEquals(map.size(), 3);
	}
	
	@Test
	public void doubleTest() {
		
		UniGrammeExtractor extractor = new UniGrammeExtractor();
		
		Map<String, Integer> map = extractor.extract("toto titi toto tata");
		
		assertEquals(map.get("toto"), new Integer(2));
		assertEquals(map.get("titi"), new Integer(1));
		assertEquals(map.get("tata"), new Integer(1));
		assertEquals(map.size(), 3);
	}

}
