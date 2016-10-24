/**
 * Contient un Tweet (Status) ainsi que son Annotation
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.BadLanguageException;
import exceptions.SmileysException;

import twitter4j.Status;

public class Tweet {

	protected int annotation;
	protected long id;
	protected String userName;
	protected String tweetText;
	protected Date date;
	protected String lang;
	
	public String getText(){
		return tweetText;
	}
	

	public Tweet(Status status) {
		
		this.id = status.getId();
		this.userName = status.getUser().getName();
		this.tweetText = status.getText();
		this.date = status.getCreatedAt();
		this.annotation = -1;
		this.lang = status.getLang();
	}
	
	public Tweet(String []csvLine){
		
		this.id = Long.parseLong(csvLine[0]);
		this.userName = csvLine[1];
		this.tweetText = csvLine[2];
		this.date = new Date(csvLine[3]);
		this.annotation = Integer.parseInt(csvLine[4]);
		this.lang = "fr";
	}

	public void setAnnotation(int annotation) {
		this.annotation = annotation;
	}

	public int getAnnotation() {
		return this.annotation;
	}

	private boolean containsInvalidEmoticone() {
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
		return negativePresent && positivePresent;
	}

	public String[] toCSVLine() throws BadLanguageException, SmileysException {
		// Verification de la validité du tweet
		// Le tweet est en langue française
		if ((lang.equals("fr"))
				&& (lang.equals("FR")))
			throw new BadLanguageException(lang);

		// Le tweet comporte des emoticones positives et negatives en même temps
		if (containsInvalidEmoticone())
			throw new SmileysException();

		String line[] = { Long.toString(id), userName, tweetText,
				date.toString(), Integer.toString(annotation) };
		return line;
	}


	public String getUserName() {
		return userName;
	}
}
