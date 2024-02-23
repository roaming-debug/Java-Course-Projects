///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    BinaryBookshelfTester.java
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
// Online Sources:  Program 10.pdf
//                  This file helped the construct the whole code.
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Test BinaryBookshelf class
 */
public class BinaryBookshelfTester {
  /**
   * Test the correctness of the implementation of the TreeNode class
   * @return true if no bugs detected, false otherwise
   */
  public static boolean testTreeNode() {
    // case 1
    TreeNode<Integer> root = new TreeNode<Integer>(1);
    if(root.getLeft() != null || root.getRight() != null || !root.getData().equals(1) ||
        !root.toString().equals("1"))
      return false;
    
    // case 2
    TreeNode<Integer> node2 = new TreeNode<Integer>(99);
    root.setRight(node2);
    if(root.getRight() == null || !root.getRight().getData().equals(node2.getData()))
      return false;
    if(root.getLeft() != null)
      return false;
    root.setRight(null);
    if(root.getRight() != null)
      return false;
    
    // case 3
    TreeNode<Character> char1 = new TreeNode<Character>('b');
    TreeNode<Character> char2 = new TreeNode<Character>('c');
    TreeNode<Character> char0 = new TreeNode<Character>('a', char1, char2);
    if(!char0.getLeft().getData().equals(char1.getData()) || 
        !char0.getRight().getData().equals(char2.getData())) {
      return false;
    }
    
    // case 4
    root = new TreeNode<Integer>(1);
    TreeNode<Integer> node3 = new TreeNode<Integer>(88);
    root.setLeft(node3);
    if(root.getLeft() == null || !root.getLeft().getData().equals(node3.getData()))
      return false;
    if(root.getRight() != null)
      return false;
    System.out.println("testTreeNode: passed");
    return true;
  }
  
  /**
   * Test the correctness of the implementation of the BinaryBookshelf class
   * @return false if any bugs detected, true otherwise
   */
  public static boolean testEmptyTree() {
    Book.resetGenerator();
    // scenario 1: invalid constructor inputs
    try {
      // empty Attribute array
      Attribute [] sortList = new Attribute[0];
      BinaryBookshelf bookshelf = new BinaryBookshelf(sortList);
      // length other than 4
      sortList = new Attribute[3];
      bookshelf = new BinaryBookshelf(sortList);
      sortList = new Attribute[5];
      bookshelf = new BinaryBookshelf(sortList);
      // array with 4 length, at lease 2 elements that are the same
      sortList = new Attribute[4];
      sortList[0] = Attribute.AUTHOR;
      sortList[1] = Attribute.ID;
      sortList[2] = Attribute.ID;
      sortList[3] = Attribute.PAGECOUNT;
      // array with something other than Attribute.AUTHOR at index 0
      bookshelf = new BinaryBookshelf(sortList);
      sortList[0] = Attribute.ID;
      sortList[1] = Attribute.AUTHOR;
      sortList[2] = Attribute.TITLE;
      sortList[3] = Attribute.PAGECOUNT;
      bookshelf = new BinaryBookshelf(sortList);
      return false;
    } catch(IllegalArgumentException iae) {
      // expected result
    } catch(Exception e) {
      return false;
    }
    // scenario 2: valid input
    try {
      // valid parameter
      Attribute [] sortList = new Attribute[4];
      sortList[0] = Attribute.AUTHOR;
      sortList[1] = Attribute.PAGECOUNT;
      sortList[2] = Attribute.ID;
      sortList[3] = Attribute.TITLE;
      BinaryBookshelf bookshelf = new BinaryBookshelf(sortList);
      // verify the size is 0
      if(bookshelf.size() != 0 || bookshelf.isEmpty() != true || !bookshelf.toString().equals("")
          || bookshelf.getRoot() != null)
        return false;
      // verify getAttributeOrder()
      if(!bookshelf.getAttributeOrder().equals("1:AUTHOR 2:PAGECOUNT 3:ID 4:TITLE"))
        return false;
      // verify contains()
      Book book1 = new Book("Attack on Titan", 8909);
      if(bookshelf.contains(book1))
        return false;
      // verify getBooksByAuthor()
      if(bookshelf.getBooksByAuthor("Hajime Isayama").size() != 0)
        return false;
    } catch (Exception e) {
      return false;
    }
    System.out.println("testEmptyTree: passed");
    return true;
  }
  
