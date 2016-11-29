package tools.classification.bayes;

import java.util.Map;

import tools.nGrammeExtractor.AbstractNGrammeExtractor;

import model.TweetsBase;

public class BayesClassificationFrequence extends AbstractBayesClassification {
	
	// correspond au TP2


	public BayesClassificationFrequence(TweetsBase tweetsBase, AbstractNGrammeExtractor nGrammeExtractor) {
		super(tweetsBase, nGrammeExtractor);
	}

	@Override
	public int getPositiveProbability(Map<String, Integer> tweetMap) {
		int positiveProbability = 1;
		
		// On remplit positiveProbability TP2
		for(String word : tweetMap.keySet()) {
			// si word appartient aux mots positifs
			if (this.positiveTweets.containsKey(word)) {
				positiveProbability *= Math.pow(this.positiveTweets.get(word)/this.nbPositiveWords, tweetMap.get(word));
			}
			else {
				positiveProbability *= Math.pow(1 / (this.nbPositiveWords + (this.nbPositiveWords + this.nbNegativeWords + this.nbNeutreWords)), tweetMap.get(word));
			}
		}

		return positiveProbability;
	}
	
	@Override
	public int getNegativeProbability(Map<String, Integer> tweetMap) {
		int negativeProbability = 1;
		
		for(String word : tweetMap.keySet()) {	
			// si word appartient aux mots negativs
			if (this.negativeTweets.containsKey(word)) {
				negativeProbability *= Math.pow(this.negativeTweets.get(word)/this.nbNegativeWords, tweetMap.get(word));
			}
			else {
				negativeProbability *= Math.pow(1 / (this.nbNegativeWords + (this.nbPositiveWords + this.nbNegativeWords + this.nbNeutreWords)), tweetMap.get(word));
			}
		}
		
		return negativeProbability;
	}

	@Override
	public int getNeutralProbability(Map<String, Integer> tweetMap) {
		int neutralProbability = 1;
		
		for(String word : tweetMap.keySet()) {
			// si word appartient aux mots neutres
			if (this.positiveTweets.containsKey(word)) {
				neutralProbability *= Math.pow(this.neutreTweets.get(word)/this.nbNeutreWords, tweetMap.get(word));
			}
			else {
				neutralProbability *= Math.pow(1 / (this.nbNeutreWords + (this.nbPositiveWords + this.nbNegativeWords + this.nbNeutreWords)), tweetMap.get(word));
			}
		}
		
		return neutralProbability;
	}
	
	

}
