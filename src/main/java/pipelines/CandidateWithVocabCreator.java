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
        CandidateWithVocabCreator cwvc = new CandidateWithVocabCreator();
        cwvc.launch("src/main/resources/resources/term_assignment/train/*.txt", "en", 1, 3, "src/main/resources/resources/agrovoc_sample.rdf");
    }

    public void launch(String path_to_txt, String lang, int min_ngram, int max_ngram, String path_to_vocab) throws Exception {
        ExternalResourceDescription candidatesResourceDesc = createExternalResourceDescription(
                CandidateList_Impl.class, new File("candidates.bin"));

        runPipeline(
                createReaderDescription(TextReader.class, TextReader.PARAM_SOURCE_LOCATION, path_to_txt
                        , TextReader.PARAM_LANGUAGE, lang),
                createEngineDescription(TextualSegmentAnnotator.class),
                createEngineDescription(Tokenizer.class),
                createEngineDescription(NGramAnnotator.class, NGramAnnotator.PARAM_MIN_NGRAM_LENGTH, min_ngram,
                        NGramAnnotator.PARAM_MAX_NGRAM_LENGTH, max_ngram),
                createEngineDescription(CandidateWithVocabAnnotator.class, CandidateWithVocabAnnotator.CANDIDATE_KEY,
                        candidatesResourceDesc, CandidateWithVocabAnnotator.PARAM_RDF_FILENAME, path_to_vocab),
                createEngineDescription(CandidateConsumer.class, CandidateConsumer.CANDIDATE_KEY,
                        candidatesResourceDesc));
    }
}
