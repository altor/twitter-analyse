package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter.Tweet;

public class NaiveClassifitation {

	Tweet tweet;
	
	public NaiveClassifitation(Tweet tweet){
		this.tweet = tweet;
	}
	
	private int getNbOccurences(String[] tableau, String texte) {
		int nb = 0;
		
		for (int i = 0; i < tableau.length; i++) {
			Pattern pattern = Pattern.compile (tableau[i]);
			Matcher matcher = pattern.matcher(texte);
			if (matcher.find())
				nb++;
		}
		return nb;
	}
	
	public void setAnnotation(String fichier, String langue){
		KeyWordClassification k = new KeyWordClassification(fichier, langue);
		String[] tableauDeMots = k.getListeMots();
		// ON MET LE TWEET APRES NETOYAGE???
		int annotation = getNbOccurences(tableauDeMots, this.tweet.getText());
		tweet.setAnnotation(annotation);
	}

}
