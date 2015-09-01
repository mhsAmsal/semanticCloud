package semantic.search.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import semantic.search.utilities.Constants;

public class RetrieveFiles {
	private IndexFile invertedIndex;
	private SJSUIndexFile sjsuIndex;
	private Thesaurus thesaurus;
	private ExtractWikipedia wiki;
	private ExtractKeyPhases wikiKeyPhrases;
	private ArrayList<String> wikiKeys;
	
	public RetrieveFiles(IndexFile index, SJSUIndexFile sjsu){
		invertedIndex = index;
		sjsuIndex = sjsu;
		thesaurus = new Thesaurus();
		//wikiKeyPhrases = new ExtractKeyPhases();
		wiki = new ExtractWikipedia();
	}
	
	public RetrieveFiles(IndexFile index){
		invertedIndex = index;
		thesaurus = new Thesaurus();
		//wikiKeyPhrases = new ExtractKeyPhases();
		wiki = new ExtractWikipedia();
	}
	
	public HashSet<String> searchKey(String key) {
		long start = System.currentTimeMillis();
		long temp = 0;
		HashSet<String> fileList = invertedIndex.searchIndexFile(key);
		HashSet<String> list;
		//temp = System.nanoTime() - lStartTime;
		ArrayList<String> synonyms = thesaurus.getSynonyms(key);
		if(fileList ==null){
			fileList = new HashSet<String>();
		}
		//lStartTime = System.nanoTime();
		for(String synonym: synonyms){
			list = invertedIndex.searchIndexFile(synonym);
			if(list != null && list.size()>0){
				fileList.addAll(list);
			}
		}
		//temp = temp + System.nanoTime() - lStartTime;
		ArrayList<String> wikiKeys = extractKeysFromWiki(key);
		//lStartTime = System.nanoTime();
		if(wikiKeys != null){
			for(String wikiKey: wikiKeys){
				list = invertedIndex.searchIndexFile(wikiKey);
				if(list != null && list.size()>0){
					fileList.addAll(list);
				}
				
			}
		}
		long end = System.currentTimeMillis();
		System.err.println("Time taken to search our index file : " + (end - start) + " ms");
		return fileList;
	}
	
	public HashSet<String> searchSJSU(String key) {
		long start = System.currentTimeMillis();
		long temp = 0;
		HashSet<String> fileList = sjsuIndex.searchIndexFile(key);
		HashSet<String> list;
		//temp = System.nanoTime() - lStartTime;
		ArrayList<String> synonyms = thesaurus.getSynonyms(key);
		if(fileList ==null){
			fileList = new HashSet<String>();
		}
		//lStartTime = System.nanoTime();
		for(String synonym: synonyms){
			list = sjsuIndex.searchIndexFile(synonym);
			if(list != null && list.size()>0){
				fileList.addAll(list);
			}
		}
		//temp = temp + System.nanoTime() - lStartTime;
		wikiKeys = extractKeysFromWiki(key);
		//lStartTime = System.nanoTime();
		if(wikiKeys != null){
			for(String wikiKey: wikiKeys){
				list = sjsuIndex.searchIndexFile(wikiKey);
				if(list != null && list.size()>0){
					fileList.addAll(list);
				}
				
			}
		}
		long end = System.currentTimeMillis();
		System.err.println("Time taken to search SJSU index file : " + (end - start) + " ms");
		return fileList;
	}
	
	public ArrayList<String> extractKeysFromWiki(String key){
		wikiKeyPhrases = new ExtractKeyPhases();
		int status = wiki.getWikiContent(key);
		if(status > 0){
			try {
				File file = new File("data/tmp/wiki.key");
				if(file.exists()){ 
					file.delete();
				}
				wikiKeyPhrases.extractKeyPhases(Constants.mauiKeyOptions);
				file = new File("data/tmp/wiki.key");
				if(file.exists()) {
					ArrayList<String> keys = new ArrayList<String>();
					String currentLine;
					BufferedReader br = new BufferedReader(new FileReader("data/tmp/wiki.key"));
					while((currentLine = br.readLine()) != null){
						keys.add(currentLine);
						System.out.println(currentLine);
						//StringTokenizer tokens = new StringTokenizer(currentLine);
//						while(tokens.hasMoreTokens()){
//							keys.add(tokens.nextToken().trim());
//						}
					}
					br.close();
					file.delete();
					return keys;
				} else {
					return null;
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		return null;
	}
}
