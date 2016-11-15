package tools.classification;


import java.util.HashMap;
import java.util.Map;

import model.TweetsBase;

import twitter.Tweet;

public class BayesClassification extends AbstractClassification {
	
	private int nbPositiveTweets = 0;
	private int nbNegativeTweets = 0;
	private int nbNeutreTweets = 0;

	private Map<String, Integer> positiveTweets;
	private Map<String, Integer> negativeTweets;
	private Map<String, Integer> neutreTweets;
	
	public BayesClassification(TweetsBase t) {  		
		this.negativeTweets = new HashMap<String, Integer>();
		
		// On compte de tweet positive, negatif et neutre
		for (Tweet tweet: t){
			if (tweet.getAnnotation() == 0) {
				nbNegativeTweets++;
			}
			if (tweet.getAnnotation() == 2) {
				nbNeutreTweets++;
			}
			if (tweet.getAnnotation() == 4) {
				nbPositiveTweets++;
			}
		}
		
		/*
		for (Tweet tweet: t){
			if (tweet.getAnnotation() == 0) {
				positiveTweets
			}
			if (tweet.getAnnotation() == 2) {
				
			}
			if (tweet.getAnnotation() == 4) {
				
			}
		}
		*/
		
		
		//this.positiveTweets = t
		//this.negativeTweets = 
		//this.neutreTweets = 
				
	}
	
	
	
	@Override
	public int getAnnotation(Tweet tweet) {
		// TODO Auto-generated method stub
		return 0;
	}

}
