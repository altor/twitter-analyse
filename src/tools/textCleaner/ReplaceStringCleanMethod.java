package tools.textCleaner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceStringCleanMethod extends CleanMethod{

	
	protected Pattern pattern;
	protected String replacementString;
	
	public ReplaceStringCleanMethod(String regexp, String replacementString){
		pattern = Pattern.compile(regexp);
		this.replacementString = replacementString;
		
	}
	
	public String clean(String text){
		Matcher m = pattern.matcher(text);
		return m.replaceAll(replacementString);
	}
}
