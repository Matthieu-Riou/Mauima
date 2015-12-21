package pipelines;

import annotators.*;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import org.apache.uima.resource.ExternalResourceDescription;
import resources.CandidateList_Impl;

import java.io.File;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import static org.apache.uima.fit.factory.ExternalResourceFactory.createExternalResourceDescription;
import static org.apache.uima.fit.pipeline.SimplePipeline.runPipeline;

public class CandidateWithVocabCreator {
  public static void main(String[] args) throws Exception {
    ExternalResourceDescription candidatesResourceDesc = createExternalResourceDescription(
            CandidateList_Impl.class, new File("candidates.bin"));

    runPipeline(
            createReaderDescription(TextReader.class, TextReader.PARAM_SOURCE_LOCATION,
                    "src/main/resources/resources/term_assignment/train/*.txt", TextReader.PARAM_LANGUAGE, "fr"),
            createEngineDescription(TextualSegmentAnnotator.class),
            createEngineDescription(Tokenizer.class),
            createEngineDescription(NGramAnnotator.class, NGramAnnotator.PARAM_MIN_NGRAM_LENGTH, 3,
                    NGramAnnotator.PARAM_MAX_NGRAM_LENGTH, 3),
            createEngineDescription(CandidateWithVocabAnnotator.class, CandidateWithVocabAnnotator.CANDIDATE_KEY,
                    candidatesResourceDesc, CandidateWithVocabAnnotator.PARAM_RDF_FILENAME, "src/main/resources/resources/agrovoc_sample.rdf"),
            createEngineDescription(CandidateConsumer.class, CandidateConsumer.CANDIDATE_KEY,
                    candidatesResourceDesc));
  }
}
