package test_pje;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import twitter4j.Status;


public class TweetTable extends JTable {
	public TweetTable(TweetsTableModel tableModel) {
        super();
         
        JTable tableau = new JTable(tableModel);
 
        
 
    }
	
	public void updateTweets(List<Status> list){
		this.setModel(new TweetsTableModel(list));
	}
}
