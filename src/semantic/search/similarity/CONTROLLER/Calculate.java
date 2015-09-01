/**
 * This class is made to calculate the how closely, according to Semantic categories, one sentence matches another.
 * The idea is to be able to insert any sentences of the same language, and of any language, and to obtain the numeric result.
 * The result ranges from 0 to 1, where 0 means no similarity and 1 means that the sentences are probably identical.
 * This program is a model of a tool previously created by me: https://sourceforge.net/projects/semantics/
 * The problem with this tool is that it is language dependent, has a large database which can become a problem to maintain or needs an SQL component (and exporting), and it takes more time to compute.
 * This model does not have any database, it works for all languages, and it is easy to import into any project.
 * <p> 
 * Nevertheless, no model will ever be perfect, so please keep that in mind. 
 * <p>
 * 
 * Therefore, use this tool responsively, keeping in mind that it is in a highly experimental stage!
 * Since it is licensed by the Creative Commons and share-alike, should you create anything interesting, please let me know.
 * Any feedback would be more than welcomed and an addition to an improvement.
 * <p>
 * @author Damir Olejar, you can reach me any-time by sending an e-mail to: olejar.damir@gmail.com
 * @version 2.0 Alpha release.
 */

package semantic.search.similarity.CONTROLLER;

import weka.classifiers.functions.MultilayerPerceptronCS;
import weka.classifiers.functions.RBFRegressor;

public class Calculate {
	
private Metrics m = new Metrics();
private RBFRegressor rss = null;
private MultilayerPerceptronCS mlp = null;
	
	/**
	 * This method takes two strings a and b, calculates the relevant distances and predicts the semantic similarity between them.
	 * NOTE: This is too slow, and speeding it up is a current work in progress. Nevertheless, it gives the satisfying results!
	 * <p>
	 * @param a This is the first String (or sentence) to be compared with the String b
	 * @param b This is the second String (or sentence) to be compared with the String a
	 * @return float representation of the semantic similarity between the strings.
	 */
	public float calculateNNSimilarity(String a, String b){
		
		float block = m.getBlockDistance(a, b); 
		float ChapmanLengthDeviation = m.getChapmanLengthDeviation(a, b);
		float CMSoundex = m.getChapmanMatchingSoundex(a, b);  
		float ChapmanMeanLength = m.getChapmanMeanLength(a, b);
		float ChapmanOrderedNameCompoundSimilarity = m.getChapmanOrderedNameCompoundSimilarity(a, b);
		float cosine = m.getCosineSimilarity(a, b); 
		float dice  = m.getDiceSimilarity(a, b); 
		float EuclideanDistance = m.getEuclideanDistance(a, b);
		float jaccard = m.getJaccardSimilarity(a, b); 
		float Jaro = m.getJaro(a, b);
		float JaroWinkler = m.getJaroWinkler(a, b);
		float Levenshtein = m.getLevenshtein(a,b);
		float MatchingCoefficient = m.getMatchingCoefficient(a, b);
		float MongeElkan = m.getMongeElkan(a, b);
		float NeedlemanWunch = m.getNeedlemanWunch(a, b);
		float OverlapCoefficient = m.getOverlapCoefficient(a, b);
		float QGrams = m.getQGramsDistance(a, b); 
		float SmithWaterman = m.getSmithWaterman(a, b);
		float SmithWatermanGotoh = m.getSmithWatermanGotoh(a, b);
		float SmithWatermanGotohWindowedAffine = m.getSmithWatermanGotohWindowedAffine(a, b);
		float TagLinkToken = m.getTagLinkToken(a, b);
		float Soundex = m.getSoundex(a, b);
		float vectorSpace = m.getVectorSpaceAnalysis(a, b); 
		
		String predict = block+","+ChapmanLengthDeviation+","+CMSoundex+","+ChapmanMeanLength+","+ChapmanOrderedNameCompoundSimilarity+","+cosine+","+dice+","+
		EuclideanDistance+","+jaccard+","+Jaro+","+JaroWinkler+","+Levenshtein+","+MatchingCoefficient+","+MongeElkan+","+NeedlemanWunch+","+OverlapCoefficient+","+QGrams+","+
		SmithWaterman+","+SmithWatermanGotoh+","+SmithWatermanGotohWindowedAffine+","+TagLinkToken+","+Soundex+","+vectorSpace;
		
		float category1 = getNNSemantics(predict); //System.out.println(category1);
		 
		return category1;
	}
	
	
	/**
	 * This method takes two strings a and b, calculates the relevant distances and predicts the semantic similarity between them.
	 * <p>
	 * @param a This is the first String (or sentence) to be compared with the String b
	 * @param b This is the second String (or sentence) to be compared with the String a
	 * @return float representation of the semantic similarity between the strings.
	 */
	public float calculateRBFSimilarity(String a, String b){
		
		float block = m.getBlockDistance(a, b); 
		float CMSoundex = m.getChapmanMatchingSoundex(a, b);  
		float cosine = m.getCosineSimilarity(a, b); 
		float dice  = m.getDiceSimilarity(a, b); 
		float jaccard = m.getJaccardSimilarity(a, b); 
		float QGrams = m.getQGramsDistance(a, b); 
		float vectorSpace = m.getVectorSpaceAnalysis(a, b); 
		
		String predict = block+","+CMSoundex+","+cosine+","+dice+","+jaccard+","+QGrams+","+vectorSpace;
		float category1 = getRBFSemantics(predict); //System.out.println(category1);
		 
		return category1;
	}
	
