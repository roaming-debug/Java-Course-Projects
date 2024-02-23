///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    LockBox.java
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


import java.util.Random;

/**
 * The class for the object that we'll be hacking into
 */
public class LockBox {
  protected static Random randGen;
  private String password;
  private boolean isOpen;

  /**
   * Constructor for LockBox
   * @param passwordLength
   */
  public LockBox(int passwordLength) throws IllegalArgumentException {
    if(randGen == null) {
      randGen = new Random();
    }
    if(passwordLength <= 0) {
      throw new IllegalArgumentException("Invalid password length");
    }
    password = "";
    for(int i = 0; i < passwordLength; i++) {
      password += Math.abs(randGen.nextInt()%10);
    }
  }

  /**
   * Checks the provided guess against the stored password. If the guess is corresponding to
   * the password, set isOpen to true
   * @param guess guess password string
   */
  public void authenticate(String guess) {
    if(guess.equals(password)) {
      isOpen = true;
    }
  }

  /**
   * Accessor for the password field
   * @return
   */
  public String hackMe() {
    return password;
  }

  /**
   * Accessor for the isOpen field, to check whether authentication was successful
   * @return isOpen field
   */
  public boolean isOpen() {
    return isOpen;
  }

  /**
   * Resets the isOpen field to false
   */
  public void reset() {
    isOpen = false;
  }
}
