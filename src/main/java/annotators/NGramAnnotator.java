package annotators;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import types.NGram;
import types.TextualSegment;

import java.util.ArrayList;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

/**
 * Class to compute n-grams over textual segments
 */
public class NGramAnnotator extends JCasAnnotator_ImplBase {
  /**
   * The name of the parameter giving the minimum length of n-grams
   */
  public static final String PARAM_MIN_NGRAM_LENGTH = "min_ngram_length_";

  /**
   * The name of the parameter giving the maximum length of n-grams
   */
  public static final String PARAM_MAX_NGRAM_LENGTH = "max_ngram_length_";

  /**
   * The attribute linked to the parameter named PARAM_MIN_NGRAM_LENGTH
   */
  @ConfigurationParameter(name = PARAM_MIN_NGRAM_LENGTH)
  private int min_ngram_length_;

  /**
   * The attribute linked to the parameter named PARAM_MAX_NGRAM_LENGTH
   */
  @ConfigurationParameter(name = PARAM_MAX_NGRAM_LENGTH)
  private int max_ngram_length_;

  /**
   * Method to process treatments over a CAS
   * @param jCas The CAS to use
   * @throws AnalysisEngineProcessException
   */
  @Override
  public void process(JCas jCas) throws AnalysisEngineProcessException {
    int min_ngram_len = min_ngram_length_;
    int max_ngram_len = max_ngram_length_;
    int current_ngram_start_pos = 0; // set relative to textual segment for parallelization
    int current_ngram_end_pos = 0;
    for (int ngram_len = min_ngram_len; ngram_len <= max_ngram_len; ngram_len++) {
      for (TextualSegment ts : select(jCas, TextualSegment.class)) {
        ArrayList<Token> tokens = new ArrayList<Token>(selectCovered(jCas, Token.class, ts));
        for (int n = 0; n < tokens.size() - (ngram_len - 1); n++) {
          current_ngram_start_pos = tokens.get(n).getBegin();
          current_ngram_end_pos = tokens.get(n + ngram_len - 1).getEnd();
          NGram ng = new NGram(jCas, current_ngram_start_pos++, current_ngram_end_pos);
          ng.addToIndexes();
        }
      }
    }
  }

  /**
   * Method to initialize the analysis engine before pipeline
   * @param context the UIMA context
   * @throws ResourceInitializationException
   */
  @Override
  public void initialize(UimaContext context) throws ResourceInitializationException {
    // TODO Auto-generated method stub
    super.initialize(context);
  }
}
