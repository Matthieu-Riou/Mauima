package resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;

public class AnnotatedCollection_Impl  implements AnnotatedCollection, SharedResourceObject  {
	
	protected List<List<Candidate>> documents;

	public List<List<Candidate>> getDocuments() {
		return documents;
	}
	
	protected void add(String line)
	{
		List<Candidate> candidates = new ArrayList<Candidate>();
		String[] splits = line.split(";");
		
		for(String c : splits)
		{
			String[] values = c.split(":");
			Candidate candidate = new Candidate(values[0]);
			
			candidate.setTerm_frequency(Integer.parseInt(values[1]));
			candidate.setDocument_frequency(Integer.parseInt(values[2]));
			candidate.setInverse_document_frequency(Integer.parseInt(values[3]));
			candidate.setFirst_occurrence(Integer.parseInt(values[4]));
			candidate.setLast_occurrence(Integer.parseInt(values[5]));
			
			candidates.add(candidate);
		}
		
		documents.add(candidates);
		
	}

	public void load(DataResource aData) throws ResourceInitializationException {
		InputStream inStr = null;
		try {
			inStr = aData.getInputStream();
		
			documents = new ArrayList<List<Candidate>>();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStr));
			String line;
			while ((line = reader.readLine()) != null) {
				add(line.trim());
			}
		} catch (IOException e) {
			throw new ResourceInitializationException(e);
		} finally {
			if (inStr != null) {
				try {
					inStr.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
