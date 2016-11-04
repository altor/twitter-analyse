package tools.classification;



import java.util.regex.Matcher;
import java.util.regex.Pattern;


import twitter.Tweet;



public class NaiveClassifitation extends AbstractClassification {
	
	String[] goodKeyWords; 
	String[] badKeyWords; 
	
	public NaiveClassifitation(String[] goodKeyWords, String[] badKeyWords) {
		this.goodKeyWords = goodKeyWords;
		this.badKeyWords = badKeyWords;
	}
	
	private static int getNbOccurences(String[] tableau, String texte) {
		int nb = 0;
		
		for (int i = 0; i < tableau.length; i++) {
			Pattern pattern = Pattern.compile (tableau[i]);
			Matcher matcher = pattern.matcher(texte);
			if (matcher.find())
				nb++;
		}
		return nb;
	}
	
	public int getAnnotation(Tweet tweet){
		int nbGoodWords = getNbOccurences(goodKeyWords, tweet.getText());
		int nbBadWords = getNbOccurences(badKeyWords, tweet.getText());
		if (nbGoodWords > nbBadWords) {
			return 4;
		}
		else if (nbGoodWords < nbBadWords) {
			return 0;
		}
		else {
			return 2;
		}
		
		
	}
	
	

}
