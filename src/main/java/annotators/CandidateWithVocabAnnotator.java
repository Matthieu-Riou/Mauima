package annotators;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.descriptor.ExternalResource;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import resources.Candidate;
import resources.CandidateList;
import types.NGram;
import types.TextualSegment;
import utils.RDFParser;

import java.util.ArrayList;
import java.util.TreeMap;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

/**
 * Analysis engine to annotate candidates using a vocabulary, formatted in an rdf file with skos
 */
public class CandidateWithVocabAnnotator extends JCasAnnotator_ImplBase {
    /**
     * The name of the parameter giving the path to the rdf file
     */
    public final static String PARAM_RDF_FILENAME = "param_rdf_filename_";
    /**
     * The key of the resource to store candidates
     */
    public final static String CANDIDATE_KEY = "candidateKey";
    /**
     * The attribute linked to the parameter named PARAM_RDF_FILENAME
     */
    @ConfigurationParameter(name = PARAM_RDF_FILENAME, mandatory = false)
    private String param_rdf_filename_;
    /**
     * The attribute linked to the resource of key CANDIDATE_KEY
     */
    @ExternalResource(key = CANDIDATE_KEY)
    private CandidateList collection;

    /**
     * The list of labels extracted from the rdf file
     */
    private TreeMap<String, ArrayList<String>> labels_;

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
        RDFParser parser_ = new RDFParser();
        parser_.parse(param_rdf_filename_);
        labels_ = parser_.getLabels();
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
                boolean once = false;
                int begin = -1;
                String fullForm = "";
                for (Token tok : selectCovered(Token.class, ng)) {
                    if (tok.getCoveredText().length() > 1) {
                        if (!once) {
                            fullForm += tok.getCoveredText();
                            begin = tok.getBegin();
                            once = true;
                        } else {
                            fullForm += " " + tok.getCoveredText();
                        }
                    } else {
                        if (!once) {
                            fullForm += tok.getCoveredText();
                            begin = tok.getBegin();
                            once = true;
                        } else {
                            fullForm += " " + tok.getCoveredText();
                        }
                    }
                }
                if (labels_.keySet().contains(fullForm)) {
                    for (String k : labels_.keySet()) {
                        if (fullForm.equalsIgnoreCase(k))
                            add(k, cList, begin, k);
                        else {
                            for (String v : labels_.get(k)) {
                                if (v.equalsIgnoreCase(fullForm)) {
                                    add(v, cList, begin, v);
                                }
                            }
                        }
                    }
                }
            }
        }
        collection.getAllCandidates().add(cList);
    }
}
