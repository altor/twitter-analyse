import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.*;

public class TweetTest {

	@Test(expected=BadLanguageException.class)
	public void exportToCSVWithBadLanguageExceptiontest() throws BadLanguageException, SmileysException {
		exportToCSV("toto", "en");
	}
	
	@Test(expected=SmileysException.class)
	public void exportToCSVWithSmileyExceptiontest() throws BadLanguageException, SmileysException {
		exportToCSVfr("toto :) titi :(");
	}

	@Test
	public void exportToCSVWithPositiveSmileyTest() throws BadLanguageException, SmileysException{
		exportToCSVfr("toto :)");
	}
	
	@Test
	public void exportToCSVWithNegativeSmileyTest() throws BadLanguageException, SmileysException{
		exportToCSVfr(":( toto :(");
	}
	
	@Test
	public void normalExportToCSVTest() throws BadLanguageException, SmileysException{
		exportToCSVfr("toto titi tata");
	}
	
	// PRIVATE METHODS
	
	private void exportToCSV(String text, String lang) throws BadLanguageException, SmileysException{
		String [] csvLine = {"0", "toto", text, (new java.util.Date(0)).toString(),"-1"};
		Tweet tweet = new Tweet(new MockedStatus("toto", text, lang));
		assertArrayEquals(csvLine, tweet.toCSVLine());
	}
	
	private void exportToCSVfr(String text) throws BadLanguageException, SmileysException{
		exportToCSV(text, "fr");
	}
	
	
}
