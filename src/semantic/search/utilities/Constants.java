package semantic.search.utilities;

public class Constants {
	
	public static String [] mauiKeyOptions = {
		"-l", "data/tmp/", "-m", "keyphrextr", "-t", "PorterStemmer", "-v", "none"
	};
	
	public static String location = "data/cloudserver";
	
	public static String indexFileName = "Index.txt";
	
	public static String sjsuIndexFileName = "SJSUIndex.txt";
	
	public static String[] getTopicExtrOption(String path) {
		String [] options = mauiKeyOptions;
		options[1] = path;
		return options;
	}
	
	public static String key = "SemanticSearch";
	
	public static String tempLocale = "data/tmp";

}
