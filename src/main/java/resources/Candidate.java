package resources;

import java.util.TreeMap;

public class Candidate {
  String name;
  String effective_full_form_;
	int term_frequency_;
	int document_frequency_;
	double inverse_document_frequency_;
	
	int first_occurrence_;
	int last_occurrence_;
	
	TreeMap<String, Integer> full_forms_;
	
	public Candidate(String name)
	{
		this.name = name;
		term_frequency_ = 0;
		document_frequency_ = 0;
		inverse_document_frequency_ = 0.0;
		first_occurrence_ = -1;
		last_occurrence_ = 0;
		full_forms_ = new TreeMap<String, Integer>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String value) {
    name = value;
  }
	
	public String getEffectiveFullForm() {
    return effective_full_form_;
  }
	
	public void setEffectiveFullForm(String value) {
    effective_full_form_ = value;
  }

	public int getTerm_frequency() {
		return term_frequency_;
	}
	
	public void setTerm_frequency(int term_frequency) {
		this.term_frequency_ = term_frequency;
	}
	
	public int getDocument_frequency() {
		return document_frequency_;
	}
	
	public void setDocument_frequency(int document_frequency) {
		this.document_frequency_ = document_frequency;
	}
	
	public double getInverse_document_frequency() {
		return inverse_document_frequency_;
	}
	
	public void setInverse_document_frequency(double inverse_document_frequency) {
		this.inverse_document_frequency_ = inverse_document_frequency;
	}
	
	public int getFirst_occurrence() {
		return first_occurrence_;
	}
	
	public void setFirst_occurrence(int first_occurrence) {
		this.first_occurrence_ = first_occurrence;
	}
	
	public int getLast_occurrence() {
		return last_occurrence_;
	}
	
	public void setLast_occurrence(int last_occurrence) {
		this.last_occurrence_ = last_occurrence;
	}
	
	public TreeMap<String, Integer> getFullForms() {
	  return full_forms_;
	}
	
	public void addInformation(String fullForm, int pos) {
	  if (full_forms_.containsKey(fullForm)) {
		  full_forms_.put(fullForm, full_forms_.get(fullForm) + 1);
	  }
	  else {
	    full_forms_.put(fullForm, 1);
	  }
	  int max = 0;
	  for(String s : full_forms_.keySet()) {
      if (full_forms_.get(s) > max) {
        max = full_forms_.get(s);
        setEffectiveFullForm(s);
      }
    }
	  term_frequency_++;
	  if (first_occurrence_ == -1)
	    first_occurrence_ = pos;
	  last_occurrence_ = pos;
	}
	
	@Override
  public String toString() {
    return "Candidate [name=" + name + ", effective_full_form_=" + effective_full_form_
            + ", full_forms_=" + full_forms_ + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Candidate other = (Candidate) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }
}
