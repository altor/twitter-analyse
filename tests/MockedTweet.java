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
	
	public static String[] magicTransformation(String userName, String tweetText){
		String[] csvLine = {"0", userName, tweetText, "", "-1"};
		return csvLine;
	}
	
	


}
