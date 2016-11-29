package tools.nGrammeExtractor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UniGrammeExtractor extends AbstractNGrammeExtractor {

	@Override
	public Map<String, Integer> extract(String text) {
		Map<String, Integer> map = new HashMap<>();
		
		for(String word : Arrays.asList(text.split("\\s+"))){
			if(map.containsKey(word)){
				Integer i = map.get(word) + 1;
				map.put(word, i);
			}
			else
				map.put(word, new Integer(1));
		}
		
		return map;
	}

}
