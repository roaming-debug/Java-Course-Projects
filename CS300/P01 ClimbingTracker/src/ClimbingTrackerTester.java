//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    ClimbingTrackerTester.java
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
// Online Sources:  https://canvas.wisc.edu/courses/273590/quizzes/298699?modul
//                  e_item_id=4179124
//                  P01 Pre-Assignment Quiz helped me structure the test code.
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This Class contains all the test methods for ClimbingTracker Class and main 
 * functions to call runAllTests().
 *
 */
public class ClimbingTrackerTester {

    /** 
     * This method is used to call runAllTests()
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        runAllTests();
    }
    
    /**
     * Checks whether sendClimb() is implemented properly
     * 
     * @return true if sendClimb() functionality is verified, false otherwise.
     */
    public static boolean testSendClimb() {
        System.out.println("Test sendClimb():");
        String addGrade = "V3";
        
        
        // 1st test scenario: add grade to an empty array
        int expectedNum = 1;
        String[] emptyArr = new String[5];
        int numEmpArr = 0;
        int returnNum = ClimbingTracker.sendClimb(emptyArr, numEmpArr, addGrade);
        if(!(returnNum == expectedNum)) {
            return false;
        }
        System.out.println("1: Empty array test passed.");
        
        
        // 2nd test scenario: add grade to a partial array
        expectedNum = 3;
        String[] partialArr = {"V1", "V3", null, null, null};
        int numPartialArr = 2;
        returnNum = ClimbingTracker.sendClimb(partialArr, numPartialArr, addGrade);
        if(!(returnNum == expectedNum)) {
            return false;
        }
        System.out.println("2. Partial array test passed.");

        
        // 3rd test scenario: add grade to a full array
        expectedNum = 5;
        String[] fullArr = {"V1", "V3", "V4", "V5", "V6"};
        int numFullArr = 5;
        returnNum = ClimbingTracker.sendClimb(fullArr, numFullArr, addGrade);
        if(!(returnNum == expectedNum)) {
            return false;
        }
        System.out.println("3. Full array test passed.");
        
        
        // 4th test scenario: add invalid grade
        expectedNum = 3;
        String invalidGrade = "v1";
        String[] addInvalidArr = {"V1", "V3", "V6", null, null};
        int numInvalidArr = 3;
        returnNum = ClimbingTracker.sendClimb(addInvalidArr, numInvalidArr, invalidGrade);
        if(!(returnNum == expectedNum)) {
            return false;
        }
        System.out.println("4. Invalid grade test passed.\n");
        
        
        return true;
    }
    
    
    
