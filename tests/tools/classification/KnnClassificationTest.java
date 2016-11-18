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
				
		Tweet tweet1 = new MockedTweet("Toute est possible jusqu'à ce que quelqu'un le fasse pas", 2);
		Tweet tweet2 = new MockedTweet("Quand un philosophe me répond, je ne comprends plus ma question.", 4);
		Tweet tweet3 = new MockedTweet("Vous riez de moi", 0);
		Tweet tweet4 = new MockedTweet("Tout est presque imposssible jusqu'à ce quelqu'un le fasse", 2);
		Tweet tweet5 = new MockedTweet("manger des pates", 4);
		Tweet tweet6 = new MockedTweet("zero zero zero", 0);


		
		TweetsBase tweetBase = new TweetsBase();
		tweetBase.addTweet(tweet1);
		tweetBase.addTweet(tweet2);
		tweetBase.addTweet(tweet3);
		tweetBase.addTweet(tweet4);
		tweetBase.addTweet(tweet5);
		tweetBase.addTweet(tweet6);

		
		int k = 2;

		this.classificator = new KnnClassification(distance, tweetBase, k);
	}

	@Test
	public void neutreGetAnnotationTest() {
		textTest("Tout est impossible jusqu'à ce que quelqu'un le fasse", 2);
	}
	
	@Test
	public void positiveGetAnnotationTest() {
		textTest("question philosophe avec des pates", 4);
	}
	
	@Test
	public void negativeGetAnnotationTest() {
		textTest("vous riez zero", 0);
	}
	
	@Test public void betweenGetannotaitonText(){
		textTest("zero est possible", 2);

	}


}
