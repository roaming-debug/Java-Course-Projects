//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    FishTank.java
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
// Online Sources:  p02FishTank1000.pdf
//                  This file helped the construct the whole code.
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Random;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;


/**
 * This class abstracts fish tank, includes the main method to run the program.
 * Press 'F' or 'f' to add fish, or press 'R' or 'r' over the fish to remove it.
 *
 */
public class FishTank {
  private static PApplet processing; // PApplet object that represents the graphic
                                     // interface of the JunglePark application
  private static PImage backgroundImage; // PImage object that represents the
                                         // background image
  private static Fish[] fishes; // perfect size array storing the different fish present
                                // in the fish tank. These fish can be of different species.
  private static Random randGen; // Generator of random numbers

  /**
   * This method is used to call Utility.startApplication() function. 
   * 
   * @param args command line arguments
   */
  public static void main(String[] args) {
    Utility.startApplication();
    
  }
  
  
  /**
   * Defines the initial environment properties of this application
   * @param processingObj a reference to the graphic display window of this application
   */
  public static void setup(PApplet processingObj) {
    processing = processingObj;
    
    // load the image of the background
    backgroundImage = processing.loadImage("images/background.png");
    
    // Draw the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    final int numFish = 8;
    fishes = new Fish[numFish];
    randGen = new Random();
  }
  
  
  /**
   * Draws and updates the application display window.
   * This callback method called in an infinite loop.
   */
  public static void draw() {
    // Draw the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    
    // Used to test the isMouseOver() function
    // System.out.println(isMouseOver(fishes[0]));
    
    for(int i = 0; i < fishes.length; i++) {
      if(fishes[i] != null) {
        if(fishes[i].isDragging()) {
          fishes[i].setPositionX(processing.mouseX);
          fishes[i].setPositionY(processing.mouseY);
        }
        fishes[i].draw();
      }
    }
    
  }

  
  /**
   * Checks if the mouse is over a specific Fish whose reference is provided
   * as input parameter
   *
   * @param Fish reference to a specific fish
   * @return true if the mouse is over the specific Fish object (i.e. over
   *        the image of the Fish), false otherwise
   */
  public static boolean isMouseOver(Fish Fish) {
    int widthFishImage = Fish.getImage().width;
    int heightFishImage = Fish.getImage().height;
    // The fish position is in at the middle point of the fish image
    if(processing.mouseX >= Fish.getPositionX()-widthFishImage/2.0
        && processing.mouseX <= Fish.getPositionX()+widthFishImage/2.0
        && processing.mouseY >= Fish.getPositionY()-heightFishImage/2.0
        && processing.mouseY <= Fish.getPositionY()+heightFishImage/2.0) {
      return true;
    }
    return false;
  }
  
  
  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    // If the mouse is over one of the fish, only the lowest index of fish will be dragged  
    for(int i = 0; i < fishes.length; i++) {
      if(fishes[i] != null && isMouseOver(fishes[i])) {
        fishes[i].setDragging(true);
        break;
      }
    }
  }
  
  
  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    // After the mouse is released, set dragging status of every fish to be false 
    for(int i = 0; i < fishes.length; i++) {
      if(fishes[i] != null) {
        fishes[i].setDragging(false);
      }
    }
  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {
    /* If user pressed the 'F' or 'f' key, add fish object to the lowest index of
     * null value of the fish array.
     */ 
    if(processing.key == 'F' || processing.key == 'f') {
      for(int i = 0; i < fishes.length; i++) {
        if(fishes[i] == null) {
          fishes[i] = new Fish(processing, (float)randGen.nextInt(processing.width), 
                      (float)randGen.nextInt(processing.height));
          break; // only the lowest index of fish array will be added
        }
      }
    }
    
    /* If the ’R’ or ’r’ key is pressed while the mouse is over a Fish, that Fish will
     * be removed from the tank.
     */
    if(processing.key == 'R' || processing.key == 'r') {
      for(int i = 0; i < fishes.length; i++) {
        if(fishes[i] != null && isMouseOver(fishes[i])) {
          fishes[i] = null;
          break; // only the lowest index of fish will be removed
        }
      }
    }
    
  }


}
