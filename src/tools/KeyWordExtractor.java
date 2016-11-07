package tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class KeyWordExtractor {	
	
	private String fichier;
	private String langue;
	
	
	public static String[] getListeMots(String fichier, String langue) {
		ArrayList<String> lignes = new ArrayList<String>();
		
		try{
			InputStream flux = new FileInputStream(fichier); 
			InputStreamReader lecture = new InputStreamReader(flux);
			BufferedReader buff = new BufferedReader(lecture);
			String ligne;
			while ((ligne=buff.readLine())!=null){
				lignes.add(ligne);
			}
			buff.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}	
		
		String motsSelectionne;
		if (langue == "FR") {
			motsSelectionne = lignes.get(1);
		}
		else if (langue == "US") {
			motsSelectionne = lignes.get(0);
		}
		else {
			motsSelectionne = lignes.get(0);
			motsSelectionne += lignes.get(1);
		}
		
		String[] tabMots;
		
		tabMots = motsSelectionne.split(",");

		// Suppression des espaces avant et après
		for (int i = 0; i < tabMots.length; i++) {
			tabMots[i] = tabMots[i].trim();
		}
		
		return tabMots;
	}

}
