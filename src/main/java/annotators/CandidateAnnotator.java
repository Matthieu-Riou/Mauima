package annotators;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
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

import java.util.TreeMap;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

/**
 * Analysis engine to annotate candidates
 */
public class CandidateAnnotator extends JCasAnnotator_ImplBase {
    /**
     * The key of the resource giving all candidates for each files
     */
    public final static String CANDIDATE_KEY = "candidateKey";

    /**
     * The attribute linked to the resource of key CANDIDATE_KEY
     */
    @ExternalResource(key = CANDIDATE_KEY)
    private CandidateList collection;

    /**
     * Method to stem a word
     *
     * @param word The word to stem
     * @return The stemmed word
     */
    private String stemmize(String word) {
        PorterStemmer stemmer = new PorterStemmer();
        stemmer.setCurrent(word);
        stemmer.stem();
        return stemmer.getCurrent();
    }

    /**
     * Method to add a candidate to the list of candidate
     *
     * @param phrase   The stemmed full form of the candidate given by the n-grams
     * @param cList    The list of candidate in which insert the new candidate
     * @param begin    The starting position of the candidate
     * @param fullForm The non-stemmed full form of the candidate given by the n-grams
     */
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

    /**
     * Method to initialize the analysis engine before pipeline
     *
     * @param context the UIMA context
     * @throws ResourceInitializationException
     */
    @Override
    public void initialize(UimaContext context) throws ResourceInitializationException {
        // TODO Auto-generated method stub
        super.initialize(context);
    }

    /**
     * Method to process treatments over a CAS
     *
     * @param jCas The CAS to use
     * @throws AnalysisEngineProcessException
     */
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
