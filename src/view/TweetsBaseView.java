package view;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import controller.TweetsTableController;

public class TweetsBaseView extends JTable implements TableModelListener {

	public TweetsBaseView(TweetsTableController tweetsTableController) {
		this.setModel(tweetsTableController.getBaseModel());
		this.getModel().addTableModelListener(this);
	}
}
