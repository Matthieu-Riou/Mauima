package resources;

import java.util.ArrayList;
import java.util.List;

public interface AnnotatedCollection {

	List<List<Candidate>> getDocuments();
	ArrayList<String> getAllFilenames();
	String getFilename(int index);
}