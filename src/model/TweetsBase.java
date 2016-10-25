package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import twitter.Tweet;
import twitter4j.Status;

import com.opencsv.CSVReader;



public class TweetsBase implements Iterable<Tweet> {

	
	protected HashMap<Long, Tweet> tweetHashMap;
	protected String csvFileName;
	
public TweetsBase() throws IOException{
		
		tweetHashMap = new HashMap();
	}
	
	/**
	 * Charge la base de Tweet à partir du fichier CSV
	 * @param csvFileName nom du fichier CSV, il ne doit pas contenir de doublons
	 * @throws IOException
	 */
	public TweetsBase(List<String[]> csvLines) throws IOException{
		
		tweetHashMap = new HashMap();
	    for (String[] nextLine : csvLines) {
	    	Tweet tweet = new Tweet(nextLine);
	        putTweet(tweet);
	     }
	}
	
	/**
	 * Ajoute un Tweet dans la base de Tweet
	 * @param tweet le tweet à ajouter
	 * @throws TweetAlreadyPresentException si le tweet est déjà présent dans la base
	 */
	public void addTweet(Tweet tweet){
		putTweet(tweet);
	}
	
	public boolean contains(Long id) {
		return tweetHashMap.containsKey(id);
	}
	
	
	private void putTweet(Tweet tweet){
		tweetHashMap.put(tweet.getId(), tweet);
	}
	
	@Override
	public Iterator<Tweet> iterator() {
		return tweetHashMap.values().iterator();
	}

	



}
