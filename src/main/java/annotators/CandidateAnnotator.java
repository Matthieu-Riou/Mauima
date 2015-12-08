package annotators;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ExternalResource;
import org.apache.uima.jcas.JCas;
import org.tartarus.snowball.ext.PorterStemmer;

import resources.AnnotatedCollection;
import resources.Candidate;
import types.NGram;
import types.TextualSegment;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class CandidateAnnotator extends JCasAnnotator_ImplBase {
	public final static String CANDIDATE_KEY = "candidateKey";

	@ExternalResource(key = CANDIDATE_KEY)
	private AnnotatedCollection collection;

	private String stemmize(String word) {
		PorterStemmer stemmer = new PorterStemmer();
		stemmer.setCurrent(word);
		stemmer.stem();
		return stemmer.getCurrent();
	}

	private void add(String phrase, ArrayList<Candidate> cList) {
	  boolean found = false;
	  Candidate candidate = new Candidate("");
	  for (Candidate c : cList) {
	    if (c.getName().equalsIgnoreCase(phrase)) {
	      found = true;
	      candidate = c;
	      break;
	    }
	  }
	      
	  if (found) {
	    candidate.setTerm_frequency(candidate.getTerm_frequency());
	  }
	  else {
	    
	  }
	}

	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {
		List<List<Candidate>> list = collection.getDocuments();
		ArrayList<Candidate> cList = new ArrayList<Candidate>();
		for (TextualSegment t : select(jCas, TextualSegment.class)) {
			for (NGram ng : selectCovered(NGram.class, t)) {
				String phrase = "";
				boolean once = false;
				for (Token tok : selectCovered(Token.class, ng)) {
					if (tok.getCoveredText().length() > 1) {
						if (!once) {
							phrase += stemmize(tok.getCoveredText());
							once = true;
						} else
							phrase += " " + stemmize(tok.getCoveredText());
					} else {
						if (!once) {
							phrase += tok.getCoveredText();
							once = true;
						} else
							phrase += " " + tok.getCoveredText();
					}
				}
				System.out.println(phrase);
				add(phrase, cList);
			}
		}
	}

}
