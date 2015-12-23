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

/**
 * Pipeline to build a Weka model
 */
public class ModelCreator {

	public static void main(String[] args) throws Exception {
        ModelCreator mc = new ModelCreator();
        mc.launch("file:target/candidates.txt", "src/main/resources/resources/automatic_tagging/train/");
    }

    /**
     * Launch the pipeline to build the Weka model
     *
     * @param path_to_candidates_resource The path to the generated file containing the candidates
     * @param path_to_key_file            The path to the directory containing all .key files corresponding
     *                                    to the .txt files
     * @throws Exception
     */
    public void launch(String path_to_candidates_resource, String path_to_key_file) throws Exception {
        ExternalResourceDescription candidatesResourceDesc = createExternalResourceDescription(AnnotatedCollection_Impl.class,
                path_to_candidates_resource);

		System.out.println("Loaded");

		CollectionReader ae_Reader = createReader(CandidateReader.class,
				CandidateReader.CANDIDATE_KEY,
                candidatesResourceDesc,
                CandidateReader.PARAM_DIRECTORY,
                path_to_key_file
        );

		AnalysisEngineDescription ae_Features = createEngineDescription(FeaturesAnnotator.class);

        AnalysisEngineDescription ae_Model = createEngineDescription(WekaModelBuilder.class);

        AnalysisEngineDescription aed = createEngineDescription(ae_Features, ae_Model);

        AnalysisEngine ae = createEngine(aed);

        runPipeline(ae_Reader, ae);
    }

}