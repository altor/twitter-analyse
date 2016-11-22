package tools.nGrammeExtractor;

import java.util.Map;

public class UniAndBiGrammeExtractor extends AbstractNGrammeExtractor{

	@Override
	public Map<String, Integer> extract(String text) {
		
		UniGrammeExtractor uniGrammeExtractor = new UniGrammeExtractor();
		Map<String, Integer> mapUniGramme = uniGrammeExtractor.extract(text);
		
		BiGrammeExtractor biGrammeExtractor = new BiGrammeExtractor();
		Map<String, Integer> mapBiGramme = biGrammeExtractor.extract(text);
		
		mapUniGramme.putAll(mapBiGramme);
		
		return mapUniGramme;
	}

}
