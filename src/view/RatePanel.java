package view;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import model.TweetTableClassifier;

import controller.TweetsTableController;


public class RatePanel extends JPanel implements TableModelListener{
	
	protected TweetTableClassifier table;
	
	public RatePanel(TweetsTableController tweetsTableController){
		super();
		this.table = tweetsTableController.getTableClassifier();
		table.addTableModelListener(this);
		
		this.setLayout(new GridLayout(9, 3));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(new JLabel("Tweets positifs : " + table.getPositivePercent()));
		this.add(new JLabel("Tweets negatifs : " + table.getNegativePercent()));
		this.add(new JLabel("Tweets neutres : " + table.getNeutralPercent()));
		
	}
	
	public void tableChanged(TableModelEvent e){
		
		
		
		this.removeAll();
		

	}
}
