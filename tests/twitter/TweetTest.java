package twitter;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import twitter.Tweet;

public class TweetTest {
	
	@Test
	public void isNotFrenchTweetTest(){
		Tweet t = createTweet("toto :) titi :(", "en");
		assertFalse(t.isFrenchTweet());
	}
	
	@Test
	public void isFrenchTweetTest(){
		Tweet t = createFRTweet("toto titi");
		assertTrue(t.isFrenchTweet());
	}
	
	@Test
	public void ContainsInvalidIconsTest(){
		Tweet t = createFRTweet("toto :) titi :(");
		assertFalse(t.containsValidEmoticone());
	}
	
	@Test
	public void ContainsValidPositiveIconsTest(){
		Tweet t = createFRTweet("toto :) titi");
		assertTrue(t.containsValidEmoticone());
	}
	
	@Test
	public void ContainsValidNegativeIconsTest(){
		Tweet t = createFRTweet("toto :( titi");
		assertTrue(t.containsValidEmoticone());
	}
	
	@Test
	public void ContainsNoIconsTest(){
		Tweet t = createFRTweet("toto titi");
		assertTrue(t.containsValidEmoticone());
	}

	@Test
	public void normalExportToCSVTest(){
		String text = "toto titi tata";
		Tweet t = createFRTweet(text);
		String [] csvLine = {"0", "toto", text, (new java.util.Date(0)).toString(),"-1"};
		assertArrayEquals(csvLine, t.toCSVLine());
	}
	


	// PRIVATE METHODS
	
	private Tweet createTweet(String text, String lang){
		return new Tweet(new MockedStatus("toto", text, lang));
	}
	
	private Tweet createFRTweet(String text){
		return createTweet(text, "fr");
	}
	
}
