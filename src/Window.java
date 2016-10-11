//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.HashMap;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Window extends JFrame {
	
	private TweetsTable table;
	
	// Cree la fenêtre
	public Window(TwitterAPI twitterAPI) {
		super("Recherche Twitter");
		this.table = new TweetsTable();
		this.setSize(500, 300);
		
	
	    this.setLayout(new GridLayout(9, 3));
	    //this.getContentPane().add(new JLabel("Rechercher"));
	    
	    
	    
	    JTextField jf = new JTextField();
	    this.getContentPane().add(jf);

	    this.getContentPane().add(new JButton(new SearchAction("Rechercher", jf, twitterAPI, table)));
	    

	    
	    this.getContentPane().add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

	    this.getContentPane().add(new JButton("Exporter les résultats en CSV"));

	    JTextField jf = new JTextField();
		
		JPanel researchContainer = new JPanel();
		researchContainer.setLayout(new BoxLayout(researchContainer, BoxLayout.X_AXIS));
		researchContainer.add(new JLabel("Rechercher"));
		researchContainer.add(jf);
		researchContainer.add(new JButton(new SearchAction("Rechercher", jf, twitterAPI, table)));
		
		Container frame = this.getContentPane();
		frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
		
	    frame.add(researchContainer);
   	    frame.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

	    this.pack();
	    this.setVisible(true);		
	}
	   

}
