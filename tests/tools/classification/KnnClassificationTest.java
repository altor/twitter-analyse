package tools.classification;

import static org.junit.Assert.*;

import model.TweetsBase;

import org.junit.Before;
import org.junit.Test;

import tools.distance.AbstractDistance;
import twitter.MockedTweet;
import twitter.Tweet;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

public class KnnClassificationTest extends AbstractClassificationTest {

	@Before
	public void setUp() throws Exception {
		AbstractDistance distance = new AbstractDistance(new Levenshtein());
		
		//Tweet tweet = new MockedTweet("Tout est impossible jusqu'à ce que quelqu'un le fasse");
		
		Tweet tweet1 = new MockedTweet("Toute est possible jusqu'à ce que quelqu'un le fasse pas");
		Tweet tweet2 = new MockedTweet("Quand un philosophe me répond, je ne comprends plus ma question.");
		Tweet tweet3 = new MockedTweet("Vous riez de moi parce que je suis différent, mais moi je ris de vous parce que vous êtes tous les même.");
		Tweet tweet4 = new MockedTweet("Tout est presque imposssible jusqu'à ce quelqu'un le fasse");
		
		TweetsBase tweetBase = new TweetsBase();
		tweetBase.addTweet(tweet1);
		tweetBase.addTweet(tweet2);
		tweetBase.addTweet(tweet3);
		tweetBase.addTweet(tweet4);
		
		int k = 3;
		this.classificator = new KnnClassification(distance, tweetBase, k);
	}
	
	@Test
	public void getAnnotationTest() {
		textTest("Tout est impossible jusqu'à ce que quelqu'un le fasse", 5);
	}

}
