//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    ExceptionalClimbingTester.java
// Course:   CS 300 Fall 2021
//
// Author:   Enze Ge
// Email:    ege2@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    None
// Partner Email:   None
// Partner Lecturer's Name: None
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  Program 03.pdf
//                  The tests are based on this file
//
///////////////////////////////////////////////////////////////////////////////

import java.util.zip.DataFormatException;

/**
 * A tester for functionality of ExceptionalClimbing class
 *
 */
public class ExceptionalClimbingTester {

  /**
   * Check ExceptionalClimbing class functionality
   * 
   * @param args command line arguments
   */
  public static void main(String[] args) {
    runAllTests();
  }
  
  /**
   * Test whether ExceptionalClimbing is implemented properly
   * 
   * @return true if all tests passed, false otherwise
   */
  public static boolean runAllTests() {
    if(!(testSendClimb() && testFailClimb() && testGetStats() && testGetHistogram())) {
      System.err.println("Some tests failed");
      return false;
    }
    System.out.println("All tests passed!");
    return true;
  }
  
  /**
   * Check whether sendClimb() works as expected
   * 
   * @return true if this test passed, false other wise
   */
  public static boolean testSendClimb() {
    // valid input
    int expectNum = 3;
    String[] partialArr = {"V1", "V3", null, null, null};
    int numPartialArr = 2;
    try {
      if(ExceptionalClimbing.sendClimb(partialArr, numPartialArr, "V4")!=expectNum 
          || partialArr.length != 5
          || !(partialArr[0].equals("V1") && partialArr[1].equals("V3")
              && partialArr[2].equals("V4") && partialArr[3]==null && partialArr[4]==null)) {
        return false;
      };
    } catch(Exception e){
      return false;
    }
    expectNum = 4;
    String[] partialArr2 = {"V1", "V1", "V1", null};
    int numPartialArr2 = 3;
    try {
      if(ExceptionalClimbing.sendClimb(partialArr2, numPartialArr2, "V4")!=expectNum 
          || partialArr2.length != 4
          || !(partialArr2[0].equals("V1") && partialArr2[1].equals("V1") 
              && partialArr2[2].equals("V1") && partialArr2[3].equals("V4"))) {
        return false;
      };
    } catch(Exception e){
      return false;
    }
    
    // invalid grades
    String invalidGrade = "V10.2";
    String expected = invalidGrade + " is not a valid grade";
    try {
      ExceptionalClimbing.sendClimb(partialArr, numPartialArr, invalidGrade);
      return false;
    } catch(IllegalArgumentException iae) {
      if(!expected.equals(iae.getMessage())) return false;
    } catch(Exception e) {
      return false;
    }
    
    // full array
    String[] fullArr = {"V1", "V2", "V3", "V5", "V5"};
    int numFullArr = 5;
    expected = "cannot add new value to full length 5 array";
    try {
      ExceptionalClimbing.sendClimb(fullArr, numFullArr, "V1");
      return false;
    } catch(IllegalArgumentException iae) {
      if(!expected.equals(iae.getMessage())) return false;
    } catch(Exception e) {
      return false;
    }
    
    // contains null elements
    String[] nullArr = {"V2", null, "V3", null, null};
    int numNullArr = 3;
    expected = "invalid oversize array";
    try {
      ExceptionalClimbing.sendClimb(nullArr, numNullArr, "V1");
      return false;
    } catch(DataFormatException dfe) {
      if(!expected.equals(dfe.getMessage())) return false;
    } catch(Exception e) {
      return false;
    }
    
    // Combination of error conditions
    expected = invalidGrade + " is not a valid grade";
    try {
      ExceptionalClimbing.sendClimb(partialArr, numPartialArr, "V4");
      ExceptionalClimbing.sendClimb(partialArr, numPartialArr, invalidGrade);
      ExceptionalClimbing.sendClimb(fullArr, numFullArr, "V1");
      ExceptionalClimbing.sendClimb(nullArr, numNullArr, "V1");
      return false;
    } catch (IllegalArgumentException iae) {
      if(!expected.equals(iae.getMessage())) return false;
    } catch(DataFormatException dfe) {
      return false;
    } catch(Exception e) {
      return false;
    }
    
    System.out.println("test passed: testSendClimb()");
    
    return true;
  }
  
