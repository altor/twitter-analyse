package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import controller.TweetsTableController;

import twitter4j.Status;
/**
 * Vue de la table des Tweets
 */

public class TweetsTableView extends JTable implements TableModelListener {

	public TweetsTableView(TweetsTableController tweetsTableController) {
		super();

		// Associe un model à la vue
		this.setModel(tweetsTableController.getModel());

		// Indique au model la vue à mettre à jour lors d'une modification de la
		// table des tweets
		this.getModel().addTableModelListener(this);

		// utilisation d'une comboBox pour éditer la colone 2
		TableColumn c = this.getColumnModel().getColumn(2);
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("-1");
		comboBox.addItem("0");
		comboBox.addItem("1");
		comboBox.addItem("2");
		c.setCellEditor(new DefaultCellEditor(comboBox));
	}

}
