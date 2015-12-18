
/* First created by JCasGen Fri Dec 18 15:02:47 CET 2015 */
package types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Fri Dec 18 15:02:47 CET 2015
 * @generated */
public class Document_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Document_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Document_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Document(addr, Document_Type.this);
  			   Document_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Document(addr, Document_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Document.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("types.Document");
 
  /** @generated */
  final Feature casFeat_documentName;
  /** @generated */
  final int     casFeatCode_documentName;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getDocumentName(int addr) {
        if (featOkTst && casFeat_documentName == null)
      jcas.throwFeatMissing("documentName", "types.Document");
    return ll_cas.ll_getStringValue(addr, casFeatCode_documentName);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDocumentName(int addr, String v) {
        if (featOkTst && casFeat_documentName == null)
      jcas.throwFeatMissing("documentName", "types.Document");
    ll_cas.ll_setStringValue(addr, casFeatCode_documentName, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Document_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_documentName = jcas.getRequiredFeatureDE(casType, "documentName", "uima.cas.String", featOkTst);
    casFeatCode_documentName  = (null == casFeat_documentName) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_documentName).getCode();

  }
}



    