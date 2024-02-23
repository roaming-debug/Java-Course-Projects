///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    FolderExplorerTester.java
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
// Online Sources:  P07_Folder_Content_Explorer.pdf
//                  This file helped the construct the whole code.
//
///////////////////////////////////////////////////////////////////////////////


import java.util.List;
import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 *
 */
public class FolderExplorerTester {
  /**
   * Test method for getContents()
   * @param folder File object 
   * @return true if the test passes, false otherwise
   */
  public static boolean testGetContents(File folder) {
    try {
      // Scenario 1
      // list the basic contents of the cs300 folder
      ArrayList<String> listContent = FolderExplorer.getContents(folder);
      // expected output must contain "exams preparation", "grades",
      // "lecture notes", "programs", "reading notes", "syllabus.txt",
      // and "todo.txt" only.
      String[] contents = new String[] {"exams preparation", "grades", "lecture notes", "programs",
          "reading notes", "syllabus.txt", "todo.txt"};
      List<String> expectedList = Arrays.asList(contents);
      // check the size and the contents of the output
      if (listContent.size() != 7) {
        System.out.println("Problem detected: cs300 folder must contain 7 elements.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of cs300 folder.");
          return false;
        }
      }
      // Scenario 2 - list the contents of the grades folder
      File f = new File(folder.getPath() + File.separator + "grades");
      listContent = FolderExplorer.getContents(f);
      if (listContent.size() != 0) {
        System.out.println("Problem detected: grades folder must be empty.");
        return false;
      }
      // Scenario 3 - list the contents of the p02 folder
      f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02");
      listContent = FolderExplorer.getContents(f);
      if (listContent.size() != 1 || !listContent.contains("FishTank.java")) {
        System.out.println(
            "Problem detected: p02 folder must contain only " + "one file named FishTank.java.");
        return false;
      }
      // Scenario 4 - List the contents of a file
      f = new File(folder.getPath() + File.separator + "todo.txt");
      try {
        listContent = FolderExplorer.getContents(f);
        System.out.println("Problem detected: Your FolderExplorer.getContents() must "
            + "throw a NotDirectoryException if it is provided an input which is not"
            + "a directory.");
        return false;
      } catch (NotDirectoryException e) { // catch only the expected exception
        // no problem detected
      }
      // Scenario 5 - List the contents of not found directory/file
      f = new File(folder.getPath() + File.separator + "music.txt");
      try {
        listContent = FolderExplorer.getContents(f);
        System.out.println("Problem detected: Your FolderExplorer.getContents() must "
            + "throw a NotDirectoryException if the provided File does not exist.");
        return false;
      } catch (NotDirectoryException e) {
        // behavior expected
      }
    } catch (Exception e) {
      System.out.println("Problem detected: Your FolderExplorer.getContents() has thrown"
          + " a non expected exception.");
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  /**
   * Check whether base case in deepGetContents() is correct
   * @param folder directory that is to be tested
   * @return true if base case is correct, false otherwise
   */
  public static boolean testDeepGetContentsBaseCase(File folder) {
    try {
      // case 1
      ArrayList<String> listContent = FolderExplorer.getDeepContents(folder);
      String[] contents = new String[] {"zyBooksCh1.txt", "zyBooksCh2.txt", "zyBooksCh3.txt",
          "zyBooksCh4.txt"};
      List<String> expectedList = Arrays.asList(contents);
      if(listContent.size() != 4) return false;
      for(String s: expectedList) {
        if(!listContent.contains(s)) {
          System.out.println(s + " is missing from the output of the list content.");
          return false;
        }
      }
      
      // case 2 - invalid direcotry name
      try {
        FolderExplorer.getDeepContents(new File("s"));
        return false;
      } catch(NotDirectoryException e) {
        // expected behavior
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return true;
  }
  
  /**
   * Test whether deepGetContents() is implemented correctly
   * @param folder directory that is to be tested
   * @return true if deepGetContents() is implemented is correct, false otherwise
   */
  public static boolean testDeepListRecursiveCase(File folder) {
    try {
      // case 1 - List cs300/programs directory
      ArrayList<String> listContent = FolderExplorer.getDeepContents(folder);
      String[] contents = new String[] {"ClimbingTracker.java", "ClimbingTrackerTester.java",
          "FishTank.java", "ExceptionalClimbing.java", "ExceptionalClimbingTester.java",
          "Program01.pdf", "Program02.pdf", "Program03.pdf"
      };
      List<String> expectedList = Arrays.asList(contents);
      if(listContent.size() != contents.length) {
        return false;
      }
      for(String s : expectedList) {
        if(!listContent.contains(s)) {
          System.out.println(s + " is missing from the output of the list content.");
          return false;
        }
      }
      
      
    } catch (Exception e) {
      System.out.println("Unexpected exception detected");
      return false;
    }
    return true;
  }
  
  /**
   * Test whether lookupByName() is implemented correctly   
   * @param folder directory that is to be tested
   * @return true if lookupByName() is implemented is correct, false otherwise
   */
  public static boolean testLookupByFileName(File folder) {
    String expected = "cs300" + File.separator + "programs" + File.separator + "p01" +
        File.separator + "ClimbingTrackerTester.java";
    if(!expected.equals(FolderExplorer.lookupByName(folder, "ClimbingTrackerTester.java"))) {
      return false; 
    }
    
    return true;
  }
  
  /**
   * Test whether lookupByKey() is implemented correctly
   * @param folder directory that is to be tested
   * @return true if lookupByKey() is implemented is correct, false otherwise
   */
  public static boolean testLookupByKeyBaseCase(File folder) {
    ArrayList<String> listContents = FolderExplorer.lookupByKey(folder, ".java");
    String[] contents = new String[] {"ClimbingTracker.java", "ClimbingTrackerTester.java"};
//    String[] contents = new String[] {"codeSamples.java", "ClimbingTracker.java",
//        "ClimbingTrackerTester.java", "ExceptionalClimbing.java", "ExceptionalClimbingTester.java"
//    };
    List<String> expected = Arrays.asList(contents);
    if(expected.size() != listContents.size()) return false;
    for(String s : expected) {
      if(!listContents.contains(s)) {
        System.out.println(s + " is missing from the output of the list content.");
        return false;
      }
    }
    return true;
  }
  
  /**
   * Tester main function
   * @param args command line arguements
   */
  public static void main(String[] args) {
    System.out.println("testGetContents: " + testGetContents(new File("cs300")));
    System.out.println("testDeepGetContentsBaseCase: " + 
        testDeepGetContentsBaseCase(new File("cs300" + File.separator + "reading notes")));
    System.out.println("testDeepListRecursiveCase: " + 
        testDeepListRecursiveCase(new File("cs300" + File.separator + "programs")));
    System.out.println("testLookupByFileName: " + testLookupByFileName(new File("cs300")));
    System.out.println("testLookupByKeyBaseCase: " + testLookupByKeyBaseCase(new File("cs300" +
        File.separator + "programs" + File.separator + "p01")));
//    System.out.println(FolderExplorer.lookupByName(new File("cs300"), "outline.txt"));
//    try {
//      ArrayList<String> t = FolderExplorer.getDeepContents(new File("cs300/programs"));
//      for(String s: t) {
//        System.out.println(s);
//      }
//    } catch (NotDirectoryException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
  }
}
