package controller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableModel;

import twitter.Tweet;
import model.TweetTableClassifier;
import model.TweetsBase;
import model.TweetsTableModel;
import tools.classification.AbstractClassification;
import tools.textCleaner.*;
import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


public class TweetsTableController {

	
	protected TweetsTableModel tweetsTableModel;
	protected TweetTableClassifier tweetsTableClassifier;
	protected TweetsBase tweetsBase;
	protected String csvFileName;
	protected TextCleaner tweetCleaner;
	protected AbstractClassification classificator;
	
	
	public TweetsTableController(String csvFileName, TweetsBase tweetsBase, AbstractClassification classificator) throws IOException{
	
		this.csvFileName = csvFileName;
		this.tweetsBase = tweetsBase;
		this.classificator = classificator;

		this.tweetsTableModel = new TweetsTableModel();
		this.tweetsTableClassifier = new TweetTableClassifier(classificator);

		tweetCleaner = new TextCleaner();
		// passage en minusculej
		tweetCleaner.add(new ToLowerCaseCleanMethod());
		// supression des déterminant, pronom, conjonction, ...
		tweetCleaner.add(new SupprimeDeterminant());
		// supression mot de - 3 lettres
		//tweetCleaner.add(new ReplaceStringCleanMethod("(\\p{Space}|^)\\w?\\w?\\w?(\\p{Space}|$)", " "));
		// supression des doubles espace et espaces insécables
		tweetCleaner.add(new ReplaceStringCleanMethod("  |\u00A0", " "));
		// supression des références à un utilisateur
		tweetCleaner.add(new ReplaceStringCleanMethod("@\\p{ASCII}[^\\p{Space}]*", ""));
		// supression des URL
		tweetCleaner.add(new ReplaceStringCleanMethod("(https?://([-\\w\\.]+)+(/([\\w/_\\.]*(\\?\\S+)?(#\\S+)?)?)?)", ""));
		// supression des guillements
		tweetCleaner.add(new ReplaceStringCleanMethod("\"\\s*\"", ""));
		tweetCleaner.add(new ReplaceStringCleanMethod(" $|^ ", ""));
		// supression des doubles espace et espaces insécables
		tweetCleaner.add(new ReplaceStringCleanMethod("  |\u00A0", " "));
		//supression des caractère spéciaux
		tweetCleaner.add(new ReplaceStringCleanMethod("[-#@$€\n()0-9+&@/%?=~_!:,\\.;\"*><^…]|RT", ""));
		
	}
	
	
	public AbstractClassification getClassificator(){
		return classificator;
	}
	
	/*
	 * Netoie les tweet de la tweetBase
	 */
	public void cleanBase(){
		for(Tweet tweet : tweetsBase)
			tweet.clean(tweetCleaner);
		tweetsBase.fireTableDataChanged();
	}
	
	
	public void loadToBase(){
		for(Tweet tweet : tweetsTableModel){
			if(tweetsBase.contains(tweet.getId()))
				System.out.println(tweet.getId() + " : Tweet déjà présent");
			else if(tweet.getAnnotation() != -1){
				//tweet.clean(tweetCleaner);
				//classificator.setAnnotation(tweet);
				tweetsBase.addTweet(tweet);
			}
		}
		updateTableModel(new ArrayList());
		tweetsBase.updateBaseModel();
	}
	
	public void toCSVFile() throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(csvFileName), '\t');
		for (Tweet tweet : tweetsBase) {
			writer.writeNext(tweet.toCSVLine());
		}
		writer.close();
	}

	public void updateTableModel(List<Tweet> tweetsList) {
		List<Tweet> validTweetList = new ArrayList();
		
		for(Tweet tweet : tweetsList){
			if(tweet.containsValidEmoticone())
				if(tweet.isFrenchTweet())
					if(!tweet.isRetweet())
						validTweetList.add(tweet);
					else
						System.out.println(tweet.getId() + " :  RT");
				else
					System.out.println(tweet.getId() + " : Mauvaise Langue");
			else
					System.out.println(tweet.getId() + " : smiley positifs et négatifs détecté");
			
		}
		tweetsTableModel.updateTableModel(validTweetList);
	}

	public TableModel getTableModel() {
		return tweetsTableModel;
	}
	
	public TableModel getBaseModel() {
		return tweetsBase;
	}

	public TweetTableClassifier getTableClassifier() {
		return this.tweetsTableClassifier;
	}




	public void updateTableClassifier(List<Tweet> tweetList) {
    List<Tweet> validTweetList = new ArrayList();
		
		for(Tweet tweet : tweetList){
			if(tweet.containsValidEmoticone())
				if(tweet.isFrenchTweet())
					if(!tweet.isRetweet()){
						tweet.clean(tweetCleaner);
						validTweetList.add(tweet);
					}
					else
						System.out.println(tweet.getId() + " :  RT");
				else
					    System.out.println(tweet.getId() + " : Mauvaise Langue");
						
		}
		tweetsTableClassifier.updateTableModel(validTweetList);
				
	}
}
