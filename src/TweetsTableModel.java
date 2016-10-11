import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.opencsv.CSVWriter;

import twitter4j.Status;

// Permet de représenter la table des tweets
public class TweetsTableModel extends AbstractTableModel {

	protected ArrayList<Status> tweetList;
	
	private final String[] columnName = {"User", "Text"};
	
	
	
	public TweetsTableModel(){
		super();
		this.tweetList = new ArrayList<Status>();
	} 
	
	public void updateTableModel(List<Status> listeDeTweet){
		this.tweetList = (ArrayList<Status>) listeDeTweet;
		fireTableDataChanged();
	}
	
	public void toCSVFile(String fileName) throws IOException{
		CSVWriter writer = new CSVWriter(new FileWriter(fileName), '\t');
		for(Status tweet : tweetList){	
			long id = tweet.getId();
			String userName = tweet.getUser().getName();
			String tweetText = tweet.getText();
			java.util.Date tweetDate = tweet.getCreatedAt();
			
			String line[] = {Long.toString(id), userName, tweetText, tweetDate.toString()};
			
			writer.writeNext(line);
		}
		writer.close();
	}
	
	public int getColumnCount() {
		return 2;
	}
	
	public int getRowCount() {
		return tweetList.size();
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Status tweet = getNTweet(rowIndex);
		if(columnIndex == 0)
			return tweet.getUser().getScreenName();
		if(columnIndex == 1)
			return tweet.getText();
		throw(new IllegalArgumentException());
	}
	
	public String getColumnName(int columnIndex){
		return this.columnName[columnIndex];
	}
	
	private Status getNTweet(int n){
		return tweetList.get(n);
	}
	
}
