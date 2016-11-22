package twitter;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import twitter.Tweet;

public class TweetTest {
	
	@Test
	public void isNotFrenchTweetTest(){
		Tweet t = new MockedTweet("user", "toto :) titi :(", "en");
		assertFalse(t.isFrenchTweet());
	}
	
	@Test
	public void isFrenchTweetTest(){
		Tweet t = new MockedTweet("toto titi");
		assertTrue(t.isFrenchTweet());
	}
	
	@Test
	public void ContainsInvalidIconsTest(){
		Tweet t = new MockedTweet("toto :) titi :(");
		assertFalse(t.containsValidEmoticone());
	}
	
	@Test
	public void ContainsValidPositiveIconsTest(){
		Tweet t = new MockedTweet("toto :) titi");
		assertTrue(t.containsValidEmoticone());
	}
	
	@Test
	public void ContainsValidNegativeIconsTest(){
		Tweet t = new MockedTweet("toto :( titi");
		assertTrue(t.containsValidEmoticone());
	}
	
	@Test
	public void ContainsNoIconsTest(){
		Tweet t = new MockedTweet("toto titi");
		assertTrue(t.containsValidEmoticone());
	}

	@Test
	public void normalExportToCSVTest(){
		String text = "toto titi tata";
		Tweet t = new MockedTweet(text);
		String [] csvLine = {"0", "toto", text, (new java.util.Date(0)).toString(),"-1"};
		assertArrayEquals(csvLine, t.toCSVLine());
	}
	
	@Test
	public void normalToMapTest(){
		Map <String, Integer> map = new HashMap();
		
		Tweet t = new MockedTweet("toto titi tata");
		map.put("toto", new Integer(1));
		map.put("titi", new Integer(1));
		map.put("tata", new Integer(1));

		assertEquals(map, t.toMap());		
	}
	
	@Test
	public void multipleWordsToMapTest(){
		Map <String, Integer> map = new HashMap();
		
		Tweet t = new MockedTweet("toto titi tata titi");
		map.put("toto", new Integer(1));
		map.put("titi", new Integer(2));
		map.put("tata", new Integer(1));

		assertEquals(map, t.toMap());		
	}
	
}
 
