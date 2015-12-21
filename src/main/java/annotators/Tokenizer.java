package annotators;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import types.TextualSegment;

import static org.apache.uima.fit.util.JCasUtil.select;

public class Tokenizer extends JCasAnnotator_ImplBase {
	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		for (TextualSegment ts : select(jCas, TextualSegment.class)) {
            System.out.println("ts.getCoveredText() = " + ts.getCoveredText());
            String text = ts.getCoveredText() + ' ';
            int pred = 0;
            Token tok;
            for (int i = 0; i < text.length(); i++) {
                switch (Character.getType(text.charAt(i))) {
                    case Character.LINE_SEPARATOR:
                    case Character.PARAGRAPH_SEPARATOR:
                    case Character.SPACE_SEPARATOR:
                        if (text.substring(pred, i).length() != 0) {
                            tok = new Token(jCas, pred + ts.getBegin(), i + ts.getBegin());
                            System.out.println("tok.getCoveredText() = " + tok.getCoveredText());
                            tok.addToIndexes();
                        }
                        pred = i + 1;
                        break;
                    case Character.DECIMAL_DIGIT_NUMBER:
                    case Character.LETTER_NUMBER:
                    case Character.OTHER_NUMBER:
                        pred++;
                        break;
                    case Character.LOWERCASE_LETTER:
                    case Character.UPPERCASE_LETTER:
                    case Character.MODIFIER_LETTER:
                    case Character.OTHER_LETTER:
                    case Character.TITLECASE_LETTER:
                        //System.out.println("letter");
                        break;
                    case Character.OTHER_PUNCTUATION:
                        if (text.substring(pred, i).length() != 0) {
                            tok = new Token(jCas, pred + ts.getBegin(), i + ts.getBegin());
                            System.out.println("tok.getCoveredText() = " + tok.getCoveredText());
                            tok.addToIndexes();
                        }
                        pred = i + 1;
                        break;
                    case Character.START_PUNCTUATION:
                    case Character.END_PUNCTUATION:
                        if (text.substring(pred, i).length() != 0) {
                            tok = new Token(jCas, pred + ts.getBegin(), i + ts.getBegin());
                            System.out.println("tok.getCoveredText() = " + tok.getCoveredText());
                            tok.addToIndexes();
                        }
                        pred = i + 1;
                        break;
                    default:
                        if (text.charAt(i) == '\n') {
                            if (text.substring(pred, i).length() != 0) {
                                tok = new Token(jCas, pred + ts.getBegin(), i + ts.getBegin());
                                System.out.println("tok.getCoveredText() = " + tok.getCoveredText());
                                tok.addToIndexes();
                            }
                            pred = i + 1;
                        }
                        //System.out.println("other");
                }
                /*if (skip) {
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
				}*/
			}
		}
	}

}
