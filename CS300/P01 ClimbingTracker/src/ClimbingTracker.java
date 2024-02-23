//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    ClimbingTracker.java
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
// Online Sources:  docs.oracle.com
//                  I searched it for docs
//
//                  https://stackoverflow.com/questions/3395286/remove-last-ch
//                  aracter-of-a-stringbuilder
//                  User Stephen C helped me how to remove the last char of a 
//                  Stringbuffer
//
///////////////////////////////////////////////////////////////////////////////

/**
 * 
 * A Class for Tracking the Climbing data. It can add data to the successful climbs 
 * and unsuccessful climbs array, create a formatted string of the aversge climb grades,
 * number of climbs at each grade. 
 *
 */
public class ClimbingTracker {
    
    
    /**
     * A private helper implemented in detail for the sendClimb() and failclimb()
     *  since two methods would be implemented in a similiar way.
     * 
     * @param array an array contains series of grades
     * @param numArr the size of the array
     * @param grade the grade wanted to be added to the array
     * @return the new size of the array
     */
    private static int addArrayHelper(String[] array, int numArr, String grade) {
        // make sure numArr still has the space
        if(array.length <= numArr || numArr < 0) {
            return numArr;
        }
        
        // make sure the grade is valid: a capital "V" + a number 0-7
        int gradeNum = 0;
        if(Character.isDigit(grade.charAt(1)) && grade.length() == 2) {
            gradeNum = Integer.parseInt(grade.substring(1));
        } else {
            return numArr;
        }
        if(!((gradeNum>= 0 && gradeNum <= 7) && grade.charAt(0) == 'V')) {
            return numArr;
        }
        
        array[numArr] = grade;
        numArr++;
        return numArr;
    }
    
    
    /**
     * Adds a successfully-completed ("sent") climb's grade to the end of the array of 
     * successful climbs if there is room AND the provided grade is valid (i.e., a capital
     * "V" + a number 0-7).
     * 
     * @param send an array of successful climbs
     * @param numSend the size of the send array
     * @param grade the grade wanted to be added to the send array
     * @return the new size of the send array
     */
    public static int sendClimb(String[] send, int numSend, String grade) {
        return addArrayHelper(send, numSend, grade);
    }
    
    
    /**
     * Adds an unsuccessful climb's grade to the end of the array of failed climbs if 
     * there is room AND the provided grade is valid (i.e., a capital "V" + a number 0-7).
     * 
     * @param fail an array of unsuccessful climbs
     * @param numFail the size of the fail array
     * @param grade the grade wanted to be added to the fail array
     * @return the new size of the fail array
     */
    public static int failClimb(String[] fail, int numFail, String grade) {
        return addArrayHelper(fail, numFail, grade);
    }
    
    
    /**
     * Creates and returns a formatted String containing the average (mean) climb grade
     * over the most recent historyLength number of climbs in each of the send and fail
     * arrays.
     * 
     * @param send
     * @param numSend
     * @param fail
     * @param numFail
     * @param historyLength
     * @return
     */
    public static String getStats(String[] send, int numSend, String[] fail, int numFail,
        int historyLength) {
        StringBuffer stats = new StringBuffer();
        
        
        stats.append("send: ");
        
        /* 1. sendSize is the number of strings will be retrieved from the send array. If
         historyLength is greater than numSend, than the numSend is used as sendSize. 
           2. If historyLength is less than 0, make sendSize 0.
        */
        int sendSize = 0;
        if(Math.min(historyLength, numSend) >= 0) {
            sendSize = Math.min(historyLength, numSend);
        } 
        if(sendSize == 0) {
            stats.append("--");
        } else {
            int sum = 0;
            for(int index = numSend-sendSize; index < numSend; index++) {
                int gradeNum = Integer.parseInt(send[index].substring(1));
                sum += gradeNum;
            }
            stats.append(1.0 * sum / sendSize);
                
        }
        stats.append("\n");
        
        stats.append("fail: ");
        // failSize is as same as the sendSize.
        int failSize = 0;
        if(Math.min(historyLength, numFail) >= 0) {
            failSize = Math.min(historyLength, numFail);
        } 
        if(failSize == 0) {
            stats.append("--");
            
        } else {
            int sum = 0;
            for(int index = numFail-failSize; index < numFail; index++) {
                int gradeNum = Integer.parseInt(fail[index].substring(1));
                sum += gradeNum;
            }
            stats.append(1.0 * sum / failSize);
        }
        
        return stats.toString();
        
    }
    
    /**
     * Creates and returns a formatted String containing the number of climbs at each grade 
     * from V0 to the highest graded climb in either array. Failures are reported first, 
     * and are represented with a "-"; successes are represented with a "+" and are listed 
     * second.
     * 
     * @param send
     * @param numSend
     * @param fail
     * @param numFail
     * @return
     */
    public static String getHistogram(String[] send, int numSend, String[] fail, int numFail) {
        
        // Edge case
        if((numSend == 0 && numFail == 0) || (numSend < 0 || numFail < 0)) {
            return "Error: no data to display";
        }
       
        int indexMax = 0;
        
        // All int initial values are 0, we could save the loop.
        int[] failStat = new int[8];
        for(int index = 0; index < numFail; index++) {
            int indexFailStat = Integer.parseInt(fail[index].substring(1));
            if(indexMax < indexFailStat) {
                indexMax = indexFailStat;
            }
            failStat[indexFailStat]++;
        }
        
        
        int[] sendStat = new int[8];
        for(int index = 0; index < numSend; index++) {
            int indexSendStat = Integer.parseInt(send[index].substring(1));
            if(indexMax < indexSendStat) {
                indexMax = indexSendStat;
            }
            sendStat[indexSendStat]++;
        }
        
        StringBuffer histogram = new StringBuffer();
        for(int index = 0; index <= indexMax; index++) {
            histogram.append("V" + index + ": ");
            
            
            
            for(int j = 0; j < failStat[index]; j++) {
                histogram.append("- ");
            }
            
            
            for(int j = 0; j < sendStat[index]; j++) {
                histogram.append("+ ");
            }
            
            
            // Delete last space in a line
            if(sendStat[index] != 0 || failStat[index] != 0) {
                histogram.setLength(histogram.length() - 1);
            }
            
            histogram.append("\n");
        }
        
        // Delete last '\n' character
        histogram.setLength(histogram.length() - 1);
        
        return histogram.toString();
    }
}
