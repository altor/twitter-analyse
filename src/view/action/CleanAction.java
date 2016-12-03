package view.action;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;

import controller.TweetsTableController;

public class CleanAction extends AbstractAction {

	TweetsTableController tweetsTableController;

	public CleanAction(String text, TweetsTableController tweetsTableController) {
		super(text);
		this.tweetsTableController = tweetsTableController;
	}

	public void actionPerformed(ActionEvent e) {
			tweetsTableController.cleanBase();
	}

}
