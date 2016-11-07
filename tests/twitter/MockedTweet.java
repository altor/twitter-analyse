package twitter;
import java.util.Date;

import twitter.Tweet;
import twitter4j.User;


// Cette classe permet de créer des tweets à laide de donnée manuel

public class MockedTweet extends Tweet {



	protected String []csvLine;
	
	
	// ICI on crée un faux tableau de tweet
	public MockedTweet(String userName, String tweetText){
		super(magicTransformation(userName, tweetText));
	}
	
	public MockedTweet(String userName, String text, String lang) {
		this(userName, text);
		this.lang = lang;
		
	}
	
	public MockedTweet(String text) {
		this("toto", text);		
	}

	public static String[] magicTransformation(String userName, String tweetText){
		Date date = new Date(0);
		String[] csvLine = {"0", userName, tweetText, date.toString(), "-1"};
		return csvLine;
	}
	
	


}
