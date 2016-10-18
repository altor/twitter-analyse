/**
 * Model représentant la table des Tweets
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.opencsv.CSVWriter;

import exceptions.BadLanguageException;
import exceptions.SmileysException;

import twitter4j.Status;

// Permet de représenter la table des tweets
public class TweetsTableModel extends AbstractTableModel {

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

	public void toCSVFile(String fileName) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(fileName, true), '\t');
		for (Tweet tweet : tweetList) {
			try {
				writer.writeNext(tweet.toCSVLine());
			} catch (BadLanguageException e) {
				System.out.println("Mauvaise Langue : |" + e.getLang() + "|\n"
						+ tweet.getStatus().getText());
			} catch (SmileysException e) {
				System.out.println("smiley positifs et négatifs détecté\n"
						+ tweet.getStatus().getText());
			}

		}
		writer.close();
	}

	private Tweet getNTweet(int n) {
		return tweetList.get(n);
	}

	// OVERIDED FUNCTIONS

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
			return tweet.getStatus().getUser().getScreenName();
		case 1:
			return tweet.getStatus().getText();
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
