
/* First created by JCasGen Thu Dec 17 15:31:38 CET 2015 */
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
 * Updated by JCasGen Thu Dec 17 16:29:38 CET 2015
 * @generated */
public class Features_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Features_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Features_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Features(addr, Features_Type.this);
  			   Features_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Features(addr, Features_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Features.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("types.Features");
 
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
      jcas.throwFeatMissing("tf", "types.Features");
    return ll_cas.ll_getIntValue(addr, casFeatCode_tf);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTf(int addr, int v) {
        if (featOkTst && casFeat_tf == null)
      jcas.throwFeatMissing("tf", "types.Features");
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
      jcas.throwFeatMissing("df", "types.Features");
    return ll_cas.ll_getIntValue(addr, casFeatCode_df);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDf(int addr, int v) {
        if (featOkTst && casFeat_df == null)
      jcas.throwFeatMissing("df", "types.Features");
    ll_cas.ll_setIntValue(addr, casFeatCode_df, v);}
    
  
 
  /** @generated */
  final Feature casFeat_idf;
  /** @generated */
  final int     casFeatCode_idf;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getIdf(int addr) {
        if (featOkTst && casFeat_idf == null)
      jcas.throwFeatMissing("idf", "types.Features");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_idf);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setIdf(int addr, double v) {
        if (featOkTst && casFeat_idf == null)
      jcas.throwFeatMissing("idf", "types.Features");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_idf, v);}
    
  
 
  /** @generated */
  final Feature casFeat_tfidf;
  /** @generated */
  final int     casFeatCode_tfidf;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public double getTfidf(int addr) {
        if (featOkTst && casFeat_tfidf == null)
      jcas.throwFeatMissing("tfidf", "types.Features");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_tfidf);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTfidf(int addr, double v) {
        if (featOkTst && casFeat_tfidf == null)
      jcas.throwFeatMissing("tfidf", "types.Features");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_tfidf, v);}
    
  
 
  /** @generated */
  final Feature casFeat_first_occurrence;
  /** @generated */
  final int     casFeatCode_first_occurrence;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getFirst_occurrence(int addr) {
        if (featOkTst && casFeat_first_occurrence == null)
      jcas.throwFeatMissing("first_occurrence", "types.Features");
    return ll_cas.ll_getIntValue(addr, casFeatCode_first_occurrence);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFirst_occurrence(int addr, int v) {
        if (featOkTst && casFeat_first_occurrence == null)
      jcas.throwFeatMissing("first_occurrence", "types.Features");
    ll_cas.ll_setIntValue(addr, casFeatCode_first_occurrence, v);}
    
  
 
  /** @generated */
  final Feature casFeat_last_occurrence;
  /** @generated */
  final int     casFeatCode_last_occurrence;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getLast_occurrence(int addr) {
        if (featOkTst && casFeat_last_occurrence == null)
      jcas.throwFeatMissing("last_occurrence", "types.Features");
    return ll_cas.ll_getIntValue(addr, casFeatCode_last_occurrence);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setLast_occurrence(int addr, int v) {
        if (featOkTst && casFeat_last_occurrence == null)
      jcas.throwFeatMissing("last_occurrence", "types.Features");
    ll_cas.ll_setIntValue(addr, casFeatCode_last_occurrence, v);}
    
  
 
  /** @generated */
  final Feature casFeat_spread;
  /** @generated */
  final int     casFeatCode_spread;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSpread(int addr) {
        if (featOkTst && casFeat_spread == null)
      jcas.throwFeatMissing("spread", "types.Features");
    return ll_cas.ll_getIntValue(addr, casFeatCode_spread);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSpread(int addr, int v) {
        if (featOkTst && casFeat_spread == null)
      jcas.throwFeatMissing("spread", "types.Features");
    ll_cas.ll_setIntValue(addr, casFeatCode_spread, v);}
    
  
 
  /** @generated */
  final Feature casFeat_class_;
  /** @generated */
  final int     casFeatCode_class_;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getClass_(int addr) {
        if (featOkTst && casFeat_class_ == null)
      jcas.throwFeatMissing("class_", "types.Features");
    return ll_cas.ll_getIntValue(addr, casFeatCode_class_);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setClass_(int addr, int v) {
        if (featOkTst && casFeat_class_ == null)
      jcas.throwFeatMissing("class_", "types.Features");
    ll_cas.ll_setIntValue(addr, casFeatCode_class_, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Features_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_tf = jcas.getRequiredFeatureDE(casType, "tf", "uima.cas.Integer", featOkTst);
    casFeatCode_tf  = (null == casFeat_tf) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tf).getCode();

 
    casFeat_df = jcas.getRequiredFeatureDE(casType, "df", "uima.cas.Integer", featOkTst);
    casFeatCode_df  = (null == casFeat_df) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_df).getCode();

 
    casFeat_idf = jcas.getRequiredFeatureDE(casType, "idf", "uima.cas.Double", featOkTst);
    casFeatCode_idf  = (null == casFeat_idf) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_idf).getCode();

 
    casFeat_tfidf = jcas.getRequiredFeatureDE(casType, "tfidf", "uima.cas.Double", featOkTst);
    casFeatCode_tfidf  = (null == casFeat_tfidf) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tfidf).getCode();

 
    casFeat_first_occurrence = jcas.getRequiredFeatureDE(casType, "first_occurrence", "uima.cas.Integer", featOkTst);
    casFeatCode_first_occurrence  = (null == casFeat_first_occurrence) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_first_occurrence).getCode();

 
    casFeat_last_occurrence = jcas.getRequiredFeatureDE(casType, "last_occurrence", "uima.cas.Integer", featOkTst);
    casFeatCode_last_occurrence  = (null == casFeat_last_occurrence) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_last_occurrence).getCode();

 
    casFeat_spread = jcas.getRequiredFeatureDE(casType, "spread", "uima.cas.Integer", featOkTst);
    casFeatCode_spread  = (null == casFeat_spread) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_spread).getCode();

 
    casFeat_class_ = jcas.getRequiredFeatureDE(casType, "class_", "uima.cas.Integer", featOkTst);
    casFeatCode_class_  = (null == casFeat_class_) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_class_).getCode();

  }
}



    