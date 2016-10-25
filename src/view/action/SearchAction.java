package view.action;

/**
 * Action réalisée après un clique sur le bouton "rechercher"
 */


import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import twitter.Tweet;
import controller.TweetsTableController;
import twitter.TwitterAPI;
import twitter4j.Status;
import twitter4j.TwitterException;

public class SearchAction extends AbstractAction {

	private JTextField requestField;
	private TwitterAPI twitterAPI;
	private TweetsTableController tweetsTableController;

	public SearchAction(String texte, JTextField requestField, TwitterAPI twitterAPI,
			TweetsTableController tweetsTableController) {
		super(texte);
		this.requestField = requestField;
		this.twitterAPI = twitterAPI;
		this.tweetsTableController = tweetsTableController;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			List<Tweet> tweetList = this.twitterAPI.getTweets(this.requestField.getText());
			tweetsTableController.updateTableModel(tweetList);
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
	}
}
