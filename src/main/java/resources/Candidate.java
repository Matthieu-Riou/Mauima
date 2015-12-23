package resources;

import java.util.TreeMap;

/**
 * Class representing a candidate
 */
public class Candidate {
    /**
     * The name of the candidate
     */
    private String name;

    /**
     * The most frequent full form of the candidate
     */
    private String effective_full_form_;

    /**
     * The term-frequency of the candidate
     */
    private int term_frequency_;

    /**
     * The document-frequency of the candidate
     */
    private int document_frequency_;

    /**
     * The inversed document-frequency of the candidate
     */
    private double inverse_document_frequency_;

    /**
     * The first occurence of the candidate, represented by its starting position
     */
    private int first_occurrence_;

    /**
     * The last occurence of the candidate, represented by its ending position
     */
    private int last_occurrence_;

    /**
     * A map giving for each full form, its number of occurrences
     */
    private TreeMap<String, Integer> full_forms_;

    /**
     * The constructor of the class Candidate
     *
     * @param name The name of the candidate
     */
    public Candidate(String name) {
        this.name = name;
        term_frequency_ = 0;
        document_frequency_ = 0;
        inverse_document_frequency_ = 0.0;
        first_occurrence_ = -1;
        last_occurrence_ = 0;
        full_forms_ = new TreeMap<String, Integer>();
    }

    /**
     * A getter for the name of the candidate
     *
     * @return The name of the candidate
     */
    public String getName() {
        return name;
    }

    /**
     * A setter for the name of the candidate
     *
     * @param value The new name of the candidate
     */
    public void setName(String value) {
        name = value;
    }

    /**
     * A getter for the effective full form of the candidate
     *
     * @return The most frequent full form of the candidate
     */
    public String getEffectiveFullForm() {
        return effective_full_form_;
    }

    /**
     * A setter for the effective full form of the candidate
     *
     * @param value The new effective full form of the candidate
     */
    public void setEffectiveFullForm(String value) {
        effective_full_form_ = value;
    }

    /**
     * A getter for the term-frequency of the candidate
     *
     * @return The term-frequency of the candidate
     */
    public int getTerm_frequency() {
        return term_frequency_;
    }

    /**
     * A setter for the term-frequency of the candidate
     *
     * @param term_frequency the new term-frequency of the candidate
     */
    public void setTerm_frequency(int term_frequency) {
        this.term_frequency_ = term_frequency;
    }

    /**
     * A getter for the document-frequency of the candidate
     *
     * @return the document-frequency of the candidate
     */
    public int getDocument_frequency() {
        return document_frequency_;
    }

    /**
     * A setter for the document-frequency of the candidate
     *
     * @param document_frequency the new document-frequency of the candidate
     */
    public void setDocument_frequency(int document_frequency) {
        this.document_frequency_ = document_frequency;
    }

    /**
     * A getter for the inverse document-frequency of the candidate
     *
     * @return The inverse document-frequency of the candidate
     */
    public double getInverse_document_frequency() {
        return inverse_document_frequency_;
    }

    /**
     * A setter for the inverse document-frequency of the candidate
     *
     * @param inverse_document_frequency The new inverse document-frequency of the candidate
     */
    public void setInverse_document_frequency(double inverse_document_frequency) {
        this.inverse_document_frequency_ = inverse_document_frequency;
    }

    /**
     * A getter for the first occurrence of the candidate
     *
     * @return The starting position of the first occurrence of the candidate
     */
    public int getFirst_occurrence() {
        return first_occurrence_;
    }

    /**
     * A setter for the first occurrence of the candidate
     *
     * @param first_occurrence The new starting position of the first occurence of the candidate
     */
    public void setFirst_occurrence(int first_occurrence) {
        this.first_occurrence_ = first_occurrence;
    }

    /**
     * A getter for the last position of the candidate
     *
     * @return The starting position of the last occurrence of the candidate
     */
    public int getLast_occurrence() {
        return last_occurrence_;
    }

    /**
     * A setter for the last occurrence of the candidate
     *
     * @param last_occurrence The new starting position of the last occurrence of the candidate
     */
    public void setLast_occurrence(int last_occurrence) {
        this.last_occurrence_ = last_occurrence;
    }

    /**
     * A getter for the full forms
     *
     * @return A map giving for each full form, its number of occurrences
     */
    public TreeMap<String, Integer> getFullForms() {
        return full_forms_;
    }

    /**
     * Method to add information about a candidate
     *
     * @param fullForm The full form of the observed candidate
     * @param pos      The starting position of the observed candidate
     */
    public void addInformation(String fullForm, int pos) {
        // update its occurrences if it exists in the map
        if (full_forms_.containsKey(fullForm)) {
            full_forms_.put(fullForm, full_forms_.get(fullForm) + 1);
        }
        // create it otherwise
        else {
            full_forms_.put(fullForm, 1);
        }
        // update the effective full form
        int max = 0;
        for (String s : full_forms_.keySet()) {
            if (full_forms_.get(s) > max) {
                max = full_forms_.get(s);
                setEffectiveFullForm(s);
            }
        }
        // update occurrences position and the term-frequency
        term_frequency_++;
        if (first_occurrence_ == -1)
            first_occurrence_ = pos;
        last_occurrence_ = pos;
    }

    /**
     * Method to represent a Candidate as a string
     *
     * @return The string representing a Candidate
     */
    @Override
    public String toString() {
        return "Candidate [name=" + name + ", effective_full_form_=" + effective_full_form_
                + ", full_forms_=" + full_forms_ + "]";
    }

    /**
     * Method computing the hash of a Candidate
     *
     * @return The hash of a Candidate
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
     * Method to test equality between two Candidates
     *
     * @param c The Candidate to test equality with
     * @return true if Candidates are equals, false otherwise
     */
    @Override
    public boolean equals(Object c) {
        if (this == c)
            return true;
        if (c == null)
            return false;
        if (getClass() != c.getClass())
            return false;
        Candidate other = (Candidate) c;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
