package tools.classification.bayes;


import java.util.HashMap;
import java.util.Map;

import model.TweetsBase;
import tools.classification.AbstractClassification;
import tools.nGrammeExtractor.AbstractNGrammeExtractor;
import twitter.Tweet;

public abstract class AbstractBayesClassification extends AbstractClassification {
	
	protected int nbPositiveTweets = 0;
	protected int nbNegativeTweets = 0;
	protected int nbNeutreTweets = 0;
	
	protected int nbPositiveWords = 0;
	protected int nbNegativeWords = 0;
	protected int nbNeutreWords = 0;	
	
	protected Map<String, Integer> positiveTweets;
	protected Map<String, Integer> negativeTweets;
	protected Map<String, Integer> neutreTweets;
	
	protected AbstractNGrammeExtractor nGrammeExtractor;
	
	// tweetsBase est la base d'apprentissage, cad noté à la main
	public AbstractBayesClassification(TweetsBase tweetsBase, AbstractNGrammeExtractor nGrammeExtractor) {
		this.nGrammeExtractor = nGrammeExtractor;
		
		this.positiveTweets = new HashMap<>();
		this.negativeTweets = new HashMap<>();
		this.neutreTweets = new HashMap<>();
		
		// On parcourt la tweetsBase 
		for (Tweet tweet : tweetsBase) {
			// On transforme ce tweet en Map
			Map<String, Integer> tweetMap;
			tweetMap = nGrammeExtractor.extract(tweet.getText());
			
			if (tweet.getAnnotation() == 0) {
				nbNegativeTweets++;
				negativeTweets = addTweet(tweetMap, negativeTweets);
			}
			else if (tweet.getAnnotation() == 2) {
				nbNeutreTweets++;
				neutreTweets = addTweet(tweetMap, neutreTweets);
			}
			else { // tweet.getAnnotation() == 4
				nbPositiveTweets++;
				positiveTweets = addTweet(tweetMap, positiveTweets);	
			}
		}
		
		// On remplit les valeurs de somme
		nbPositiveWords = getValueSum(positiveTweets);
		nbNegativeWords = getValueSum(negativeTweets);
		nbNeutreWords = getValueSum(neutreTweets);
	}
	
	public static int getValueSum(Map<String,Integer> map) {
		int sum = 0;
		for(String word : map.keySet()) {
			sum += map.get(word);
		}
		return sum;
	}
	
	public static Map<String,Integer> addTweet(Map<String, Integer> tweetMap, Map<String, Integer> tweetsMap){
		for(String word : tweetMap.keySet()) {
			if(tweetsMap.containsKey(word)){
				Integer valeur = tweetsMap.get(word) + tweetMap.get(word);
				tweetsMap.put(word, valeur);
			}
			else {
				tweetsMap.put(word, tweetMap.get(word));
			}
		}
		return tweetsMap;
	}
	
	public abstract int getPositiveProbability(Map<String, Integer> tweetMap);
	
	public abstract int getNegativeProbability(Map<String, Integer> tweetMap);
	
	public abstract int getNeutralProbability(Map<String, Integer> tweetMap);
	
	@Override
	public int getAnnotation(Tweet tweet) {
		// On transforme ce tweet en Map
		Map<String, Integer> tweetMap;
		tweetMap = nGrammeExtractor.extract(tweet.getText());
		
		// GERER LE CAS SI TWEETMAP EST VIDE???
		
		double negativeProbability;
		double positiveProbability;
		double neutralProbability;
		
		positiveProbability = getPositiveProbability(tweetMap);
		positiveProbability *= this.nbPositiveTweets / (this.nbNegativeTweets + this.nbNeutreTweets + this.nbPositiveTweets);
				
		negativeProbability = getNegativeProbability(tweetMap);
		negativeProbability *= this.nbNegativeTweets / (this.nbNegativeTweets + this.nbNeutreTweets + this.nbPositiveTweets);
		
		neutralProbability = getNeutralProbability(tweetMap);
		neutralProbability *= this.nbNeutreTweets / (this.nbNegativeTweets + this.nbNeutreTweets + this.nbPositiveTweets);		
		
		
		if (negativeProbability > positiveProbability && negativeProbability > neutralProbability) {
			return 0;
		}
		else if (positiveProbability > negativeProbability && positiveProbability > neutralProbability) {
			return 4;
		}
		else {
			return 2;
		}
	}
}
