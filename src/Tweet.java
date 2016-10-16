
/**
 * Contient un Tweet (Status) ainsi que son Annotation
 */

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

	public String[] toCSVLine() {
		long id = status.getId();
		String userName = status.getUser().getName();
		String tweetText = status.getText();
		java.util.Date tweetDate = status.getCreatedAt();

		String line[] = { Long.toString(id), userName, tweetText, tweetDate.toString(), Integer.toString(annotation) };
		return line;
	}
}
