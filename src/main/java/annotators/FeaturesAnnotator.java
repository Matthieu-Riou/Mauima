package annotators;

import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ExternalResource;
import org.apache.uima.jcas.JCas;

import resources.AnnotatedCollection;
import resources.Candidate;

public class FeaturesAnnotator extends JCasAnnotator_ImplBase {
  public final static String CANDIDATE_KEY = "candidateKey";

  @ExternalResource(key = CANDIDATE_KEY)
  private AnnotatedCollection collection;

  @Override
  public void process(JCas jCas) throws AnalysisEngineProcessException {
    // TODO Auto-generated method stub

    for (List<Candidate> candidates : collection.getDocuments()) {
      for (Candidate c : candidates) {
        int tf = c.getTerm_frequency(); // 1
        int df = c.getDocument_frequency(); // 2
        double idf = c.getInverse_document_frequency(); // 3
        double tfidf = tf * idf;
        int firstOccurrence = c.getFirst_occurrence(); // 4
        int lastOccurrence = c.getLast_occurrence(); // 5
        int spread = lastOccurrence - firstOccurrence; // 6

        System.out.println(c.getName() + " - Tf-idf : " + tfidf);
      }
    }

  }

}
