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
// Online Sources:  CS300_Fall_2021_P04_Fish_Tank_2000.pdf
//                  This file helped the construct the whole code.
//
///////////////////////////////////////////////////////////////////////////////

 import processing.core.PApplet;
 import processing.core.PImage;
/**
 * The abstraction class of fish objects, it can define its image, position and
 * other data field.
 *
 */
public class Fish {
  private static PApplet processing;
  private PImage image;
  private float x;
  private float y;
  private int speed;
  private boolean isDragging;
  private boolean isSwimming;
  private static int oldMouseX;
  private static int oldMouseY;

  /**
   * Creates a new fish object located at a specific (x, y) position of the display window
   * @param processing processing PApplet object that represents the display window
   * @param x x-position of the image of this fish in the display window
   * @param y y-position of the image of this fish in the display window
   * @param speed speed the swimming speed of this fish
   * @param fishImageFileName fishImageFileName file name of the image of the fish to be created
   */
  public Fish(PApplet processing, float x, float y, int speed, String fishImageFileName) {
    Fish.processing = processing;
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.isDragging = false;
    this.isSwimming = false;
    image = processing.loadImage(fishImageFileName);
    
  }
  /**
   * Creates a new fish object positioned at the center of the display window.
   * @param processing
   */
  public Fish(PApplet processing) {
    this(processing, (float)(processing.width/2.0), (float)(processing.height/2.0), 5, 
        "images/orange.png");
  }
  
  /**
   * Returns the image of type PImage of this fish
   * @return the image instance field
   */
  public PImage getImage() {
    return this.image;
  }
  
  /**
   * Returns the x-position of this fish in the display window
   * @return the x-position of this fish
   */
  public float getPositionX() {
    return this.x;
  }
  
  /**
   * Returns the y-position of this fish in the display window
   * @return the y-position of this fish
   */
  public float getPositionY() {
    return this.y;
  }
  
  /**
   * Moves this fish with dx and dy
   * @param dx adds dx move to the x-position of this fish
   * @param dy adds dy move to the y-position of this fish
   */
  public void move(int dx, int dy) {
    this.x += dx;
    this.y += dy;
  }
  
  /**
   * Checks whether this fish is being dragged
   * @return the isDragging instance field
   */
  public boolean isDragging() {
    return this.isDragging;
  }
  
  /**
   * Starts dragging this fish
   */
  public void startDragging() {
    Fish.oldMouseX = processing.mouseX;
    Fish.oldMouseY = processing.mouseY;
    this.isDragging = true;
  }

  /**
   * Stops dragging this fish
   */
  public void stopDragging() {
    this.isDragging = false;
  }
  
  /**
   * Draws this fish to the display window. This method sets also the position of
   * this fish to follow the moves of the mouse if it is being dragged.
   */
  public void draw() {
    if(this.isDragging) {
      this.move(Fish.processing.mouseX-Fish.oldMouseX, Fish.processing.mouseY-Fish.oldMouseY);
      Fish.oldMouseX = Fish.processing.mouseX;
      Fish.oldMouseY = Fish.processing.mouseY;
      
    }
    if(this.isSwimming) {
      this.swim();
    }
    processing.image(this.image, this.x, this.y);
    
  }
  
  /**
   * Checks if the mouse is over a given fish whose reference is provided as input parameter
   * 
   * @return true if the mouse is over the given fish object (i.e. over the image of the fish),
   *         false otherwise
   */
  public boolean isMouseOver() {
    int fishWidth = this.getImage().width;
    int fishHeight = this.getImage().height;

    // checks if the mouse is over the provided fish
    return processing.mouseX >= this.getPositionX() - fishWidth / 2
        && processing.mouseX <= this.getPositionX() + fishWidth / 2
        && processing.mouseY >= this.getPositionY() - fishHeight / 2
        && processing.mouseY <= this.getPositionY() + fishHeight / 2;
  }
  
  /**
   * Starts swimming this fish
   */
  public void startSwimming() {
    this.stopDragging();
    this.isSwimming = true;
  }
  
  /**
   * Stops swimming this fish
   */
  public void stopSwimming() {
    this.isSwimming = false;
  }
  
  /**
   * Moves horizontally the fish one speed step from left to right
   */
  public void swim() {
    this.x = (this.x+this.speed)%Fish.processing.width;
  }
}







