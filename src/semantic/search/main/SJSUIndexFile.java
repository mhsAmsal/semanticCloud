package semantic.search.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import semantic.search.des.CipherText;
import semantic.search.utilities.Constants;
import semantic.search.utilities.Stopwords;

public class SJSUIndexFile {

	public static File indexFile = null;
	CipherText cipher = null;
	HashMap<String, HashSet<String>> postingList = null;
	String encryptIndexFile = Constants.location + "/"
			+ Constants.sjsuIndexFileName;
	String decyrptIndexFile = Constants.tempLocale + "/"
			+ Constants.sjsuIndexFileName;
	Stopwords stopwords = null;

	public SJSUIndexFile() throws Throwable {
		File file = new File(encryptIndexFile);
		cipher = new CipherText();
		if (!file.exists()) {
			file.createNewFile();
			new File(decyrptIndexFile).createNewFile();
		} else {
			cipher.decrypt(Constants.key, encryptIndexFile, decyrptIndexFile);
		}
		indexFile = new File(decyrptIndexFile);
		stopwords = new Stopwords();
		System.out.println("Succesfully loaded SJSU index file");
		preparePostingList();
	}

	private void preparePostingList() throws Exception {
		postingList = new HashMap<String, HashSet<String>>();
		BufferedReader br = null;
		String currentLine = null;
		try {
			br = new BufferedReader(new FileReader(indexFile.getAbsolutePath()));
			while ((currentLine = br.readLine()) != null) {
				if (currentLine.lastIndexOf(".txt") == -1) {
					System.err
							.println("Unexpected format of data in index file : "
									+ currentLine);
					continue;
				}
				int idx = currentLine.lastIndexOf(".txt");
				String fileName = currentLine.substring(0, idx + 4);
				//System.out.println("fileName : " + fileName);
				String topic = currentLine.substring(idx + 5, currentLine.length());
				//System.out.println("topic : " + topic);
				addToPostingList(topic, fileName);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			br.close();
		}
	}

	private void addToPostingList(String topic, String fileName) {
		HashSet<String> files = postingList.get(topic);
		if (files == null || files.size() == 0) {
			files = new HashSet<String>();
		}
		files.add(fileName);
		postingList.put(topic, files);
	}

	private void writePostingListToIndexFile() throws Exception {
		BufferedWriter bw = null;
		System.out.println("Writing new SJSU index file");
		try {
			FileOutputStream fos = new FileOutputStream(indexFile);

			bw = new BufferedWriter(new OutputStreamWriter(fos));

			for (Iterator<String> it = postingList.keySet().iterator(); it.hasNext();) {
				String topic = it.next();
				HashSet<String> files = postingList.get(topic);
				for(String file : files)
				{
					bw.write(file + " " + topic.toLowerCase());
					bw.newLine();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			bw.close();
		}
	}

	public void updateIndexFile(List<String> files) throws Throwable {
		// The following code assumes index file is already created(at least an
		// empty file)
		BufferedReader br = null;
		// preparePostingList();
		try {
			String fileName = null;
			String currentLine = null;
			for (String file : files) {
				br = new BufferedReader(new FileReader(file));
				fileName = new File(file).getName();
				System.out.println("Updating SJSU index file with contents of " + fileName);
				while ((currentLine = br.readLine()) != null) {
					currentLine = currentLine.replaceAll("(@[A-Za-z0-9]+)|([^0-9A-Za-z \t])|(\\w+://\\S+)", " ");
					currentLine = currentLine.toLowerCase();
					currentLine = removeStopwords(currentLine);
					StringTokenizer st = new StringTokenizer(currentLine);
					while(st.hasMoreTokens()) {
						addToPostingList(st.nextToken(), fileName);
					}
				}
				br.close();
			}
			writePostingListToIndexFile();
			encryptIndexFile();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
	}
	
	public HashSet<String> searchIndexFile(String key) {
		HashSet<String> files = new HashSet<String>();
		for(Iterator<String> it = postingList.keySet().iterator(); it.hasNext();) {
			String topic = it.next();
			if(matches(topic, key)) {
				files.addAll(postingList.get(topic));
			}
		}
		return files;
	}
	
	public boolean matches(String topic, String searchText) {
		topic = removeStopwords(topic);
		StringTokenizer st = new StringTokenizer(searchText);
		while(st.hasMoreTokens()) {
			if(topic.contains(st.nextToken()))
				return true;
		}
		return false;
	}
	
	private String removeStopwords(String text) {
		StringTokenizer st = new StringTokenizer(text);
		if(st.countTokens() < 1) return text;
		String filteredText = "";
		filteredText = st.nextToken();
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			if(stopwords.isStopword(token)) {
				continue;
			} else {
				filteredText = filteredText + " " + token;	
			}
		}			
		return filteredText;
	}
	
	
	private void encryptIndexFile() throws Throwable	{
		System.out.println("Encrypting SJSU index file");
		cipher.encrypt(Constants.key,decyrptIndexFile, encryptIndexFile);
	}

}
