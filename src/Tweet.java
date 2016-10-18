/**
 * Contient un Tweet (Status) ainsi que son Annotation
 */

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.BadLanguageException;
import exceptions.SmileysException;

import twitter4j.Status;

public class Tweet {

	protected Status status;
	protected int annotation;

	public Tweet(Status status) {
		this.status = status;
		this.annotation = -1;
	}

	public Status getStatus() {
		return this.status;
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
			Matcher m = p.matcher(status.getText());
			if (m.find())
				positivePresent = true;
		}

		for (Pattern p : negativeEmoticonsPatternList) {
			Matcher m = p.matcher(status.getText());
			if (m.find())
				negativePresent = true;
		}

		return negativePresent ^ positivePresent;
	}

	public String[] toCSVLine() throws BadLanguageException, SmileysException {
		// Verification de la validité du tweet
		// Le tweet est en langue française
		if ((!status.getLang().equals("fr"))
				&& (!status.getLang().equals("FR")))
			throw new BadLanguageException(status.getLang());

		// Le tweet comporte des emoticones positives et negatives en même temps
		if (containsInvalidEmoticone())
			throw new SmileysException();

		long id = status.getId();
		String userName = status.getUser().getName();
		String tweetText = status.getText();
		java.util.Date tweetDate = status.getCreatedAt();

		String line[] = { Long.toString(id), userName, tweetText,
				tweetDate.toString(), Integer.toString(annotation) };
		return line;
	}
}
