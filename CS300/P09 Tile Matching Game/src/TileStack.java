///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    TileStack.java
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
import java.util.Iterator;

/**
 * This class models an Iterable stack of Tiles.
 * It implements both StackADT and Iterable interfaces
 */
public class TileStack implements Iterable<Tile>, StackADT<Tile>{
  private Node top;
  private int size;
  
  /**
   * Creates an empty stack
   */
  public TileStack() {
    size = 0;
    top = null;
  }
  /**
   * Pushes the provided tile at top of this stack
   */
  @Override
  public void push(Tile element) {
    Node newNode = new Node(element);
    newNode.setNext(top);
    top = newNode;
    size++;
  }
  
  /**
   * Removes and returns the tile at the top of this stack
   * @return the removed tile
   * @throws EmptyStackException if this stack is empty
   */
  @Override
  public Tile pop() {
    if(isEmpty()) throw new EmptyStackException();
    size--;
    Node curr = top;
    top = top.getNext();
    return curr.getTile();
  }
  
  /**
   * Returns the tile at the top of this stack
   * @return the tile at the top of this stack
   * @throws EmptyStackException if this stack is empty
   */
  @Override
  public Tile peek() {
    if(isEmpty()) throw new EmptyStackException();
    return top.getTile();
  }
  
  /**
   * Check whether this stack is empty
   * @return true if this stack contains no elements, otherwise false
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }
  
  /**
   * Returns the size of this stack
   * @return the size of this stack
   */
  @Override
  public int size() {
    return size;
  }
  
  /**
   * Returns an iterator to iterate through this stack starting from its top
   * @return an iterator to iterate through elements of type Tile
   */
  @Override
  public Iterator<Tile> iterator() {
    return new TileListIterator(top);
  }
  
}
