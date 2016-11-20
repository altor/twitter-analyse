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
				negativeTweets = AddTweet(tweetMap, negativeTweets);
			}
			else if (tweet.getAnnotation() == 2) {
				nbNeutreTweets++;
				neutreTweets = AddTweet(tweetMap, neutreTweets);
			}
			else { // tweet.getAnnotation() == 4
				nbPositiveTweets++;
				positiveTweets = AddTweet(tweetMap, positiveTweets);	
			}
		}
	}
	
	public static Map<String,Integer> AddTweet(Map<String, Integer> tweetMap, Map<String, Integer> tweetsMap){
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
		
		int probaNegatif;
		int probaNeutre;
		int probaPositif = 1;
		
		// Pour positif
		// On parcourt le tweetMap
		/*for(String word : tweetMap.keySet()) {
			// 
			if () {
				probaPositif = 
			}

			
			
		}*/
		

		
		// pour chaque classe
		// P(T|positif) = 2 * 5/nb_total * ... pour chaque mots
		// tweet : Bonjour 2
		// positive : Bonjour 5
		// 
		
		
		// TODO Auto-generated method stub
		return 0;
	}

}
