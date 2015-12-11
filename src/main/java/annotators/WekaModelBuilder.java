package annotators;


import java.util.ArrayList;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ExternalResource;
import org.apache.uima.jcas.JCas;

import resources.FeaturesList;
import resources.FeaturesMap;
import weka.classifiers.Classifier;
import weka.classifiers.meta.Bagging;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.Utils;

public class WekaModelBuilder extends JCasAnnotator_ImplBase {
	
	public final static String FEATURES_KEY = "featuresKey";
	@ExternalResource(key = FEATURES_KEY)
	private FeaturesList featuresList_;
	  
	private ArrayList<FeaturesMap> loadInstances(){
		//TODO read features and set their values in a FeaturesMap object
		ArrayList<FeaturesMap> instances = new ArrayList<FeaturesMap>();
		
		for(ArrayList<FeaturesMap> e : featuresList_.getFeaturesList())
		{
			instances.addAll(e);
		}
		
		// read instances here
		return instances;
	}
	
	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {
		ArrayList<FeaturesMap> instances = this.loadInstances();
		int numFeatures = instances.get(0).nb_of_features; 
		Boolean nominalClassValue = false;
		FastVector attributes = new FastVector();
		
		attributes.addElement(new Attribute("TF")); // 1
		attributes.addElement(new Attribute("DF")); // 2
		attributes.addElement(new Attribute("IDF")); // 3
		attributes.addElement(new Attribute("TFxIDF")); 
		attributes.addElement(new Attribute("FirstOccurrence")); // 4
		attributes.addElement(new Attribute("LastOccurrence")); // 5
		attributes.addElement(new Attribute("Spread")); // 6
		// declare a class attribute :
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
		
		for(FeaturesMap f : instances)
		{
			classifierData.add(f.toInstance());
		}
		
		Classifier classifier = new Bagging();
		try {
			classifier.setOptions(Utils.splitOptions("-P 100 -S 1 -I 10 -W weka.classifiers.trees.M5P -- -U -M 7.0"));
		} catch (Exception e) {
			//TODO
			System.out.println("Error while loading classifier options");
		}
		try {
			classifier.buildClassifier(classifierData);
		} catch (Exception e) {
			//TODO
			System.out.println("Error while building the classifier : " + e.getMessage());
		}
		//TODO dump model in file 
		//check for https://weka.wikispaces.com/Serialization
		try {
      weka.core.SerializationHelper.write("target/m5p.model", classifier);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
	}
}

