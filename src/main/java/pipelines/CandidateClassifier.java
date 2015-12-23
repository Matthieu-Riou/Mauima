package pipelines;

import annotators.FeaturesAnnotator;
import annotators.WekaClassifier;
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

public class CandidateClassifier {

    public static void main(String[] args) throws Exception {
        CandidateClassifier cc = new CandidateClassifier();
        cc.launch("file:target/candidates.txt", "src/main/resources/resources/automatic_tagging/train/",
                "target/m5p.model", 10, 0.3f);
    }

    public void launch(String path_to_candidates_resource, String path_to_key_file, String path_to_model, int top_k, float threshold) throws Exception {
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

		AnalysisEngineDescription ae_Model = createEngineDescription(WekaClassifier.class,
				WekaClassifier.PARAM_MODEL,
                path_to_model,
                WekaClassifier.PARAM_TOP_K,
                top_k,
                WekaClassifier.PARAM_THRESHOLD,
                threshold);

        AnalysisEngineDescription aed = createEngineDescription(ae_Features, ae_Model);

		AnalysisEngine ae = createEngine(aed);

        runPipeline(ae_Reader, ae);
    }

}
