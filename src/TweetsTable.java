import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;

import twitter4j.Status;

public class TweetsTable extends JTable implements TableModelListener {
	
	// Construit une table de tweet
	public TweetsTable(TweetsTableModel table) {
        super();
		this.setModel(table);
		this.getModel().addTableModelListener(this);
     }
	
	

}
