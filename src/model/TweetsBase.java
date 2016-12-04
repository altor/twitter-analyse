package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import twitter.Tweet;
import twitter4j.Status;

import com.opencsv.CSVReader;



public class TweetsBase extends AbstractTableModel implements Iterable<Tweet>{

	private final String[] columnName = { "User", "Text", "Annotation" };
	protected List <Tweet> tweetList;
	protected TreeSet<Long> tweetIds;
	
	protected String csvFileName;
	
	public TweetsBase() throws IOException{	
		tweetList = new ArrayList<>();
		tweetIds = new TreeSet<>();
	}
	
	/**
	 * Charge la base de Tweet à partir du fichier CSV
	 * @param csvFileName nom du fichier CSV, il ne doit pas contenir de doublons
	 * @throws IOException
	 */
	public TweetsBase(List<String[]> csvLines) throws IOException{
		tweetIds = new TreeSet<>();
		tweetList = new ArrayList();
		
	    for (String[] nextLine : csvLines) {
	    	Tweet tweet = new Tweet(nextLine);
	        addTweet(tweet);
	     }
	}
	
	public TweetsBase(List<Tweet> tweetList, boolean b) {
		tweetIds = new TreeSet<>();
		this.tweetList = new ArrayList();
		
	    for (Tweet tweet : tweetList) {
	        addTweet(tweet);
	     }
	}
	
	/**
	 * Ajoute un Tweet dans la base de Tweet
	 * @param tweet le tweet à ajouter
	 */
	public void addTweet(Tweet tweet){
		tweetIds.add(tweet.getId());
		tweetList.add(tweet);
	}
	
	public boolean contains(Long id) {
		return tweetIds.contains(id);
	}
	
	public int getNbPositiveTweet(){
		int i = 0;
		for(Tweet t : tweetList){
			if(t.getAnnotation() == 4)
				i++;
		}
		
		return i;
		
	}
	
	public int getNbNegativeTweet(){
		int i = 0;
		for(Tweet t : tweetList){
			if(t.getAnnotation() == 0)
				i++;
		}
		
		return i;
		
	}
	
	public int getNbNeutreTweet(){
		int i = 0;
		for(Tweet t : tweetList){
			if(t.getAnnotation() == 2)
				i++;
		}
		
		return i;
		
	}
	
	@Override
	public Iterator<Tweet> iterator() {
		return tweetList.iterator();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return this.columnName[columnIndex];
	}

	@Override
	public int getRowCount() {
		return tweetList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Tweet tweet = tweetList.get(rowIndex);
		switch (columnIndex) {

		case 0:
			return tweet.getUserName();
		case 1:
			return tweet.getText();
		case 2:
			return tweet.getAnnotation();
		default:
			throw (new IllegalArgumentException());
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public void updateBaseModel() {
		fireTableDataChanged();		
	}
	
	public Tweet getTweet(int i) {
		return this.tweetList.get(i);
	}
}
