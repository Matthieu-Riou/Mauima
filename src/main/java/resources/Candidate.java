package resources;

public class Candidate {
  String name;
	int term_frequency;
	int document_frequency;
	int inverse_document_frequency;
	
	int first_occurrence;
	int last_occurrence;
	
	public Candidate(String name)
	{
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int getTerm_frequency() {
		return term_frequency;
	}
	
	public void setTerm_frequency(int term_frequency) {
		this.term_frequency = term_frequency;
	}
	
	public int getDocument_frequency() {
		return document_frequency;
	}
	
	public void setDocument_frequency(int document_frequency) {
		this.document_frequency = document_frequency;
	}
	
	public int getInverse_document_frequency() {
		return inverse_document_frequency;
	}
	
	public void setInverse_document_frequency(int inverse_document_frequency) {
		this.inverse_document_frequency = inverse_document_frequency;
	}
	
	public int getFirst_occurrence() {
		return first_occurrence;
	}
	
	public void setFirst_occurrence(int first_occurrence) {
		this.first_occurrence = first_occurrence;
	}
	
	public int getLast_occurrence() {
		return last_occurrence;
	}
	
	public void setLast_occurrence(int last_occurrence) {
		this.last_occurrence = last_occurrence;
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
