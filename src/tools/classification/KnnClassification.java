package tools.classification;

import java.util.ArrayList;
import java.util.List;


import model.TweetsBase;
import tools.distance.AbstractDistance;
import twitter.Tweet;

public class KnnClassification extends AbstractClassification {

	private AbstractDistance distance;
	private TweetsBase tweetBase;
	
	public List<Tweet> plusProcheVoisin(Tweet tweet, int k) {
		
		ArrayList<Tweet> plusProcheVoisin = new ArrayList<Tweet>(k);
		ArrayList<Integer> distanceTable = new ArrayList<Integer>(k);
		
		// mettre les k premiers tweets dans plusProcheVoisin
		for (int i = 0; i < k; i++) {
			Tweet tweetI = this.tweetBase.getTweet(i);
			plusProcheVoisin.add(tweetI);
			distanceTable.add(this.distance.distance(tweetI, tweet));
		}
		
		// On parcourt le reste de tweetBase
		for (int i = k; i < this.tweetBase.getRowCount(); i++) {
			
			// chercher le max de distanceTable
			int distanceMax = 0;
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
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
