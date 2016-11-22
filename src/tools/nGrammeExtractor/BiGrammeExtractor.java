package tools.nGrammeExtractor;

import java.util.HashMap;
import java.util.Map;

public class BiGrammeExtractor extends AbstractNGrammeExtractor{

	@Override
	public Map<String, Integer> extract(String text) {
		Map<String, Integer> map = new HashMap<>();
		
		String[] tweetTextTable;
		tweetTextTable = text.split(" ");
		
		for (int i = 0; i < tweetTextTable.length - 1; i++) {
			String word = tweetTextTable[i] + " " + tweetTextTable[i+1];
			
			if(map.containsKey(word)){
				Integer j = map.get(word) + 1;
				map.put(word, j);
			}
			else
				map.put(word, new Integer(1));
		}				
		
		return map;
	}


}