    /**
     * Checks whether failClimb() works as expected
     * 
     * @return true if failClimb() functionality is verified, false otherwise
     */
    public static boolean testFailClimb() {
        System.out.println("Test failClimb():");
        String addGrade = "V3";
        
        
        // 1st test scenario: add grade to an empty array
        int expectedNum = 1;
        String[] emptyArr = new String[5];
        int numEmpArr = 0;
        int returnNum = ClimbingTracker.failClimb(emptyArr, numEmpArr, addGrade);
        if(!(returnNum == expectedNum)) {
            return false;
        }
        System.out.println("1: Empty array test passed.");
        
        
        // 2nd test scenario: add grade to a partial array
        expectedNum = 3;
        String[] partialArr = {"V1", "V3", null, null, null};
        int numPartialArr = 2;
        returnNum = ClimbingTracker.failClimb(partialArr, numPartialArr, addGrade);
        if(!(returnNum == expectedNum)) {
            return false;
        }
        System.out.println("2. Partial array test passed.");

        
        // 3rd test scenario: add grade to a full array
        expectedNum = 5;
        String[] fullArr = {"V1", "V3", "V4", "V5", "V6"};
        int numFullArr = 5;
        returnNum = ClimbingTracker.failClimb(fullArr, numFullArr, addGrade);
        if(!(returnNum == expectedNum)) {
            return false;
        }
        System.out.println("3. Full array test passed.");
        
        
        // 4th test scenario: add invalid grade
        expectedNum = 3;
       
        String[] addInvalidArr = {"V1", "V3", "V6", null, null};
        int numInvalidArr = 3;
        
        String invalidGrade1 = "v5";
        returnNum = ClimbingTracker.failClimb(addInvalidArr, numInvalidArr, invalidGrade1);
        if(!(returnNum == expectedNum)) {
            return false;
        }
        
        String invalidGrade2 = "V9";
        returnNum = ClimbingTracker.failClimb(addInvalidArr, numInvalidArr, invalidGrade2);
        if(!(returnNum == expectedNum)) {
            return false;
        }
        
        String invalidGrade3 = "Va";
        returnNum = ClimbingTracker.failClimb(addInvalidArr, numInvalidArr, invalidGrade3);
        if(!(returnNum == expectedNum)) {
            return false;
        }
        
        String invalidGrade4 = "V1b";
        returnNum = ClimbingTracker.failClimb(addInvalidArr, numInvalidArr, invalidGrade4);
        if(!(returnNum == expectedNum)) {
            return false;
        }
        
        System.out.println("4. Invalid grade test passed. \n");
        
        
        return true;
    }
    
    
    /**
     * Checks whether getStats() works as expected
     * 
     * @return true if getStats() functionality is verified, false otherwise
     */
    public static boolean testGetStats() {
        System.out.println("Test getStats():");
        
        // 1st test scenario: a mix of grades in both
        String expected = "send: 3.5\nfail: 1.5";
        String[] send = {"V1", "V3", "V4", null, null};
        int numSend = 3;
        String[] fail = {"V5", "V2", "V1", null, null};
        int numFail = 3;
        if(!(expected.equals(ClimbingTracker.getStats(send, numSend, fail, numFail, 2)))) {
            return false;
        }
        System.out.println("1. A mix of grades test passed.");
        
        
        // 2nd test scenario: historyLength is 0
        expected = "send: --\nfail: --";
        if(!(expected.equals(ClimbingTracker.getStats(send, numSend, fail, numFail, 0)))) {
            return false;
        }
        System.out.println("2. historyLength is 0 test passed");
        
        
        // 3rd test scenario: numSend is 0
        expected = "send: --\nfail: 2.6666666666666665";
        if(!(expected.equals(ClimbingTracker.getStats(send, 0, fail, numFail, 3)))) {
            return false;
        }
        System.out.println("3. numSend is 0 test passed");
        
        
        // 4th test scenario: numFail is 0
        expected = "send: 2.6666666666666665\nfail: --";
        if(!(expected.equals(ClimbingTracker.getStats(send, numSend, fail, 0, 3)))) {
            return false;
        }
        System.out.println("4. numFail is 0 test passed");
        
        
        // 5th test scenario: numSend and numFail are both 0
        expected = "send: --\nfail: --";
        if(!(expected.equals(ClimbingTracker.getStats(send, 0, fail, 0, 3)))) {
            return false;
        }
        System.out.println("5. numSend and numFail are both 0 test passed");
        
        
        // 6th test scenario: multiple instances of one grade in both
        expected = "send: 2.0\nfail: 2.0";
        send[0] = "V2";
        send[1] = "V2";
        send[2] = "V2";
        fail[0] = "V2";
        fail[1] = "V2";
        fail[2] = "V2";
        if(!(expected.equals(ClimbingTracker.getStats(send, numSend, fail, numFail, 3)))) {
            return false;
        }
        System.out.println("6. multiple instances of one grade in both test passed");
        
        // 7th test scenario: negative historyLength case
        expected = "send: --\nfail: --";
        if(!(expected.equals(ClimbingTracker.getStats(send, numSend, fail, numFail, -1)))) {
            return false;
        }
        System.out.println("7. historyLength is negative test passed");
        
        System.out.println();
        return true;
    }
    
    
    /**
     * Checks whether getHistogram() works as expected
     * 
     * @return true if getHistogram() functionality is verified, false otherwise
     */
    public static boolean testGetHistogram() {

        System.out.println("Test getHistogram():");
        
        
        // 1st test scenario: normal sample case
        String expected = "V0: + + +\nV1: - +\nV2: -";
        String[] send = {"V0", "V1", "V0", "V0", null};
        int numSend = 4;
        String[] fail = {"V2", "V1", null, null, null};
        int numFail = 2;
        if(!expected.equals(ClimbingTracker.getHistogram(send, numSend, fail, numFail))) {
            return false;
        }
        System.out.println("1. Normal sample case test passed.");
        
        
        // 2nd test scenario: numSend and numFail are 0
        expected = "Error: no data to display";
        if(!expected.equals(ClimbingTracker.getHistogram(send, 0, fail, 0))) {
            return false;
        }
        System.out.println("2. Edge case test passed: numSend and numFail are 0.");
        
        /* 3rd test scenario: no attempts for a given grade but there are attempts 
         for a higher one case
        */
        expected = "V0: + + +\nV1: - +\nV2: \nV3: -";
        send[0] = "V0";
        send[1] = "V1";
        send[2] = "V0";
        send[3] = "V0";
        send[4] = null;
        numSend = 4;
        fail[0] = "V3";
        fail[1] = "V1";
        fail[2] = null;
        fail[3] = null;
        fail[4] = null;
        numFail = 2;
        if(!expected.equals(ClimbingTracker.getHistogram(send, numSend, fail, numFail))) {
            return false;
        }
        System.out.println("3. Edge case test passed: no attempts for a given grade "
            + "but there are attempts for a higher one case.");
        
        System.out.println();
        return true;
    }
    
    
    /**
     * Test whether ClimbingTracker Class is implemented properly.
     * 
     * @return true if all the tests return true, false otherwise
     */
    public static boolean runAllTests() {
        if(!(testSendClimb() && testFailClimb() && testGetStats() && testGetHistogram())) {
            System.err.println("Some tests failed");
            return false;
        }
        return true;
    }
    
    

}
