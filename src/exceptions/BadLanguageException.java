package exceptions;

public class BadLanguageException extends Exception {

	private String lang;

	public BadLanguageException(String lang) {
		super();
		this.lang = lang;
	}

	public String getLang() {
		return this.lang;
	}
}
