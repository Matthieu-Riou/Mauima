package pipelines;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import static org.apache.uima.fit.pipeline.SimplePipeline.runPipeline;

import java.io.FileOutputStream;

import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.resource.metadata.TypeSystemDescription;

import annotators.BoundariesAnnotator;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;

public class ModelCreator {
	
	public static void main(String[] args) throws Exception {
	    runPipeline(
	        createReaderDescription(TextReader.class,
	            TextReader.PARAM_SOURCE_LOCATION, "src/main/resources/train/*.txt",
	            TextReader.PARAM_LANGUAGE, "fr"),
	            createEngineDescription(BoundariesAnnotator.class)
	        );
	  }

}
