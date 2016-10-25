/**
 * Contient un Tweet (Status) ainsi que son Annotation
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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


	
	public void suppUrl(){
		
		Pattern p = Pattern.compile("http://[a-z A-Z]*[0-9]");
		Matcher m = p.matcher(tweetText);
		tweetText =(m.replaceAll(""));
			
	}
	
	
	public void suppHttps(){
		
		Pattern p = Pattern.compile("(https?://([-\\w\\.]+)+(/([\\w/_\\.]*(\\?\\S+)?(#\\S+)?)?)?)");
		Matcher m = p.matcher(tweetText);
		tweetText =(m.replaceAll(""));
			
	}
	
			

	public void cleanSpaces(){
		Pattern p = Pattern.compile("  | $|^ ");
		Matcher m = p.matcher(tweetText);
		tweetText = m.replaceAll(" ");
	}


	private boolean tweet(String string, char d, char e, char f, char g) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean tweet(char c, char d, char e, char f, char g) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String getUserName() {
		return userName;
	}

	public Long getId() {
		return id;
	}

	public void filtreArobase() {
		this.tweetText = this.tweetText.replaceAll("@\\p{ASCII}[^\\p{Space}]*", "");
	}
	
	public void filtreHastag() {
		this.tweetText = this.tweetText.replaceAll("#\\p{ASCII}[^\\p{Space}]*", "");
	}

	public boolean isRetweet() {
		return this.isRT;
	}
	
}
