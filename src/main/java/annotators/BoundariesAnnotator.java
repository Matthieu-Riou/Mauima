package annotators;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import static org.apache.uima.fit.util.JCasUtil.select;

import types.Boundaries;

public class BoundariesAnnotator extends JCasAnnotator_ImplBase {
	private String boundary_regex_ = "[0-9]+ | [^a-zA-Z0-9]+(\\.|@|_|&|/|\\)+[^a-zA-Z0-9]+";

	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		boolean once = false;
		int st;
		for (Token t : select(jCas, Token.class)) {
			if (!once) {
				st = t.getBegin();
				once = true;
			}
			if (t.getCoveredText().matches(boundary_regex_)) {
				Boundary bound
				once = false;
			}
		}
	}

}
