/**
 * Model représentant la table des Tweets
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.opencsv.CSVWriter;

import exceptions.BadLanguageException;
import exceptions.SmileysException;

import twitter4j.Status;
import twitter4j.json.DataObjectFactory;

// Permet de représenter la table des tweets
public class TweetsTableModel extends AbstractTableModel implements Iterable<Tweet>{

	// Liste des Tweets contenus dans la table des tweets
	protected ArrayList<Tweet> tweetList;

	private final String[] columnName = { "User", "Text", "Annotation" };

	public TweetsTableModel() {
		super();
		this.tweetList = new ArrayList<Tweet>();
	}

	/**
	 * Met à jour la table des tweet avec une nouvelle liste de tweets
	 * 
	 * @param listeDeTweet
	 *            la nouvelle liste de tweets
	 */
	public void updateTableModel(List<Tweet> listeDeTweet) {
		this.tweetList = (ArrayList<Tweet>) listeDeTweet;

		// Met à jour la vue
		fireTableDataChanged();
	}



	private Tweet getNTweet(int n) {
		return tweetList.get(n);
	}

	// OVERIDED FUNCTIONS

	public Iterator<Tweet> iterator(){
		return tweetList.iterator();
	}
	
	public int getColumnCount() {
		return 3;
	}

	public int getRowCount() {
		return tweetList.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Tweet tweet = getNTweet(rowIndex);
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

	public void setValueAt(Object annotation, int rowIndex, int columnIndex) {
		if (columnIndex == 2)
			tweetList.get(rowIndex).setAnnotation(
					Integer.parseInt((String) annotation));
		else
			throw (new IllegalArgumentException());
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 2)
			return true;
		return false;
	}

	public String getColumnName(int columnIndex) {
		return this.columnName[columnIndex];
	}
}
