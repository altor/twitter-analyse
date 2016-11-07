package view;

/**
 * Fenêtre de l'interface graphique
*/

import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.TweetsTableController;

import twitter.TwitterAPI;
import view.action.ExportTweetsToCSVAction;
import view.action.LoadToBaseAction;
import view.action.SearchAction;


public class Window extends JFrame {

	private TwitterAPI twitterAPI;
	private TweetsTableController tweetsTableController;


	
	public Window(TwitterAPI twitterAPI, TweetsTableController tweetsTableController) {
		
		// Informations générales
		super("Recherche Twitter");		
		
		this.twitterAPI = twitterAPI;
		this.tweetsTableController = tweetsTableController;
		
		JTabbedPane tabs = new JTabbedPane();
		
		
		this.setSize(500, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		tabs.addTab("Request", requestTab());
		tabs.addTab("tweet Base", tweetBaseTab());

		
		this.getContentPane().add(tabs);
		
		this.pack();
		this.setVisible(true);
	}
	



	// METHODES DE CRÉATION DU 1ER ONGLETS
	private JPanel requestTab(){
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(9, 3));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		panel.add(requestBarJPanel());
		panel.add(tablePane());
		panel.add(buttonJPanel());
		
		return panel;
	}

	//Création de la barre de recherche
	private JPanel requestBarJPanel(){
		JPanel researchContainer = new JPanel();
		researchContainer.setLayout(new BoxLayout(researchContainer, BoxLayout.X_AXIS));
		researchContainer.add(new JLabel("Rechercher"));
		JTextField jf = new JTextField();
		researchContainer.add(jf);
		researchContainer.add(new JButton(new SearchAction("Rechercher", jf, twitterAPI, tweetsTableController)));
		
		return researchContainer;
	}
	
	// Création du tableau de visualiation des tweets trouvés
	private JScrollPane tablePane(){
		TweetsTableView tweetstableView = new TweetsTableView(tweetsTableController);
		JScrollPane tableContainer = new JScrollPane(tweetstableView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return tableContainer;
	}
	
	// création des boutons d'action
	private JPanel buttonJPanel(){
		JPanel buttonsContainer = new JPanel();
		buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.X_AXIS));
		buttonsContainer
			.add(new JButton(new LoadToBaseAction("charger dans la base", tweetsTableController)));
		
		return buttonsContainer;
	}
	
	// MÉTHODES DE CRÉATION DU 2ÉME ONGLETS
	
	private JPanel tweetBaseTab() {	
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		panel.add(basePane());
		panel.add(secondTabButtonsJPanel());

		return panel;
	}

	// Création du tableau de visualisation de la base
	private JScrollPane basePane(){
		TweetsBaseView tweetsBaseView = new TweetsBaseView(tweetsTableController);
		JScrollPane tableContainer = new JScrollPane(tweetsBaseView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return tableContainer;
	}
	
	// Création des boutons d'actions
	private JPanel secondTabButtonsJPanel(){
		JPanel buttonsContainer = new JPanel();
		buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.X_AXIS));
		buttonsContainer
			.add(new JButton(new ExportTweetsToCSVAction("Exporter les résultats en CSV", tweetsTableController)));
		return buttonsContainer;
	}
	
}
