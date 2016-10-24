import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableModel;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


public class TweetsTableController {

	
	protected TweetsTableModel tweetsTableModel;
	protected TweetsBase tweetsBase;
	protected String csvFileName;
	
	
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
		
	}
	
	public void loadToBase(){
		for(Tweet tweet : tweetsTableModel){
			if(tweetsBase.contains(tweet.getId()))
				System.out.println(tweet.getId() + " : Tweet déjà présent");
			else
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
					validTweetList.add(tweet);
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
