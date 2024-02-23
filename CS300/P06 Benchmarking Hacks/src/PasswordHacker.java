///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PasswordHacker.java
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
 * This is the object we'll be hacking with
 */
public class PasswordHacker {
  private LockBox toPick;
  private int passwordLength;

  /**
   * The constructor
   * @param passwordLength
   */
  public PasswordHacker(int passwordLength) {
    this.passwordLength = passwordLength;
    toPick = new LockBox(passwordLength);
  }

  /**
   * Try to hack the LockBox object
   * Complexity: O(1)
   */
  public void hack() {
    toPick.reset();
    toPick.authenticate(toPick.hackMe());
  }

  /**
   * Brute force the password
   * Complexity: O(N)
   */
  public void bruteForce() {
    toPick.reset();
    int times = (int)Math.pow(10, passwordLength)-1;
    for(int i = 0; i <= times; i++) {
      toPick.authenticate(generateGuess(i));
    }
  }

  /**
   * Generate guess password string
   * @param count guess time
   * @return
   */
  public String generateGuess(int count) {
    String guess = "";
    for(int i = 0; i < passwordLength; i++) {
      guess = count%10 + guess;
      count /= 10;
    }
    return guess;
  }
}
