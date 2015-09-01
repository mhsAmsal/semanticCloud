package semantic.search.main;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import semantic.search.des.CipherText;
import semantic.search.utilities.Constants;

public class Uploader {

	String path = null;

	ExtractKeyPhases extractPhases = null;
	CipherText cipher = null;
	IndexFile indexFile = null;

	public Uploader(String location, IndexFile index) throws Throwable {
		path = location;
		extractPhases = new ExtractKeyPhases();
		cipher = new CipherText();
		indexFile = index;
	}

	public boolean upload() throws Throwable {
		boolean success = true;
		long start = System.currentTimeMillis();
		String[] options = Constants.getTopicExtrOption(path);
		try {
			extractPhases.extractKeyPhases(options);
		} catch(Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("Time taken to extract topics from the input files : " + (end - start) + " ms" );
		List<String> files = getFiles();
		List<String> keyFiles = new ArrayList<String>();
		List<String> txtFiles = new ArrayList<String>();
		start = System.currentTimeMillis();
		for (String file : files) {
			if(file.endsWith(".key")) {
				keyFiles.add(file);
			}
			if(file.endsWith(".txt")) {
				txtFiles.add(file);
				encrypt(file);
			}
		}
		end = System.currentTimeMillis();
		System.out.println("Time taken to encrypt files : " + (end - start) + " ms" );
		start = System.currentTimeMillis();
		indexFile.updateIndexFile(keyFiles);
		end = System.currentTimeMillis();
		System.out.println("Time taken to update index file : " + (end - start) + " ms" );
		return success;
	}

	public List<String> getFiles() {
		File dir = new File(path);
		List<String> files = new ArrayList<String>();
		if (dir.isDirectory()) {
			String[] contents = dir.list();
			for (String file : contents) {
				files.add(path + "/" + file);
			}
		} else {
			files.add(path);
		}
		return files;
	}

	public String getFileName(String path) {
		Path p = Paths.get(path);
		return p.getFileName().toString();
	}
	
	
	private void encrypt(String file) {
		try {

			// Construct the .key file
			String fileName = getFileName(file);
			System.out.println("Encrypting file : " + fileName);  
			// Encrypt the input file
			cipher.encrypt(Constants.key, file, Constants.location + "/"
					+ fileName);
			// new File(file).delete();

		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
