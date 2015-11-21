package annotators;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import types.NGram;
import types.TextualSegment;

import java.util.ArrayList;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

public class NGramAnnotator extends JCasAnnotator_ImplBase {

    @Override
    public void process(JCas jCas) throws AnalysisEngineProcessException {
        // TODO Auto-generated method stub
        int min_ngram_len = 3;
        int max_ngram_len = 3;
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
}
