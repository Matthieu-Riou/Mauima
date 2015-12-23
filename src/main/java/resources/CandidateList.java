package resources;

import java.util.ArrayList;
import java.util.TreeMap;

public interface CandidateList {
  /**
   * A getter for the allCandidates_ attribute
   *
   * @return The list of candidates for each files to handle, grouped by stemmed n-grams
   */
  ArrayList<TreeMap<String, Candidate>> getAllCandidates();

  /**
   * Method to save all information into a file
   * @param filename The name of the file to write information
   * @param files A list containing all filenames of files handled
   */
  void save(String filename, ArrayList<String> files);
}
