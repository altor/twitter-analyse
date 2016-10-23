import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.*;

public class TweetTest {

	@Test(expected=BadLanguageException.class)
	public void BadLanguageExceptiontest() throws BadLanguageException, SmileysException {
		Tweet tweet = new Tweet(new MockedStatus("toto", "hahaha", "en"));
		tweet.toCSVLine();
	}
	
	@Test(expected=SmileysException.class)
	public void SmileyExceptiontest() throws BadLanguageException, SmileysException {
		Tweet tweet = new Tweet(new MockedStatus("toto", "toto :) titi :(", "fr"));
		tweet.toCSVLine();
	}
}
