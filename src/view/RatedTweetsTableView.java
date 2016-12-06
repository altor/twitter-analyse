package view;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import controller.TweetsTableController;

public class RatedTweetsTableView extends JTable implements TableModelListener {

	public RatedTweetsTableView(TweetsTableController tweetsTableController) {
		super();

		// Associe un model à la vue
		this.setModel(tweetsTableController.getTableClassifier());

		// Indique au model la vue à mettre à jour lors d'une modification de la
		// table des tweets
		this.getModel().addTableModelListener(this);
	}

}
