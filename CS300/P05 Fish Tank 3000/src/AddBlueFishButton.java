///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    AddBlueFishButton.java
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
 * Add blue fish button class
 */
public class AddBlueFishButton extends Button {
  /**
   * Specify label string, x-position, and y-position
   *
   * @param x x-position
   * @param y y-position
   */
  AddBlueFishButton(float x, float y) {
    super("Add Blue", x, y);
  }

  /**
   * When mouse clicked, a blue fish object will be added into the tank
   */
  @Override
  public void mousePressed() {
    super.mousePressed();
    tank.addObject(new BlueFish());
  }
}
