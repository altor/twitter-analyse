package tools.classification;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import twitter.MockedTweet;
import twitter.Tweet;

public class AbstractClassificationTest {

	protected AbstractClassification classificator;

	protected void textTest(String text, int annotation) {
		Tweet tweet = new MockedTweet("toto", text);
		assertEquals(classificator.getAnnotation(tweet), annotation);
	}

}
