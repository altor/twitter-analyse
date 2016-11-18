package twitter;
/**
 * Contient un Tweet (Status) ainsi que son Annotation
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import tools.textCleaner.TextCleaner;
import twitter4j.Status;

public class Tweet {

	protected int annotation;

	protected Long id;
	protected String userName;
	protected String tweetText;
	protected String date;
	protected String lang;
	
	protected boolean isRT;
	
	public String getText(){
		return tweetText;
	}
	


	public Tweet(Status status) {
		this.isRT = status.isRetweet();
		this.id = new Long(status.getId());
		this.userName = status.getUser().getName();
		this.tweetText = status.getText();
		this.date = status.getCreatedAt().toString();
		this.annotation = -1;
		this.lang = status.getLang();
	}
	
	public Tweet(String []csvLine){
		this.isRT = false;
		this.id = new Long(Long.parseLong(csvLine[0]));
		this.userName = csvLine[1];
		this.tweetText = csvLine[2];
		this.date = csvLine[3];
		this.annotation = Integer.parseInt(csvLine[4]);
		this.lang = "fr";
	}

	public void setAnnotation(int annotation) {
		this.annotation = annotation;
	}

	public int getAnnotation() {
		return this.annotation;
	}

	/**
	 * Transforme le tweet en une table de hashage contenant la liste des mot du tweet associé a son nombre d'occurence dans le texte du tweet
	 * @return la table de hashage
	 */
	public Map <String, Integer> toMap(){
		Map<String, Integer> map = new HashMap<>();
		
		for(String word : Arrays.asList(tweetText.split("\\s+"))){
			if(map.containsKey(word)){
				Integer i = map.get(word) + 1;
				map.put(word, i);
			}
			else
				map.put(word, new Integer(1));
		}
		
		
		return map;
	}
	
	public boolean containsValidEmoticone() {
		ArrayList<Pattern> positiveEmoticonsPatternList = new ArrayList<>();

		positiveEmoticonsPatternList.add(Pattern.compile("=\\)"));
		positiveEmoticonsPatternList.add(Pattern.compile("=]"));
		positiveEmoticonsPatternList.add(Pattern.compile(":\\)"));
		positiveEmoticonsPatternList.add(Pattern.compile("XD"));
		positiveEmoticonsPatternList.add(Pattern.compile(";\\)"));

		ArrayList<Pattern> negativeEmoticonsPatternList = new ArrayList<>();
		negativeEmoticonsPatternList.add(Pattern.compile("=\\("));
		negativeEmoticonsPatternList.add(Pattern.compile(":\\("));
		negativeEmoticonsPatternList.add(Pattern.compile(":'\\("));

		boolean positivePresent = false;
		boolean negativePresent = false;

		for (Pattern p : positiveEmoticonsPatternList) {
			Matcher m = p.matcher(tweetText);
			if (m.find())
				positivePresent = true;
		}

		for (Pattern p : negativeEmoticonsPatternList) {
			Matcher m = p.matcher(tweetText);
			if (m.find())
				negativePresent = true;
		}
		return !(negativePresent && positivePresent);
	}

	public boolean isFrenchTweet(){
		return (lang.equals("fr")) || (lang.equals("FR"));
	}
	
	public String[] toCSVLine() {
		String line[] = { id.toString(), userName, tweetText,
				date.toString(), Integer.toString(annotation) };
		return line;
	}

	public String getUserName() {
		return userName;
	}

	public Long getId() {
		return id;
	}
	
	public boolean isRetweet() {
		return this.isRT;
	}

	public void clean(TextCleaner tweetCleaner) {
		tweetText = tweetCleaner.clean(tweetText);
	}
	
}
