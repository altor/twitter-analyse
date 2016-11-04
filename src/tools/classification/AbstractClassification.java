package tools.classification;

import model.TweetsBase;
import twitter.Tweet;

public abstract class AbstractClassification {
	
	protected abstract int getAnnotation(Tweet tweet);
	
	public void setAnnotation(Tweet tweet){
		tweet.setAnnotation(getAnnotation(tweet));
	}


}
