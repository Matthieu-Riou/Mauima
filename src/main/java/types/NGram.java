

/* First created by JCasGen Fri Nov 20 15:54:30 CET 2015 */
package types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** ngram type
 * Updated by JCasGen Thu Dec 17 16:29:38 CET 2015
 * XML source: /comptes/E103642E/Documents/2015-2016/DeveloppementLogiciel/atal2.dlp/workspace/Mauima/src/main/resources/types/typeSystemDescriptor.xml
 * @generated */
public class NGram extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NGram.class);
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
  protected NGram() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public NGram(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public NGram(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public NGram(JCas jcas, int begin, int end) {
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
  //* Feature: length

  /** getter for length - gets length of the NGram: 2 for bigram, 3 for trigram, ...
   * @generated
   * @return value of the feature 
   */
  public byte getLength() {
    if (NGram_Type.featOkTst && ((NGram_Type)jcasType).casFeat_length == null)
      jcasType.jcas.throwFeatMissing("length", "types.NGram");
    return jcasType.ll_cas.ll_getByteValue(addr, ((NGram_Type)jcasType).casFeatCode_length);}
    
  /** setter for length - sets length of the NGram: 2 for bigram, 3 for trigram, ... 
   * @generated
   * @param v value to set into the feature 
   */
  public void setLength(byte v) {
    if (NGram_Type.featOkTst && ((NGram_Type)jcasType).casFeat_length == null)
      jcasType.jcas.throwFeatMissing("length", "types.NGram");
    jcasType.ll_cas.ll_setByteValue(addr, ((NGram_Type)jcasType).casFeatCode_length, v);}    
  }

    