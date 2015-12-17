package resources;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;

public class CandidateList_Impl implements CandidateList, SharedResourceObject {
  private ArrayList<TreeMap<String, Candidate>> allCandidates_;

  public void load(DataResource arg0) throws ResourceInitializationException {
    if (allCandidates_ == null) {
      allCandidates_ = new ArrayList<TreeMap<String, Candidate>>();
    }
  }

  @Override
  public String toString() {
    return "CandidateList_Impl [allCandidates_=" + allCandidates_ + "]";
  }

  public ArrayList<TreeMap<String, Candidate>> getAllCandidates() {
    return allCandidates_;
  }

  public void save(String filename, ArrayList<String> files) {
    PrintWriter out = null;
    try {
      out = new PrintWriter(filename);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    for (TreeMap<String, Candidate> cMap : allCandidates_) {
      String s = files.remove(0) + ";";
      
      int cpt = 0;
      for (String n : cMap.keySet()) {
        if(cpt > 0) {
          s+= ";";
        }
        
        Candidate c = cMap.get(n);
        s += c.getName() + ":" + c.getTerm_frequency() + ":" + c.getDocument_frequency() + ":"
                + c.getInverse_document_frequency() + ":" + c.getFirst_occurrence() + ":"
                + c.getLast_occurrence();
        
        cpt++;
      }
      if (files.isEmpty())
        out.print(s);
      else
        out.println(s);
    }
    out.close();
  }
}
