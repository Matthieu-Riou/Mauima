package pipelines;
/*
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import static org.apache.uima.fit.factory.ExternalResourceFactory.createExternalResourceDescription;
import static org.apache.uima.fit.pipeline.SimplePipeline.runPipeline;

import java.io.File;

import org.apache.uima.resource.ExternalResourceDescription;

import resources.CandidateList_Impl;
import annotators.CandidateAnnotator;
import annotators.CandidateConsumer;
import annotators.NGramAnnotator;
import annotators.TextualSegmentAnnotator;
import annotators.Tokenizer;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
*/
public class CandidateClassifier {
/*
  public static void main(String[] args) throws Exception {
    ExternalResourceDescription candidatesResourceDesc = createExternalResourceDescription(
            CandidateList_Impl.class, new File("candidates.bin"));

    runPipeline(
            createReaderDescription(TextReader.class, TextReader.PARAM_SOURCE_LOCATION,
                    "src/main/resources/train/*.txt", TextReader.PARAM_LANGUAGE, "fr"),
            createEngineDescription(TextualSegmentAnnotator.class),
            createEngineDescription(Tokenizer.class),
            createEngineDescription(NGramAnnotator.class, NGramAnnotator.PARAM_MIN_NGRAM_LENGTH, 3,
                    NGramAnnotator.PARAM_MAX_NGRAM_LENGTH, 3),
            createEngineDescription(CandidateAnnotator.class, CandidateAnnotator.CANDIDATE_KEY,
                    candidatesResourceDesc),
            createEngineDescription(CandidateConsumer.class, CandidateConsumer.CANDIDATE_KEY,
                    candidatesResourceDesc));
  }
  */
}