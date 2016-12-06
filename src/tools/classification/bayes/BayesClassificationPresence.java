package tools.classification.bayes;

import java.util.Map;

import tools.nGrammeExtractor.AbstractNGrammeExtractor;

import model.TweetsBase;

public class BayesClassificationPresence extends AbstractBayesClassification {

	public BayesClassificationPresence(TweetsBase tweetsBase, AbstractNGrammeExtractor nGrammeExtractor) {
		super(tweetsBase, nGrammeExtractor);
	}

	// correspond au TP1

	@Override
	public double getPositiveProbability(Map<String, Integer> tweetMap) {
		double positiveProbability = 1;
		
		for(String word : tweetMap.keySet()) {
			// si word appartient aux mots positifs
			if (this.positiveTweets.containsKey(word)) {
				positiveProbability *= ((double)(this.positiveTweets.get(word)) + 1)/(2 * this.nbPositiveWords + this.nbNegativeWords + this.nbNeutreWords);
			}
			else {
				positiveProbability *= 1.0 / (this.nbPositiveWords + (this.nbPositiveWords + this.nbNegativeWords + this.nbNeutreWords));
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
				negativeProbability *= ((double)(this.negativeTweets.get(word)) + 1.0)/(this.nbPositiveWords + this.nbNegativeWords * 2 + this.nbNeutreWords);
			}
			else {
				negativeProbability *= 1.0 / (this.nbNegativeWords + (this.nbPositiveWords + this.nbNegativeWords + this.nbNeutreWords));
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
				neutralProbability *= ((double)(this.neutreTweets.get(word)) + 1.0)/(this.nbPositiveWords + this.nbNegativeWords + 2 * this.nbNeutreWords);
			}
			else {
				neutralProbability *= 1.0 / (this.nbNeutreWords + (this.nbPositiveWords + this.nbNegativeWords + this.nbNeutreWords));
			}
		}
		
		return neutralProbability;
	}

}
