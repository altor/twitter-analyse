import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;


public class ExportTweetsToCSVAction extends AbstractAction{

	TweetsTableModel tableTweets;
	
	public ExportTweetsToCSVAction(String texte, TweetsTableModel tableTweets){
		super(texte);
		this.tableTweets = tableTweets;
	}

	public void actionPerformed(ActionEvent e) {
		// c'est quand on clique sur le bouton, on arrive la DIREECT
		// On veut cree le fichier toCSVFile
		//TweetsTableModel t = new TweetsTableModel();
		//t.toCSVFile("Tweets.csv");
		try {
			tableTweets.toCSVFile("Tweets.csv");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

}
