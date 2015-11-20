package pipelines;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import static org.apache.uima.fit.factory.ExternalResourceFactory.createExternalResourceDescription;
import static org.apache.uima.fit.pipeline.SimplePipeline.runPipeline;

import org.apache.uima.resource.ExternalResourceDescription;

import resources.AnnotatedCollection_Impl;
import annotators.TextualSegmentAnnotator;
import annotators.Tokenizer;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;

public class ModelCreator {
	
	public static void main(String[] args) throws Exception {
		ExternalResourceDescription candidatesResourceDesc = createExternalResourceDescription(AnnotatedCollection_Impl.class,
				 "file:resources/test.txt");
		System.out.println("Loaded");
	  }

}
