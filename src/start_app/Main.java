package start_app;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

import controller.TweetsTableController;
import model.TweetsBase;
import model.TweetsTableModel;
import tools.KeyWordExtractor;
import tools.classification.AbstractClassification;
import tools.classification.KnnClassification;
import tools.classification.NaiveClassifitation;
import tools.distance.AbstractDistance;
import twitter.TwitterAPI;
import twitter4j.*;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;
import view.Window;

public class Main {

	public static void main(String[] args) throws TwitterException, IOException {
		
		// Vérification du Nombre d'Argument
		if(args.length < 2){
			System.err.println("Mauvais nombre d'argument");
			System.exit(1);
		}
		
		TwitterAPI twitterAPI = new TwitterAPI();
		TweetsBase tweetsBase = LaunchConfiguration.getTweetBase(args, 0);
		AbstractClassification classifier = LaunchConfiguration.getClassification(args, 1, tweetsBase);
		
		// Lancement du controlleur
		TweetsTableController controller = null;
		try {
			controller = new TweetsTableController(args[0], tweetsBase, classifier);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		// Création de la fenêtre d'interface
		Window window = new Window(twitterAPI, controller);
	}
}
