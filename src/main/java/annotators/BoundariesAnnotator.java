package annotators;

import static org.apache.uima.fit.util.JCasUtil.select;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import types.TextualSegment;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

import java.util.ArrayList;
import java.util.regex.*;

public class BoundariesAnnotator extends JCasAnnotator_ImplBase {
	private String boundary_regex_ = "([0-9]+)|(\\.|@|_|\\/|\\\\|:)";
			
	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		Pattern p = Pattern.compile(boundary_regex_);
		Matcher m = p.matcher(jCas.getDocumentText());
		
		ArrayList<Integer> l = new ArrayList<Integer>();
		int pred = 0;
		while(m.find()) {
			TextualSegment b = new TextualSegment(jCas, pred, Math.max(m.start(0), m.start(1)));
			b.addToIndexes();
			pred = Math.max(m.start(0), m.start(1))+(Math.max(m.end(0), m.end(1)) - Math.max(m.start(0), m.start(1)))+1;
		}
	}

}
