package resources;

public class FeaturesMap {
	// Utilitarian class used to store features needed for the construction of the WEKA model

	public int nb_of_features;
	public int tf;
	public int df;
	public double idf;
	public double tfidf;
	public int firstOccurence;
	public int lastOccurence;
	public int spread;
	
	public FeaturesMap(int tf, int df, double idf, double tfidf, int firstOccurence, int lastOccurence, int spread) {
		this.nb_of_features = 7;
		this.tf = tf;
		this.df = df;
		this.idf = idf;
		this.tfidf = tfidf;
		this.firstOccurence = firstOccurence;
		this.lastOccurence = lastOccurence;
		this.spread = spread;
	}

  @Override
  public String toString() {
    return "FeaturesMap [nb_of_features=" + nb_of_features + ", tf=" + tf + ", df=" + df + ", idf="
            + idf + ", tfidf=" + tfidf + ", firstOccurence=" + firstOccurence + ", lastOccurence="
            + lastOccurence + ", spread=" + spread + "]";
  }
}

