package resources;

import java.util.ArrayList;

import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;

public class FeaturesList_Impl implements FeaturesList, SharedResourceObject {
  private ArrayList<ArrayList<FeaturesMap>> featuresList_;

  public ArrayList<ArrayList<FeaturesMap>> getFeaturesList() {
    return featuresList_;
  }

  public void load(DataResource arg0) throws ResourceInitializationException {
    if (featuresList_ == null)
      featuresList_ = new ArrayList<ArrayList<FeaturesMap>>();
  }

}
