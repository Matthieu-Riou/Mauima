package annotators;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import static org.apache.uima.fit.util.JCasUtil.select;
import types.Features;
import weka.classifiers.Classifier;
import weka.classifiers.meta.Bagging;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;

public class WekaModelBuilder extends JCasAnnotator_ImplBase {
	
	Instances classifierData;
	int indexClass = 7;
	Classifier classifier;
	
	@Override
	public void initialize(UimaContext context)
			throws ResourceInitializationException {
		// TODO Auto-generated method stub
		super.initialize(context);
		
		Boolean nominalClassValue = true;
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
	
		classifierData = new Instances("ClassifierData", attributes, 0);
		classifierData.setClassIndex(indexClass);
		
		classifier = new Bagging();
	}

	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {

		for(Features f : select(jCas, Features.class))
		{	
			double[] vals = {f.getTf(), f.getDf(), f.getIdf(), f.getTfidf(), f.getFirst_occurrence(), f.getLast_occurrence(), f.getSpread(), f.getClass_()};
			
			classifierData.add(new Instance(1, vals));
		}
		
	}
	
	@Override
	  public void collectionProcessComplete() throws AnalysisEngineProcessException {
	    // TODO Auto-generated method stub
	    super.collectionProcessComplete();
	    
	    System.out.println("Collection Process Complete --> build model...");
	    
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
	    
	    System.out.println("Collection Process Complete --> saving model...");
	    
	  //TODO dump model in file 
  		//check for https://weka.wikispaces.com/Serialization
  		try {
	        weka.core.SerializationHelper.write("target/m5p.model", classifier);
	      } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      }
	  		
	    System.out.println("Model successfully saved to target/m5p.model");
	  }
}

