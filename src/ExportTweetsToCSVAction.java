
/**
 * Action lancée lorsque le bouton "exporter au format CSV est activé Il
 * demande au model d'exporter ces données au format CSV
 */

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;

public class ExportTweetsToCSVAction extends AbstractAction {

	TweetsTableModel tweetsTableModel;

	public ExportTweetsToCSVAction(String text, TweetsTableModel tweetsTableModel) {
		super(text);
		this.tweetsTableModel = tweetsTableModel;
	}

	public void actionPerformed(ActionEvent e) {

		try {
			tweetsTableModel.toCSVFile("Tweets.csv");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
