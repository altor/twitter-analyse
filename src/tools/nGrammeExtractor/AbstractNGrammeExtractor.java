package tools.nGrammeExtractor;

import java.util.Map;

public abstract class AbstractNGrammeExtractor {

	public abstract Map <String, Integer> extract(String text);

}
