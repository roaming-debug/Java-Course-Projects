///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    AssignmentQueue.java
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
// Online Sources:  None
//                  
//
///////////////////////////////////////////////////////////////////////////////


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array-based heap implementation of a priority queue containing Assignments. Guarantees the
 * min-heap invariant, so that the Assignment at the root should have the earliest due date, and
 * children always have a due date after or at the same time as their parent. The root of a
 * non-empty queue is always at index 0 of this array-heap.
 */
public class AssignmentQueue implements PriorityQueueADT<Assignment>, Iterable<Assignment> {
  private Assignment[] queue; // array min-heap of assignments representing this priority queue
  private int size; // size of this priority queue


  /**
   * Creates a new empty AssignmentQueue with the given capacity
   * 
   * @param capacity Capacity of this AssignmentQueue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  public AssignmentQueue(int capacity) {
    if(capacity <= 0)
      throw new IllegalArgumentException("The capacity is negative");
    queue = new Assignment[capacity];
    size = 0;
  }

  /**
   * Checks whether this AssignmentQueue is empty
   * 
   * @return {@code true} if this AssignmentQueue is empty
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the size of this AssignmentQueue
   * 
   * @return the size of this AssignmentQueue
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns the capacity of this AssignmentQueue
   * 
   * @return the capacity of this AssignmentQueue
   */
  public int capacity() {
    return queue.length; 
  }
  
  
  /**
   * Removes all elements from this AssignmentQueue
   */
  @Override
  public void clear() {
    queue = new Assignment[capacity()];
    size = 0;
  }
  
  /**
   * Returns the Assignment at the root of this AssignmentQueue, i.e. the Assignment with the
   * earliest due date.
   * 
   * @return the Assignment in this AssignmentQueue with the earliest due date
   * @throws NoSuchElementException if this AssignmentQueue is empty
   */
  @Override
  public Assignment peek() {
    if(size == 0)
      throw new NoSuchElementException("Warning: Queue is empty!");
    return queue[0];
  }


  /**
   * Adds the given Assignment to this AssignmentQueue at the correct position based on the min-heap
   * ordering. This queue should maintain the min-heap invariant, so that the Assignment at each
   * index has an earlier or equivalent due-date than the Assignments in its child nodes.
   * Assignments should be compared using the Assignment.compareTo() method.
   * 
   * @param e Assignment to add to this AssignmentQueue
   * @throws NullPointerException  if the given Assignment is null
   * @throws IllegalStateException with a descriptive error message if this AssignmentQueue is full
   */
  @Override
  public void enqueue(Assignment e) {
    if(e == null)
      throw new NullPointerException("The given Assignment is null");
    if(capacity() == size)
      throw new IllegalStateException("The AssignmentQueue is full");
    queue[size] = e;
    size++;
    percolateUp(size-1);
  }

  /**
   * Removes and returns the Assignment at the root of this AssignmentQueue, i.e. the Assignment
   * with the earliest due date.
   * 
   * @return the Assignment in this AssignmentQueue with the earliest due date
   * @throws NoSuchElementException with a descriptive error message if this AssignmentQueue is
   *                                empty
   */
  @Override
  public Assignment dequeue() {
    Assignment best = queue[0];
    if(isEmpty())
      throw new NoSuchElementException("Warning: Queue is empty!");
    size--;
    if(size == 0) {
      queue[0] = null;
      return best;
    }
    // swap root and the last leaf
    Assignment temp = queue[0];
    queue[0] = queue[size];
    queue[size] = temp;
    percolateDown(0);
    return best;
  }

  /**
   * Recursive implementation of percolateDown() method. Restores the min-heap invariant of a given
   * subtree by percolating its root down the tree. If the element at the given index does not
   * violate the min-heap invariant (it is due before its children), then this method does not
   * modify the heap. Otherwise, if there is a heap violation, then swap the element with the
   * correct child and continue percolating the element down the heap.
   * 
   * @param i index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateDown(int i) {
    // provide the worst-case runtime complexity of this method assuming that the problem size
    // N is the size of this queue
    // Time complexity: O(log(N))
    int leftChildIndex = 2*i+1;
    int rightChildIndex = 2*i+2;
    int lesser = -1;
    if(rightChildIndex < size && queue[rightChildIndex].compareTo(queue[i]) < 0) {
      lesser = queue[leftChildIndex].compareTo(queue[rightChildIndex]) < 0?
          leftChildIndex : rightChildIndex;
    } else if(leftChildIndex < size && queue[leftChildIndex].compareTo(queue[i]) < 0) {
      lesser = leftChildIndex;
    }
    if(lesser != -1) {
      Assignment temp = queue[i];
      queue[i] = queue[lesser];
      queue[lesser] = temp;
      
      percolateDown(lesser);
    }
  }

  /**
   * Recursive implementation of percolateUp() method. Restores the min-heap invariant of the tree
   * by percolating a leaf up the tree. If the element at the given index does not violate the
   * min-heap invariant (it occurs after its parent), then this method does not modify the heap.
   * Otherwise, if there is a heap violation, swap the element with its parent and continue
   * percolating the element up the heap.
   * 
   * @param i index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateUp(int i) {
    // provide the worst-case runtime complexity of this method assuming that the problem size
    // N is the size of this queue
    // Time complexity: O(log(N))
    if(i == 0)
      return; // base case
    int parentIndex = (i-1)/2;
    if(queue[i].compareTo(queue[parentIndex]) < 0) {
      Assignment temp = queue[i];
      queue[i] = queue[parentIndex];
      queue[parentIndex] = temp;
    } else
      return;
    percolateUp(parentIndex);
    
  }
  
  /**
   * Returns a deep copy of this AssignmentQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate 
   * assignments. Only the instance of the heap (including the array and its size) will be
   * duplicated.
   * 
   * @return a deep copy of this AssignmentQueue. The returned new assignment queue has the same
   *         length and size as this queue.
   */
  public AssignmentQueue deepCopy() {
    AssignmentQueue copy = new AssignmentQueue(capacity());
    for(int i = 0; i < size; i++) {
      copy.enqueue(queue[i]);
    }
    return copy;
  }

  /**
   * Returns a String representing this AssignmentQueue, where each element (assignment) of the
   * queue is listed on a separate line, in order from earliest to latest.
   * 
   * @see Assignment#toString()
   * @see AssignmentIterator
   * @return a String representing this AssignmentQueue
   */
  public String toString() {
    StringBuilder val = new StringBuilder();

    for (Assignment a : this) {
      val.append(a).append("\n");
    }

    return val.toString();
  }

  /**
   * Returns an Iterator for this AssignmentQueue which proceeds from the earliest to the latest
   * Assignment in the queue.
   * 
   * @see AssignmentIterator
   * @return an Iterator for this AssignmentQueue
   */
  @Override
  public Iterator<Assignment> iterator() {
    return new AssignmentIterator(this);
  }
}
