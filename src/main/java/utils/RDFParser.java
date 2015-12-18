package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class RDFParser {
  public TreeMap<String, ArrayList<String>> labels_;
  
  public RDFParser() {
    labels_ = new TreeMap<String, ArrayList<String>>();
  }
  
  void parse(String filename) {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(filename));
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
      
    try {
      Pattern p = Pattern.compile(">(.+)<");
      String line;
      String last = "";
      while ((line = reader.readLine()) != null) {
        if (line.contains("prefLabel") || line.contains("altLabel")) {
          boolean add = false;
          String value = "";
          for (int i = 0; i < line.length(); ++i) {
            if (line.charAt(i) == '<')
              add = false;
            if (add)
              value += line.charAt(i);
            if (line.charAt(i) == '>')
              add = true;  
          }
          if (line.contains("prefLabel")) {
            labels_.put(value, new ArrayList<String>());
            last = value;
          }
          else
            labels_.get(last).add(value);
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public TreeMap<String, ArrayList<String>> getLabels() {
    return labels_;
  }
  
  public static void main(String[] args) {
    RDFParser parser = new RDFParser();
    parser.parse("src/main/resources/resources/agrovoc_sample.rdf");
    System.out.println(parser.getLabels());
  }
}
