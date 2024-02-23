import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

/**
 * This class implements a text-based driver for the Tile Matching Game
 * @author Mouna
 *
 */
public class TileMatchingDriver {
  private static final String WELCOME_MSG =
      "=============   Welcome to the Tile Matching Game  " + "=============";
  private static final String QUIT_MSG =
      "==============    Thank you for using our GAME!!!!!    ==============";
  private static final String PROMPT_COMMAND_LINE = "ENTER COMMAND: ";
  private static final String MENU =
      "\nCOMMAND MENU:\n" + " [D <index>] Drop next tile at column index\n"
          + " [N] Restart the game\n" + " [Q]uit the game\n";

  /**
   * Helper method to process user command lines
   * @param game a tile matching game
   * @param tile next tile to drop
   * @param command user command line
   */
  private static void processUserCommandLine(TileMatchingGame game, Tile tile, String command) {

    // parse the command line
    String[] input = command.trim().split(" "); // Array of Strings representing the command
    // process the command with respect to the first character
    switch (Character.toUpperCase(input[0].charAt(0))) {
      case 'D': // Drop a tile
        if (input.length == 2) {
          try {
            game.dropTile​(tile, Integer.valueOf(input[1])); // drop tile
            // an InputMismatchException will be thrown and the program will crash if input[1] is
            // not
            // convertible to the type int
          } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println(
                "Please enter a valid index in the range of 0 .. " + game.getColumnsNumber());
          }
        } else {
          System.out.println("Please enter a valid command line");
        }
        break;
      case 'N': // restart the game
        game.restartGame();
        break;
      case 'Q': // Quit the game
        break;
      default:
        System.out.println("Please enter a valid command");
    }
  }

  /**
   * Helper method to drive the game
   */
  private static void driver() {
    // create a new TileMatchingGame
    TileMatchingGame game = new TileMatchingGame(10);
    // get an array of all the valid tile colors
    Color[] colors = Color.values();
    // create a random rumber generator
    Random randGen = new Random();
    Tile tile = new Tile(colors[randGen.nextInt(colors.length)]);

    // Scanner to read user input line commands
    Scanner scanner = new Scanner(System.in);


    char command = ' '; // first character in the user command line 
    do {
      // create next tile
      tile = new Tile(colors[randGen.nextInt(colors.length)]);
      // display columns contents
      System.out.println("\n" + game);
      // Display menu
      System.out.println(MENU);
      // display next tile to be dropped
      System.out.println("Next Tile: " + tile);
      // prompt user
      System.out.print(PROMPT_COMMAND_LINE);
      // read user command line
      String commandLine = scanner.nextLine().trim(); // input line command
      command = commandLine.charAt(0);// first character in the user command line

      // parse and process user command line
      processUserCommandLine(game, tile, commandLine);

    } while (Character.toUpperCase(command) != 'Q');

  }

  /**
   * Main method for the text-based Tile Matching Driver
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    // Display Welcome message
    System.out.println(WELCOME_MSG);

    // start playing
    driver();

    // Display Good bye message
    System.out.println(QUIT_MSG);

  }
}
