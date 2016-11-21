package tools.classification;


import java.util.HashMap;
import java.util.Map;

import model.TweetsBase;

import twitter.Tweet;

public class BayesClassification extends AbstractClassification {
	
	private int nbPositiveTweets = 0;
	private int nbNegativeTweets = 0;
	private int nbNeutreTweets = 0;
	
	private int nbPositiveWords = 0;
	private int nbNegativeWords = 0;
	private int nbNeutreWords = 0;	
	
	private Map<String, Integer> positiveTweets;
	private Map<String, Integer> negativeTweets;
	private Map<String, Integer> neutreTweets;
	
	// tweetsBase est la base d'apprentissage, cad noté à la main
	public BayesClassification(TweetsBase tweetsBase) {
		this.positiveTweets = new HashMap<>();
		this.negativeTweets = new HashMap<>();
		this.neutreTweets = new HashMap<>();
		
		// On parcourt la tweetsBase 
		for (Tweet tweet : tweetsBase) {
			// On transforme ce tweet en Map
			Map<String, Integer> tweetMap;
			tweetMap = tweet.toMap();
			
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
	
	@Override
	public int getAnnotation(Tweet tweet) {
		// On transforme ce tweet en Map
		Map<String, Integer> tweetMap;
		tweetMap = tweet.toMap();
		
		// GERER LE CAS SI TWEETMAP EST VIDE???
		
		float negativeProbability = 1;
		float positiveProbability = 1;
		float neutralProbability = 1;
		
		// On remplit positiveProbability
		for(String word : tweetMap.keySet()) {
			// si word appartient aux mots positifs
			if (this.positiveTweets.containsKey(word)) {
				positiveProbability *= this.positiveTweets.get(word)/this.nbPositiveWords;
			}
			else {
				positiveProbability *= 1 / (this.nbPositiveWords + (this.nbPositiveWords + this.nbNegativeWords + this.nbNeutreWords));
			}
		}
		positiveProbability *= this.nbPositiveTweets / (this.nbNegativeTweets + this.nbNeutreTweets + this.nbPositiveTweets);
		
		// On remplit negativeProbability
		for(String word : tweetMap.keySet()) {	
			// si word appartient aux mots negativs
			if (this.negativeTweets.containsKey(word)) {
				negativeProbability *= this.negativeTweets.get(word)/this.nbNegativeWords;
			}
			else {
				negativeProbability *= 1 / (this.nbNegativeWords + (this.nbPositiveWords + this.nbNegativeWords + this.nbNeutreWords));
			}
		}
		negativeProbability *= this.nbNegativeTweets / (this.nbNegativeTweets + this.nbNeutreTweets + this.nbPositiveTweets);
		
		// On remplit neutralProbability
		for(String word : tweetMap.keySet()) {
			
			// si word appartient aux mots neutres
			if (this.positiveTweets.containsKey(word)) {
				neutralProbability *= this.neutreTweets.get(word)/this.nbNeutreWords;
			}
			else {
				neutralProbability *= 1 / (this.nbNeutreWords + (this.nbPositiveWords + this.nbNegativeWords + this.nbNeutreWords));
			}
		}
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
