import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

import twitter4j.Status;

import com.opencsv.CSVReader;


public class TweetsBase implements Iterable<Tweet> {

	
	protected HashMap<Long, Status> tweetHashMap;
	protected String csvFileName;
	
	public TweetsBase(String csvFileName) throws IOException{
		CSVReader reader = new CSVReader(new FileReader(csvFileName));
	    String [] nextLine;
	    while ((nextLine = reader.readNext()) != null) {
	        // nextLine[] is an array of values from the line
	        System.out.println(nextLine[0] + nextLine[1] + "etc...");
	     }
		
	}
	
	@Override
	public Iterator<Tweet> iterator() {
		// TODO Auto-generated method stub
		return null;
	}



}
