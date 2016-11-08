package tools.distance;

import java.util.List;

import twitter.Tweet;
import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;

public class AbstractDistance {

	protected AbstractStringMetric stringMetric;
	
	public AbstractDistance(AbstractStringMetric stringMetric){
		this.stringMetric = stringMetric;
	}
	
	public float distance(Tweet tweet1, Tweet tweet2){
		return stringMetric.getSimilarity(tweet1.getText(), tweet2.getText());
	}

}
