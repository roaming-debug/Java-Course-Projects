///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    BlackFish.java
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
 * A black fish moves back and forth its two favorable decoration tank objects
 */
public class BlackFish extends Fish {
  private TankObject source;
  private  TankObject destination;

  /**
   * Creates a new black fish located at a random position of the screen, whose speed is 2
   * @param source
   * @param destination
   */
  public BlackFish(TankObject source, TankObject destination) {
    super(2, "images" + File.separator + "black.png");
    this.source = source;
    this.destination = destination;
  }

  /**
   * move the fish towards its destination
   */
  @Override
  public void swim() {
    moveTowardsDestination();
    if (isOver(destination)) {
      TankObject temp = destination;
      destination = source;
      source = temp;
    }
  }

  /**
   * makes one speed move towards destination
   */
  public void moveTowardsDestination() {
    float dx = destination.getX() - this.getX();
    float dy = destination.getY() - this.getY();
    int d = (int)(Math.sqrt(dx*dx + dy*dy));
    setX(getX() + speed() * dx / d);
    setY(getY() + speed() * dy / d);
  }

  /**
   * returns true if this black fish is over another tank object, and false otherwise
   * @param other another tank object
   * @return true if this black fish is over another tank object, and false otherwise
   */
  public boolean isOver(TankObject other) {
    float x1 = getX() - image.width/2;
    float x2 = getX() + image.width/2;
    float y1 = getY() - image.height/2;
    float y2 = getY() + image.height/2;
    float x3 = other.getX() - other.image.width/2;
    float x4 = other.getX() + other.image.width/2;
    float y3 = other.getY() - other.image.height/2;
    float y4 = other.getY() + other.image.height/2;
    return (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2);
  }


}
