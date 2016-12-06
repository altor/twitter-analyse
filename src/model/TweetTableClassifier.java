package model;

import java.util.List;

import tools.classification.AbstractClassification;
import twitter.Tweet;

public class TweetTableClassifier extends TweetsTableModel {
	protected AbstractClassification classifier;
	
	public TweetTableClassifier(AbstractClassification classifier){
		super();
	}

	 //Taux des tweet positif
	
	public double getPositivePercent(){
		
		double nbPositif =0;
		double tauxPositif=0;
		
		for(Tweet tweet : tweetList){
			
		int annotation = tweet.getAnnotation();
		
			if(annotation==4){
			   nbPositif++;
			
			}
		}
		double nb_total= tweetList.size();
		tauxPositif=nbPositif/nb_total;
	
		return tauxPositif * 100;
	}
	
	//Taux des tweets negatifs

	
	public double getNegativePercent(){
		
		double nbnegatif =0;
		double taux_negatif=0;
		
		for(Tweet tweet : tweetList){
			
		int annotation = tweet.getAnnotation();
		
			if(annotation==0){
			   nbnegatif++;
			
			}
		}
		
		double nb_total= tweetList.size();
		taux_negatif=nbnegatif/nb_total;
	
		return taux_negatif * 100;
		
	}
	
	//Taux des tweet neutres

	public double getNeutralPercent(){
		
		double nb_neutre =0;
		double taux_neutre=0;
		
		for(Tweet tweet : tweetList){
		int annotation = tweet.getAnnotation();
		
			if(annotation==2){
			   nb_neutre++;
			
			}
		}
		double nb_total= tweetList.size();
		taux_neutre=nb_neutre/nb_total;
	
		return taux_neutre * 100;
	
	}
	
}
