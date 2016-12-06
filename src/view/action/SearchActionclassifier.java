package view.action;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JTextField;

import twitter.Tweet;
import twitter.TwitterAPI;
import controller.TweetsTableController;



public class SearchActionclassifier extends AbstractAction {

	private JTextField requestField;
	private TwitterAPI twitterAPI;
	private TweetsTableController tweetsTableController;

	public SearchActionclassifier(String texte, JTextField requestField, TwitterAPI twitterAPI,
			TweetsTableController tweetsTableController) {
		super(texte);
		this.requestField = requestField;
		this.twitterAPI = twitterAPI;
		this.tweetsTableController = tweetsTableController;
		
	}

	

	public void actionPerformed(ActionEvent e) {
		try {
			List<Tweet> tweetList = this.twitterAPI.getTweets(this.requestField.getText());
			//.updateTableModel(tweetList);
			for(Tweet t : tweetList)
				tweetsTableController.getClassificator().setAnnotation(t);
			tweetsTableController.updateTableClassifier(tweetList);

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
	}
}
