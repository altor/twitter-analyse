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
	public double getPositiveProbability(Map<String, Integer> tweetMap) {
		double positiveProbability = 1;
		
		// On remplit positiveProbability TP2
		for(String word : tweetMap.keySet()) {
			// si word appartient aux mots positifs
			if (this.positiveTweets.containsKey(word)) {
				positiveProbability *= Math.pow(((double)(this.positiveTweets.get(word)))/this.nbPositiveWords, tweetMap.get(word));
			}
			else {
				positiveProbability *= Math.pow(1.0 / (this.nbPositiveWords + (this.nbPositiveWords + this.nbNegativeWords + this.nbNeutreWords)), tweetMap.get(word));
			}
		}

		return positiveProbability;
	}
	
	@Override
	public double getNegativeProbability(Map<String, Integer> tweetMap) {
		double negativeProbability = 1;
		
		for(String word : tweetMap.keySet()) {	
			// si word appartient aux mots negativs
			if (this.negativeTweets.containsKey(word)) {
				negativeProbability *= Math.pow(((double)(this.negativeTweets.get(word)))/this.nbNegativeWords, tweetMap.get(word));
			}
			else {
				negativeProbability *= Math.pow(1.0 / (this.nbNegativeWords + (this.nbPositiveWords + this.nbNegativeWords + this.nbNeutreWords)), tweetMap.get(word));
			}
		}
		
		return negativeProbability;
	}

	@Override
	public double getNeutralProbability(Map<String, Integer> tweetMap) {
		double neutralProbability = 1;
		
		for(String word : tweetMap.keySet()) {
			// si word appartient aux mots neutres
			if (this.neutreTweets.containsKey(word)) {

				neutralProbability *= Math.pow(((double)(this.neutreTweets.get(word)))/this.nbNeutreWords, tweetMap.get(word));
			}
			else {
				neutralProbability *= Math.pow(1.0 / (this.nbNeutreWords + (this.nbPositiveWords + this.nbNegativeWords + this.nbNeutreWords)), tweetMap.get(word));
			}
		}
		
		return neutralProbability;
	}
	
	

}
