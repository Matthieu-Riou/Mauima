package resources;

import java.util.ArrayList;
import java.util.List;

public interface AnnotatedCollection {

    /**
     * A getter for the list of Candidates of each files
     *
     * @return TThe list of Candidates for each handled files
     */
    List<List<Candidate>> getDocuments();

    /**
     * A getter for the filenames of handled files
     *
     * @return The list of the filenames of each handled files
     */
    ArrayList<String> getAllFilenames();

    /**
     * A getter for a specific filename
     *
     * @param index The index of the file
     * @return The filename of the file at position index
     */
    String getFilename(int index);
}