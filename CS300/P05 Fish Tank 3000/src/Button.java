///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Button.java
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
 * A button class implemented button features
 */
public class Button implements TankListener {
  private static final int WIDTH = 85; // Width of this Button
  private static final int HEIGHT = 32; // Height of this Button
  protected static FishTank tank; // PApplet object where this button will be displayed
  private float x; // x-position of this button in the display window
  private float y; // y-position of this button in the display window
  protected String label; // text/label which represents this button

  /**
   * Creates a new Button at a given position within the display window and sets its label
   * @param label variable that is going to set
   * @param x variable that is going to set
   * @param y variable that is going to set
   */
  public Button(String label, float x, float y) {
    this.label = label;
    this.x = x;
    this.y = y;
  }

  /**
   * sets the he PApplet display window where this button is displayed and handled
   * @param tank Fish Tank object that is going to set
   */
  public static void setProcessing(FishTank tank) {
    Button.tank = tank;
  }

  /**
   * Checks whether the mouse is over this button
   * @return true if the mouse is over this button, false otherwise.
   */
  @Override
  public boolean isMouseOver() {
    return tank.mouseX >= x - Button.WIDTH / 2 && tank.mouseX <= x + Button.WIDTH / 2
        && tank.mouseY >= y - Button.HEIGHT / 2 && tank.mouseY <= y + Button.HEIGHT / 2;
  }

  /**
   * Draws this button to the display window
   */
  @Override
  public void draw() {
    tank.stroke(0);// set line value to black

    // If the mouse is over this button, sets the fill color to dark gray.
    // Sets the fill color to light gray otherwise
    if (isMouseOver()) {
      tank.fill(169, 169, 169);
    } else {
      tank.fill(211, 211, 211);
    }
    // draw the button (rectangle with a centered text)
    tank.rect(x - WIDTH / 2.0f, y - HEIGHT / 2.0f,
        x + WIDTH / 2.0f, y + HEIGHT / 2.0f);
    tank.fill(0); // set the fill color to black
    tank.text(label, x, y); // display the text of the current button
  }

  /**
   * Implements the default behavior of this button when the mouse is pressed.
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      System.out.println("A button was pressed.");
    }
  }

  /**
   * Implements the default behavior of this button when the mouse is released.
   */
  @Override
  public void mouseReleased() {
    // empty
  }


}

