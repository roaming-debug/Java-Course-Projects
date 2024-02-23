///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    TankObject.java
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


import processing.core.PImage;

/**
 * A Class for all the objects inside the fish tank
 */
public class TankObject implements TankListener {
  protected static FishTank tank; // PApplet object which represents the display window
  protected PImage image; // image of this tank object
  private float x; // x-position of this tank in the display window
  private float y; // y-position of this tank in the display window
  private boolean isDragging; // indicates whether this tank object is being dragged or not
  private static int oldMouseX; // old x-position of the mouse
  private static int oldMouseY; // old y-position of the mouse

  /**
   * Constructor of this TankObject Class
   * @param x x-position
   * @param y y-position
   * @param imageFileName image file's name
   */
  public TankObject(float x, float y, String imageFileName) {
    this.x = x;
    this.y = y;
    this.image = tank.loadImage(imageFileName);
    this.isDragging = false;
  }

  /**
   * Sets the PApplet graphic display window for all TankObjects
   * @param tank FishTank object that is going to set
   */
  public static void setProcessing(FishTank tank) {
    TankObject.tank = tank;
  }

  /**
   * Moves this tank object with dx and dy
   *
   * @param dx move to the x-position of this tank object
   * @param dy move to the y-position of this tank object
   */
  public void move(int dx, int dy) {
    this.x += dx;
    this.y += dy;
  }

  /**
   * Returns the x-position of this tank object
   *
   * @return the x-position of this tank object
   */
  public float getX() {
    return this.x;
  }

  /**
   * Returns the y-position of this tank object
   *
   * @return the y-position of this tank object
   */
  public float getY() {
    return this.y;
  }

  /**
   * Sets the x-position of this object
   * @param x
   */
  public void setX(float x) {
    this.x = x;
  }

  /**
   * Sets the y-position of this object
   * @param y
   */
  public void setY(float y) {
    this.y = y;
  }

  /**
   * Returns the image of this tank object
   * @return
   */
  public PImage getImage() {
    return this.image;
  }

  /**
   * Getter of the isDragging field.
   * @return true if this object is being dragged, false otherwise
   */
  public boolean isDragging() {
    return this.isDragging;
  }

  /**
   * Starts dragging this tank object
   */
  public void startDragging() {
    TankObject.oldMouseX = tank.mouseX;
    TankObject.oldMouseY = tank.mouseY;
    this.isDragging = true;
  }

  /**
   * Stops dragging this tank object
   */
  public void stopDragging() {
    this.isDragging = false;
  }

  /**
   * Draw current fish object
   */
  @Override
  public void draw() {
    // if this object is dragging, set its position to follow the mouse moves
    if (this.isDragging) {
      int dx = tank.mouseX - oldMouseX;
      int dy = tank.mouseY - oldMouseY;
      move(dx, dy);
      oldMouseX = tank.mouseX;
      oldMouseY = tank.mouseY;
    }

    // draw this decoration object at its current position
    tank.image(this.image, this.x, this.y);
  }

  /**
   * If mouse is pressed, start dragging
   */
  @Override
  public void mousePressed() {
    this.startDragging();
  }

  /**
   * If mouse is released, stop dragging
   */
  @Override
  public void mouseReleased() {
    this.stopDragging();
  }

  /**
   * Checks whether the mouse is over the object
   * @return true if mouse is over the object, false otherwise
   */
  @Override
  public boolean isMouseOver() {
    // checks if the mouse is over this object
    return tank.mouseX >= x - image.width / 2 && tank.mouseX <= x + image.width / 2
        && tank.mouseY >= y - image.height / 2 && tank.mouseY <= y + image.height / 2;
  }
}
