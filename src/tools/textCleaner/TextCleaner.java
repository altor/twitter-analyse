package tools.textCleaner;

import java.util.ArrayList;
import java.util.List;

public class TextCleaner {
	
	protected List<ReplaceStringCleanMethod> cleanMethodList;
	
	public TextCleaner(){
		cleanMethodList = new ArrayList<>();
	}
	
	public void add(ReplaceStringCleanMethod cm){
		cleanMethodList.add(cm);
	}
	
	public String clean(String text){
		for(ReplaceStringCleanMethod cm : cleanMethodList)
			text = cm.clean(text);
		return text;
	}
}
