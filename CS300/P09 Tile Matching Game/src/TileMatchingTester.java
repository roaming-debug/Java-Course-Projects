///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    TileMatchingTester.java
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
// Online Sources:  P09_Tile_Matching_Game.pdf
//                  This file helped the construct the whole code.
//
///////////////////////////////////////////////////////////////////////////////

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * Tester Class for P09
 *
 */
public class TileMatchingTester {
  /**
   * Check the correctness of any implementation of the Tile.equals() method
   * @return false if any bug is detected, true otherwise
   */
  public static boolean tileEqualsTester() {
    // Two Tile have the same color
    Tile a = new Tile(Color.BLUE);
    Tile b = new Tile(Color.BLUE);
    if(a.equals(b) == false)
      return false;
    // Two tile have different color
    b = new Tile(Color.ORANGE);
    if(a.equals(b) == true)
      return false;
    Object c = new Object();
    try {
      if(a.equals(c))
        return false;
    } catch(Exception e) {
      return false;
    }
    System.out.println("tileEqualsTester(): passed");
    return true;
  }
  
  /**
   * Test TileListIterator Class and its methods
   * @return false if any bug is detected, true otherwise
   */
  public static boolean tileListIteratorTester() {
    Node node1 = new Node(new Tile(Color.BLACK));
    Node node2 = new Node(new Tile(Color.ORANGE), node1);
    Node node3 = new Node(new Tile(Color.ORANGE), node2);
    TileListIterator it = new TileListIterator(node3);
    if(it.hasNext() == false) return false;
    try {
      if(it.next() != node3.getTile()) return false;
    } catch(Exception e) {
      return false;
    }
    if(it.hasNext() == false)
      return false;
    try {
      if(it.next() != node2.getTile())
        return false;
    } catch(Exception e) {
      return false;
    }
    if(it.hasNext() == false)
      return false;
    try {
      if(it.next() != node1.getTile())
        return false;
    } catch(Exception e) {
      return false;
    }
    if(it.hasNext() == true)
      return false;
    try {
      it.next();
      return false; // if NoSuchElementException is not throw, 
    } catch(NoSuchElementException nsee) {
      // normal behavior
    } catch(Exception e) {
      return false;
    }
    System.out.println("tileListIteratorTester(): passed");
    return true;
  }
  /**
   * Check the correctness of any implementation of the methods of TileStack Class
   * @return false if any bug is detected, true otherwise
   */
  public static boolean tileStackTester() {
    TileStack ts = new TileStack();
    
    // Test isEmpty(), push(), peek(), size() methods
    if(ts.isEmpty() == false && ts.size() != 0)
      return false;
    try {
      ts.peek();
      return false;
    } catch(EmptyStackException ese) {
      // normal behavior
    } catch(Exception e) {
      return false;
    }
    Tile firstTile = new Tile(Color.BLACK);
    ts.push(firstTile);
    if(ts.isEmpty() == true && ts.size() != 1)
      return false;
    try {
      if(ts.peek() != firstTile && ts.size() != 1)
        return false;
    } catch(Exception e) {
      return false;
    }
    ts.push(firstTile);
    if(ts.size() != 2)
      return false;
    
    // test pop() methods
    try {
      ts.pop();
      ts.pop();
    } catch(Exception e) {
      return false;
    }
    try {
      ts.pop();
      return false;
    } catch (EmptyStackException ese) {
      // normal behavior
    } catch(Exception e) {
      return false;
    }
    
    // test iterator() method
    ts.push(firstTile);
    if(ts.iterator().next() != firstTile)
      return false;
    System.out.println("tileStackTester(): passed");
    return true;
  }
  
  /**
   * Check IndexOutOfBoundsException Exception in the tileMatchingGame Class methods
   * @return false if any bugs detected, true other wise
   */
  private static boolean tileMatchingGameTesterException() {
    TileMatchingGame tmg;
    try {
      tmg = new TileMatchingGame(3);
    } catch(Exception e) {
      return false;
    }
    try {
      tmg.column(3);
      tmg.column(-1);
      tmg.dropTile​(new Tile(Color.BLACK), -1);
      tmg.dropTile​(new Tile(Color.BLACK), 3);
      tmg.clearColumn(-1);
      tmg.clearColumn(4);
    } catch(IndexOutOfBoundsException ioobe) {
      // normal behavior
    } catch(Exception e) {
      return false;
    }
    return true;
  }
  /**
   * Test implementation of methods of the TileMatchingGame Class  
   * @return false if any bug is detected, true otherwise
   */
  public static boolean tileMatchingGameTester() {
    TileMatchingGame tmg;
    // test constructors
    try {
      tmg = new TileMatchingGame(3);
    } catch(Exception e) {
      return false;
    }
    try {
      new TileMatchingGame(-1);
      new TileMatchingGame(0);
    } catch(IllegalArgumentException iae) {
      // normal behavior;
    } catch(Exception e) {
      return false;
    }
    if(!tileMatchingGameTesterException())
      return false;
    
    try {
      // test dropFile() method
      tmg.dropTile​(new Tile(Color.BLACK), 0);
      String expected = "BLACK";
      if(!expected.equals(tmg.column(0)))
        return false;
      
      tmg.dropTile​(new Tile(Color.BLUE), 0);
      expected = "BLUE BLACK";
      if(!expected.equals(tmg.column(0)))
        return false;
      
      tmg.dropTile​(new Tile(Color.BLUE), 0);
      expected = "BLACK";
      if(!expected.equals(tmg.column(0)))
        return false;
      
      // test toString()
      expected = "0: BLACK\n"
          + "1: \n"
          + "2: ";
      if(!expected.equals(tmg.toString()))
        return false;
      // test getColumnsNumber()
      if(tmg.getColumnsNumber() != 3)
        return false;
      
      // test column()
      expected = "";
      if(!expected.equals(tmg.column(1)))
        return false;
      // test clearColumn()
      tmg.clearColumn(0);
      if(!expected.equals(expected))
        return false;
      // test restart game
      tmg.dropTile​(new Tile(Color.BLUE), 0);
      tmg.restartGame();
      expected = "0: \n"
          + "1: \n"
          + "2: ";
      if(!expected.equals(tmg.toString()))
        return false;
      
    } catch(Exception e) {
      return false;
    }
    System.out.println("tileMatchingGameTester(): passed");
    return true;
  }
  /**
   * Main function to run these tests
   * @param args
   */
  public static void main(String[] args) {
    if(tileEqualsTester() && tileListIteratorTester() && tileStackTester() &&
        tileMatchingGameTester()) {
      System.out.println("All tests passed");
    } else {
      System.err.println("Some tests failed");
    }
  }
}
