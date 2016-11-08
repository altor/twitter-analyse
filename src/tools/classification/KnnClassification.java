package tools.classification;

import java.util.ArrayList;
import java.util.List;


import model.TweetsBase;
import tools.distance.AbstractDistance;
import twitter.Tweet;

public class KnnClassification extends AbstractClassification {

	private AbstractDistance distance;
	private TweetsBase tweetBase;
	private int k;
	
	public KnnClassification(AbstractDistance distance, TweetsBase tweetBase, int k) {
		this.distance = distance;
		this.tweetBase = tweetBase;
		this.k = k;
	}
	
	public List<Tweet> plusProcheVoisin(Tweet tweet) {
		
		ArrayList<Tweet> plusProcheVoisin = new ArrayList<Tweet>(k);
		ArrayList<Float> distanceTable = new ArrayList<Float>(k);
		
		// mettre les k premiers tweets dans plusProcheVoisin
		for (int i = 0; i < this.k; i++) {
			Tweet tweetI = this.tweetBase.getTweet(i);
			plusProcheVoisin.add(tweetI);
			distanceTable.add(this.distance.distance(tweetI, tweet));
		}
		
		// On parcourt le reste de tweetBase
		for (int i = this.k; i < this.tweetBase.getRowCount(); i++) {
			
			// chercher le max de distanceTable
			float distanceMax = 0;
			int indiceMax = 0;
			// on parcourt distanceTable
			for (int j = 0; j < distanceTable.size(); j++) {
				if (distanceTable.get(j) > distanceMax) {
					distanceMax = distanceTable.get(j);
					indiceMax = j;
				}
			}
			
			// comparer cette distance avec la distance entre le tweet et le tweet de la table traité
			if (distanceMax > this.distance.distance(tweet, tweetBase.getTweet(i))) {
				// on echange
				Tweet tweetI = this.tweetBase.getTweet(i);
				plusProcheVoisin.set(indiceMax, tweetI);
				distanceTable.set(indiceMax, this.distance.distance(tweet, tweetI));
			}
		}
		
		return plusProcheVoisin;
	}


	@Override
	public int getAnnotation(Tweet tweet) {
		// On fait la moyenne des anootation des tweet les plus proche
		int sommeAnnotations = 0;
		List<Tweet> tweetsVoisins = this.plusProcheVoisin(tweet);
		for (int i = 0; i < tweetsVoisins.size(); i++) {
			sommeAnnotations += tweetsVoisins.get(i).getAnnotation();
		}
		
		float annotation;
		annotation = (float)sommeAnnotations / (float)tweetsVoisins.size();
		return Math.round(annotation);
	}
	
	

}
