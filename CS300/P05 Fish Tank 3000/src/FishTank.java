///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
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
// Online Sources:  CS300_Fall_2021_P05_Fish_Tank_3000.pdf
//                  This file helped the construct the whole code.
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;


/**
 * Fish Tank Class contains main function and use processing.core
 *
 */
public class FishTank extends PApplet {

  private PImage backgroundImage; // PImage object which represents the background image
  protected ArrayList<TankListener> objects; // list storing interactive objects
  protected Random randGen; // Generator of random numbers
  private TankObject flower;
  private TankObject log;
  private TankObject shell;
  private TankObject ship;

  /**
   * sets the size of this PApplet to 800 width x 600 height
   */
  @Override
  public void settings() {
    size(800, 600);
  }

  /**
   * Defines initial environment properties such as screen size and loads the background
   * image and fonts as the program starts. It also initializes all data fields.
   */
  @Override public void setup() {
    // Set and display the title of the display window
    this.getSurface().setTitle("Fish Tank 3000");
    // Set the location from which images are drawn to CENTER
    this.imageMode(PApplet.CENTER);
    // Set the location from which rectangles are drawn.
    this.rectMode(PApplet.CORNERS);
    // rectMode(CORNERS) interprets the first two parameters of rect() method
    // as the location of one corner, and the third and fourth parameters as
    // the location of the opposite corner.
    // rect() method draws a rectangle to the display window

    this.focused = true; // Confirms that our Processing program is focused,
    // meaning that it is active and will accept mouse or keyboard input.

    // sets the text alignment to center
    this.textAlign(PApplet.CENTER, PApplet.CENTER);

    // load the background image and store the loaded image to backgroundImage
    backgroundImage = this.loadImage("images" + File.separator + "background.png");

    // create an empty array list of objects
    objects = new ArrayList<TankListener>(0);

    // set randGen to the reference of a new Random objects
    randGen = new Random();

    TankObject.setProcessing(this);

    flower = new TankObject(430, 60, "images" + File.separator + "flower.png");
    log = new TankObject(580, 470, "images" + File.separator + "log.png");
    shell = new TankObject(65, 520, "images" + File.separator + "shell.png");
    ship = new TankObject(280, 535, "images" + File.separator + "ship.png");
    objects.add(flower);
    objects.add(log);
    objects.add(shell);
    objects.add(ship);

    objects.add(new BlackFish(log, flower));
    objects.add(new BlackFish(shell, flower));

    Button.setProcessing(this);
    objects.add(new AddBlueFishButton(43, 16));
    objects.add(new AddOrangeFishButton(129, 16));
    objects.add(new AddYellowFishButton(215, 16));
    objects.add(new ClearTankButton(301, 16));
  }

  /**
   * Continuously draws and updates the application display window
   */
  @Override
  public void draw() {
    // clear the display window by drawing the background image
    this.image(backgroundImage, this.width / 2, this.height / 2);
    // traverse the objects list and draw each of the objects to this display window
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).draw();
    }

  }

  /**
   * Callback method called each time the user presses the mouse
   */
  @Override
  public void mousePressed() {
    // traverse the objects list and call mousePressed method
    // of the first object being clicked in the list
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).isMouseOver()) {
        objects.get(i).mousePressed();
        break;
      }
    }

  }

  /**
   * Callback method called each time the mouse is released
   */
  @Override
  public void mouseReleased() {
    // traverse the objects list and call each object's mouseReleased() method
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).mouseReleased();
    }
  }

  // adds an instance of TankListener passed as input to the objects arraylist
  public void addObject(TankListener object) {
    objects.add(object);
  }

  /**
   * Callback method called each time the user presses a key
   */
  @Override public void keyPressed() {
    switch (Character.toUpperCase(key)) {
      case 'O': // create a new orange fish
        objects.add(new Fish());
        break;
      case 'Y': // create a new yellow fish whose speed is 2
        this.objects.add(
            new Fish(2, "images" + File.separator + "yellow.png"));
        break;
      // if the object is instance of Fish, and the mouse is over it, remove it from the tank
      case 'R':
        for (int i = 0; i < objects.size(); i++) {
          if (objects.get(i) instanceof Fish && objects.get(i).isMouseOver()) {
            objects.remove(i);
            break;
          }
        }
        break;
      case 'S':
        for (int i = 0; i < objects.size(); i++) {
          if (objects.get(i) instanceof Fish) {
            ((Fish) objects.get(i)).startSwimming();
          }
        }
        break;
      case 'X':
        for (int i = 0; i < objects.size(); i++) {
          if (objects.get(i) instanceof Fish) {
            ((Fish) objects.get(i)).stopSwimming();
          }
        }
        break;
      case 'C':
       this.clear();
       break;
      case 'B':
        objects.add(new BlueFish());
        break;
    }
  }

  /**
   * Removes instances of the class Fish from this tank
   */
  public void clear() {
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i) instanceof Fish) {
        objects.remove(i);
        i--;
      }
    }
  }



  /**
   * This main method starts the application
   *
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    // starts the application
    PApplet.main("FishTank");
  }

}
