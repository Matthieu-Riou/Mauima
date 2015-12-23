package annotators;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import types.Features;
import weka.classifiers.Classifier;
import weka.classifiers.meta.Bagging;
import weka.core.*;

import static org.apache.uima.fit.util.JCasUtil.select;

/**
 * An analysis engine to learn and save a Weka model
 */
public class WekaModelBuilder extends JCasAnnotator_ImplBase {
	/**
	 * A list of Instance, defined by Weka's API
	 */
	private Instances classifierData;

	/**
	 * The index of the attribute containing the class of the Instance
	 */
	private int indexClass = 7;

	/**
	 * The Weka classifier
	 */
	private Classifier classifier;

	/**
	 * Method to initialize the analysis engine before pipeline
	 *
	 * @param context the UIMA context
	 * @throws ResourceInitializationException
	 */
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

	/**
	 * Method to process treatments over a CAS
	 * @param jCas The CAS to use
	 * @throws AnalysisEngineProcessException
	 */
	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {

		for(Features f : select(jCas, Features.class))
		{
			double[] vals = {f.getTf(), f.getDf(), f.getIdf(), f.getTfidf(), f.getFirst_occurrence(), f.getLast_occurrence(), f.getSpread(), f.getClass_()};

			classifierData.add(new Instance(1, vals));
		}

	}

	/**
	 * Method to make some treatments after the pipeline has ended
	 * @throws AnalysisEngineProcessException
	 */
	@Override
	public void collectionProcessComplete() throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		super.collectionProcessComplete();

		System.out.println("Collection Process Complete --> build model...");

		try {
			classifier.setOptions(Utils.splitOptions("-P 10 -S 1 -I 10 -W weka.classifiers.trees.J48 -- -U -M 2"));
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

