package annotators;

import static org.apache.uima.fit.util.JCasUtil.select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import resources.FeaturesMap;
import types.Features;
import types.TextualSegment;
import weka.classifiers.Classifier;
import weka.classifiers.meta.Bagging;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.Utils;



	class ProbaList {
		double[] probabilities;
		int index_to_compare;
		public ProbaList(double[] probas){
			this.probabilities = probas;
			this.index_to_compare = 0;
		}
		
		public double getElemAt(int index) {
			return this.probabilities[index];
		}
	    public int compare(ProbaList a, ProbaList b) {
	        return ((Double) a.getElemAt(index_to_compare)).compareTo((Double) b.getElemAt(index_to_compare)); 
	    }
	}

public class WekaClassifier extends JCasAnnotator_ImplBase {
	
	private ArrayList<Integer> sortCandidatesByProbabality(ArrayList<double[]> candidates_relevantness_probabilities) {
		int relevant_class_index = 0;
		ArrayList<Integer> candidates_indexes_list = new ArrayList<Integer>();
		for (double[] relevantness_proba : candidates_relevantness_probabilities){
			
		}
		
		return candidates_indexes_list;
	}

	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {
		//Input: jcas representing the list of candidates topics for a document, as a collection of features 
		//represented by a class Features
		
		ArrayList<double[]> candidates_relevantness_probabilities = new ArrayList<double[]>();
		Classifier classifier = null; 
		//Loading model from file
		String model_file_name = "target/m5p.model";
		try {
			classifier = (Classifier) weka.core.SerializationHelper.read(model_file_name); // ??
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    }
		
		//Declaring features
		FastVector attributes = new FastVector();
		int numFeatures = 7; //TODO get this number instead of having it hardcoded
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
		
		Instances classifierData = new Instances("ClassifierData", attributes, 0);
		classifierData.setClassIndex(numFeatures);
		
		//Feeding instances data to weka model to get class probabilities
		for (Features candidate : select(jCas, Features.class)) {
			double[] values = {
						candidate.getTf(), 
						candidate.getDf(), 
						candidate.getIdf(), 
						candidate.getTfidf(), 
						candidate.getFirst_occurrence(), 
						candidate.getLast_occurrence(), 
						candidate.getSpread()
						//TODO class ?
					};
			Instance current_instance = new Instance(1, values);
			try {
				candidates_relevantness_probabilities.add(classifier.distributionForInstance(current_instance));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		ArrayList<Integer> best_candidates_indexes = sortCandidatesByProbabality(candidates_relevantness_probabilities);
		//TODO sort candidates_relevantness_probabilities using class ProbaList
		
		
	}
}