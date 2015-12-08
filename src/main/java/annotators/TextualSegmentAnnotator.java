package annotators;

import java.util.ArrayList;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import types.TextualSegment;

public class TextualSegmentAnnotator extends JCasAnnotator_ImplBase {
			
	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {
		ArrayList<Character> digits = new ArrayList<Character>();
		for (Character i = '0'; i != '9'+1; ++i)
			digits.add(i);
		
		ArrayList<Character> caps = new ArrayList<Character>();
		for (Character i = 'A'; i != 'Z' + 1; ++i)
			caps.add(i);
		
		String text = jCas.getDocumentText() + " ";
		int pred = 0;
		boolean skip = false;
		boolean digit_read = false;
		for(int i = 0; i < text.length(); ++i) {
			if (skip) {
				skip = false;
				continue;
			}
			if (text.charAt(i) == '.' && (text.charAt(i+1) == ' ' || text.charAt(i+1) == '\n')) {
				TextualSegment seg = new TextualSegment(jCas, pred, i);
				seg.addToIndexes();
				pred = i+2;
				skip = true;
			}
			else if (text.charAt(i) == '\n' && caps.contains(text.charAt(i+1)) || text.charAt(i) == ':') {
			  if (text.charAt(i-1) == ' ') {
          TextualSegment seg = new TextualSegment(jCas, pred, i-1);
          seg.addToIndexes();
          pred = i+1;
        }
        else {
            TextualSegment seg = new TextualSegment(jCas, pred, i);
            seg.addToIndexes();
            pred = i+1;
        }
			}
			else if (!digit_read && digits.contains(text.charAt(i))) {
				TextualSegment seg = new TextualSegment(jCas, pred, i);
				seg.addToIndexes();
				pred = i+1;
				skip = true;
				digit_read = true;
			}
			else if (digit_read && text.charAt(i) == ' ') {
				digit_read = false;
				pred = i+1;
			}
		}
	}

}
