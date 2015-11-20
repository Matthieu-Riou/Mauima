package pipelines;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import static org.apache.uima.fit.pipeline.SimplePipeline.runPipeline;
import annotators.TextualSegmentAnnotator;
import annotators.Tokenizer;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;

public class CandidateCreator {
	
	public static void main(String[] args) throws Exception {
	    runPipeline(
	        createReaderDescription(TextReader.class,
	            TextReader.PARAM_SOURCE_LOCATION, "src/main/resources/train/*.txt",
	            TextReader.PARAM_LANGUAGE, "fr"),
	            createEngineDescription(TextualSegmentAnnotator.class),
	            createEngineDescription(Tokenizer.class)
	        );
	  }

}
