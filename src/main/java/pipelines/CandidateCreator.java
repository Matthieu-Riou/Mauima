package pipelines;

import annotators.*;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.resource.ExternalResourceDescription;
import resources.CandidateList_Impl;

import java.io.File;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import static org.apache.uima.fit.factory.ExternalResourceFactory.createExternalResourceDescription;
import static org.apache.uima.fit.pipeline.SimplePipeline.runPipeline;

/**
 * Pipeline to generate candidates with or without a vocabulary
 */
public class CandidateCreator {

    public static void main(String[] args) throws Exception {
        CandidateCreator cc = new CandidateCreator();
        cc.launch("src/main/resources/resources/term_assignment/train/*.txt", "en", 1, 3, false, "src/main/resources/resources/agrovoc_sample.rdf");
    }

    /**
     * Launch the pipeline to generate candidates with or without a vocabulary
     *
     * @param path_to_txt   The path to the directory containing all .txt files
     * @param lang          The language of all .txt files
     * @param min_ngram     The minimum size of the n-gram
     * @param max_ngram     The maximim size of the n-gram
     * @param use_vocab     Flag to check if vocabulary is needed or not
     * @param path_to_vocab The path to the rdf file containing the vocabulary
     * @throws Exception
     */
    public void launch(String path_to_txt, String lang, int min_ngram, int max_ngram, boolean use_vocab, String path_to_vocab) throws Exception {
        ExternalResourceDescription candidatesResourceDesc = createExternalResourceDescription(
                CandidateList_Impl.class, new File("candidates.bin"));

        AnalysisEngineDescription aed = null;

        if (use_vocab) {
            aed = createEngineDescription(CandidateWithVocabAnnotator.class, CandidateWithVocabAnnotator.CANDIDATE_KEY,
                    candidatesResourceDesc, CandidateWithVocabAnnotator.PARAM_RDF_FILENAME, path_to_vocab);
        } else {
            aed = createEngineDescription(CandidateAnnotator.class, CandidateWithVocabAnnotator.CANDIDATE_KEY,
                    candidatesResourceDesc);
        }

        runPipeline(
                createReaderDescription(TextReader.class, TextReader.PARAM_SOURCE_LOCATION, path_to_txt
                        , TextReader.PARAM_LANGUAGE, lang),
                createEngineDescription(TextualSegmentAnnotator.class),
                createEngineDescription(Tokenizer.class),
                createEngineDescription(NGramAnnotator.class, NGramAnnotator.PARAM_MIN_NGRAM_LENGTH, min_ngram,
                        NGramAnnotator.PARAM_MAX_NGRAM_LENGTH, max_ngram),
                aed,
                createEngineDescription(CandidateConsumer.class, CandidateConsumer.CANDIDATE_KEY,
                        candidatesResourceDesc));
    }
}
