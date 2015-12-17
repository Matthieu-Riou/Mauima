

/* First created by JCasGen Fri Nov 20 16:31:48 CET 2015 */
package types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.StringList;
import org.apache.uima.jcas.tcas.Annotation;


/** candidate
 * Updated by JCasGen Thu Dec 17 16:00:48 CET 2015
 * XML source: /comptes/E103642E/Documents/2015-2016/DeveloppementLogiciel/atal2.dlp/workspace/Mauima/src/main/resources/types/typeSystemDescriptor.xml
 * @generated */
public class Candidate extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Candidate.class);
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
  protected Candidate() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Candidate(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Candidate(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Candidate(JCas jcas, int begin, int end) {
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
  //* Feature: frequency

  /** getter for frequency - gets the frequency of the candidate
   * @generated
   * @return value of the feature 
   */
  public int getFrequency() {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_frequency == null)
      jcasType.jcas.throwFeatMissing("frequency", "types.Candidate");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Candidate_Type)jcasType).casFeatCode_frequency);}
    
  /** setter for frequency - sets the frequency of the candidate 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFrequency(int v) {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_frequency == null)
      jcasType.jcas.throwFeatMissing("frequency", "types.Candidate");
    jcasType.ll_cas.ll_setIntValue(addr, ((Candidate_Type)jcasType).casFeatCode_frequency, v);}    
   
    
  //*--------------*
  //* Feature: last_occ

  /** getter for last_occ - gets last occurence of the candidate
   * @generated
   * @return value of the feature 
   */
  public int getLast_occ() {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_last_occ == null)
      jcasType.jcas.throwFeatMissing("last_occ", "types.Candidate");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Candidate_Type)jcasType).casFeatCode_last_occ);}
    
  /** setter for last_occ - sets last occurence of the candidate 
   * @generated
   * @param v value to set into the feature 
   */
  public void setLast_occ(int v) {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_last_occ == null)
      jcasType.jcas.throwFeatMissing("last_occ", "types.Candidate");
    jcasType.ll_cas.ll_setIntValue(addr, ((Candidate_Type)jcasType).casFeatCode_last_occ, v);}    
   
    
  //*--------------*
  //* Feature: name

  /** getter for name - gets the topic title of the candidate
   * @generated
   * @return value of the feature 
   */
  public String getName() {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "types.Candidate");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Candidate_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets the topic title of the candidate 
   * @generated
   * @param v value to set into the feature 
   */
  public void setName(String v) {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "types.Candidate");
    jcasType.ll_cas.ll_setStringValue(addr, ((Candidate_Type)jcasType).casFeatCode_name, v);}    
   
    
  //*--------------*
  //* Feature: tf

  /** getter for tf - gets term frequency
   * @generated
   * @return value of the feature 
   */
  public int getTf() {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_tf == null)
      jcasType.jcas.throwFeatMissing("tf", "types.Candidate");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Candidate_Type)jcasType).casFeatCode_tf);}
    
  /** setter for tf - sets term frequency 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTf(int v) {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_tf == null)
      jcasType.jcas.throwFeatMissing("tf", "types.Candidate");
    jcasType.ll_cas.ll_setIntValue(addr, ((Candidate_Type)jcasType).casFeatCode_tf, v);}    
   
    
  //*--------------*
  //* Feature: df

  /** getter for df - gets document frequency
   * @generated
   * @return value of the feature 
   */
  public int getDf() {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_df == null)
      jcasType.jcas.throwFeatMissing("df", "types.Candidate");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Candidate_Type)jcasType).casFeatCode_df);}
    
  /** setter for df - sets document frequency 
   * @generated
   * @param v value to set into the feature 
   */
  public void setDf(int v) {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_df == null)
      jcasType.jcas.throwFeatMissing("df", "types.Candidate");
    jcasType.ll_cas.ll_setIntValue(addr, ((Candidate_Type)jcasType).casFeatCode_df, v);}    
   
    
  //*--------------*
  //* Feature: idf

  /** getter for idf - gets inverse document frequency
   * @generated
   * @return value of the feature 
   */
  public double getIdf() {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_idf == null)
      jcasType.jcas.throwFeatMissing("idf", "types.Candidate");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((Candidate_Type)jcasType).casFeatCode_idf);}
    
  /** setter for idf - sets inverse document frequency 
   * @generated
   * @param v value to set into the feature 
   */
  public void setIdf(double v) {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_idf == null)
      jcasType.jcas.throwFeatMissing("idf", "types.Candidate");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((Candidate_Type)jcasType).casFeatCode_idf, v);}    
   
    
  //*--------------*
  //* Feature: first_occ

  /** getter for first_occ - gets first occurence of the candidate
   * @generated
   * @return value of the feature 
   */
  public int getFirst_occ() {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_first_occ == null)
      jcasType.jcas.throwFeatMissing("first_occ", "types.Candidate");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Candidate_Type)jcasType).casFeatCode_first_occ);}
    
  /** setter for first_occ - sets first occurence of the candidate 
   * @generated
   * @param v value to set into the feature 
   */
  public void setFirst_occ(int v) {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_first_occ == null)
      jcasType.jcas.throwFeatMissing("first_occ", "types.Candidate");
    jcasType.ll_cas.ll_setIntValue(addr, ((Candidate_Type)jcasType).casFeatCode_first_occ, v);}    
   
    
  //*--------------*
  //* Feature: effective_full_form

  /** getter for effective_full_form - gets 
   * @generated
   * @return value of the feature 
   */
  public String getEffective_full_form() {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_effective_full_form == null)
      jcasType.jcas.throwFeatMissing("effective_full_form", "types.Candidate");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Candidate_Type)jcasType).casFeatCode_effective_full_form);}
    
  /** setter for effective_full_form - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setEffective_full_form(String v) {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_effective_full_form == null)
      jcasType.jcas.throwFeatMissing("effective_full_form", "types.Candidate");
    jcasType.ll_cas.ll_setStringValue(addr, ((Candidate_Type)jcasType).casFeatCode_effective_full_form, v);}    
   
    
  //*--------------*
  //* Feature: full_forms

  /** getter for full_forms - gets 
   * @generated
   * @return value of the feature 
   */
  public StringList getFull_forms() {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_full_forms == null)
      jcasType.jcas.throwFeatMissing("full_forms", "types.Candidate");
    return (StringList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Candidate_Type)jcasType).casFeatCode_full_forms)));}
    
  /** setter for full_forms - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFull_forms(StringList v) {
    if (Candidate_Type.featOkTst && ((Candidate_Type)jcasType).casFeat_full_forms == null)
      jcasType.jcas.throwFeatMissing("full_forms", "types.Candidate");
    jcasType.ll_cas.ll_setRefValue(addr, ((Candidate_Type)jcasType).casFeatCode_full_forms, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    