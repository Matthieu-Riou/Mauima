package annotators;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import java.util.Set;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ExternalResource;
import org.apache.uima.jcas.JCas;

import resources.AnnotatedCollection;
import resources.Candidate;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class FeaturesAnnotator extends JCasAnnotator_ImplBase {
	public final static String CANDIDATE_KEY = "candidateKey";
	
	@ExternalResource(key = CANDIDATE_KEY)
	private AnnotatedCollection collection;
	
	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		
		for (Set<Candidate> candidates : collection.getDocuments())
		{
			for (Candidate c : candidates) {
				int tf = c.getTerm_frequency(); //1
				int df = c.getDocument_frequency(); //2
				int idf = c.getInverse_document_frequency(); //3
				int tfidf = tf*idf;
				int firstOccurrence = c.getFirst_occurrence(); //4
				int lastOccurrence = c.getLast_occurrence(); //5
				int spread = lastOccurrence - firstOccurrence; 
				
				System.out.println(c.getName() + " - Tf-idf : " + Integer.toString(tfidf));
			}
		}

	}

}
