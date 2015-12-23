package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Parse a RDF file to get PrefLabels and AltLabels
 */
public class RDFParser {
  /**
   * A map containing PrefLabels and AltLabels
   * Key: PrefLabel
   * value: ArrayList containing AltLabels
   */
  public TreeMap<String, ArrayList<String>> labels_;

  /**
   * Default constuctor
   */
  public RDFParser() {
    labels_ = new TreeMap<String, ArrayList<String>>();
  }

  /**
   * Parse a RDF file
   * @param filename Name of the file to parse
   */
  public void parse(String filename) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      String line;
      String last = "";
      while ((line = reader.readLine()) != null) {
        if (line.contains("prefLabel") || line.contains("altLabel")) {
          boolean add = false; // check if we can add the character into the string
          String value = "";
          for (int i = 0; i < line.length(); ++i) {
            /* if we are at the beginning of the ending prefLabel or altLabel element,
             * we stop adding characters
             */
            if (line.charAt(i) == '<')
              add = false;

            if (add)
              value += line.charAt(i);

            /* if we are at the end of the starting prefLabel or altLabel element,
             * we can start adding characters
             */
            if (line.charAt(i) == '>')
              add = true;
          }
          if (line.contains("prefLabel")) {
            labels_.put(value, new ArrayList<String>());
            last = value;
          } else
            labels_.get(last).add(value);
        }
      }
      reader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Getter for the labels_ attribute
   * @return A map containing PrefLabels and AltLabels
   */
  public TreeMap<String, ArrayList<String>> getLabels() {
    return labels_;
  }
}
