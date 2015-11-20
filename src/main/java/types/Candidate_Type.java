
/* First created by JCasGen Fri Nov 20 16:31:48 CET 2015 */
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

/** candidate
 * Updated by JCasGen Fri Nov 20 16:31:52 CET 2015
 * @generated */
public class Candidate_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Candidate_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Candidate_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Candidate(addr, Candidate_Type.this);
  			   Candidate_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Candidate(addr, Candidate_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Candidate.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("types.Candidate");
 
  /** @generated */
  final Feature casFeat_frequency;
  /** @generated */
  final int     casFeatCode_frequency;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getFrequency(int addr) {
        if (featOkTst && casFeat_frequency == null)
      jcas.throwFeatMissing("frequency", "types.Candidate");
    return ll_cas.ll_getIntValue(addr, casFeatCode_frequency);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFrequency(int addr, int v) {
        if (featOkTst && casFeat_frequency == null)
      jcas.throwFeatMissing("frequency", "types.Candidate");
    ll_cas.ll_setIntValue(addr, casFeatCode_frequency, v);}
    
  
 
  /** @generated */
  final Feature casFeat_last_occ;
  /** @generated */
  final int     casFeatCode_last_occ;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getLast_occ(int addr) {
        if (featOkTst && casFeat_last_occ == null)
      jcas.throwFeatMissing("last_occ", "types.Candidate");
    return ll_cas.ll_getIntValue(addr, casFeatCode_last_occ);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setLast_occ(int addr, int v) {
        if (featOkTst && casFeat_last_occ == null)
      jcas.throwFeatMissing("last_occ", "types.Candidate");
    ll_cas.ll_setIntValue(addr, casFeatCode_last_occ, v);}
    
  
 
  /** @generated */
  final Feature casFeat_name;
  /** @generated */
  final int     casFeatCode_name;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getName(int addr) {
        if (featOkTst && casFeat_name == null)
      jcas.throwFeatMissing("name", "types.Candidate");
    return ll_cas.ll_getStringValue(addr, casFeatCode_name);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setName(int addr, String v) {
        if (featOkTst && casFeat_name == null)
      jcas.throwFeatMissing("name", "types.Candidate");
    ll_cas.ll_setStringValue(addr, casFeatCode_name, v);}
    
  
 
  /** @generated */
  final Feature casFeat_tf;
  /** @generated */
  final int     casFeatCode_tf;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getTf(int addr) {
        if (featOkTst && casFeat_tf == null)
      jcas.throwFeatMissing("tf", "types.Candidate");
    return ll_cas.ll_getIntValue(addr, casFeatCode_tf);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTf(int addr, int v) {
        if (featOkTst && casFeat_tf == null)
      jcas.throwFeatMissing("tf", "types.Candidate");
    ll_cas.ll_setIntValue(addr, casFeatCode_tf, v);}
    
  
 
  /** @generated */
  final Feature casFeat_df;
  /** @generated */
  final int     casFeatCode_df;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getDf(int addr) {
        if (featOkTst && casFeat_df == null)
      jcas.throwFeatMissing("df", "types.Candidate");
    return ll_cas.ll_getIntValue(addr, casFeatCode_df);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDf(int addr, int v) {
        if (featOkTst && casFeat_df == null)
      jcas.throwFeatMissing("df", "types.Candidate");
    ll_cas.ll_setIntValue(addr, casFeatCode_df, v);}
    
  
 
  /** @generated */
  final Feature casFeat_idf;
  /** @generated */
  final int     casFeatCode_idf;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getIdf(int addr) {
        if (featOkTst && casFeat_idf == null)
      jcas.throwFeatMissing("idf", "types.Candidate");
    return ll_cas.ll_getIntValue(addr, casFeatCode_idf);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setIdf(int addr, int v) {
        if (featOkTst && casFeat_idf == null)
      jcas.throwFeatMissing("idf", "types.Candidate");
    ll_cas.ll_setIntValue(addr, casFeatCode_idf, v);}
    
  
 
  /** @generated */
  final Feature casFeat_first_occ;
  /** @generated */
  final int     casFeatCode_first_occ;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getFirst_occ(int addr) {
        if (featOkTst && casFeat_first_occ == null)
      jcas.throwFeatMissing("first_occ", "types.Candidate");
    return ll_cas.ll_getIntValue(addr, casFeatCode_first_occ);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFirst_occ(int addr, int v) {
        if (featOkTst && casFeat_first_occ == null)
      jcas.throwFeatMissing("first_occ", "types.Candidate");
    ll_cas.ll_setIntValue(addr, casFeatCode_first_occ, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Candidate_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_frequency = jcas.getRequiredFeatureDE(casType, "frequency", "uima.cas.Integer", featOkTst);
    casFeatCode_frequency  = (null == casFeat_frequency) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_frequency).getCode();

 
    casFeat_last_occ = jcas.getRequiredFeatureDE(casType, "last_occ", "uima.cas.Integer", featOkTst);
    casFeatCode_last_occ  = (null == casFeat_last_occ) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_last_occ).getCode();

 
    casFeat_name = jcas.getRequiredFeatureDE(casType, "name", "uima.cas.String", featOkTst);
    casFeatCode_name  = (null == casFeat_name) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_name).getCode();

 
    casFeat_tf = jcas.getRequiredFeatureDE(casType, "tf", "uima.cas.Integer", featOkTst);
    casFeatCode_tf  = (null == casFeat_tf) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tf).getCode();

 
    casFeat_df = jcas.getRequiredFeatureDE(casType, "df", "uima.cas.Integer", featOkTst);
    casFeatCode_df  = (null == casFeat_df) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_df).getCode();

 
    casFeat_idf = jcas.getRequiredFeatureDE(casType, "idf", "uima.cas.Integer", featOkTst);
    casFeatCode_idf  = (null == casFeat_idf) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_idf).getCode();

 
    casFeat_first_occ = jcas.getRequiredFeatureDE(casType, "first_occ", "uima.cas.Integer", featOkTst);
    casFeatCode_first_occ  = (null == casFeat_first_occ) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_first_occ).getCode();

  }
}



    