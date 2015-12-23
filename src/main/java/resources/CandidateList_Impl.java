package resources;

import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * A class to represent a resource representing candidates for each file to handle
 */
public class CandidateList_Impl implements CandidateList, SharedResourceObject {

  /**
   * The list of candidates for each files to handle, grouped by stemmed n-grams
   */
  private ArrayList<TreeMap<String, Candidate>> allCandidates_;

  /**
   * Method to load a resource into the object
   * @param arg0 The resource to load
   * @throws ResourceInitializationException
   */
  public void load(DataResource arg0) throws ResourceInitializationException {
    if (allCandidates_ == null) {
      allCandidates_ = new ArrayList<TreeMap<String, Candidate>>();
    }
  }

  @Override
  /**
   * Represent the class as a string
   * @return The string representing an instance of the class
   */
  public String toString() {
    return "CandidateList_Impl [allCandidates_=" + allCandidates_ + "]";
  }

  /**
   * A getter for the allCandidates_ attribute
   * @return The list of candidates for each files to handle, grouped by stemmed n-grams
   */
  public ArrayList<TreeMap<String, Candidate>> getAllCandidates() {
    return allCandidates_;
  }

  /**
   * Method to save all information into a file
   * @param filename The name of the file to write information
   * @param files A list containing all filenames of files handled
   */
  public void save(String filename, ArrayList<String> files) {
    PrintWriter out = null;
    try {
      out = new PrintWriter(filename);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    for (TreeMap<String, Candidate> cMap : allCandidates_) {
      String s = files.remove(0) + ";";

      int cpt = 0;
      for (String n : cMap.keySet()) {
        if (cpt > 0) {
          s += ";";
        }

        Candidate c = cMap.get(n);
        s += c.getName() + ":" + c.getTerm_frequency() + ":" + c.getDocument_frequency() + ":"
                + c.getInverse_document_frequency() + ":" + c.getFirst_occurrence() + ":"
                + c.getLast_occurrence() + ":" + c.getEffectiveFullForm() + ":";

        boolean once = true;
        for (String full_form : c.getFullForms().keySet()) {
          if (!once) {
            s += "///";
          } else {
            once = false;
          }

          s += full_form + "%%" + c.getFullForms().get(full_form);
        }

        cpt++;
      }

      if (out != null) {
        if (files.isEmpty())
          out.print(s);
        else
          out.println(s);
      }
    }
    if (out != null) {
      out.close();
    }
  }
}
