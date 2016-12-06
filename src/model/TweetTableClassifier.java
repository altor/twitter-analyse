package model;

public class TweetTableClassifier extends TweetsTableModel {
	public TweetTableClassifier(){
		super();
	}

	public double getPositivePercent(){
		
		return 0;
	}
	
	public double getNegativePercent(){
			
		return 0;
	}
	
	public double getNeutralPercent(){
		
		return 0;
	}
	
	public void classify(){
		
		
		// fin de la fonction pour raffraichir la vue
		fireTableDataChanged();
	}
	
	
}
