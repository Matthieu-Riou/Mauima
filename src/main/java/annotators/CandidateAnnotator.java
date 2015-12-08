package annotators;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ExternalResource;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.tartarus.snowball.ext.PorterStemmer;

import resources.Candidate;
import resources.CandidateList;
import types.NGram;
import types.TextualSegment;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class CandidateAnnotator extends JCasAnnotator_ImplBase {
  public final static String CANDIDATE_KEY = "candidateKey";

  @ExternalResource(key = CANDIDATE_KEY)
  private CandidateList collection;

  private String stemmize(String word) {
    PorterStemmer stemmer = new PorterStemmer();
    stemmer.setCurrent(word);
    stemmer.stem();
    return stemmer.getCurrent();
  }

  private void add(String phrase, TreeMap<String, Candidate> cList, int begin, String fullForm) {
    boolean found = false;
    Candidate candidate = new Candidate(phrase);
    if (cList.containsKey(phrase))
      candidate = cList.get(phrase);

    if (found) {
      candidate.addInformation(fullForm, begin);
    } else {
      candidate.addInformation(fullForm, begin);
      cList.put(phrase, candidate);
    }
  }

  @Override
  public void initialize(UimaContext context) throws ResourceInitializationException {
    // TODO Auto-generated method stub
    super.initialize(context);
  }

  @Override
  public void process(JCas jCas) throws AnalysisEngineProcessException {
    TreeMap<String, Candidate> cList = new TreeMap<String, Candidate>();
    for (TextualSegment t : select(jCas, TextualSegment.class)) {
      for (NGram ng : selectCovered(NGram.class, t)) {
        String phrase = "";
        boolean once = false;
        int begin = -1;
        String fullForm = "";
        for (Token tok : selectCovered(Token.class, ng)) {
          if (tok.getCoveredText().length() > 1) {
            if (!once) {
              phrase += stemmize(tok.getCoveredText());
              fullForm += tok.getCoveredText();
              begin = tok.getBegin();
              once = true;
            } else {
              phrase += " " + stemmize(tok.getCoveredText());
              fullForm += " " + tok.getCoveredText();
            }
          } else {
            if (!once) {
              phrase += tok.getCoveredText();
              fullForm += tok.getCoveredText();
              begin = tok.getBegin();
              once = true;
            } else {
              phrase += " " + tok.getCoveredText();
              fullForm += " " + tok.getCoveredText();
            }
          }
        }
        add(phrase, cList, begin, fullForm);
      }
    }
    collection.getAllCandidates().add(cList);
  }

}
