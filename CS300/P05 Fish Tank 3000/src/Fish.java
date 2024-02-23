///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Fish.java
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
 * Fish class implemented basic feature for drawing, swimming, dragging
 */
public class Fish extends TankObject {
  private int speed;
  private boolean isSwimming;

  /**
   * Create a new fish object located at a random position of the display window
   * @param speed the speed of Fish object to set
   * @param fishImageFileName the image name of Fish object to set
   * @throws IllegalArgumentException throw if speed argument is negative or zero
   */
  public Fish(int speed, String fishImageFileName) throws IllegalArgumentException {
    super(tank.randGen.nextInt(tank.width), tank.randGen.nextInt(tank.height), fishImageFileName);
    if(speed <= 0) {
      throw new IllegalArgumentException("Warning: speed cannot be negative");
    }
    this.speed = speed;
  }

  /**
   * Create a new fish object located at a random position of the display window with speed 5
   * and file image name referring to the orange fish.
   */
  public Fish() {
    this(5, "images" + File.separator + "orange.png");
  }

  /**
   * Overrides the draw() method implemented in the parent class. This method sets the
   * position of this fish to follow the mouse moves if it is dragging, calls its swim() method
   * if it is swimming, and draws it to the display window.
   */
  @Override
  public void draw() {
    super.draw();

    // if the fish is swimming, call its swim() method
    if (isSwimming) {
      this.swim();
    }
    // draw the fish at its current position
    // tank.image(this.image, super.getX(), super.getY());
  }

  /**
   * Checks whether this fish is swimming
   * @return isSwimming variable
   */
  public boolean isSwimming() {
    return isSwimming;
  }

  /**
   * Starts swimming this fish
   *
   */
  public void startSwimming() {
    super.stopDragging();
    this.isSwimming = true;
  }

  /**
   * Stops swimming this fish
   *
   */
  public void stopSwimming() {
    this.isSwimming = false;
  }

  /**
   * Gets the speed of this fish
   * @return speed
   */
  public int speed() {
    return speed;
  }

  /**
   * Moves horizontally the fish one speed step from left to right. Note that x-position of the fish
   * is bounded by the width of the display window. If the x-position of this fish reaches the width
   * of the display window, it is going to be set to zero. You may think of using the modulo
   * operator.
   */
  public void swim() {
    super.setX((super.getX() + speed) % tank.width);
  }
}
