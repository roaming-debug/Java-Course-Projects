///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Benchmarker.java
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
// Online Sources:  Program 06.pdf
//                  This file helped the construct the whole code.
//
///////////////////////////////////////////////////////////////////////////////


/**
 * Benchmarker Class
 */
public class Benchmarker {
  /**
   * Determine the time elapsed during calling bruteForce function
   * @param ph PasswordHacker object
   * @return time elapsed during running bruteForce function
   */
  public static long timeBruteForce(PasswordHacker ph) {
    long timeB = System.currentTimeMillis(); // time before calling
    ph.bruteForce();
    long timeA = System.currentTimeMillis(); // time after calling
    return timeA - timeB;
  }

  /**
   * Determine the time elapsed during calling hack function
   * @param ph PasswordHacker object
   * @return time elapsed during running hack function
   */
  public static long timeHack(PasswordHacker ph) {
    long timeB = System.currentTimeMillis(); // time before calling
    ph.hack();
    long timeA = System.currentTimeMillis(); // time after calling
    return timeA - timeB;
  }

  /**
   * Race the two approaches against each other
   * @param passwordLength password length
   * @param numRuns number of runs
   * @return result in string
   */
  public static String race(int passwordLength, int numRuns) {
    long sum = 0;
    for(int i = 0; i < numRuns; i++) {
      PasswordHacker ph = new PasswordHacker(passwordLength);
      sum += timeBruteForce(ph);
    }
    long bruteTime = sum / numRuns;
    sum = 0;
    for(int i = 0; i < numRuns; i++) {
      PasswordHacker ph = new PasswordHacker(passwordLength);
      sum += timeHack(ph);
    }
    long hackTime = sum / numRuns;
    return String.format("Brute force %d: %d\nHack %d: %d",
        passwordLength, bruteTime, passwordLength, hackTime);
  }
  /**
   * Main function
   * @param args command line argument
   */
  public static void main(String[] args) {
    System.out.println(race(6, 10));
  }
}
