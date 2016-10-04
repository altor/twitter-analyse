//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.HashMap;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sun.prism.paint.Color;

public class Window extends JFrame {
	
	private TweetsTable table;
	
	// Cree la fenêtre
	public Window(TwitterAPI twitterAPI) {
		super("Recherche Twitter");
		
		this.table = new TweetsTable();
		this.setSize(1000, 300);
		this.setVisible(true);
		
	    this.setLayout(new GridLayout(9, 3));
	    this.getContentPane().add(new JLabel("Rechercher"));
	    
	    JTextField jf = new JTextField();
	    this.getContentPane().add(jf);

	    this.getContentPane().add(new JButton(new SearchAction("Rechercher", jf, twitterAPI, table)));
	    this.getContentPane().add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

	    this.setVisible(true);		
	}
	   

}