package tools.textCleaner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tools.KeyWordExtractor;

public class SupprimeDeterminant extends CleanMethod{

	protected String tableau[];
	public SupprimeDeterminant(){


		String nomDuFichier = "determinant.txt";
		tableau =	KeyWordExtractor.getListeMots(nomDuFichier, "US");
		
	}

	@Override
	public String clean(String clean){
		for (int i = 0; i < tableau.length; i++) {
			Pattern pattern = Pattern.compile ("(\\p{Blank}|^)"+tableau[i]+"(\\p{Blank}|$)");
			Matcher matcher = pattern.matcher(clean);
			clean = matcher.replaceAll(" ");
		}		
	return clean; 		
	
}
}


