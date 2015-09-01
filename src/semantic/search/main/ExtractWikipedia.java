package semantic.search.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;

public class ExtractWikipedia {
	final String endPoint = "http://en.wikipedia.org/wiki/";

	public int getWikiContent(String word) {
		System.out.println("extracting wiki");
		String data = "";
		// url for wikipedia
		StringTokenizer st = new StringTokenizer(word);
		String key = "";
		File file = new File("data/tmp/wiki.txt");
		if(file.exists()) file.delete();
		if (st.countTokens() > 1) {
			key = st.nextToken();
			while (st.hasMoreTokens()) {
				key = key + "_" + st.nextToken();
			}
		} else {
			key = word;
		}
		try {
			// download WIkipedia article and extract keyphrases
			URL url = new URL(endPoint + key);
			data = Jsoup.parse(url, 10000).text();
			System.out.println(url.toExternalForm());
			PrintWriter writer = new PrintWriter("data/tmp/wiki.txt", "UTF-8");
			writer.print(data);
			writer.close();
		} catch (IOException e) {
			System.err.println("Invalid wiki url for search string : " + word + " url : " + endPoint + key);
			// e.printStackTrace();
			return -1;
		}
		return data.trim().length();
	}
}
