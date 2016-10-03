package test_pje;

import java.util.List;

import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwiterAPI {

	private final static String consumerKey = "kZGG8qllB1U6nlQLmJ6E2pbZL";
	private final static String consumerSecret = "NcczUUj3mQ9L1p1hLDVqOJHQFQ1vtWRcV2CFymNkPapAChZS17";
	private final static String accessToken = "719670964808773632-4Lb2Qp8TIZznZiRMwW8yjJSqz3v0faO";
	private final static String accessTokenSecret = "sN5ekp6mmHaLopBpi8kAeZnhIPd8fW4udfPBINyagXBcO";	
	private Twitter twitter;
	
	public TwiterAPI() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(consumerKey)
		  .setOAuthConsumerSecret(consumerSecret)
		  .setOAuthAccessToken(accessToken)
		  .setOAuthAccessTokenSecret(accessTokenSecret)
		  .setHttpProxyHost("cache.univ-lille1.fr")
		  .setHttpProxyPort(3128);
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		this.twitter = tf.getInstance();
	}
	
	public List <Status> getTweet(String request) throws TwitterException{
		Query query = new Query(request);
		return this.twitter.search(query).getTweets();
	}

}
