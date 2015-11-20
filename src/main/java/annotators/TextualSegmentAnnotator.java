package annotators;

import static org.apache.uima.fit.util.JCasUtil.select;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import types.TextualSegment;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

import java.util.ArrayList;
import java.util.regex.*;

public class TextualSegmentAnnotator extends JCasAnnotator_ImplBase {
	//private String boundary_regex_ = "([0-9]+)|(\\.|@|_|\\/|\\\\|:)";
			
	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		/*/
		Pattern p = Pattern.compile(boundary_regex_); 
		Matcher m = p.matcher(jCas.getDocumentText());
		
		ArrayList<Integer> l = new ArrayList<Integer>();
		int pred = 0;
		while(m.find()) {
			TextualSegment b = new TextualSegment(jCas, pred, Math.max(m.start(0), m.start(1)));
			b.addToIndexes();
			pred = Math.max(m.start(0), m.start(1))+(Math.max(m.end(0), m.end(1)) - Math.max(m.start(0), m.start(1)))+1;
		}
		//*/
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
			else if (text.charAt(i) == '\n' && caps.contains(text.charAt(i+1))) {
				TextualSegment seg = new TextualSegment(jCas, pred, i);
				seg.addToIndexes();
				pred = i+1;
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
