///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    FolderExplorer.java
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

import java.io.File;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.NoSuchElementException;


/**
 * Folder Explorer class
 *
 */
public class FolderExplorer {
  /**
   * Returns a list of the names of all files and directories in the the 
   * given folder currentDirectory.
   * @param currentDirectory
   * @return a list of the names of all files and directories in the the 
   * given folder currentDirectory.
   * @throws NotDirectoryException with a description error message if the provided
   * currentDirectory does not exist or if it is not a directory
   */
  public static ArrayList<String> getContents(File currentDirectory)
      throws NotDirectoryException {
    if(!currentDirectory.isDirectory()) {
      throw new NotDirectoryException("This is not a directory.");
    } else if(currentDirectory.list().length == 0) {
      return new ArrayList<String>();
    }
    String [] contents = currentDirectory.list();
    ArrayList<String> result = new ArrayList<String>();
    for(int i = 0; i < contents.length; i++) {
      result.add(contents[i]);
    }
    return result;
  }

  /**
   * Recursive method that lists the names of all the files (not directories) 
   * in the given directory and its sub-directories.
   * @param currentDirectory
   * @return
   * @throws NotDirectoryException with a description error message if 
   * the provided currentDirectory does not exist or if it is not a directory
   */
  public static ArrayList<String> getDeepContents (File currentDirectory)
      throws NotDirectoryException {
    if(!currentDirectory.isDirectory()) throw new NotDirectoryException("This is not a directory.");
    boolean foundDir = false;
    ArrayList<File> dirArr = new ArrayList<File>();
    ArrayList<String> fileName = new ArrayList<String>();
    File[] files = currentDirectory.listFiles();
    for(int i = 0; i < files.length; i++) {
      if(files[i].isDirectory()) {
        dirArr.add(files[i]);
        foundDir = true;
      } else {
        fileName.add(files[i].getName());
      }
    }
    if(foundDir) {
      for(File f: dirArr) {
        fileName.addAll(getDeepContents(f));
      }
    }
    return fileName;
  }
  
  private static String lookupByNameHelper(File currentDirectory, String fileName) {
    String path = "";
    File[] files = currentDirectory.listFiles();
    for(File f: files) {
      if(f.isDirectory()) {
        path = lookupByNameHelper(f, fileName);
        if(!path.equals("")) {
          path = currentDirectory.getName() + File.separator +path;
          return path;
        }
      } else if(f.getName().equals(fileName)) {
        path = currentDirectory.getName() + File.separator + fileName;
        break;
      }
    }
    return path;
  }
  
  /**
   * Searches the given directory and all of its sub-directories for an exact match to 
   * the provided fileName. This method must be recursive or must use a recursive 
   * private helper method to operate.
   * @param currentDirectory
   * @param fileName
   * @return a path to the file, if it exists
   * @throws NoSuchElementException with a descriptive error message if the search operation 
   * returns with no results found (including the case if fileName is null or currentDirectory
   * does not exist, or was not a directory)
   */
  public static String lookupByName (File currentDirectory, String fileName) {  
    if(fileName == null || !currentDirectory.exists() || !currentDirectory.isDirectory()) {
      throw new NoSuchElementException();
    }
    String path = lookupByNameHelper(currentDirectory, fileName);
    if(path.equals("")) throw new NoSuchElementException("File Not Found"); 
    return path;
  }
  /**
   * Recursive method that searches the given folder and its sub-directories for
   * ALL files that contain the given key in part of their name.
   * @param currentDirectory current directory
   * @param key keywords
   * @return An arraylist of all the names of files that match and an empty arraylist
   * when the operation returns with no results found (including the case where
   * currentDirectory is not a directory).
   */
  public static ArrayList<String> lookupByKey (File currentDirectory, String key) {
    ArrayList<String> result = new ArrayList<String>();
    if(!currentDirectory.isDirectory()) return result;
    File[] files = currentDirectory.listFiles();
    for(File f : files) {
      if(f.isDirectory()) {
        result.addAll(lookupByKey(f, key));
      } else if(f.isFile() && f.getName().contains(key)) {
        result.add(f.getName());
      }
    }
    return result;
  }
  /**
   * Recursive method that searches the given folder and its sub-directories 
   * for ALL files whose size is within the given max and min values, inclusive.
   * @param currentDirectory current directory
   * @param sizeMin minimum size
   * @param sizeMax maximum size
   * @return an array list of the names of all files whose size are within the boundaries
   * and an empty arraylist if the search operation returns with no results 
   * found (including the case where currentDirectory is not a directory). 
   */
  public static ArrayList<String> lookupBySize(File currentDirectory, long sizeMin,
        long sizeMax) {
    ArrayList<String> result = new ArrayList<String>();
    if(!currentDirectory.isDirectory()) return result;
    File[] files = currentDirectory.listFiles();
    for(File f : files) {
      if(f.isDirectory()) {
        result.addAll(lookupBySize(f, sizeMin, sizeMax));
      } else if(f.isFile() && f.length() <= sizeMax && f.length() >= sizeMin) {
        result.add(f.getName());
      }
    }
    return result;
  }
}