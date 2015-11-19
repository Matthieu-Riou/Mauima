package annotators;

import static org.apache.uima.fit.util.JCasUtil.select;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import types.Boundary;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class BoundariesAnnotator extends JCasAnnotator_ImplBase {
	private String boundary_regex_ = "[0-9]+ | [^a-zA-Z0-9]+(\\.|@|_|&|/|\\)+[^a-zA-Z0-9]+";

	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		boolean once = false;
		int st = 0;
		
		for (Token t : select(jCas, Token.class)) {
			System.out.println(t.getCoveredText());
			if (!once) {
				st = t.getBegin();
				once = true;
			}
			if (t.getCoveredText().matches(boundary_regex_)) {
				Boundary bound = new Boundary(jCas, st, t.getEnd());
				bound.addToIndexes();
				once = false;
			}
		}
	}

}
