package annotators;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import types.TextualSegment;

public class TextualSegmentAnnotator extends JCasAnnotator_ImplBase {
			
	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {
		String text = jCas.getDocumentText() + " ";
		int pred = 0;
        for (int i = 0; i < text.length(); i++) {
            switch (Character.getType(text.charAt(i))) {
                case Character.LINE_SEPARATOR:
                case Character.PARAGRAPH_SEPARATOR:
                case Character.SPACE_SEPARATOR:
                    break;
                case Character.DECIMAL_DIGIT_NUMBER:
                case Character.LETTER_NUMBER:
                case Character.OTHER_NUMBER:
                    break;
                case Character.LOWERCASE_LETTER:
                case Character.UPPERCASE_LETTER:
                case Character.MODIFIER_LETTER:
                case Character.OTHER_LETTER:
                case Character.TITLECASE_LETTER:
                    break;
                default:
                    if (text.charAt(i) == '\n') {
                        TextualSegment seg = new TextualSegment(jCas, pred, i);
                        seg.addToIndexes();
                        pred = i + 1;
                    }
            }
        }
	}

}