  /**
   * Check whether failClimb() works as expected
   * 
   * @return true if this test passed, false other wise
   */
  public static boolean testFailClimb() {
    // valid input
    int expectNum = 3;
    String[] partialArr = {"V1", "V3", null, null, null};
    int numPartialArr = 2;
    try {
      if(ExceptionalClimbing.failClimb(partialArr, numPartialArr, "V4")!=expectNum 
          || partialArr.length != 5
          || !(partialArr[0].equals("V1") && partialArr[1].equals("V3") && partialArr[2].equals("V4") 
              && partialArr[3]==null && partialArr[4]==null)) {
          return false;
      };
    } catch(Exception e){
      return false;
    }
    expectNum = 4;
    String[] partialArr2 = {"V1", "V1", "V1", null};
    int numPartialArr2 = 3;
    try {
      if(ExceptionalClimbing.failClimb(partialArr2, numPartialArr2, "V4")!=expectNum 
          || partialArr2.length != 4
          || !(partialArr2[0].equals("V1") && partialArr2[1].equals("V1") 
              && partialArr2[2].equals("V1") && partialArr2[3].equals("V4"))) {
        return false;
      };
    } catch(Exception e){
      return false;
    }
    
    // invalid grades
    String invalidGrade = "V10.2";
    String expected = invalidGrade + " is not a valid grade";
    try {
      ExceptionalClimbing.failClimb(partialArr, numPartialArr, invalidGrade);
      return false;
    } catch(IllegalArgumentException iae) {
      if(!expected.equals(iae.getMessage())) return false;
    } catch(Exception e) {
      return false;
    }
    
    // full array
    String[] fullArr = {"V1", "V2", "V3", "V5", "V5"};
    int numFullArr = 5;
    expected = "cannot add new value to full length 5 array";
    try {
      ExceptionalClimbing.failClimb(fullArr, numFullArr, "V1");
      return false;
    } catch(IllegalArgumentException iae) {
      if(!expected.equals(iae.getMessage())) return false;
    } catch(Exception e) {
      return false;
    }
    
    // contains null elements
    String[] nullArr = {"V2", null, "V3", null, null};
    int numNullArr = 3;
    expected = "invalid oversize array";
    try {
      ExceptionalClimbing.failClimb(nullArr, numNullArr, "V1");
      return false;
    } catch(DataFormatException dfe) {
      if(!expected.equals(dfe.getMessage())) return false;
    } catch(Exception e) {
      return false;
    }
    
    // Combination of error conditions
    expected = invalidGrade + " is not a valid grade";
    try {
      ExceptionalClimbing.failClimb(partialArr, numPartialArr, "V4");
      ExceptionalClimbing.failClimb(partialArr, numPartialArr, invalidGrade);
      ExceptionalClimbing.failClimb(fullArr, numFullArr, "V1");
      ExceptionalClimbing.failClimb(nullArr, numNullArr, "V1");
      return false;
    } catch (IllegalArgumentException iae) {
      if(!expected.equals(iae.getMessage())) return false;
    } catch(DataFormatException dfe) {
      return false;
    } catch(Exception e) {
      return false;
    }
    
    System.out.println("test passed: testFailClimb()");
    
    return true;
  }
  
