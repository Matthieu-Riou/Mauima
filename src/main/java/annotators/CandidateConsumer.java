package annotators;

import java.util.TreeMap;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.descriptor.ExternalResource;
import org.apache.uima.jcas.JCas;

import resources.Candidate;
import resources.CandidateList;

public class CandidateConsumer extends JCasAnnotator_ImplBase {
  public final static String CANDIDATE_KEY = "candidateKey";

  @ExternalResource(key = CANDIDATE_KEY)
  private CandidateList collection;

  public static final String PARAM_FILENAME = "filename_";

  @ConfigurationParameter(name = PARAM_FILENAME, mandatory = true, defaultValue = "target/candidates.txt")
  private String filename_;

  @Override
  public void collectionProcessComplete() throws AnalysisEngineProcessException {
    // TODO Auto-generated method stub
    super.collectionProcessComplete();
    System.out.println("Collection Process Complete --> saving candidates...");
    // compute DF and IDF
    for (TreeMap<String, Candidate> ac : collection.getAllCandidates()) {
      for (Candidate c : ac.values()) {
        int df = 0;
        for (TreeMap<String, Candidate> ac2 : collection.getAllCandidates()) {
          if (ac2.containsKey(c.getName())) {
            df++;
          }
        }
        c.setDocument_frequency(df);
        c.setInverse_document_frequency(-Math.log((c.getDocument_frequency() + 1.0)
                / (collection.getAllCandidates().size() + 1.0)));
      }
    }
    collection.save(filename_);
    System.out.println("Candidates successfully saved to " + filename_);
  }

  @Override
  public void process(JCas arg0) throws AnalysisEngineProcessException {

  }
}
