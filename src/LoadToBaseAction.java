import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

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
