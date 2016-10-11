
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import twitter4j.Status;
import twitter4j.TwitterException;

public class SearchAction extends AbstractAction {
	
	private JTextField requestField;
	private TwitterAPI twitterAPI;
	private TweetsTableModel table;

	public SearchAction(String texte, JTextField requestField, TwitterAPI twitterAPI, TweetsTableModel table) {
		super(texte);
		this.requestField = requestField;
		this.twitterAPI = twitterAPI;
		this.table = table;
	}
	
	// Action réalisé après click sur le bouton "recherché"
	public void actionPerformed(ActionEvent e) {
		try {
			List<Status> listeDeTweet = this.twitterAPI.getTweets(this.requestField.getText());
			table.updateTableModel(listeDeTweet);
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}		
	}

}
