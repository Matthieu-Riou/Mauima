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

/**
 * A custom reader
 */
public class CandidateReader extends JCasCollectionReader_ImplBase {
	

	/*public static final String PARAM_SELECTOR = "SELECTOR";
	@ConfigurationParameter(name = PARAM_SELECTOR,
			description = "css element to analyse",
			mandatory = false, defaultValue = "body")
	private String cssSelector;
	*/

	/**
	 * The name of the key for the resource containing Candidates
	 */
	public static final String CANDIDATE_KEY = "candidateKey";

	/**
	 * The name of the language parameter
	 */
	public static final String PARAM_LANGUAGE = "LANGUAGE";

	/**
	 * The name of the parameter giving the directory into which we have .key files
	 */
	public static final String PARAM_DIRECTORY = "key_directory";

	/**
	 * A counter to avoid iterate over the size
	 */
	private int i = 0;

	/**
	 * The number of CAS to generate
	 */
	private int size = 0;

	/**
	 * The attribute linked to the resource of key CANDIDATE_KEY
	 */
	@ExternalResource(key = CANDIDATE_KEY)
	private AnnotatedCollection collection;

	/**
	 * The attribute linked to the parameter named PARAM_LANGUAGE
	 */
	@ConfigurationParameter(name = PARAM_LANGUAGE,
			description = "default language for the text",
			mandatory = false, defaultValue = "en")
	private String language;

	/**
	 * The attribute linked to the parameter named PARAM_DIRECTORY
	 */
	@ConfigurationParameter(
			name = PARAM_DIRECTORY,
			description = "The directory containing the key files",
			mandatory = true)
	private String directory_dir;

	/**
	 * Method to initialize the reader before the pipeline
	 * @param context The uima context
	 * @throws ResourceInitializationException
	 */
	@Override
	public void initialize(UimaContext context)
			throws ResourceInitializationException {
		super.initialize(context);
		size = collection.getDocuments().size();
	}

	/**
	 * Method checking the progression of the generation
	 * @return An array
	 */
	public Progress[] getProgress() {
		return new Progress[] { new ProgressImpl(i, size, Progress.ENTITIES)};
	}

	/**
	 * Check if there is an other CAS to generate
	 * @return true if there is an other CAS to generate, false otherwise
	 * @throws IOException
	 * @throws CollectionException
	 */
	public boolean hasNext() throws IOException, CollectionException {
		return i < size;
	}

	/**
	 * Compute the class of a candidate
	 * @param candidate A candidate
	 * @param documentName The filename of the document
	 * @return 1 if the candidate is in the .key associatited to the document, 0 otherwise
	 */
	public int computeClass(resources.Candidate candidate, Document document)
	{
		try
		{
			document.setHasValidTopics(true);
			BufferedReader readerFile = new BufferedReader(new FileReader(directory_dir+document.getDocumentName().replaceAll(".txt", ".key")));
			String line;
			while ((line = readerFile.readLine()) != null) {
				String[] splits = line.split("\t");
				if (candidate.getFullForms().containsKey(splits[0].toLowerCase())) {
					readerFile.close();
					return 1;
				}
			}
			readerFile.close();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			document.setHasValidTopics(false);
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * Method to generate a new CAS
	 * @param jcas The CAS in which to store the generated CAS
	 * @throws IOException
	 * @throws CollectionException
	 */
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

			candidate.setClass_(computeClass(c, doc_type));

			candidate.addToIndexes();

			cpt++;
		}

		i++;
	}

}