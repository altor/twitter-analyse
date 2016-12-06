package tools.classification.bayes;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import model.TweetsBase;
import tools.classification.AbstractClassificationTest;
import tools.classification.KnnClassification;
import tools.distance.AbstractDistance;
import tools.nGrammeExtractor.UniAndBiGrammeExtractor;
import tools.nGrammeExtractor.UniGrammeExtractor;
import twitter.MockedTweet;
import twitter.Tweet;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

public class AbstractBayesTest extends AbstractClassificationTest{

	@Before
	public void setUp() throws Exception {
				
		
		
		Tweet tweet1 = new MockedTweet("toto", 4);
		Tweet tweet2 = new MockedTweet("titi", 2);
		Tweet tweet3 = new MockedTweet("tata", 0);
		
		TweetsBase tweetBase = new TweetsBase();
		tweetBase.addTweet(tweet1);
		tweetBase.addTweet(tweet2);
		tweetBase.addTweet(tweet3);

		
		int k = 2;

		this.classificator = new BayesClassificationPresence(tweetBase, new UniGrammeExtractor());
		

	}
	
	@Test
	public void neutreGetAnnotationTest() {
		
		textTest("haha titi", 2);
	}
	
	@Test
	public void positiveGetAnnotationTest() {
		textTest("toto toto", 4);
	}
	@Test
	public void negativeGetAnnotationTest() {

		textTest("haha tata", 0);
	}

}
