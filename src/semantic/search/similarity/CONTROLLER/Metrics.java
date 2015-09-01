package semantic.search.similarity.CONTROLLER;

import net.sf.classifier4J.vector.HashMapTermVectorStorage;
import net.sf.classifier4J.vector.TermVectorStorage;
import net.sf.classifier4J.vector.VectorClassifier;
import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;

public class Metrics {
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getEuclideanDistance(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation();
		float result = metric.getSimilarity(a, b);
		return result;
	} 
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getSoundex(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation();
		float result = metric.getSimilarity(a, b);
		return result;
	} 
	
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getTagLinkToken(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation();
		float result = metric.getSimilarity(a, b);
		return result;
	} 
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getSmithWatermanGotohWindowedAffine(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation();
		float result = metric.getSimilarity(a, b);
		return result;
	} 
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getSmithWatermanGotoh(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation();
		float result = metric.getSimilarity(a, b);
		return result;
	} 
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getSmithWaterman(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation();
		float result = metric.getSimilarity(a, b);
		return result;
	} 
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getOverlapCoefficient(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation();
		float result = metric.getSimilarity(a, b);
		return result;
	} 
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getMatchingCoefficient(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation();
		float result = metric.getSimilarity(a, b);
		return result;
	} 
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getMongeElkan(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation();
		float result = metric.getSimilarity(a, b);
		return result;
	} 
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getNeedlemanWunch(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation();
		float result = metric.getSimilarity(a, b);
		return result;
	} 
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getLevenshtein(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation();
		float result = metric.getSimilarity(a, b);
		return result;
	} 
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getJaroWinkler(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation();
		float result = metric.getSimilarity(a, b);
		return result;
	} 
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getJaro(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation();
		float result = metric.getSimilarity(a, b);
		return result;
	} 
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getChapmanMeanLength(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation();
		float result = metric.getSimilarity(a, b);
		return result;
	} 
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getChapmanOrderedNameCompoundSimilarity(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation();
		float result = metric.getSimilarity(a, b);
		return result;
	} 
	
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getChapmanLengthDeviation(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanLengthDeviation();
		float result = metric.getSimilarity(a, b);
		return result;
	} 
	
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getBlockDistance(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.BlockDistance();
		float result = metric.getSimilarity(a, b);
		return result;
	} 
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getChapmanMatchingSoundex(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.ChapmanMatchingSoundex();
		float result = metric.getSimilarity(a, b);
		return result;
	}
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getCosineSimilarity(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.CosineSimilarity();
		float result = metric.getSimilarity(a, b);
		return result;
	}
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getDiceSimilarity(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.DiceSimilarity();
		float result = metric.getSimilarity(a, b);
		return result;
	}
	
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getJaccardSimilarity(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.JaccardSimilarity();
		float result = metric.getSimilarity(a, b);
		return result;
	}
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getQGramsDistance(String a, String b){
		AbstractStringMetric metric = new uk.ac.shef.wit.simmetrics.similaritymetrics.QGramsDistance();
		float result = metric.getSimilarity(a, b);
		return result;
	}
	
	/**
	 * Calculates the String-metric distance
	 * 
	 * @param a String representing the sentence a.
	 * @param b String representing the sentence b.
	 * @return float representing the String-metric distance.
	 */
	public float getVectorSpaceAnalysis(String a, String b){
		try{
		TermVectorStorage storage = new HashMapTermVectorStorage();
		VectorClassifier vc = new VectorClassifier(storage);
		vc.teachMatch("category", a);
	    float result = (float)vc.classify("category", b);
	    return result;
		}catch(Exception e){}
		return 0;
	}
	
}
