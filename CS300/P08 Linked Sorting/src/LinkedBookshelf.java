///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    LinkedBookshelf.java
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
 * a singly-linked list of LinedNodes containing Book objects
 */
public class LinkedBookshelf {
  private LinkedNode<Book> front;
  private LinkedNode<Book> back;
  private int size;
  private Attribute sortedBy;
  
  /**
   * constructor for LinkedBookshelf
   */
  public LinkedBookshelf() {
    sortedBy = Attribute.ID;
    size = 0;
    front = null;
    back = null;
  }

  /**
   * returns the current number of books on the shelf
   * @return the size
   */
  public int size() {
    return size;
  }
  /**
   * check whether bookshelf is empty or not
   * @return true if and only if the shelf contains no books
   */
  public boolean isEmpty() {
    return size == 0;
  }
  
  /**
   * Convert the current state of the shelf to string
   * @return a string representation of the current state of the shelf
   */
  @Override
  public String toString() {
    String res = "";
    switch(sortedBy) {
      case TITLE:
        res = "TITLE";
        break;
      case AUTHOR:
        res = "AUTHOR";
        break;
      case PAGECOUNT:
        res = "PAGECOUNT";
        break;
      case ID:
        res = "ID";
        break;
    }
    if(size == 0) return res;
    LinkedNode<Book> pt = front; // pointer
    do {
      res = res + '\n' + pt.getData().toString();
      pt = pt.getNext();
    } while(pt != null);
    res = res + '\n';
    return res;
  }
  
  /**
   * {@literal Returns the LinkedNode <Book> at the given index}
   * @param index the index of the node that will be returned
   * @return {@literal the LinkedNode <Book> at the given index}
   */
  public LinkedNode<Book> getNode(int index) {
    if(index >= size || index<0) throw new IndexOutOfBoundsException();
    LinkedNode<Book> pt = front; // pointer
    index--;
    while(index>=0) {
      pt = pt.getNext();
      index--;
    }
    return pt;
  }
  /**
   * Returns the Book object at the given index
   * @param index the index of the node that will be returned
   * @return the Book at the given index
   */
  public Book get(int index) {
    return getNode(index).getData();
  }
  
  /**
   * Returns the Book at the front of the list
   * @return the Book at the front of the list
   */
  public Book getFirst() {
    return front!=null?front.getData():null;
  }
  /**
   * Returns the Book at the back of the list
   * @return the Book at the back of the list
   */
  public Book getLast() {
    return back!=null?back.getData():null;
  }
  
  /**
   * Restores the shelf to an empty state
   */
  public void clear() {
    size = 0;
    sortedBy = Attribute.ID;
    front = back = null;
  }
  
  /**
   * Adds the provided Book object to the end of the linked list and increases the 
   * bookshelf's size accordingly
   * @param toAdd the Book object that will be added to the List
   */
  public void appendBook(Book toAdd) {
    if(size == 0) {
      front = back = new LinkedNode<Book>(toAdd);
    } else {
      LinkedNode<Book> toAddNode = new LinkedNode<Book>(toAdd);
      back.setNext(toAddNode);
      back = toAddNode;
    }
    size++;
  }
  
  /**
   * inserts the provided book at the correct location in the list
   * @param toAdd the Book object that will be added
   */
  public void insertBook(Book toAdd) {
    if(size == 0) {
      appendBook(toAdd);
    } else {
      LinkedNode<Book> toAddNode = new LinkedNode<Book>(toAdd);
      LinkedNode<Book> pt, ptNext;
      size++;
      pt = front;
      while(pt!=null) {
        ptNext = pt.getNext();
        // toAdd Book is less than the head Book
        if(pt == front && toAdd.compareTo(front.getData(), sortedBy) <= 0) {
            toAddNode.setNext(front);
            front = toAddNode;
            break;
        } else if(pt == back && toAdd.compareTo(back.getData(), sortedBy) >= 0) {
            back.setNext(toAddNode);
            back = toAddNode;
            break;
        } else if(ptNext != null && toAdd.compareTo(ptNext.getData(), sortedBy) <= 0 && 
          toAdd.compareTo(pt.getData(), sortedBy) >= 0) {
          toAddNode.setNext(ptNext);
          pt.setNext(toAddNode);
          break;
        } else {
          pt = ptNext;
        }
      }
    }
  }
  /**
   * Sort the bookshelf
   * @param b shelf that will be sorted
   * @param sortedBy sort kind
   */
  public static void sort(LinkedBookshelf b, Attribute sortedBy) {
    b.sortedBy = sortedBy;
    if(b.size == 0 || b.size == 1) return;
    LinkedNode<Book> pt = b.getNode(1);
    b.size = 1;
    b.front.setNext(null);
    b.back = b.front;
    while(pt!=null) {
      b.insertBook(pt.getData());
      pt = pt.getNext();
    }
  }
}

