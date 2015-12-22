package readers;

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
import types.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class CandidateReader extends JCasCollectionReader_ImplBase {
	

	/*public static final String PARAM_SELECTOR = "SELECTOR";
	@ConfigurationParameter(name = PARAM_SELECTOR,
			description = "css element to analyse",
			mandatory = false, defaultValue = "body")
	private String cssSelector;
	*/
	
	public static final String CANDIDATE_KEY = "candidateKey";
	public static final String PARAM_LANGUAGE = "LANGUAGE";
	public static final String PARAM_DIRECTORY = "key_directory";
	int i = 0;
	int size = 0;
	 @ExternalResource(key = CANDIDATE_KEY)
	 private AnnotatedCollection collection;
		@ConfigurationParameter(name = PARAM_LANGUAGE,
				description = "default language for the text",
				mandatory = false, defaultValue = "en")
		private String language;
	@ConfigurationParameter(
	name = PARAM_DIRECTORY,
	description = "The directory containing the key files",
	mandatory = true)
	private String directory_dir;

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
			BufferedReader readerFile = new BufferedReader(new FileReader(directory_dir+documentName.replaceAll(".txt", ".key")));
			String line;
			 while((line = readerFile.readLine()) != null){
				 String[] splits = line.split("\t");
				 System.out.println("candidate.getFullForms() = " + candidate.getFullForms());
				 System.out.println("splits[0] = " + splits[0]);
				 if (candidate.getFullForms().containsKey(splits[0].toLowerCase()))
				 {
					 return 1;
				 }
			 }
			 readerFile.close();
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
		
		Document doc_type = new Document(jcas, 0, 0);
		doc_type.setDocumentName(documentName);
		
		doc_type.addToIndexes();
		
		int cpt = 1;
		for(resources.Candidate c : document)
		{
			types.Candidate candidate = new types.Candidate(jcas, cpt, cpt);
			candidate.setName(c.getName());
			candidate.setEffective_full_form(c.getEffectiveFullForm());
			candidate.setTf(c.getTerm_frequency());
			candidate.setDf(c.getDocument_frequency());
			candidate.setIdf(c.getInverse_document_frequency());
			candidate.setFirst_occ(c.getFirst_occurrence());
			candidate.setLast_occ(c.getLast_occurrence());

			candidate.setClass_(computeClass(c, documentName));
			
			candidate.addToIndexes();
			
			cpt++;
		}
		
		i++;
	}

}