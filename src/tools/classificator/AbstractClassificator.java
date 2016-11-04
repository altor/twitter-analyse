package tools.classificator;

import model.TweetsBase;
import twitter.Tweet;

public abstract class AbstractClassificator {

	protected TweetsBase tweetsBase;
	
	public AbstractClassificator(TweetsBase tweetsBase) {
		this.tweetsBase = tweetsBase;
	}
	
	protected abstract int getAnnotation(Tweet tweet);
	
	public void setAnnotation(Tweet tweet){
		tweet.setAnnotation(getAnnotation(tweet));
	}


}
