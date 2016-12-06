package start_app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

import model.TweetsBase;
import tools.classification.AbstractClassification;
import twitter.Tweet;
import twitter.TwitterAPI;

public class TestClassification {

	public static void main(String[] args) throws IOException {
		// lancement de L'API Twitter (conexion)
		TwitterAPI twitterAPI = new TwitterAPI();
		// Création de la liste des Tweet à classifier
		List<Tweet> tweetList = LaunchConfiguration.getTweetListFromCSV(args, 0);
		// Initialisation de la base de Tweet
		TweetsBase tweetsBase = LaunchConfiguration.getTweetBase(args, 1);
		//Initialisation du classificateur
		AbstractClassification classifier = LaunchConfiguration.getClassification(args, 2, tweetsBase);
		
		int vraiPositif = 0;
		int fauxPositif = 0;
		int vraiNegatif = 0;
		int fauxNegatif = 0;
		int vraiNeutre = 0;
		int fauxNeutre = 0;
		
		for(Tweet tweet : tweetList){
			int annotation = tweet.getAnnotation();
			classifier.setAnnotation(tweet);
			int classifierAnnotation = tweet.getAnnotation();
			
			if(annotation == 4){
				if(annotation != classifierAnnotation)
					fauxPositif++;
				else
					vraiPositif++;
			}
			else if(annotation == 2){
				if(annotation != classifierAnnotation)
					fauxNeutre++;
				else
					vraiNeutre++;
			}
			
			else if(annotation == 0){
				if(annotation != classifierAnnotation)
					fauxNegatif++;
				else
					vraiNegatif++;
			}
			
			System.out.println("vrai positifs : " + vraiPositif + "\n" + 
					"faux positifs : " + fauxPositif + "\n" + 
					"vrai neutre : " + vraiNeutre + "\n" +
					"faux neutre : " + fauxNeutre + "\n" + 
					"vrai negatif : " + vraiNegatif + "\n" + 
					"faux negatif : " + fauxNegatif + "\n");
			System.exit(0);
			
		}
		
		

	}
	private List<Tweet>[] splitKTweetList(List<Tweet> tweetList, int k){
		
		return null;
	}

	private double classifier(List<Tweet> tweetList, TweetsBase base, AbstractClassification classifier){
       
    int  nb_Faux =0;
    double taux =0;


	for (Tweet tweet :tweetList){
			int annotation = tweet.getAnnotation();
			int classifierAnnotation = classifier.getAnnotation(tweet);


			if(annotation !=classifierAnnotation){
			
				nb_Faux++;
			}
		}
	
		int nb_total=tweetList.size();
	
		taux = nb_Faux /nb_total; 			
		return taux;
		
	}
	
}
