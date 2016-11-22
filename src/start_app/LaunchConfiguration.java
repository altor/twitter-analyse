package start_app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

import model.TweetsBase;
import tools.KeyWordExtractor;
import tools.classification.AbstractClassification;
import tools.classification.KnnClassification;
import tools.classification.NaiveClassifitation;
import tools.distance.AbstractDistance;
import twitter.Tweet;
import twitter.TwitterAPI;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

public class LaunchConfiguration {
	
	public static TweetsBase getTweetBase(String [] args, int baseCSVRank) throws IOException{
		// Initialisation de la base de Tweet
		FileReader file = null;
		String csvFileName = args[baseCSVRank];
		try {
			file = new FileReader(csvFileName);
			CSVReader reader = new CSVReader(new FileReader(csvFileName), '\t');
			return new TweetsBase(reader.readAll());
		} catch (FileNotFoundException e) {
			return new TweetsBase();
		}
	}
	
	public static List<Tweet> getTweetListFromCSV(String [] args, int csvRank){
		FileReader file = null;
		String csvFileName = args[csvRank];
		List<Tweet> tweetList = new ArrayList<>();

		try {
			file = new FileReader(csvFileName);
			CSVReader reader = new CSVReader(new FileReader(csvFileName), '\t');
			for(String[] nextLine  : reader.readAll()){
				tweetList.add(new Tweet(nextLine));
			}
		} catch (IOException e) {
			System.err.println("Erreur lors de la lecture du fichier " + args[0]);
			System.exit(1);
		}
		return tweetList;
	}
	
	
	public static AbstractClassification getClassification(String [] args, int classificatorRank, TweetsBase tweetsBase){
		if(args[classificatorRank].equals("naif")){
			if(args.length != classificatorRank + 3){
				System.err.println("args : naif fichier_mot_positifs fichier_mot_negatif");
				System.exit(1);
			}
			String [] goodKeyWords = KeyWordExtractor.getListeMots(args[classificatorRank + 1], "FR");
			String [] badKeyWords = KeyWordExtractor.getListeMots(args[classificatorRank + 2], "FR");

			return new NaiveClassifitation(goodKeyWords, badKeyWords);
		}
		if(args[1].equals("knn")){
			if(args.length != classificatorRank + 3){
				System.err.println("args : knn k distanceName");
				System.exit(1);
			}
			int k = Integer.parseInt(args[classificatorRank + 1]);
			if(args[classificatorRank + 2].equals("levenshtein"))
				return new KnnClassification(new AbstractDistance(new Levenshtein()), tweetsBase, k);
		}

		System.err.println("Classificateur " + args[classificatorRank] + " inconnu");
		System.exit(1);

		return null;
	}
	

}
