package annotators;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import types.Candidate;
import types.Document;
import types.Features;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

import java.util.*;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;



public class WekaClassifier extends JCasAnnotator_ImplBase {

    public static final String PARAM_MODEL = "MODEL";
    public static final String PARAM_TOP_K = "TOP_K";
    public static final String PARAM_THRESHOLD = "THRESHOLD";

    Classifier classifier;
    Instances classifierData;
    int i = 0;
    @ConfigurationParameter(name = PARAM_MODEL,
            description = "classifier model",
            mandatory = true)
    private String param_model_;

    @ConfigurationParameter(name = PARAM_TOP_K,
            description = "Number of topics to keep",
            mandatory = true)
    private int top_k_;

    private Map<String, Double> bests_;

    @ConfigurationParameter(name = PARAM_THRESHOLD,
            description = "Threshold to choose K best probabilities",
            mandatory = true,
            defaultValue = "0.0")
    private double threshold_;

    @Override
    public void initialize(UimaContext context)
            throws ResourceInitializationException {
        // TODO Auto-generated method stub
        super.initialize(context);

        try {
            classifier = (Classifier) weka.core.SerializationHelper.read(param_model_); // ??
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        // Declaring features
        FastVector attributes = new FastVector();
        int numFeatures = 7; // TODO get this number instead of having it hardcoded
        attributes.addElement(new Attribute("TF")); // 1
        attributes.addElement(new Attribute("DF")); // 2
        attributes.addElement(new Attribute("IDF")); // 3
        attributes.addElement(new Attribute("TFxIDF")); // oups lol
        attributes.addElement(new Attribute("FirstOccurrence")); // 4
        attributes.addElement(new Attribute("LastOccurrence")); // 5
        attributes.addElement(new Attribute("Spread")); // 6
        // declare a class attribute :
        boolean nominalClassValue = true;
        if (nominalClassValue) {
            FastVector vals = new FastVector(2);
            vals.addElement("False");
            vals.addElement("True");
            attributes.addElement(new Attribute("Keyphrase?", vals));
        } else {
            attributes.addElement(new Attribute("Keyphrase?"));
        }

        classifierData = new Instances("ClassifierData", attributes, 0);
        classifierData.setClassIndex(numFeatures);
        bests_ = new TreeMap<String, Double>();
    }

    private <K, V extends Comparable<? super V>> Map<K, V> take_n(Map<K, V> map) {
        ArrayList<K> list = new ArrayList<K>(map.keySet());

        Map<K, V> result = new LinkedHashMap<K, V>();
        int k = 0;
        while (k < top_k_) {
            K n = list.remove(list.size() - 1);
            result.put(n, map.get(n));
            k++;
        }
        return result;
    }

    private <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    @Override
    public void process(JCas jCas) throws AnalysisEngineProcessException {

        Collection<Document> docs = select(jCas, Document.class);
        assert (docs.size() == 1);

        System.out.println("\n\nFile : " + docs.iterator().next().getDocumentName());
        // Input: jcas representing the list of candidates topics for a document, as a collection of
        // features
        // represented by a class Features

        TreeMap<String, double[]> candidates_relevantness_probabilities = new TreeMap<String, double[]>();

        // Feeding instances data to weka model to get class probabilities
        for (Features candidate : select(jCas, Features.class)) {
            double[] values = {candidate.getTf(), candidate.getDf(), candidate.getIdf(),
                    candidate.getTfidf(), candidate.getFirst_occurrence(), candidate.getLast_occurrence(),
                    candidate.getSpread(), candidate.getClass_()};

            Instance current_instance = new Instance(1, values);
            current_instance.setDataset(classifierData);

            Collection<Candidate> covered = selectCovered(jCas, Candidate.class, candidate);
            assert (covered.size() == 1);


            try {
                candidates_relevantness_probabilities.put(covered.iterator().next().getEffective_full_form(), classifier
                        .distributionForInstance(current_instance));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        for (String c : candidates_relevantness_probabilities.keySet()) {
            if (candidates_relevantness_probabilities.get(c)[1] >= threshold_) {
                bests_.put(c, candidates_relevantness_probabilities.get(c)[1]);
            }
        }

        bests_ = take_n(sortByValue(bests_));

        for (String s : bests_.keySet()) {
            System.out.println(s + " : " + bests_.get(s));
        }
    }
}
