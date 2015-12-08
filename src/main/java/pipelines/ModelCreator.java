package pipelines;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import static org.apache.uima.fit.factory.ExternalResourceFactory.createExternalResourceDescription;
import static org.apache.uima.fit.pipeline.SimplePipeline.runPipeline;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ExternalResourceDescription;

import resources.AnnotatedCollection_Impl;
import annotators.FeaturesAnnotator;

public class ModelCreator {
	
	public static void main(String[] args) throws Exception {
		ExternalResourceDescription candidatesResourceDesc = createExternalResourceDescription(AnnotatedCollection_Impl.class,
				 "resources/test.txt");
		System.out.println("Loaded");
		
		AnalysisEngineDescription aed = createEngineDescription(FeaturesAnnotator.class, FeaturesAnnotator.CANDIDATE_KEY, candidatesResourceDesc);
		
		AnalysisEngine ae = createEngine(aed);
		
		JCas jcas = ae.newJCas();
		
		runPipeline(jcas, ae);
	  }

}