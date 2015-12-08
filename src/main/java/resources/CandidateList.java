package resources;

import java.util.ArrayList;
import java.util.TreeMap;

public interface CandidateList {
  ArrayList<TreeMap<String, Candidate>> getAllCandidates();
  
  void save(String filename);
}
