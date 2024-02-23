///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    TileListIterator.java
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class models an iterator through a collection of tiles
 */
public class TileListIterator implements Iterator<Tile>{
  private Node nextIteration;
  
  /**
   * Creates a new iterator to iterate through a list of tiles starting from its head
   * @param head is a reference to the head of the linked list of tiles
   */
  public TileListIterator(Node head) {
    nextIteration = head;
  }
  
  /**
   * Returns true if the iteration has more elements
   * @return true if the iteration has more elements
   */
  @Override
  public boolean hasNext() {
    return nextIteration != null;
  }
  /**
   * Returns the next element in the iteration
   * @return the next element in the iteration
   */
  @Override
  public Tile next() {
    if(nextIteration == null)
      throw new NoSuchElementException("There's no more tiles in the iteration");
    Node curr = nextIteration;
    nextIteration = nextIteration.getNext();
    return curr.getTile();
  }
  
}
