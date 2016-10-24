
/**
 * Action lancée lorsque le bouton "exporter au format CSV est activé Il
 * demande au model d'exporter ces données au format CSV
 */

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;

public class ExportTweetsToCSVAction extends AbstractAction {

	TweetsTableController tweetsTableController;

	public ExportTweetsToCSVAction(String text, TweetsTableController tweetsTableController) {
		super(text);
		this.tweetsTableController = tweetsTableController;
	}

	public void actionPerformed(ActionEvent e) {

		try {
			tweetsTableController.toCSVFile("Tweets.csv");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
