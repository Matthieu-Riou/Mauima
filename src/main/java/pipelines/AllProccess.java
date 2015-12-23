package pipelines;

/**
 * Author: adrien
 * Date: 23/12/15
 */
public class AllProccess {
    public static void main(String[] args) throws Exception {
        String path_to_txt = "src/main/resources/resources/automatic_tagging/train/*.txt";
        String lang = "en";
        String path_to_candidates_resource = "file:target/candidates.txt";
        String path_to_key_file = "src/main/resources/resources/automatic_tagging/train/";
        String path_to_vocab = "src/main/resources/resources/agrovoc_sample.rdf";
        String path_to_model = "target/m5p.model";

        int min_ngram = 1;
        int max_ngram = 3;
        int top_k = 10;

        float threshold = 0.3f; // must be a float !!!

        boolean use_vocab = false;

        CandidateCreator ccr = new CandidateCreator();
        if (use_vocab)
            ccr.launch(path_to_txt, lang, min_ngram, max_ngram, use_vocab, path_to_vocab);
        else
            ccr.launch(path_to_txt, lang, min_ngram, max_ngram);

        ModelCreator mc = new ModelCreator();
        mc.launch(path_to_candidates_resource, path_to_key_file);

        CandidateClassifier cc = new CandidateClassifier();
        cc.launch(path_to_candidates_resource, path_to_key_file, path_to_model, top_k, threshold);

    }
}
