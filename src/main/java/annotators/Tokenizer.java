package annotators;

import static org.apache.uima.fit.util.JCasUtil.select;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import types.TextualSegment;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class Tokenizer extends JCasAnnotator_ImplBase {
	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		for (TextualSegment ts : select(jCas, TextualSegment.class)) {
			String text = ts.getCoveredText() + ' ';
			int pred = 0;
			boolean skip = false;
			for (int i = 0; i < text.length(); i++) {
				if (skip) {
					skip = false;
					continue;
				}
				if (text.charAt(i) == ' ' || text.charAt(i) == '\''
						 || text.charAt(i) == '-' || text.charAt(i) == '\n') {
					Token tok = new Token(jCas, pred+ts.getBegin(), i+ts.getBegin());
					tok.addToIndexes();
					if (i+1 < text.length() && (text.charAt(i+1) == '(' || text.charAt(i+1) == ' ')) {
						pred = i+2;
						skip = true;
					}
					else
						pred = i+1;
				}
				else if (text.charAt(i) == ',' || text.charAt(i) == ')'|| text.charAt(i) == ';') {
					Token tok = new Token(jCas, pred+ts.getBegin(), i+ts.getBegin());
					tok.addToIndexes();
					pred = i+2;
					skip = true;
				}
			}
		}
	}

}
