package annotators;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import static org.apache.uima.fit.util.JCasUtil.select;

import types.Candidate;
import types.Features;

public class FeaturesAnnotator extends JCasAnnotator_ImplBase {

  @Override
  public void process(JCas jCas) throws AnalysisEngineProcessException {

	  for (Candidate c : select(jCas, Candidate.class)) 
	  { 
		Features features = new Features(jCas);
		
		features.setTf(c.getTf()); // 1
	    features.setDf(c.getDf()); // 2
	    features.setIdf(c.getIdf()); // 3
	    features.setTfidf(features.getTf() * features.getIdf());
	    features.setFirst_occurrence(c.getFirst_occ()); // 4
	    features.setLast_occurrence(c.getLast_occ()); // 5
	    features.setSpread(features.getLast_occurrence() - features.getFirst_occurrence()); // 6
	    
	    features.addToIndexes();
	    //System.out.println(c.getName() + " - Tf-idf : " + tfidf);
	  }

  }
  
}
