package controller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableModel;

import twitter.Tweet;

import model.TweetsBase;
import model.TweetsTableModel;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import controller.textCleaner.CleanMethod;
import controller.textCleaner.TextCleaner;


public class TweetsTableController {

	
	protected TweetsTableModel tweetsTableModel;
	protected TweetsBase tweetsBase;
	protected String csvFileName;
	protected TextCleaner tweetCleaner;
	
	public TweetsTableController(String csvFileName) throws IOException{
		FileReader file = null;
		
		try {
			file = new FileReader(csvFileName);
			CSVReader reader = new CSVReader(new FileReader(csvFileName), '\t');
			this.tweetsBase = new TweetsBase(reader.readAll());
		} catch (FileNotFoundException e) {
			tweetsBase = new TweetsBase();
		}
		this.tweetsTableModel = new TweetsTableModel();
		this.csvFileName = csvFileName;
		
		tweetCleaner = new TextCleaner();
		// supression des doubles espace et espaces insécables
		tweetCleaner.add(new CleanMethod("  |\u00A0|", " "));
		// supression des références à un utilisateur
		tweetCleaner.add(new CleanMethod("@\\p{ASCII}[^\\p{Space}]*", ""));
		// supression des URL
		tweetCleaner.add(new CleanMethod("(https?://([-\\w\\.]+)+(/([\\w/_\\.]*(\\?\\S+)?(#\\S+)?)?)?)", ""));
		// supression des guillements
		tweetCleaner.add(new CleanMethod("\"\\s*\"", ""));
		tweetCleaner.add(new CleanMethod(" $|^ ", ""));
		// supression des doubles espace et espaces insécables
		tweetCleaner.add(new CleanMethod("  |\u00A0|", " "));

		


	}
	
	public void loadToBase(){
		for(Tweet tweet : tweetsTableModel){
			if(tweetsBase.contains(tweet.getId()))
				System.out.println(tweet.getId() + " : Tweet déjà présent");
			else
				tweet.clean(tweetCleaner);
				tweetsBase.addTweet(tweet);
		}
		updateTableModel(new ArrayList());
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

	public TableModel getModel() {
		return tweetsTableModel;
	}
}
