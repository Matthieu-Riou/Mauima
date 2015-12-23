package resources;

import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing Files as a list of Candidates
 */
public class AnnotatedCollection_Impl  implements AnnotatedCollection, SharedResourceObject  {

    /**
     * The list of Candidates for each handled files
     */
    protected List<List<Candidate>> documents;

    /**
     * The filename of each handled files
     */
    protected ArrayList<String> filenames_ = new ArrayList<String>();

    /**
     * A getter for the list of Candidates of each files
     *
     * @return TThe list of Candidates for each handled files
     */
    public List<List<Candidate>> getDocuments() {
        return documents;
    }

    /**
     * Add a new list of Candidates
     *
     * @param line The line of the file containing structured list of Candidates to parse
     */
    protected void add(String line) {
        List<Candidate> candidates = new ArrayList<Candidate>();

        String[] splits = line.split(";");
        filenames_.add(splits[0]);

        boolean once = false;

        for (String c : splits) {
            if (!once) {
                once = true;
                continue;
            }
            String[] values = c.split(":");
            Candidate candidate = new Candidate(values[0]);

            for (String f : values[7].split("///")) {
                String[] f_split = f.split("%%");
                candidate.addInformation(f_split[0], Integer.parseInt(f_split[1]));
            }

            candidate.setTerm_frequency(Integer.parseInt(values[1]));
            candidate.setDocument_frequency(Integer.parseInt(values[2]));
            candidate.setInverse_document_frequency(Double.parseDouble(values[3]));
            candidate.setFirst_occurrence(Integer.parseInt(values[4]));
            candidate.setLast_occurrence(Integer.parseInt(values[5]));
            candidate.setEffectiveFullForm(values[6]);

            candidates.add(candidate);
        }

        documents.add(candidates);

    }

    /**
     * Method to load information from a resource
     *
     * @param aData The resource containing information
     * @throws ResourceInitializationException
     */
    public void load(DataResource aData) throws ResourceInitializationException {
        InputStream inStr = null;
        try {
            inStr = aData.getInputStream();

            documents = new ArrayList<List<Candidate>>();
            //filenames_ = new ArrayList<String>();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inStr));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty())
                    add(line.trim());
            }
        } catch (IOException e) {
            throw new ResourceInitializationException(e);
        } finally {
            if (inStr != null) {
                try {
                    inStr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * A getter for the filenames of handled files
     *
     * @return The list of the filenames of each handled files
     */
    public ArrayList<String> getAllFilenames() {
        return filenames_;
    }

    /**
     * A getter for a specific filename
     *
     * @param index The index of the file
     * @return The filename of the file at position index
     */
    public String getFilename(int index) {
        if (index >= filenames_.size())
            index = filenames_.size() - 1;
        else if (index < 0)
            index = 0;
        return filenames_.get(index);
    }
}
