

/* First created by JCasGen Thu Dec 17 15:31:38 CET 2015 */
package types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Dec 17 15:31:38 CET 2015
 * XML source: /comptes/E103642E/Documents/2015-2016/DeveloppementLogiciel/atal2.dlp/workspace/Mauima/src/main/resources/types/typeSystemDescriptor.xml
 * @generated */
public class Features extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Features.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Features() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Features(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Features(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Features(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: tf

  /** getter for tf - gets 
   * @generated
   * @return value of the feature 
   */
  public int getTf() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_tf == null)
      jcasType.jcas.throwFeatMissing("tf", "types.Features");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Features_Type)jcasType).casFeatCode_tf);}
    
  /** setter for tf - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTf(int v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_tf == null)
      jcasType.jcas.throwFeatMissing("tf", "types.Features");
    jcasType.ll_cas.ll_setIntValue(addr, ((Features_Type)jcasType).casFeatCode_tf, v);}    
   
    
  //*--------------*
  //* Feature: df

  /** getter for df - gets 
   * @generated
   * @return value of the feature 
   */
  public int getDf() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_df == null)
      jcasType.jcas.throwFeatMissing("df", "types.Features");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Features_Type)jcasType).casFeatCode_df);}
    
  /** setter for df - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDf(int v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_df == null)
      jcasType.jcas.throwFeatMissing("df", "types.Features");
    jcasType.ll_cas.ll_setIntValue(addr, ((Features_Type)jcasType).casFeatCode_df, v);}    
   
    
  //*--------------*
  //* Feature: idf

  /** getter for idf - gets 
   * @generated
   * @return value of the feature 
   */
  public double getIdf() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_idf == null)
      jcasType.jcas.throwFeatMissing("idf", "types.Features");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((Features_Type)jcasType).casFeatCode_idf);}
    
  /** setter for idf - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setIdf(double v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_idf == null)
      jcasType.jcas.throwFeatMissing("idf", "types.Features");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((Features_Type)jcasType).casFeatCode_idf, v);}    
   
    
  //*--------------*
  //* Feature: tfidf

  /** getter for tfidf - gets 
   * @generated
   * @return value of the feature 
   */
  public double getTfidf() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_tfidf == null)
      jcasType.jcas.throwFeatMissing("tfidf", "types.Features");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((Features_Type)jcasType).casFeatCode_tfidf);}
    
  /** setter for tfidf - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTfidf(double v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_tfidf == null)
      jcasType.jcas.throwFeatMissing("tfidf", "types.Features");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((Features_Type)jcasType).casFeatCode_tfidf, v);}    
   
    
  //*--------------*
  //* Feature: first_occurrence

  /** getter for first_occurrence - gets 
   * @generated
   * @return value of the feature 
   */
  public int getFirst_occurrence() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_first_occurrence == null)
      jcasType.jcas.throwFeatMissing("first_occurrence", "types.Features");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Features_Type)jcasType).casFeatCode_first_occurrence);}
    
  /** setter for first_occurrence - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFirst_occurrence(int v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_first_occurrence == null)
      jcasType.jcas.throwFeatMissing("first_occurrence", "types.Features");
    jcasType.ll_cas.ll_setIntValue(addr, ((Features_Type)jcasType).casFeatCode_first_occurrence, v);}    
   
    
  //*--------------*
  //* Feature: last_occurrence

  /** getter for last_occurrence - gets 
   * @generated
   * @return value of the feature 
   */
  public int getLast_occurrence() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_last_occurrence == null)
      jcasType.jcas.throwFeatMissing("last_occurrence", "types.Features");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Features_Type)jcasType).casFeatCode_last_occurrence);}
    
  /** setter for last_occurrence - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setLast_occurrence(int v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_last_occurrence == null)
      jcasType.jcas.throwFeatMissing("last_occurrence", "types.Features");
    jcasType.ll_cas.ll_setIntValue(addr, ((Features_Type)jcasType).casFeatCode_last_occurrence, v);}    
   
    
  //*--------------*
  //* Feature: spread

  /** getter for spread - gets 
   * @generated
   * @return value of the feature 
   */
  public int getSpread() {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_spread == null)
      jcasType.jcas.throwFeatMissing("spread", "types.Features");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Features_Type)jcasType).casFeatCode_spread);}
    
  /** setter for spread - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSpread(int v) {
    if (Features_Type.featOkTst && ((Features_Type)jcasType).casFeat_spread == null)
      jcasType.jcas.throwFeatMissing("spread", "types.Features");
    jcasType.ll_cas.ll_setIntValue(addr, ((Features_Type)jcasType).casFeatCode_spread, v);}    
  }

    