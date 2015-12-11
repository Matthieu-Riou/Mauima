package annotators;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ExternalResource;
import org.apache.uima.jcas.JCas;

import resources.AnnotatedCollection;
import resources.Candidate;
import resources.FeaturesList;
import resources.FeaturesMap;

public class FeaturesAnnotator extends JCasAnnotator_ImplBase {
  public final static String CANDIDATE_KEY = "candidateKey";
  @ExternalResource(key = CANDIDATE_KEY)
  private AnnotatedCollection collection;
  
  public final static String FEATURES_KEY = "featuresKey";
  @ExternalResource(key = FEATURES_KEY)
  private FeaturesList featuresList_;

  @Override
  public void process(JCas jCas) throws AnalysisEngineProcessException {
    for (List<Candidate> candidates : collection.getDocuments()) {
      ArrayList<FeaturesMap> lf = new ArrayList<FeaturesMap>();
      for (Candidate c : candidates) {
        int tf = c.getTerm_frequency(); // 1
        int df = c.getDocument_frequency(); // 2
        double idf = c.getInverse_document_frequency(); // 3
        double tfidf = tf * idf;
        int firstOccurrence = c.getFirst_occurrence(); // 4
        int lastOccurrence = c.getLast_occurrence(); // 5
        int spread = lastOccurrence - firstOccurrence; // 6
        lf.add(new FeaturesMap(tf, df, idf, tfidf, firstOccurrence, lastOccurrence, spread));
        //System.out.println(c.getName() + " - Tf-idf : " + tfidf);
      }
      featuresList_.getFeaturesList().add(lf);
      //System.out.println(lf);
    }

  }

}
