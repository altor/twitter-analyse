package controller.textCleaner;

import java.util.ArrayList;
import java.util.List;

public class TextCleaner {
	
	protected List<CleanMethod> cleanMethodList;
	
	public TextCleaner(){
		cleanMethodList = new ArrayList<>();
	}
	
	public void add(CleanMethod cm){
		cleanMethodList.add(cm);
	}
	
	public String clean(String text){
		for(CleanMethod cm : cleanMethodList)
			text = cm.clean(text);
		return text;
	}
}
