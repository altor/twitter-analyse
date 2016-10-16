
/**
 * Fenêtre de l'interface graphique
*/
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

	public Window(TwitterAPI twitterAPI, TweetsTableModel tweetsTableModel) {

		// Informations générales
		super("Recherche Twitter");
		this.setSize(500, 300);
		this.setLayout(new GridLayout(9, 3));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Zone de Recherche
		JPanel researchContainer = new JPanel();
		researchContainer.setLayout(new BoxLayout(researchContainer, BoxLayout.X_AXIS));
		researchContainer.add(new JLabel("Rechercher"));
		JTextField jf = new JTextField();
		researchContainer.add(jf);
		researchContainer.add(new JButton(new SearchAction("Rechercher", jf, twitterAPI, tweetsTableModel)));

		// Zone d'affichage du tableau
		TweetsTableView tweetstableView = new TweetsTableView(tweetsTableModel);
		JScrollPane tableContainer = new JScrollPane(tweetstableView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// Ajouts des différentes zones
		Container frame = this.getContentPane();
		frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
		frame.add(researchContainer);
		frame.add(tableContainer);

		// Boutton d'export
		this.getContentPane()
				.add(new JButton(new ExportTweetsToCSVAction("Exporter les résultats en CSV", tweetsTableModel)));

		// Affichage
		this.pack();
		this.setVisible(true);
	}

}
