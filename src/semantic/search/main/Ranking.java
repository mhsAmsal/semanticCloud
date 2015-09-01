package semantic.search.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

import semantic.search.similarity.CONTROLLER.*;
import semantic.search.utilities.Constants;

public class Ranking {

	public Ranking() {

	}

	public HashMap<String , Float> rankSearchBasedOnSingleTermQuery(String term,
			HashSet<String> files) throws Exception {
		String path = Constants.location;
		BufferedReader br = null;
		String currentLine = null;
		HashMap<String , Float> map = new HashMap<String, Float>(); 
		try {
			for (String fileName : files) {
				int i = 0;
				fileName = fileName.replaceAll(".txt", ".key");
				br = new BufferedReader(new FileReader(path + File.separator + fileName));
				while ((currentLine = br.readLine()) != null) {
					StringTokenizer st = new StringTokenizer(currentLine);
					while(st.hasMoreTokens()) {
						if(st.nextToken().toLowerCase().contains(term)) {
							i++;
						}
					}
				}
				fileName = fileName.replaceAll(".key", ".txt");
				if(i != 0) {
					map.put(fileName, (float) i);
				}
				br.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return map;
	}
	
	
	public HashMap<String , Float> rankSearchBasedOnMultiTermQuery(String term,
			HashSet<String> files, IndexFile index) throws Exception {
		String path = Constants.location;
		BufferedReader br = null;
		String currentLine = null;
		Calculate c = new Calculate();
		HashMap<String , Float> map = new HashMap<String, Float>(); 
		try {
			for (String fileName : files) {
				fileName = fileName.replaceAll(".txt", ".key");
				br = new BufferedReader(new FileReader(path + File.separator + fileName));
				float maxSimilarity = 0, similarity = 0;
				while ((currentLine = br.readLine()) != null) {
					 if(index.matches(currentLine.toLowerCase(), term)) {
						 similarity = c.calculateRBFSimilarityFast(currentLine, term);
						 /*System.out.println("File - " + fileName + " Similarity b/w " + currentLine + " / "
								 + term + " : " + similarity);*/
					 }
					 if(similarity > maxSimilarity) {
						 maxSimilarity = similarity;
					 }
				}
				map.put(fileName, maxSimilarity);
				fileName = fileName.replaceAll(".key", ".txt");
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return map;
	}	

}