  /**
   * Check whether getStats() works as expected
   * 
   * @return true if this test passed, false other wise
   */
  public static boolean testGetStats() {
    // valid input
    String expected = "send: 2.0\nfail: --";
    String[] valSendArr = {"V1", "V3", "V2"};
    int numValSend = 3;
    String[] valFailArr = {null, null, null, null};
    int numValFail = 0;
    try {
      if(!expected.equals(ExceptionalClimbing.getStats(valSendArr,
                            numValSend, valFailArr, numValFail, 3))) {
        return false; 
      }
    } catch(Exception e) {
      return false;
    }
    expected = "send: 1.5\nfail: 3.5";
    String[] valSendArr2 = {"V1", "V2"};
    String[] valFailArr2 = {"V3", "V4"};
    try {
      if(!expected.equals(ExceptionalClimbing.getStats(valSendArr2,
          2, valFailArr2, 2, 2))) {
        return false; 
      }
    } catch(Exception e) {
      return false;
    }
    
    // both empty arrays cause RuntimeException
    expected = "no climbs provided";
    try {
      ExceptionalClimbing.getStats(valSendArr, 0, valFailArr, 0, 3);
      return false;
    } catch(RuntimeException re) {
      if(!expected.equals(re.getMessage())) return false;
    } catch(Exception e) {
      return false;
    }
    
    // 0 hisotryLength causes IllegalArgumentException
    expected = "0 is not a valid history length";
    try {
      ExceptionalClimbing.getStats(valSendArr, numValSend, valFailArr, numValFail, 0);
      return false;
    } catch(IllegalArgumentException iae) {
      if(!expected.equals(iae.getMessage())) return false;
    } catch(Exception e) {
      return false;
    }
    // negative hisotryLength causes IllegalArgumentException
    expected = "-1 is not a valid history length";
    try {
      ExceptionalClimbing.getStats(valSendArr, numValSend, valFailArr, numValFail, -1);
      return false;
    } catch(IllegalArgumentException iae) {
      if(!expected.equals(iae.getMessage())) return false;
    } catch(Exception e) {
      return false;
    }
    
    // combination of errors causes the first applicable exception
    expected = "no climbs provided";
    try{
      ExceptionalClimbing.getStats(valSendArr, 0, valFailArr, 0, 3);
      ExceptionalClimbing.getStats(valSendArr, numValSend, valFailArr, numValFail, 0);
      ExceptionalClimbing.getStats(valSendArr, numValSend, valFailArr, numValFail, -1);
      return false;
    } catch(RuntimeException re) {
      if(!expected.equals(re.getMessage())) return false;
    } catch(Exception e) {
      return false;
    }
    
    System.out.println("test passed: testGetStats()");
    return true;
  }
  
  /**
   * Check whether getHistogram() works as expected
   * 
   * @return true if this test passed, false other wise
   */
  public static boolean testGetHistogram() {
    // valid input
    String expected = "V0: +\nV1: +\nV2: +\nV3: +\nV4:\nV5: + +";
    String[] valSend = {"V0", "V1", "V2", "V3", "V5", "V5", null, null};
    int numValSend = 6;
    String[] valFail = {null, null, null};
    int numValFail = 0;
    try {
      if(!expected.equals(ExceptionalClimbing.getHistogram(valSend, numValSend, 
                                                           valFail, numValFail))) {
        return false;
      }
    } catch(Exception e) {
      return false;
    }
    expected = "V0: +\nV1: - +\nV2: -";
    String[] valSend2 = {"V0", "V1"};
    String[] valFail2 = {"V1", "V2"};
    try {
      if(!expected.equals(ExceptionalClimbing.getHistogram(valSend2, 2, valFail2, 2))){
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    
    // both arrays empty cause RuntimeException
    expected = "no climbs provided";
    try {
      ExceptionalClimbing.getHistogram(valSend, 0, valFail, 0);
      return false;
    } catch(RuntimeException re) {
      if(!expected.equals(re.getMessage())) return false;
    } catch(Exception e) {
      return false;
    }
    
    
    System.out.println("test passed: testGetHistogram()");
    return true;
  }
  
}
