///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    LinkedNode.java
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
// Online Sources:  Program 08.pdf
//                  This file helped the construct the whole code.
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Linked Node Class
 */
public class LinkedNode <T>{
  private T data;
  private LinkedNode<T> next;
  /**
   * initializes the data field and leaves next null
   * @param data
   */
  public LinkedNode(T data) {
    this.data = data;
    this.next = null;
  }
  /**
   * initializes both data and next fields
   * @param data
   * @param next
   */
  public LinkedNode(T data, LinkedNode<T> next) {
    this.data = data;
    this.next = next;
  }
  /**
   * returns the value of the data instance field
   * @return the data
   */
  public T getData() {
    return data;
  }
  
  /**
   * returns a reference to the next node in the list
   * @return the next
   */
  public LinkedNode<T> getNext() {
    return next;
  }
  /**
   * updates the next field to be the provided node (possibly null)
   * @param next the next to set
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }
  /**
   * Returns the string representation of the node's data
   */
  @Override
  public String toString() {
    return data.toString();
  }
  
}
