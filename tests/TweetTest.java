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
	
	@Test
	public void cleanTest(){
		//chaine avec espace insécable avant le :
		String text = "J'aime une vidéo @YouTube : \"CYPRIEN - PARODIE PUB APPLE WATCH\" à l'adresse https://t.co/qDqSlif08B.";;
		Tweet t = createFRTweet(text);
		t.cleanText();
		//chaine sans espace insécable avant le :
		assertEquals(t.getText(), "J'aime une vidéo : \"CYPRIEN - PARODIE PUB APPLE WATCH\" à l'adresse");
	}
	
	@Test
	public void cleanSpacesTest(){
		String text = "toto  titi";
		Tweet t = createFRTweet(text);
		t.suppUrl();
		t.cleanSpaces();
		assertEquals(t.getText(), "toto titi");
		
		text = "toto  titi ";
		t = createFRTweet(text);
		t.suppUrl();
		t.cleanSpaces();
		assertEquals(t.getText(), "toto titi");
		
		text = " toto titi";
		t = createFRTweet(text);
		t.suppUrl();
		t.cleanSpaces();
		assertEquals(t.getText(), "toto titi");
		
		
		
	}
	
	@Test
	public void suppUrlEmptyTest(){
		String text = "toto titi tata";
		Tweet t = createFRTweet(text);
		t.suppUrl();
		t.cleanSpaces();
		assertEquals(t.getText(), "toto titi tata");
	}
	
	// PRIVATE METHODS
	
	private Tweet createTweet(String text, String lang){
		return new Tweet(new MockedStatus("toto", text, lang));
	}
	
	private Tweet createFRTweet(String text){
		return createTweet(text, "fr");
	}
	
}
