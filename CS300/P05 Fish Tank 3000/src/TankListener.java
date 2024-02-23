///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    TankListener.java
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
// Online Sources:  CS300_Fall_2021_P05_Fish_Tank_3000.pdf
//                  This file helped the construct the whole code.
//
///////////////////////////////////////////////////////////////////////////////

/**
 * An interface defines what methods to implement
 */
public interface TankListener {
  /**
   * draws this tank object to the display window
   */
  public void draw();

  /**
   * called each time the mouse is Pressed
   */
  public void mousePressed();

  /**
   * called each time the mouse is Released
   */
  public void mouseReleased();

  /**
   * checks whether the mouse is over this Tank GUI
   * @return return true if the mouse is over this tank GUI object, false otherwise
   */
  public boolean isMouseOver();
}
