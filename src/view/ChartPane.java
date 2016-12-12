package view;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PlotState;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import controller.TweetsTableController;
import model.TweetTableClassifier;

public class ChartPane extends ChartPanel implements TableModelListener{

	
	protected JFreeChart chart;
	protected DefaultPieDataset dataSet;
	protected TweetTableClassifier table;

    /**
     * Default constructor.
     *
     * @param title  the frame title.
     */
    public ChartPane(TweetsTableController tweetsTableController) {
        super(new JFreeChart(new PiePlot()));
        
        this.table = tweetsTableController.getTableClassifier();
		table.addTableModelListener(this);
        
        this.dataSet = new DefaultPieDataset();
        this.setChart(        
        ChartFactory.createPieChart(
                "Repartition",  // chart title
                dataSet,             // data
                true,               // include legend
                true,
                false)
        );  
    }

public void tableChanged(TableModelEvent e){
		dataSet.setValue("Negatifs", table.getNegativePercent());
		dataSet.setValue("Neutres", table.getNeutralPercent());
		dataSet.setValue("Positifs", table.getPositivePercent());
	}


}