  /**
   * Test BinaryBookshelf insertBook() method
   * @return true if no bugs is detected, false otherwise
   */
  public static boolean testInsertBook() {
    Book.resetGenerator();
    Attribute [] sortList;
    BinaryBookshelf bookshelf;
    Book book1 = new Book("Attack on Titan", 8909, "Isayama", "Hajime");
    Book book2 = new Book("Qi Li Xiang", 300, "Chou", "Jay");
    Book book3 = new Book("Attack on Titan 2", 10000, "Isayama", "Hajime");
    Book book4 = book3;;
    try {
      // scenario 1: inserting into an empty tree
      // valid parameter
      sortList = new Attribute[4];
      sortList[0] = Attribute.AUTHOR;
      sortList[1] = Attribute.PAGECOUNT;
      sortList[2] = Attribute.ID;
      sortList[3] = Attribute.TITLE;
      bookshelf = new BinaryBookshelf(sortList);
      // verify the size is 0
      if(bookshelf.size() != 0 || bookshelf.isEmpty() != true || !bookshelf.toString().equals("")
          || bookshelf.getRoot() != null)
        return false;
      // insert a single book
      bookshelf.insertBook(book1);
      if(bookshelf.size() != 1)
        return false;
      // verify root
      if(!bookshelf.getRoot().getData().equals(book1)) {
        return false;
      }
      
      // scenario 2: insert a unique, smaller value
      bookshelf.insertBook(book2);
      if(!bookshelf.getRoot().getLeft().getData().equals(book2) || bookshelf.size() != 2) {
        return false;
      }
      if(bookshelf.getRoot().getRight() != null) {
        return false;
      }
      // scenario 3: insert a value with a shared author
      bookshelf.insertBook(book3);
      if(!bookshelf.getRoot().getRight().getData().equals(book3))
        return false;
    } catch(Exception e) {
      return false;
    }

    // scenario 4: insert a duplicate value
    try {
      bookshelf.insertBook(book4);
      return false;
    } catch(IllegalArgumentException iae) {
      // expected behaviour
    } catch(Exception e) {
      return false;
    }
    // scenario 5: test complicated scenarios
    Book book5 = new Book("2001", 296, "Clarke", "Arthur C");
    Book book6 = new Book("FEED", 608, "Grant", "Mira");
    Book book7 = new Book("Good Omens", 288, "Gaiman", "Neil");
    Book book8 = new Book("Snow Crash", 468, "Stephenson", "Neal");
    try {
      bookshelf.insertBook(book5);
      bookshelf.insertBook(book6);
      bookshelf.insertBook(book7);
      bookshelf.insertBook(book8);
      String expected = "1: \"Qi Li Xiang\", Chou, Jay (300)\n"
          + "3: \"2001\", Clarke, Arthur C (296)\n"
          + "5: \"Good Omens\", Gaiman, Neil (288)\n"
          + "4: \"FEED\", Grant, Mira (608)\n"
          + "0: \"Attack on Titan\", Isayama, Hajime (8909)\n"
          + "2: \"Attack on Titan 2\", Isayama, Hajime (10000)\n"
          + "6: \"Snow Crash\", Stephenson, Neal (468)";
      System.out.println(bookshelf);
      if(!bookshelf.toString().equals(expected)) {
        return false;
      }
    } catch(Exception e) {
      return false;
    }
    System.out.println("testInsertBook: passed");
    return true;
  }
  
