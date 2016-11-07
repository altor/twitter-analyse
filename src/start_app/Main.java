package start_app;
import java.io.IOException;

import controller.TweetsTableController;
import tools.KeyWordExtractor;
import tools.classification.AbstractClassification;
import tools.classification.NaiveClassifitation;
import twitter.TwitterAPI;
import twitter4j.*;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;
import view.Window;

public class Main {

	public static void main(String[] args) throws TwitterException {

		// lancement de L'API Twitter (conexion)
		TwitterAPI twitterAPI = new TwitterAPI();
		AbstractClassification classificator = null;
		// Création du classificateur en fonction des arguments
		if(args.length < 1){
			System.err.println("Mauvais nombre d'argument");
			System.exit(1);
		}
		if(args[0].equals("naif")){
			if(args.length != 3){
				System.err.println("args : naif fichier_mot_positifs fichier_mot_negatif");
				System.exit(1);
			}
			String [] goodKeyWords = KeyWordExtractor.getListeMots(args[1], "FR");
			String [] badKeyWords = KeyWordExtractor.getListeMots(args[2], "FR");

			classificator = new NaiveClassifitation(goodKeyWords, badKeyWords);
		}
		else{
			System.err.println("Argument " + args[0] + " inconnu");
			System.exit(1);
		}
		
		// Lancement du controlleur
		TweetsTableController tableTweets = null;
		try {
			tableTweets = new TweetsTableController("tweetsBase.csv", classificator);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		// Création de la fenêtre d'interface
		Window window = new Window(twitterAPI, tableTweets);
	}
}
