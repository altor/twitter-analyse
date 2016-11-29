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
	public int getPositiveProbability(Map<String, Integer> tweetMap) {
		int positiveProbability = 1;
		
		for(String word : tweetMap.keySet()) {
			// si word appartient aux mots positifs
			if (this.positiveTweets.containsKey(word)) {
				positiveProbability *= this.positiveTweets.get(word)/this.nbPositiveWords;
			}
			else {
				positiveProbability *= 1 / (this.nbPositiveWords + (this.nbPositiveWords + this.nbNegativeWords + this.nbNeutreWords));
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
				negativeProbability *= this.negativeTweets.get(word)/this.nbNegativeWords;
			}
			else {
				negativeProbability *= 1 / (this.nbNegativeWords + (this.nbPositiveWords + this.nbNegativeWords + this.nbNeutreWords));
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
				neutralProbability *= this.neutreTweets.get(word)/this.nbNeutreWords;
			}
			else {
				neutralProbability *= 1 / (this.nbNeutreWords + (this.nbPositiveWords + this.nbNegativeWords + this.nbNeutreWords));
			}
		}
		
		return neutralProbability;
	}

}
