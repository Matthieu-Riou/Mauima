package utils;

public class ProbaList {
  double[] probabilities;

  int index_to_compare;

  public ProbaList(double[] probas) {
    this.probabilities = probas;
    this.index_to_compare = 0;
  }

  public double getElemAt(int index) {
    if (index >= probabilities.length)
      index = probabilities.length-1;
    else if (index < 0)
      index = 0;
    return this.probabilities[index];
  }

  public int compare(ProbaList a, ProbaList b) {
    return ((Double) a.getElemAt(index_to_compare)).compareTo((Double) b
            .getElemAt(index_to_compare));
  }
}