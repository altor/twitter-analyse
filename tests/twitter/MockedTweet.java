package twitter;
import java.util.Date;

import twitter.Tweet;
import twitter4j.User;


// Cette classe permet de créer des tweets à laide de donnée manuel

public class MockedTweet extends Tweet {



	protected String []csvLine;
	
	
	// ICI on crée un faux tableau de tweet
	public MockedTweet(String userName, String tweetText, int annotation){
		super(magicTransformation(userName, tweetText, annotation));
	}
	
	
	public MockedTweet(String userName, String text, String lang) {
		this(userName, text, -1);
		this.lang = lang;
		
	}
	
	public MockedTweet(String text, int annotation){
		this("toto", text, annotation);
	}
	
	public MockedTweet(String text) {
		this("toto", text, -1);		
	}
	

	public static String[] magicTransformation(String userName, String tweetText, int annotation){
		Date date = new Date(0);
		String[] csvLine = {"0", userName, tweetText, date.toString(), "-1"};
		return csvLine;
	}
	
	
	
	


}
