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
                    //System.out.println("separator");
                    break;
                case Character.DECIMAL_DIGIT_NUMBER:
                case Character.LETTER_NUMBER:
                case Character.OTHER_NUMBER:
                    //System.out.println("number");
                    break;
                case Character.LOWERCASE_LETTER:
                case Character.UPPERCASE_LETTER:
                case Character.MODIFIER_LETTER:
                case Character.OTHER_LETTER:
                case Character.TITLECASE_LETTER:
                    //System.out.println("letter");
                    break;
                default:
                    if (text.charAt(i) == '\n') {
                        TextualSegment seg = new TextualSegment(jCas, pred, i);
                        seg.addToIndexes();
                        pred = i + 1;
                    }
                    //System.out.println("other");
            }
            /*if (text.charAt(i) == '.' && (text.charAt(i + 1) == ' ' || text.charAt(i + 1) == '\n' || text.charAt(i + 1) == '.')) {
                TextualSegment seg = new TextualSegment(jCas, pred, i);
                seg.addToIndexes();
                pred = i + 2;
                skip = true;
			}
			else if (text.charAt(i) == '\n' && (caps.contains(text.charAt(i+1)) || smalls.contains(text.charAt(i+1))) || text.charAt(i) == ':') {
			  if (text.charAt(i-1) == ' ') {
                  TextualSegment seg = new TextualSegment(jCas, pred, i - 1);
                  seg.addToIndexes();
                  pred = i + 2;
                }
                else {
                    TextualSegment seg = new TextualSegment(jCas, pred, i);
                    seg.addToIndexes();
                    pred = i + 1;
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
			}*/
        }
	}

}
