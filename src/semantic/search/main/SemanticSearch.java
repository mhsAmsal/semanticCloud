package semantic.search.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.crypto.Cipher;

import semantic.search.des.CipherText;
import semantic.search.utilities.Constants;

public class SemanticSearch {

	public static void main(String[] args) {
		try {
			IndexFile index = new IndexFile();
			// SJSUIndexFile sjsu = new SJSUIndexFile();
			// RetrieveFiles semanticSearch = new RetrieveFiles(index, sjsu);
			RetrieveFiles semanticSearch = new RetrieveFiles(index);
			HashSet<String> files;
			CipherText cipher = new CipherText();
			Scanner scan;
			System.out.println("Upload -u  or Search -s");
			scan = new Scanner(System.in);
			String key = scan.nextLine();
			String inputFolder = "";
			System.out.println("Enter the input/output folder");
			inputFolder = scan.nextLine();
			File folder = new File(inputFolder);
			if (!folder.exists()) {
				System.out.println("Invalid input/output folder");
				System.exit(0);
			}
			if (key.equals("-u")) {
				Uploader up = new Uploader(inputFolder, index);
				up.upload();
				/*
				 * long start = System.currentTimeMillis();
				 * sjsu.updateIndexFile(up.getFiles()); long end =
				 * System.currentTimeMillis();
				 * System.out.println("Time taken to update SJSU index file : "
				 * + (end - start) + " ms");
				 */
				index.clear(inputFolder);
			} else if (key.equals("-s")) {
				System.out.println("Enter the search query");
				key = scan.nextLine();
				key = key.toLowerCase();

				/*
				 * files = semanticSearch.searchSJSU(key);
				 * System.out.println("Found " + key +
				 * " In Following Files according to SJSU"); for (String file :
				 * files) { System.out.println(file); }
				 */

				files = semanticSearch.searchKey(key);
				System.out.println("Found " + key + " In Following Files");
				for (String file : files) {
					System.out.println(file);
				}
				if (files != null && files.size() > 0) {
					StringTokenizer st = new StringTokenizer(key);
					Ranking ranking = new Ranking();
					HashMap<String, Float> map;
					boolean isSingleTerm = false;
					if (st.countTokens() == 1) {
						map = ranking.rankSearchBasedOnSingleTermQuery(key,
								files);
						isSingleTerm = true;
					} else {
						map = ranking.rankSearchBasedOnMultiTermQuery(key,
								files, index);
					}

					List<Map.Entry<String, Float>> entries = new ArrayList<Entry<String, Float>>(
							map.entrySet());

					Collections.sort(entries,
							new Comparator<Map.Entry<String, Float>>() {

								@Override
								public int compare(Map.Entry<String, Float> a,
										Map.Entry<String, Float> b) {
									return a.getValue().compareTo(b.getValue());
								}
							});

					for (int i = 0; i < map.size(); i++) {
						System.out.println(entries.get(entries.size() - i - 1)
								.getKey()
								+ (isSingleTerm ? " Term Frequency : "
										: " Semantic Similarity : ")
								+ entries.get(entries.size() - i - 1)
										.getValue() + " rank : " + (i + 1));
						cipher.decrypt(
								Constants.key,
								Constants.location + "/"
										+ entries.get(entries.size() - i - 1).getKey(),
								inputFolder + "/"
										+ entries.get(entries.size() - i - 1).getKey());

					}

				} else {
					System.out.println("No Files Found For " + key);
				}
				scan.close();
				index.clear(null);
			} else {
				System.out.println("Invalid input");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
