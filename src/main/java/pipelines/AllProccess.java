package pipelines;

/**
 * Author: adrien
 * Date: 23/12/15
 */
public class AllProccess {
    public static void main(String[] args) throws Exception {
        String path_to_txt = "src/main/resources/resources/automatic_tagging/train/*.txt";
        String lang = "en";
        String path_to_candidates_resource = "target/candidates.txt";
        String path_to_key_file = "src/main/resources/resources/automatic_tagging/train/";
        
        String path_to_txt_test = "src/main/resources/resources/automatic_tagging/test/*.txt";
        String path_to_candidates_resource_test = "target/candidates_test.txt";
        String path_to_key_file_test = "src/main/resources/resources/automatic_tagging/test/";
        
        
        String path_to_vocab = "src/main/resources/resources/agrovoc_sample.rdf";
        String path_to_model = "target/m5p.model";

        int min_ngram = 1;
        int max_ngram = 3;
        int top_k = 10;

        float threshold = 0.0f; // must be a float !!!

        boolean use_vocab = false;

        CandidateCreator ccr = new CandidateCreator();
        if (use_vocab)
            ccr.launch(path_to_txt, lang, path_to_candidates_resource, min_ngram, max_ngram, use_vocab, path_to_vocab);
        else
            ccr.launch(path_to_txt, lang, path_to_candidates_resource, min_ngram, max_ngram);


        ModelCreator mc = new ModelCreator();
        mc.launch("file:" + path_to_candidates_resource, path_to_key_file);

        
        CandidateCreator ccr_test = new CandidateCreator();
        if (use_vocab)
            ccr_test.launch(path_to_txt_test, lang, path_to_candidates_resource_test, min_ngram, max_ngram, use_vocab, path_to_vocab);
        else
            ccr_test.launch(path_to_txt_test, lang, path_to_candidates_resource_test, min_ngram, max_ngram);
        
        CandidateClassifier cc = new CandidateClassifier();
        cc.launch("file:" + path_to_candidates_resource_test, path_to_key_file_test, path_to_model, top_k, threshold);

    }
}
