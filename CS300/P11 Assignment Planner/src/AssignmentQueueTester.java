///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    AssignmentQueueTester.java
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

import java.util.NoSuchElementException;

/**
 * Tester class for the AssignmentQueue implementation of PriorityQueueADT
 */
public class AssignmentQueueTester {
  /**
   * Tests the functionality of the constructor for AssignmentQueue Should implement at least the
   * following tests:
   *
   * - Calling the AssignmentQueue with an invalid capacity should throw an IllegalArgumentException
   * - Calling the AssignmentQueue with a valid capacity should not throw any errors, and should
   * result in a new AssignmentQueue which is empty, and has size 0
   *
   * @return true if the constructor of AssignmentQueue functions properly
   * @see AssignmentQueue#AssignmentQueue(int)
   */
  public static boolean testConstructor() {
    // scenario 1: invalid capacity
    try {
      new AssignmentQueue(0);
      new AssignmentQueue(-1);
      return false;
    } catch(IllegalArgumentException  iae) {
      // normal behavior
    } catch(Exception e) {
      return false;
    }
    // scenario 2: valid capacity
    try {
      AssignmentQueue aq = new AssignmentQueue(1);
      if(!aq.isEmpty() || aq.size() != 0)
        return false;
    } catch(Exception e) {
      return false;
    }
    System.out.println("testConstructor(): passed");
    return true;
  }

  /**
   * Tests the functionality of the enqueue() and peek() methods Should implement at least the
   * following tests:
   *
   * - Calling peek on an empty queue should throw a NoSuchElementException 
   * - Calling enqueue on a queue which is empty should add the Assignment, and increment the size 
   *   of the queue
   * - Calling enqueue on a non-empty queue should add the Assignment at the proper position, 
   *   and increment the size of the queue. Try add at least 5 assignments 
   * - Calling peek on a non-empty queue should always return the Assignment with the earliest due
   *   date
   * - Calling enqueue on a full queue should throw an IllegalStateException 
   * - Calling enqueue with a null Assignment should throw a NullPointerException
   *
   * @return true if AssignmentQueue.enqueue() and AssignmentQueue.peek() function properly
   */
  public static boolean testEnqueue() {
    AssignmentQueue aq = new AssignmentQueue(6);
    // case 1
    try {
      aq.peek();
      return false;
    } catch(NoSuchElementException nsee) {
      // expected behavior
    } catch(Exception e) {
      return false;
    }
    // case 2
    try {
      Assignment p10 = new Assignment("CS300 P10", 12, 6, 22);
      aq.enqueue(p10);
      if(aq.size() != 1 || aq.isEmpty())
        return false;
      Assignment p11 = new Assignment("CS300 P11", 12, 13, 22);
      if(!aq.peek().equals(p10))
        return false;
      Assignment finalExam = new Assignment("CS 300 Final Exam", 12, 20, 17);
      Assignment HW10 = new Assignment("CS240 H10", 12, 6, 17);
      Assignment study300Plan = new Assignment("Study for CS300 Final", 12, 7, 11);
      Assignment study310Plan = new Assignment("CS 310 Study for the final", 12, 16, 15);
      aq.enqueue(p11);
      aq.enqueue(finalExam);
      aq.enqueue(HW10);
      aq.enqueue(study300Plan);
      aq.enqueue(study310Plan);
      if(!aq.peek().equals(HW10) || aq.size() != 6)
        return false;
    } catch(Exception  e) {
      return false;
    }
    try {
      Assignment HW10 = new Assignment("CS240 H10", 12, 6, 17);
      aq.enqueue(HW10);
      return false;
    } catch(IllegalStateException ise) {
      // normal behavior
    } catch(Exception e) {
      return false;
    }
    try {
      aq.enqueue(null);
      return false;
    } catch(NullPointerException npe) {
      // normal behavior
    } catch(Exception e) {
      return false;
    }
    System.out.println("testEnqueue(): passed");
    return true;
  }

