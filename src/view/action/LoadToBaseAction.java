package view.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import controller.TweetsTableController;

public class LoadToBaseAction extends AbstractAction {

	TweetsTableController tweetsTableController;
	
	public LoadToBaseAction(String text, TweetsTableController tweetsTableController) {
		super(text);
		this.tweetsTableController = tweetsTableController;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		tweetsTableController.loadToBase();
	}

}
