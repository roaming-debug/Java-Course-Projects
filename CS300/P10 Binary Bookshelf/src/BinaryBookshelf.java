///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    BinaryBookshelf.java
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

import java.util.ArrayList;


/**
 * A binary search tree version of our sorted Bookshelf, where all Books are
 * sorted based on (in decreasing order of importance) the Attributes contained in
 * the sortList. Books must be compared first based on their authors, and then on the
 * other Attributes in the order they appear in the sortList.
 */
public class BinaryBookshelf {
  private TreeNode<Book> root;
  private int size;
  private Attribute[] sortList;
  
  /**
   * One-argument constructor, initializes an empty BinaryBookshelf
   * @param sortList an ordered array of Attributes, must begin with AUTHOR and contain
   * exactly one copy of each Attribute in the enum
   * @throws IllegalArgumentException if the sortList argument is not a four-element
   * array of unique attributes, beginning with Attribute.AUTHOR
   */
  public BinaryBookshelf(Attribute[] sortList) {
    if(sortList.length != 4 || sortList[0] != Attribute.AUTHOR) {
      ArrayList<Attribute> al = new ArrayList<Attribute>();
      for(Attribute a : sortList) {
        al.add(a);
      }
      for(Attribute a : Attribute.values()) {
        if(!al.contains(a))
          throw new IllegalArgumentException("The argument is invalid.");
      }
    } 
    this.sortList = sortList;
    size = 0;
    root = null;
  }
  
  /**
   * O(1)
   * Get the number of nodes currently in the BST
   * @return the number of nodes currently in the BST
   */
  public int size() {
    return size;
  }
  
  /**
   * O(1)
   * Determine whether the BST is empty
   * @return true if the BST is empty, false otherwise
   */
  public boolean isEmpty() {
    return size == 0;
  }
  
  /**
   * Provides a String-formatted list of the attributes in the order in which they are used,
   * for example:<br>
   * <code>
   * "1:AUTHOR 2:PAGECOUNT 3:TITLE 4:ID"
   * </code>
   * @return a String-formatted list of the sorting attributes
   */
  public String getAttributeOrder() {
    String res = "";
    for(int i = 1; i <= sortList.length; i++) {
      res = res + i + ":" + sortList[i-1].name();
      if(i != sortList.length) {
        res += " ";
      }
    }
    return res;
  }
  
  /**
   * O(logN)
   * Searches for the input book in the bookshelf. This must be implemented recursively;
   * do not call toString() in this method.
   * @param book the book to search for
   * @return true if the book is present in the shelf, false otherwise
   */
  public boolean contains(Book book) {
    return containsHelper(book, root);
  }
  
  /**
   * Recursive helper method; searches for the input book in the subtree rooted at current
   * @param book the query book to search for
   * @param current the root of the current subtree
   * @return true if the book is contained in this subtree, false otherwise
   */
  protected boolean containsHelper(Book book, TreeNode<Book> current) {
    if(current == null)
      return false;
    if(compareToHelper(book, current.getData()) > 0)
      return containsHelper(book, current.getRight());
    if(compareToHelper(book, current.getData()) < 0)
      return containsHelper(book, current.getLeft());
    return true;
  }
  
  /**
   * OPTIONAL: helper method to compare two Book objects according to the sortList of attributes.
   * Uses both equals() and compareTo() from the Book class.
   * @param one the first Book
   * @param two the second Book
   * @return a negative value if one < two, a positive value if one > two, and 0 if they are equal
   */
  protected int compareToHelper(Book one, Book two) {
    int [] comp = new int[4];
    for(int i = 0; i < sortList.length; i++) {
      comp[i] = one.compareTo(two, sortList[i]);
    }
    if(one.equals(two))
      return 0;
    if(comp[0] != 0)
      return comp[0];
    if(comp[1] != 0)
      return comp[1];
    if(comp[2] != 0)
      return comp[2];
    if(comp[3] != 0)
      return comp[3];
    return 0; // will not be executed
  }
  
  /**
   * Returns a list of books in the bookshelf written by the given author
   * @param authorName the author name to filter on
   * @return a list of books by the author
   */
  public ArrayList<Book> getBooksByAuthor(String authorName) {
    return getBooksByAuthorHelper(authorName, root);
  }
  
  /**
   * Recursive helper method; returns a list of books in the subtree rooted at current which
   * were written by the given author
   * @param authorName the author name to filter on
   * @param current the root of the current subtrees
   * @return a list of books by the author in the current subtree
   */
  protected ArrayList<Book> getBooksByAuthorHelper(String authorName, TreeNode<Book> current) {
    ArrayList<Book> list = new ArrayList<Book>();
    // base case
    if(current == null)
      return list;
    
    String nodeAuthor = current.getData().getAuthor();
    if(nodeAuthor.equals(authorName)) {
      list.add(current.getData());
    }
    if(authorName.compareTo(nodeAuthor) >= 0){
      list.addAll(getBooksByAuthorHelper(authorName, current.getRight()));
    } else {
      list.addAll(getBooksByAuthorHelper(authorName, current.getLeft()));
    }
    return list;
  }
  
  /**
   * O(N)
   * Creates and returns an in-order traversal of the BST, with each Book on a separate line
   * @return an in-order traversal of the BST, with each Book on a separate line
   */
  @Override
  public String toString() {
    String ret = toStringHelper(root);
    if(ret.equals("")) return "";
    return ret.substring(0, ret.length()-1);
  }
  
  /**
   * Recursive helper method; creates and returns the String representation of the subtree
   * rooted at the current node
   * @param current 
   * @return the string representation of this subtree
   */
  protected String toStringHelper(TreeNode<Book> current) {
    String res = "";
    if(current == null) {
      return res;
    }
    String left = toStringHelper(current.getLeft());
    String right = toStringHelper(current.getRight());
    if(!left.equals("")) {
      res += left;
    }
    res += current.getData().toString() + '\n';
    if(!right.equals("")) {
      res += right;
    }
    return res;
  }
  
  /**
   * Returns a shallow copy of the root node so that test tree structures may be constructed
   * manually rather than by using the insertBook() method
   * @return a reference to the current root node
   */
  protected TreeNode<Book> getRoot() {
    return root;
  }

  /**
   * Rests the BST to be empty
   */
  public void clear() {
    root = null;
    size = 0;
  }
  
  /**
   * Adds a new Book to the BST in sorted order, relative to this BST's sortList attributes
   * @param book the Book object to be added to the BST
   * @throws IllegalArgumentException if this Book is already in the BST
   */
  public void insertBook(Book book) {
    insertBookHelper(book, root);
  }
  
  /**
   * Recursive helper method; adds the given Book to the subtree rooted at current.
   * @param book the Book object to be added to the BST
   * @param current the root of the current subtree
   * @throws IllegalArgumentException if this Book is already in the BST
   */
  protected void insertBookHelper(Book book, TreeNode<Book> current) {
    if(root == null) {
      root = new TreeNode<Book>(book);
      size++;
      return;
    }
    if(compareToHelper(book, current.getData()) == 0)
      throw new IllegalArgumentException("This Book is already in the BST");
    if(compareToHelper(book, current.getData()) > 0) {
      if(current.getRight() == null) {
        current.setRight(new TreeNode<Book>(book));
        size++;
      }
      else
        insertBookHelper(book, current.getRight());
    }
    if(compareToHelper(book, current.getData()) < 0) {
      if(current.getLeft() == null) {
        current.setLeft(new TreeNode<Book>(book));
        size++;
      }
      else
        insertBookHelper(book, current.getLeft());
    }
  }
}
