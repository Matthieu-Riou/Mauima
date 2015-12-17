package annotators;

import static org.apache.uima.fit.util.JCasUtil.select;

import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.descriptor.ExternalResource;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import resources.Candidate;
import resources.CandidateList;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;

public class CandidateConsumer extends JCasAnnotator_ImplBase {
  public final static String CANDIDATE_KEY = "candidateKey";

  @ExternalResource(key = CANDIDATE_KEY)
  private CandidateList collection;

  public static final String PARAM_FILENAME = "filename_";

  @ConfigurationParameter(name = PARAM_FILENAME, mandatory = true, defaultValue = "target/candidates.txt")
  private String filename_;
  
  private ArrayList<String> files_;

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
    collection.save(filename_, files_);
    System.out.println("Candidates successfully saved to " + filename_);
  }

  @Override
  public void process(JCas jcas) throws AnalysisEngineProcessException {
    for (DocumentMetaData dmd : select(jcas, DocumentMetaData.class)) {
      files_.add(dmd.getDocumentTitle());
    }
  }

  @Override
  public void initialize(UimaContext context) throws ResourceInitializationException {
    // TODO Auto-generated method stub
    super.initialize(context);
    files_ = new ArrayList<String>();
  }
}
