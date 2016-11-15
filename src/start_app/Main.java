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

		// lancement de L'API Twitter (conexion)
		TwitterAPI twitterAPI = new TwitterAPI();
		AbstractClassification classificator = null;
		
		// Vérification du Nombre d'Argument
		if(args.length < 2){
			System.err.println("Mauvais nombre d'argument");
			System.exit(1);
		}
		
		// Initialisation de la base de Tweet
		TweetsBase tweetsBase;
		FileReader file = null;
		String csvFileName = args[0];
		try {
			file = new FileReader(csvFileName);
			CSVReader reader = new CSVReader(new FileReader(csvFileName), '\t');
			tweetsBase = new TweetsBase(reader.readAll());
		} catch (FileNotFoundException e) {
			tweetsBase = new TweetsBase();
		}
		
		
		// Création du classificateur en fonction des arguments
		if(args[1].equals("naif")){
			if(args.length != 4){
				System.err.println("args : naif fichier_mot_positifs fichier_mot_negatif");
				System.exit(1);
			}
			String [] goodKeyWords = KeyWordExtractor.getListeMots(args[2], "FR");
			String [] badKeyWords = KeyWordExtractor.getListeMots(args[3], "FR");

			classificator = new NaiveClassifitation(goodKeyWords, badKeyWords);
		}
		if(args[1].equals("knn")){
			if(args.length != 4){
				System.err.println("args : knn k distanceName");
				System.exit(1);
			}
			int k = Integer.parseInt(args[2]);
			if(args[3].equals("levenshtein"))
				classificator = new KnnClassification(new AbstractDistance(new Levenshtein()), tweetsBase, k);
		}
		else{
			System.err.println("Argument " + args[1] + " inconnu");
			System.exit(1);
		}
		
		// Lancement du controlleur
		TweetsTableController controller = null;
		try {
			controller = new TweetsTableController("tweetsBase.csv", tweetsBase, classificator);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		// Création de la fenêtre d'interface
		Window window = new Window(twitterAPI, controller);
	}
}
