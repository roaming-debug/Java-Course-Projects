///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    BlueFish.java
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


import java.io.File;

/**
 * A bkue fish moves from right to left
 */
public class BlueFish extends Fish {
  BlueFish() {
    super(2, "images" + File.separator + "blue.png");
  }

  /**
   * swim from right to left
   */
  @Override
  public void swim() {
    if (super.getX() - super.speed() >= 0) {
      super.setX(super.getX() - super.speed());
    } else {
      super.setX(tank.width);
    }
  }
}
