package start_app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		ArrayList<ArrayList<Tweet>> lists = splitKTweetList(tweetList, 3);
		
		double ea0, ea1, ea2;
		
		//Calcul de ea0
		//Initialisation du classificateur
		List<Tweet> l1Ul2 = new ArrayList();
		l1Ul2.addAll(lists.get(1));
		l1Ul2.addAll(lists.get(2));
		AbstractClassification classificateur = LaunchConfiguration.getClassification(args, 1, new TweetsBase(l1Ul2, true));
		ea0 = classifier(lists.get(0), classificateur);
		
		//Calcul de ea1
		//Initialisation du classificateur
		List<Tweet> l0Ul2 = new ArrayList();
		l0Ul2.addAll(lists.get(0));
		l0Ul2.addAll(lists.get(2));
		classificateur = LaunchConfiguration.getClassification(args, 1, new TweetsBase(l0Ul2, true));
		ea1 = classifier(lists.get(1), classificateur);
		
		//Calcul de ea2
		//Initialisation du classificateur
		List<Tweet> l0Ul1 = new ArrayList();
		l0Ul1.addAll(lists.get(0));
		l0Ul1.addAll(lists.get(1));
		classificateur = LaunchConfiguration.getClassification(args, 1, new TweetsBase(l0Ul1, true));
		ea2 = classifier(lists.get(2), classificateur);
		
		double e = ((ea0 + ea1 + ea2) / 3) * 100;
		System.out.println(e);

	}

	private static ArrayList<ArrayList<Tweet>> splitKTweetList(List<Tweet> tweetList, int k){
    	ArrayList<ArrayList<Tweet>> listOLists = new ArrayList<ArrayList<Tweet>>();
    	
    	for (int i = 0; i < k; i++) {
        	listOLists.add(new ArrayList<Tweet>());
    	}
    	
    	if (k > tweetList.size()) {
            System.err.println("La tweetBase ne contient pas assez de tweets, choississez un k plus petit ou ajouter des tweets à la base");
            System.exit(-1);
        }
    	else {
    		 for (int i = 0; i < tweetList.size() - k + 1; i += k) {
    			 for (int j = 0; j < k; j++) {
        			 listOLists.get(j).add(tweetList.get(i+j));
    			 }
    		 }
    	}
    	
        return listOLists;
	}

	private static double classifier(List<Tweet> tweetList, AbstractClassification classifier){
       
    
	
		double  nb_Faux =0;
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
