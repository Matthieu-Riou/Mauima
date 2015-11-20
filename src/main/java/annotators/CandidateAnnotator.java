package annotators;

import java.util.ArrayList;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.tartarus.snowball.ext.PorterStemmer;

import types.NGram;
import types.TextualSegment;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

public class CandidateAnnotator extends JCasAnnotator_ImplBase {

	private String stemmize(String word) {
		PorterStemmer stemmer = new PorterStemmer();
		stemmer.setCurrent(word);
		stemmer.stem();
		return stemmer.getCurrent();
	}
	
	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {
		ArrayList<String> allCandidates = new ArrayList<String>();
		for (TextualSegment t : select(jCas, TextualSegment.class)) {
			for (NGram ng : selectCovered(NGram.class, t)) {
				String phrase = "";
				boolean once = false;
				for (Token tok : selectCovered(Token.class, ng)) {
						if (!once) {
							phrase += stemmize(tok.getCoveredText());
							once = true;
						}
						else
							phrase += " " + stemmize(tok.getCoveredText());
				}
				//System.out.println(phrase);
			}
		}
	}

}
