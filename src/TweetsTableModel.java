import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import twitter4j.Status;

// Permet de représenter la table des tweets
public class TweetsTableModel extends AbstractTableModel {

	protected ArrayList<Status> tweetList;
	
	private final String[] columnName = {"User", "Text"};
	
	public TweetsTableModel(List<Status> tweetList){
		this.tweetList = (ArrayList<Status>) tweetList;
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
