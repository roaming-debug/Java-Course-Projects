///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    TreeNode.java
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
 * 
 * A generic binary tree node
 *
 * @param <T> the type of data stored in this TreeNode
 */
public class TreeNode <T> {
  private T data;
  private TreeNode<T> left;
  private TreeNode<T> right;
  
  /**
   * Constructs a node with the given data and no children
   * @param data the data that will be set
   */
  public TreeNode(T data) {
    this.data = data;
    left = null;
    right = null;
  }
  
  /**
   * Constructs a node with the given data and children
   * @param data the data to be contained in this node 
   * @param left the left child of this node, may be null
   * @param right the right child of this node, may be null
   */
  public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }
  
  /**
   * Accessor for the data contained in the node
   * @return the data contained in the node
   */
  public T getData() {
    return data;
  }
  
  /**
   * Accessor for the left child of the node
   * @return a reference to the left child of this node
   */
  public TreeNode<T> getLeft() {
    return left;
  }
  
  /**
   * Accessor for the right child of the node
   * @return a reference to the right child of this node
   */
  public TreeNode<T> getRight() {
    return right;
  }
  
  /**
   * Change the left child reference of this node; may be null
   * @param left the new left child reference
   */
  public void setLeft(TreeNode<T> left) {
    this.left = left;
  }
  
  /**
   * Change the right child reference of this node; may be null
   * @param right the new right child reference
   */
  public void setRight(TreeNode<T> right) {
    this.right = right;
  }
  /**
   * Returns a string representation of this node's data
   * @return a string representation of this node's data
   */
  @Override
  public String toString() {
    return data.toString();
  }
}