  /**
   * Test contains() method
   * @return true if no bug is detected, false otherwise
   */
  public static boolean testContains() {
    Book.resetGenerator();
    // scenario 1: non-recursive case
    Attribute [] sortList = new Attribute[4];
    sortList[0] = Attribute.AUTHOR;
    sortList[1] = Attribute.PAGECOUNT;
    sortList[2] = Attribute.ID;
    sortList[3] = Attribute.TITLE;
    BinaryBookshelf bookshelf = new BinaryBookshelf(sortList);
    Book book1 = new Book("Attack on Titan", 8909, "Isayama", "Hajime");
    bookshelf.insertBook(book1);
    if(!bookshelf.contains(book1))
      return false;
    Book book2 = new Book("Qi Li Xiang", 300, "Chou", "Jay");
    if(bookshelf.contains(book2))
      return false;
    // scenario 2: recursive case
    Book book3 = new Book("Attack on Titan 2", 10000, "Isayama", "Hajime");
    Book book5 = new Book("2001", 296, "Clarke", "Arthur C");
    Book book6 = new Book("FEED", 608, "Grant", "Mira");
    Book book7 = new Book("Good Omens", 288, "Gaiman", "Neil");
    TreeNode<Book> root = bookshelf.getRoot();
    root.setLeft(new TreeNode<Book>(book2));
    root.setRight(new TreeNode<Book>(book3));
    root.getLeft().setRight(new TreeNode<Book>(book5));
    root.getLeft().getRight().setRight(new TreeNode<Book>(book6));
    if(!bookshelf.contains(book2))
      return false;
    if(!bookshelf.contains(book3))
      return false;
    if(!bookshelf.contains(book5))
      return false;
    if(!bookshelf.contains(book6))
      return false;
    if(bookshelf.contains(book7))
      return false;
    System.out.println("testContains: passed");
    return true;
  }
  
  /**
   * Test getBooksByAuthor() method
   * @return true if no bug is detected, false otherwise
   */
  public static boolean testGetBooksByAuthor() {
    Book.resetGenerator();
    // scenario 1: non-recursive case
    Attribute [] sortList = new Attribute[4];
    sortList[0] = Attribute.AUTHOR;
    sortList[1] = Attribute.PAGECOUNT;
    sortList[2] = Attribute.ID;
    sortList[3] = Attribute.TITLE;
    BinaryBookshelf bookshelf = new BinaryBookshelf(sortList);
    Book book1 = new Book("Attack on Titan", 8909, "Isayama", "Hajime");
    Book book2 = new Book("Qi Li Xiang", 300, "Chou", "Jay");
    bookshelf.insertBook(book1);
    if(bookshelf.getBooksByAuthor(book1.getAuthor()).size() != 1)
      return false;
    if(bookshelf.getBooksByAuthor(book2.getAuthor()).size() != 0)
      return false;
    // scenario 2: recursive case
    Book book3 = new Book("Attack on Titan 2", 10000, "Isayama", "Hajime");
    Book book5 = new Book("2001", 296, "Clarke", "Arthur C");
    Book book6 = new Book("FEED", 608, "Grant", "Mira");
    Book book7 = new Book("Good Omens", 288, "Gaiman", "Neil");
    TreeNode<Book> root = bookshelf.getRoot();
    root.setLeft(new TreeNode<Book>(book2));
    root.setRight(new TreeNode<Book>(book3));
    root.getLeft().setRight(new TreeNode<Book>(book5));
    root.getLeft().getRight().setRight(new TreeNode<Book>(book6));
    if(bookshelf.getBooksByAuthor(book2.getAuthor()).size() != 1)
      return false;
    if(bookshelf.getBooksByAuthor(book1.getAuthor()).size() != 2)
      return false;
    if(bookshelf.getBooksByAuthor(book7.getAuthor()).size() != 0)
      return false;
    
    System.out.println("testGetBooksByAuthor: passed");
    return true;
  }
  
  /**
   * Run the tester
   * @param args command line arguments
   */
  public static void main(String[] args) {
    if(!(testTreeNode() && testEmptyTree() && testInsertBook() && testContains()
        && testGetBooksByAuthor())) {
      System.err.println("Some tests failed");
    }
  }
}
