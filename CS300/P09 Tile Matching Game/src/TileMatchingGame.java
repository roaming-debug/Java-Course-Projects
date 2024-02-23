///////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    TileMatchingGame.java
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
// Online Sources:  P09_Tile_Matching_Game.pdf
//                  This file helped the construct the whole code.
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;

/**
 * Implements a text-based driver for the tile matching game
 */
public class TileMatchingGame {
  private TileStack [] columns;
  /**
   * Creates a new matching tile game with a given number of columns and initializes its contents.
   * A new matching tile game stores an empty TileStack at each of its columns.
   * @param columnCount number of columns in this game. (That is the capacity of the array columns)
   * @throws with a descriptive error message if columnNumber is less or equal to zero
   */
  public TileMatchingGame(int columnCount) {
    if(columnCount < 0 || columnCount == 0) {
      throw new IllegalArgumentException("Error: columnCount is less or equal to zero");
    }
    columns = new TileStack[columnCount];
    for(int i = 0; i < columnCount; i++) {
      columns[i] = new TileStack();
    }
  }
  /**
   * Removes all the tiles from a column with a given index
   * @param index  of the column to clear
   * @throws IndexOutOfBoundsException  if index is out of bounds of the 
   * indexes of the columns of this game
   */
  public void clearColumn(int index) {
    if(index < 0 || index >= columns.length) {
      throw new IndexOutOfBoundsException();
    }
    TileStack ts = columns[index];
    while(!ts.isEmpty()) {
      ts.pop();
    }
  }
  /**
   * Returns a string representation of the stack of tiles at a given column index,
   * and an empty string "" if the stack is empty. For instance, if the stack at column
   * index contains BLUE, BLACK, and ORANGE blocks as follows, top: BLUE BLACK ORANGE
   * <br>
   * the returned string will be: "BLUE BLACK ORANGE"
   * @param index the index of a column in this game
   * @return a string representation of the column at a given index of this game
   * @throws IndexOutOfBoundsException  if index is out of bounds of the indexes of the columns
   * of this game
   */
  public String column(int index) {
    String res = "";
    if(index < 0 || index >= columns.length) {
      throw new IndexOutOfBoundsException();
    }
    Iterator<Tile> it = columns[index].iterator();
    int size = columns[index].size();
    while(it.hasNext()) {
      if(size > 1) {
        res = res + it.next().toString() + " ";
      } else
        res = res + it.next().toString();
      size--;
    }
    return res;
  }
  /**
   * Returns a string representation of this tile matching game The format of the returned string
   * is as follows: 
   * GAME COLUMNS:\n<br>
   * [String representation of each column separated by newline]<br>
   * For instance, a game with 4 columns will be represented as follows.<br>
   * GAME COLUMNS: <br>
   * 0: <br>
   * 1: BLACK BLUE<br> 
   * 2: ORANGE YELLOW<br>
   * 3: YELLOW<br>
   * @return a string representation of this tile matching game
   */
  @Override
  public String toString() {
    String res = "";
    for(int i = 0; i < columns.length; i++) {
      res += i + ": ";
      res += column(i);
      if(i != columns.length-1)
        res += '\n'; 
    }
    return res;
  }
  
  /**
   * Drops a tile at the top of the stack located at the given column index.
   * If tile will be dropped at the top of an equal tile (of same color),
   * both tiles will be removed from the stack of tiles at column index.
   * @param tile a tile to drop
   * @param index  column position of the stack of tiles where tile will be dropped
   * @throws IndexOutOfBoundsException if index is out of bounds of the indexes of the
   * columns of this game
   */
  public void dropTile​(Tile tile, int index) {
    if(index < 0 || index >= columns.length) {
      throw new IndexOutOfBoundsException();
    }
    if(columns[index].size() == 0) {
      columns[index].push(tile);
    } else if(columns[index].peek().equals(tile)) {
      columns[index].pop();
    } else {
      columns[index].push(tile);
    }
  }
  /**
   * Restarts the game. All stacks of tiles in the different columns of this game will be empty
   */
  public void restartGame() {
    for(int i = 0; i < columns.length; i++) {
      clearColumn(i);
    }
  }
  /**
   * Gets the number of columns in this tile matching game
   * @return the number of columns in this tile matching game
   */
  public int getColumnsNumber() {
    return columns.length;
  }
  
}
