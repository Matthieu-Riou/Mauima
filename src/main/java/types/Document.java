

/* First created by JCasGen Fri Dec 18 15:02:47 CET 2015 */
package types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Dec 24 00:46:12 CET 2015
 * XML source: /home/matthieu/Documents/Cours/M2/Dev/Mauima/src/main/resources/types/typeSystemDescriptor.xml
 * @generated */
public class Document extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Document.class);
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
  protected Document() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Document(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Document(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Document(JCas jcas, int begin, int end) {
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
  //* Feature: documentName

  /** getter for documentName - gets 
   * @generated
   * @return value of the feature 
   */
  public String getDocumentName() {
    if (Document_Type.featOkTst && ((Document_Type)jcasType).casFeat_documentName == null)
      jcasType.jcas.throwFeatMissing("documentName", "types.Document");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Document_Type)jcasType).casFeatCode_documentName);}
    
  /** setter for documentName - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDocumentName(String v) {
    if (Document_Type.featOkTst && ((Document_Type)jcasType).casFeat_documentName == null)
      jcasType.jcas.throwFeatMissing("documentName", "types.Document");
    jcasType.ll_cas.ll_setStringValue(addr, ((Document_Type)jcasType).casFeatCode_documentName, v);}    
   
    
  //*--------------*
  //* Feature: hasValidTopics

  /** getter for hasValidTopics - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getHasValidTopics() {
    if (Document_Type.featOkTst && ((Document_Type)jcasType).casFeat_hasValidTopics == null)
      jcasType.jcas.throwFeatMissing("hasValidTopics", "types.Document");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Document_Type)jcasType).casFeatCode_hasValidTopics);}
    
  /** setter for hasValidTopics - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setHasValidTopics(boolean v) {
    if (Document_Type.featOkTst && ((Document_Type)jcasType).casFeat_hasValidTopics == null)
      jcasType.jcas.throwFeatMissing("hasValidTopics", "types.Document");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Document_Type)jcasType).casFeatCode_hasValidTopics, v);}    
  }

    