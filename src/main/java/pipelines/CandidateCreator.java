package pipelines;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.resource.ExternalResourceDescription;

import resources.AnnotatedCollection_Impl;
import annotators.CandidateAnnotator;
import annotators.NGramAnnotator;
import annotators.TextualSegmentAnnotator;
import annotators.Tokenizer;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import static org.apache.uima.fit.factory.ExternalResourceFactory.createExternalResourceDescription;
import static org.apache.uima.fit.pipeline.SimplePipeline.runPipeline;

public class CandidateCreator {
	
	public static void main(String[] args) throws Exception {
		ExternalResourceDescription candidatesResourceDesc = createExternalResourceDescription(AnnotatedCollection_Impl.class,
				 "candidates.bin");
		
	    runPipeline(
	        createReaderDescription(TextReader.class,
	            TextReader.PARAM_SOURCE_LOCATION, "src/main/resources/train/*.txt",
	            TextReader.PARAM_LANGUAGE, "fr"),
	            createEngineDescription(TextualSegmentAnnotator.class),
	            createEngineDescription(Tokenizer.class),
                createEngineDescription(NGramAnnotator.class,
                        NGramAnnotator.PARAM_MIN_NGRAM_LENGTH, 3,
                        NGramAnnotator.PARAM_MAX_NGRAM_LENGTH, 3),
                createEngineDescription(CandidateAnnotator.class, CandidateAnnotator.CANDIDATE_KEY, candidatesResourceDesc)
	        );
	  }
}
