///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Decoration.java
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
// Online Sources:  CS300_Fall_2021_P04_Fish_Tank_2000.pdf
//                  This file helped the construct the whole code.
//
///////////////////////////////////////////////////////////////////////////////

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The abstraction class for decoration objects, has some same property as the
 * Fish class.
 */
public class Decoration {
  private static PApplet processing;
  private PImage image;
  private float x;
  private float y;
  private boolean isDragging;
  private static int oldMouseX;
  private static int oldMouseY;
  
  /**
   * 
   * @param processing PApplet reference to the display window of the Fish Tank application
   * @param x x-position of this decoration object
   * @param y y-position of this decoration object
   * @param imageFileName filename of the image to be loaded for this object
   */
  public Decoration(PApplet processing, float x, float y, String imageFileName) {
    Decoration.processing = processing;
    this.x = x;
    this.y = y;
    this.isDragging = false;
    image = processing.loadImage(imageFileName);
  }
  
  /**
   * Returns the image of this decoration object
   * @return the image instance field 
   */
  public PImage getImage() {
    return this.image;
  }
  
  /**
   * Returns the x-position of this decoration in the display window
   * @return the x-position of this decoration
   */
  public float getPositionX() {
    return this.x;
  }
  
  /**
   * Returns the y-position of this decoration in the display window
   * @return the y-position of this decoration
   */
  public float getPositionY() {
    return this.y;
  }
  
  
  /**
   * Checks whether this decoration is being dragged
   * @return the isDragging instance field
   */
  public boolean isDragging() {
    return this.isDragging;
  }
 
  /**
   * Starts dragging this decoration
   */
  public void startDragging() {
    Decoration.oldMouseX = processing.mouseX;
    Decoration.oldMouseY = processing.mouseY;
    this.isDragging = true;
  }

  /**
   * Stops dragging this decoration
   */
  public void stopDragging() {
    this.isDragging = false;
  }
  
  /**
   * Checks if the mouse is over a given decoration whose reference is provided as input parameter
   * 
   * @return true if the mouse is over the given decoration object (i.e. over the image of the 
   * decoration), false otherwise
   */
  public boolean isMouseOver() {
    int decWidth = this.getImage().width;
    int decHeight = this.getImage().height;

    // checks if the mouse is over the provided decoration
    return processing.mouseX >= this.getPositionX() - decWidth / 2
        && processing.mouseX <= this.getPositionX() + decWidth / 2
        && processing.mouseY >= this.getPositionY() - decHeight / 2
        && processing.mouseY <= this.getPositionY() + decHeight / 2;
  }
  
  
  /**
   * Moves this decoration with dx and dy
   * @param dx adds dx move to the x-position of this decoration object
   * @param dy adds dy move to the y-position of this decoration object
   */
  public void move(int dx, int dy) {
    this.x += dx;
    this.y += dy;
  }
  
  
  /**
   * Draws this decoration object to the display window. This method sets
   * also the position of this object to follow the moves of the mouse if it is being dragged
   */
  public void draw() {
    if(this.isDragging) {
      this.move(Decoration.processing.mouseX-Decoration.oldMouseX,
          Decoration.processing.mouseY-Decoration.oldMouseY);
      Decoration.oldMouseX = Decoration.processing.mouseX;
      Decoration.oldMouseY = Decoration.processing.mouseY;
      
    }
    processing.image(this.image, this.x, this.y);
    
  }
  
  
}
