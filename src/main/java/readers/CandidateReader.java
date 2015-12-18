package readers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.component.JCasCollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.descriptor.ExternalResource;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

import resources.AnnotatedCollection;
import resources.Candidate;


public class CandidateReader extends JCasCollectionReader_ImplBase {
	

	/*public static final String PARAM_SELECTOR = "SELECTOR";
	@ConfigurationParameter(name = PARAM_SELECTOR,
			description = "css element to analyse",
			mandatory = false, defaultValue = "body")
	private String cssSelector;
	*/
	
	public static final String CANDIDATE_KEY = "candidateKey";
	 @ExternalResource(key = CANDIDATE_KEY)
	 private AnnotatedCollection collection;
	 
	 public static final String PARAM_LANGUAGE = "LANGUAGE";
		@ConfigurationParameter(name = PARAM_LANGUAGE,
				description = "default language for the text",
				mandatory = false, defaultValue = "en")
		private String language;
	
	public static final String PARAM_DIRECTORY = "key_directory";
	@ConfigurationParameter(
	name = PARAM_DIRECTORY,
	description = "The directory containing the key files",
	mandatory = true)
	private String directory_dir;
	
	int i = 0;
	int size = 0;

	@Override
	public void initialize(UimaContext context)
			throws ResourceInitializationException {
		super.initialize(context);
		size = collection.getDocuments().size();
	}

	public Progress[] getProgress() {
		return new Progress[] { new ProgressImpl(i, size, Progress.ENTITIES) };
	}

	public boolean hasNext() throws IOException, CollectionException {
		return i < size;
	}
	
	public int computeClass(resources.Candidate candidate, String documentName)
	{
		try
		{
			BufferedReader readerFile = new BufferedReader(new FileReader(directory_dir+documentName.replaceAll(".txt", ".keys")));
			String line;
			 while((line = readerFile.readLine()) != null){
				 String[] splits = line.split("\t");
				 
				 if(candidate.getFullForms().containsKey(splits[0]))
				 {
					 return 1;
				 }
			 }
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public void getNext(JCas jcas) throws IOException, CollectionException {
		jcas.setDocumentLanguage(language);
		
		List<Candidate> document = collection.getDocuments().get(i);
		String documentName = collection.getFilename(i);
		
		for(resources.Candidate c : document)
		{
			types.Candidate candidate = new types.Candidate(jcas);
			candidate.setName(c.getName());
			candidate.setEffective_full_form(c.getEffectiveFullForm());
			candidate.setTf(c.getTerm_frequency());
			candidate.setDf(c.getDocument_frequency());
			candidate.setIdf(c.getInverse_document_frequency());
			candidate.setFirst_occ(c.getFirst_occurrence());
			candidate.setLast_occ(c.getLast_occurrence());

			candidate.setClass_(computeClass(c, documentName));
			
			candidate.addToIndexes();
		}
		
		i++;
	}

}