	/**
	 * This method calculates the fast approximation of the Semantic similarity. Although more robust, it saves the time while having to do a heavy-load of computations.
	 * <p><p>
	 * The statistical evaluation (4100 data rows), in comparison to a model of the Semantic similarity is the following:
	 * "R^2 Goodness of Fit"    0.97380756
	 * "Correlation Coefficient"    0.98788911
	 * "Maximum Error"    0.18123742
	 * "Mean Squared Error"    0.00045578554
	 * "Mean Absolute Error"    0.01539882
     * <p>
	 */
	
	public float calculateRBFSimilarityFast(String aa, String bb){
		float a = m.getBlockDistance(aa, bb);  
		float b = m.getChapmanMatchingSoundex(aa, bb);  
		float f = m.getQGramsDistance(aa, bb);  
		float g = m.getVectorSpaceAnalysis(aa, bb);  
		float h = (float) (Math.pow(b,2)*Math.atan2(b, Math.atan2(Math.cosh(a), f + g)));
		if (h >1){return 1;}
		if (h <0){return 0;}
		return h;
	}
	
	
	/**
	 * This method was made mainly for the testing purposes. It accepts the comma separated String-metric distances (Block Distance, Chapman Matching Soundex, Cosine Similarity,
	 * Dice Coefficient, Jaccard Similarity, QGramas Distance, Vector-Space Analysis), and returns a semantic prediction as a float.
	 *  
	 * @param dataRow The row of calculated distances as a String
	 * @return float representation of the semantic similarity between the Strings. 
	 */
	public float calculateForDataRow(String dataRow){
		return  getRBFSemantics(dataRow);
	} 
	
	/**
	 * This method was made mainly for the testing purposes. It accepts the comma separated String-metric distances (Block Distance, Chapman Matching Soundex, Cosine Similarity,
	 * Dice Coefficient, Jaccard Similarity, QGramas Distance, Vector-Space Analysis), and returns a semantic prediction as a float.
	 *  
	 * @param dataRow The row of calculated distances as a String
	 * @return float representation of the semantic similarity between the Strings. 
	 */
	public float calculateForDataRowFast(String dataRow){
		String[]s=dataRow.split(",");
		double a = Double.parseDouble(s[0]);
		double b = Double.parseDouble(s[1]);
		double f = Double.parseDouble(s[5]);
		double g = Double.parseDouble(s[6]);
		float h = (float) (Math.pow(b,2)*Math.atan2(b, Math.atan2(Math.cosh(a), f + g)));
		return  h;
	} 
	
	
	
	/**
	 * This method loads the semantic Neural Net model and predicts the semantic similarity based on an input String of calculated String-metric distances.
	 * 	
	 * @param in String representing the calculated String-metric distances, comma separated.
	 * @return float representation of the semantic similarity between the Strings. 
	 */
	private float getNNSemantics(String in){
	/*	in = in+",0";
		try{
		if((this.mlp==null)){this.mlp = (MultilayerPerceptronCS) weka.core.SerializationHelper.read("src/MODEL/NN0_9978.model");}
		Instance first = instanceMaker(in);
		float classified = (float)this.mlp.classifyInstance(first); 
		if (classified >1){return 1;}
		if (classified <0){return 0;}
		return classified;
		
		}catch(Exception e){
			e.printStackTrace();
		} */
		return 0;
	}
	
	
	
	/**
	 * This method loads the semantic model and predicts the semantic similarity based on an input String of calculated String-metric distances.
	 * 	
	 * @param in String representing the calculated String-metric distances, comma separated.
	 * @return float representation of the semantic similarity between the Strings. 
	 */
	private float getRBFSemantics(String in){
	/*	in = in+",0";
		try{
			if ((rss == null)){ this.rss = (RBFRegressor) weka.core.SerializationHelper.read("src/MODEL/Semantic_RBFR.model");}
		Instance first = instanceMaker(in);

		float classified = (float)rss.classifyInstance(first); 
		if (classified >1){return 1;}
		if (classified <0){return 0;}
		return classified;
		
		
		
		}catch(Exception e){
			e.printStackTrace();
		} */
		return 0;
	}
	
	/**
	 * 
	 * @param in String representing the calculated String-metric distances, comma separated.
	 * @return Instance The inputted series of numbers (comma separated) as Instance.
	 */
	
	/* private Instance instanceMaker(String in){
		
		String[]s=in.split(",");
		double[] r = new double[s.length];
		for (int t=0;t<r.length;t++){r[t]=Double.parseDouble(s[t]);}
		
		int sz = r.length-1;

		ArrayList<Attribute> atts = new ArrayList<Attribute>(sz);

		for (int t = 0; t < sz + 1; t++) {
			atts.add(new Attribute("number" + t, t));
		}

		Instances dataRaw = new Instances("TestInstances", atts, sz);
		dataRaw.add(new DenseInstance(1.0, r));
		Instance first = dataRaw.firstInstance(); //
		int cIdx = dataRaw.numAttributes() - 1;
		dataRaw.setClassIndex(cIdx);
		
		return first;
		
	} */
	

 

 
}
