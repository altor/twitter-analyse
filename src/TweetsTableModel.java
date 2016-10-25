
/**
 * Model représentant la table des Tweets
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.AbstractTableModel;

import com.opencsv.CSVWriter;

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
		CSVWriter writer = new CSVWriter(new FileWriter(fileName), '\t');
		for (Tweet tweet : tweetList)
			writer.writeNext(tweet.toCSVLine());
		writer.close();
	}

	private Tweet getNTweet(int n) {
		return tweetList.get(n);
	}
	
	
	
	// méthode pour nettoyer les caractére 
	public void clenner() {
		
		for(Tweet tweet : tweetList){ 
			//Object p;
			if(tweet ==  ('@','#','-','_',' ' )){
				
				 Pattern p1 = Pattern.compile("@[a-z A-Z]*[0-9]|#-_|http://[a-z A-Z]*[0-9]");
			     Matcher m = p1.matcher(" ");
			     System.out.println(m.replaceAll(""));
			     System.out.println(m.find());	    
			     
			}
	    }
	}
			
    
			
		
	

	private boolean tweet(char c, char d, char e, char f) {
		// TODO Auto-generated method stub
		return false;
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
			tweetList.get(rowIndex).setAnnotation(Integer.parseInt((String) annotation));
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
