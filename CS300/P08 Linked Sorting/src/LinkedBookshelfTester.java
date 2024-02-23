///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    LinkedBookshelfTester.java
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
 * Tester for LinkedBookshelf & LinedNode class
 */
public class LinkedBookshelfTester {
  /**
   * Test LinkedNode constructor, accessor, mutator
   * @return true if and only if all of tests pass
   */
  public static boolean testLinkedNode() {
    // test constructor & accessor
    Integer expectedData = 2;
    LinkedNode<Integer> a = new LinkedNode<Integer>(1);
    LinkedNode<Integer> b = new LinkedNode<Integer>(2, a);
    if(b.getData() != expectedData || b.getNext() != a) {
      return false;
    }
    // test mutator
    a.setNext(b);
    if(a.getNext() != b) return false;
    
    System.out.println("testLinedNode(): passed");
    return true;
  }
  
  /**
   * Tests the correctness of LinkedBookshelf's clear() method 
   * @return true if the function is correct
   */
  public static boolean testClear() {
    Book.resetGenerator();
    LinkedBookshelf s = new LinkedBookshelf();
    Book a = new Book("Good Omens", 288);
    Book b = new Book("FEED", 608);
    s.appendBook(a);
    s.appendBook(b);
    s.clear();
    try {
      if(s.getFirst()!=null) return false;
    } catch(NullPointerException npe) {
      // normal behavior
    }
    try {
      if(s.getLast()!=null) return false;
    } catch (NullPointerException npe) {
      // normal behavior
    }
    if(!s.isEmpty()) return false;
    System.out.println("testClear(): passed");
    return true; 
  }
  /**
   * Tests the correctness of LinkedBookshelf's appendBook() method 
   * @return true if the function is correct
   */
  public static boolean testAddBooks() {
    Book.resetGenerator();
    Book a = new Book("Good Omens", 288);
    Book b = new Book("FEED", 100);
    Book c = new Book("Snow Crash", 468);
    Book d = new Book("2001", 296);
    try {
      LinkedBookshelf s = new LinkedBookshelf();
      s.appendBook(a);
      if(s.size() != 1) return false;
      s.appendBook(b);
      s.appendBook(c);
      s.appendBook(d);
      if(s.getFirst() != a || s.getNode(0).getNext().getData() != b || 
          s.getNode(0).getNext().getNext().getData() != c ||
          s.getLast() != d || s.size() != 4) {
        return false;
      }
      System.out.println("testAddBooks(): passed");
    } catch(Exception e) {
      return false;
    }
    return true; 
  }
  /**
   * Tests the correctness of LinkedBookshelf's sort() method
   * @return true if the function is correct
   */
  public static boolean testSortBooks() {
    Book.resetGenerator();
    Book a = new Book("Good Omens", 288, "Gaiman", "Neil");
    Book b = new Book("FEED", 608, "Grant", "Mira");
    Book c = new Book("Snow Crash", 468, "Stephenson", "Neal");
    Book d = new Book("2001", 296, "Clarke", "Arthur C");
    LinkedBookshelf s = new LinkedBookshelf();
    s.appendBook(a);
    s.appendBook(b);
    s.appendBook(c);
    s.appendBook(d);
    LinkedBookshelf.sort(s, Attribute.TITLE);
    String expected = "TITLE\n"
        + "3: \"2001\", Clarke, Arthur C (296)\n"
        + "1: \"FEED\", Grant, Mira (608)\n"
        + "0: \"Good Omens\", Gaiman, Neil (288)\n"
        + "2: \"Snow Crash\", Stephenson, Neal (468)\n";
    if(!expected.equals(s.toString())) return false;
    LinkedBookshelf.sort(s, Attribute.PAGECOUNT);
    expected = "PAGECOUNT\n"
        + "0: \"Good Omens\", Gaiman, Neil (288)\n"
        + "3: \"2001\", Clarke, Arthur C (296)\n"
        + "2: \"Snow Crash\", Stephenson, Neal (468)\n"
        + "1: \"FEED\", Grant, Mira (608)\n";
    if(!expected.equals(s.toString())) return false;
    System.out.println("testSortBooks(): passed");
    return true; 
  }
  /**
   * Run all tests 
   * @return true if all the function are correct
   */
  public static boolean runAllTests() {
    if(!testLinkedNode() || !testAddBooks() || !testClear() || !testSortBooks()) {
      System.err.println("Test failed");
      return false;
    }
    System.out.println("All tests passed");
    return true; 
  }
  
  /**
   * main function for the Tester
   * @param args command line arguments
   */
  public static void main(String[] args) {
    runAllTests();
  }

}
