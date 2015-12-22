package pipelines;

import annotators.FeaturesAnnotator;
import annotators.WekaModelBuilder;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.resource.ExternalResourceDescription;
import readers.CandidateReader;
import resources.AnnotatedCollection_Impl;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;
import static org.apache.uima.fit.factory.ExternalResourceFactory.createExternalResourceDescription;
import static org.apache.uima.fit.pipeline.SimplePipeline.runPipeline;

public class ModelCreator {
	
	public static void main(String[] args) throws Exception {
		ExternalResourceDescription candidatesResourceDesc = createExternalResourceDescription(AnnotatedCollection_Impl.class,
				 "file:target/candidates.txt");

		System.out.println("Loaded");
		
		CollectionReader ae_Reader = createReader(CandidateReader.class,
				CandidateReader.CANDIDATE_KEY,
		        candidatesResourceDesc,
		        CandidateReader.PARAM_DIRECTORY,
				"src/main/resources/train/keys/"
				);
		
		AnalysisEngineDescription ae_Features = createEngineDescription(FeaturesAnnotator.class);
		
		AnalysisEngineDescription ae_Model = createEngineDescription(WekaModelBuilder.class);
		
		AnalysisEngineDescription aed = createEngineDescription(ae_Features, ae_Model);
		
		AnalysisEngine ae = createEngine(aed);
		
		runPipeline(ae_Reader, ae);
	  }

}