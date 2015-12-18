package annotators;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.TreeMap;

import org.apache.uima.UimaContext;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import types.Candidate;
import types.Features;
import weka.classifiers.Classifier;
import weka.classifiers.meta.Bagging;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

import utils.ProbaList;


public class WekaClassifier extends JCasAnnotator_ImplBase {
  
  public static final String PARAM_MODEL = "MODEL";
  @ConfigurationParameter(name = PARAM_MODEL,
  description = "classifier model",
  mandatory = true)
  private String param_model_;
  
  Classifier classifier;
  Instances classifierData;
  
  int i = 0;

  private ArrayList<Integer> sortCandidatesByProbabality(
          ArrayList<double[]> candidates_relevantness_probabilities) {
    int relevant_class_index = 0;
    ArrayList<Integer> candidates_indexes_list = new ArrayList<Integer>();
    for (double[] relevantness_proba : candidates_relevantness_probabilities) {

    }

    return candidates_indexes_list;
  }
  
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
	    boolean nominalClassValue = false;
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
	}

  @Override
  public void process(JCas jCas) throws AnalysisEngineProcessException {
	  
	  System.out.println("File : ");
    // Input: jcas representing the list of candidates topics for a document, as a collection of
    // features
    // represented by a class Features

    TreeMap<String, double[]> candidates_relevantness_probabilities = new TreeMap<String, double[]>();

    // Feeding instances data to weka model to get class probabilities
    for (Features candidate : select(jCas, Features.class)) {
      double[] values = { candidate.getTf(), candidate.getDf(), candidate.getIdf(),
          candidate.getTfidf(), candidate.getFirst_occurrence(), candidate.getLast_occurrence(),
          candidate.getSpread(), candidate.getClass_()};

      Instance current_instance = new Instance(1, values);
      current_instance.setDataset(classifierData);
      
      Collection<Candidate> covered = selectCovered(jCas, Candidate.class, candidate);
      assert(covered.size() == 1);
      

        try {
			candidates_relevantness_probabilities.put(covered.iterator().next().getEffective_full_form(), classifier
			        .distributionForInstance(current_instance));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    for(String c : candidates_relevantness_probabilities.keySet())
    {
    	System.out.println(c + " : " + Arrays.toString(candidates_relevantness_probabilities.get(c)));
    }

    
    
    //ArrayList<Integer> best_candidates_indexes = sortCandidatesByProbabality(candidates_relevantness_probabilities);
    // TODO sort candidates_relevantness_probabilities using class ProbaList

  }
}
