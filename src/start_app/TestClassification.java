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
		
		// Extraction des 3 listes de Tweet
		List<Tweet> [] lists = splitKTweetList(tweetList, 3);
		
		double ea0, ea1, ea2;
		
		//Calcul de ea0
		//Initialisation du classificateur
		List<Tweet> l1Ul2 = new ArrayList();
		l1Ul2.addAll(lists[1]);
		l1Ul2.addAll(lists[2]);
		AbstractClassification classificateur = LaunchConfiguration.getClassification(args, 2, new TweetsBase(l1Ul2, true));
		ea0 = classifier(l1Ul2, classificateur);
		
		//Calcul de ea1
		//Initialisation du classificateur
		List<Tweet> l0Ul2 = new ArrayList();
		l0Ul2.addAll(lists[0]);
		l0Ul2.addAll(lists[2]);
		classificateur = LaunchConfiguration.getClassification(args, 2, new TweetsBase(l0Ul2, true));
		ea1 = classifier(l0Ul2, classificateur);
		
		//Calcul de ea2
		//Initialisation du classificateur
		List<Tweet> l0Ul1 = new ArrayList();
		l0Ul1.addAll(lists[0]);
		l0Ul1.addAll(lists[1]);
		classificateur = LaunchConfiguration.getClassification(args, 2, new TweetsBase(l0Ul1, true));
		ea2 = classifier(l0Ul1, classificateur);
		
		double e = (ea0 + ea1 + ea2) / 3;
		System.out.println(e);
	}
	private static List<Tweet>[] splitKTweetList(List<Tweet> tweetList, int k){
		
		return null;
	}

	private static double classifier(List<Tweet> tweetList, AbstractClassification classifier){

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