  /**
   * Tests the functionality of dequeue() and peek() methods. The peek() method must return without
   * removing the assignment with the highest priority in the queue. The dequeue() method must
   * remove, and return the assignment with the highest priority in the queue. The size must be
   * decremented by one, each time the dequeue() method is successfully called. Try to check the
   * edge cases (calling peek and dequeue on an empty queue, and calling dequeue on a queue of size
   * one). For normal cases, try to consider dequeuing from a queue whose size is at least 6. Try to
   * consider cases where percolate-down recurses on left and right.
   * 
   * @return true if AssignmentQueue.dequeue() and AssignmentQueue.peek() function properly
   */
  public static boolean testDequeuePeek() {
    AssignmentQueue aq = new AssignmentQueue(10);
    try {
      Assignment p10 = new Assignment("CS300 P10", 12, 6, 22);
      Assignment p11 = new Assignment("CS300 P11", 12, 13, 22);
      Assignment finalExam = new Assignment("CS 300 Final Exam", 12, 20, 17);
      Assignment HW10 = new Assignment("CS240 H10", 12, 6, 17);
      Assignment study300Plan = new Assignment("Study for CS300 Final", 12, 7, 11);
      Assignment study310Plan = new Assignment("CS 310 Study for the final", 12, 16, 15);
      aq.enqueue(HW10);
      aq.enqueue(study300Plan);
      aq.enqueue(p10);
      aq.enqueue(p11);
      aq.enqueue(study310Plan);
      aq.enqueue(finalExam);
      if(!aq.peek().equals(HW10))
        return false;
      aq.dequeue();
      if(!aq.peek().equals(p10)|| aq.size() != 5)
        return false;
      aq.dequeue();
      if(!aq.peek().equals(study300Plan) || aq.size() !=  4)
        return false;
      aq.dequeue();
      if(!aq.peek().equals(p11) || aq.size() !=  3)
        return false;
      aq.dequeue();
      if(!aq.peek().equals(study310Plan) || aq.size() !=  2)
        return false;
      aq.dequeue();
      if(!aq.peek().equals(finalExam) || aq.size() !=  1)
        return false;
      aq.dequeue();
      if(aq.size() !=  0)
        return false;
    } catch(Exception e) {
      return false;
    }
    try {
      aq.peek();
      aq.dequeue();
      return false;
    } catch(NoSuchElementException nsee) {
      // normal exception
    } catch(Exception e) {
      return false;
    }
    
    System.out.println("testDequeuePeek(): passed");
    return true;
  }

  /**
   * Tests the functionality of the clear() method Should implement at least the following 
   * scenarios: 
   * - clear can be called on an empty queue with no errors 
   * - clear can be called on a non-empty queue with no errors - After calling clear, the queue 
   *    should contain no Assignments
   *
   * @return true if AssignmentQueue.clear() functions properly
   */
  public static boolean testClear() {
    AssignmentQueue aq = new AssignmentQueue(10);
    try {
      Assignment p10 = new Assignment("CS300 P10", 12, 6, 22);
      Assignment p11 = new Assignment("CS300 P11", 12, 13, 22);
      Assignment finalExam = new Assignment("CS 300 Final Exam", 12, 20, 17);
      Assignment HW10 = new Assignment("CS240 H10", 12, 6, 17);
      Assignment study300Plan = new Assignment("Study for CS300 Final", 12, 7, 11);
      Assignment study310Plan = new Assignment("CS 310 Study for the final", 12, 16, 15);
      aq.clear();
      aq.enqueue(HW10);
      aq.enqueue(study300Plan);
      aq.enqueue(p10);
      aq.enqueue(p11);
      aq.enqueue(study310Plan);
      aq.enqueue(finalExam);
      if(aq.size() != 6) {
        return false;
      }
      aq.clear();
      if(aq.size() != 0 || aq.capacity() != 10) {
        return false;
      }
    } catch(Exception e) {
      return false;
    }
    try {
      aq.peek();
      return false;
    } catch(NoSuchElementException nsee) {
      // normal behavior
    } catch(Exception e) {
      return false;
    }
    System.out.println("testClear(): passed");
    return true;
  }

  /**
   * Tests all the methods of the AssignmentQueue class
   * 
   */
  public static boolean runAllTests() {
    if(!(testConstructor() && testEnqueue() && testDequeuePeek() && testClear())) {
      System.err.println("Some tests failed");
      return false;
    }
    System.out.println("All tests passed");
    return true;
  }
  
  /**
   * Main method
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    runAllTests();
  }
}
