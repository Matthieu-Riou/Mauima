package pipelines;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.ExternalResourceFactory.createExternalResourceDescription;
import static org.apache.uima.fit.pipeline.SimplePipeline.runPipeline;

import java.io.File;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ExternalResourceDescription;

import resources.AnnotatedCollection_Impl;
import resources.FeaturesList_Impl;
import annotators.FeaturesAnnotator;
import annotators.WekaModelBuilder;

public class ModelCreator {
	
	public static void main(String[] args) throws Exception {
		ExternalResourceDescription candidatesResourceDesc = createExternalResourceDescription(AnnotatedCollection_Impl.class,
				 "file:target/candidates.txt");
		ExternalResourceDescription featuresResourceDesc = createExternalResourceDescription(FeaturesList_Impl.class,
		         new File("features.bin"));
		System.out.println("Loaded");
		
		AnalysisEngineDescription ae_Features = createEngineDescription(FeaturesAnnotator.class,
		        FeaturesAnnotator.CANDIDATE_KEY,
		        candidatesResourceDesc,
		        FeaturesAnnotator.FEATURES_KEY,
		        featuresResourceDesc);
		
		AnalysisEngineDescription ae_Model = createEngineDescription(WekaModelBuilder.class,
		        WekaModelBuilder.FEATURES_KEY,
		        featuresResourceDesc);
		
		AnalysisEngineDescription aed = createEngineDescription(ae_Features, ae_Model);
		
		AnalysisEngine ae = createEngine(aed);
		
		JCas jcas = ae.newJCas();
		
		runPipeline(jcas, ae);
	  }

}