import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.table.TableModel;

import com.opencsv.CSVWriter;

import exceptions.BadLanguageException;
import exceptions.SmileysException;


public class TweetsTableController {

	
	protected TweetsTableModel tweetsTableModel;
	
	
	
	public TweetsTableController(){
		this.tweetsTableModel = new TweetsTableModel();
	}
		
	public void toCSVFile(String fileName) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(fileName, true), '\t');
		for (Tweet tweet : tweetsTableModel) {
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

	public void updateTableModel(List<Tweet> tweetList) {
		tweetsTableModel.updateTableModel(tweetList);
	}



	public TableModel getModel() {
		return tweetsTableModel;
	}
}
