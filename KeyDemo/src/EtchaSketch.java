import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class EtchaSketch
		implements NativeKeyListener {

	// Directional data for the direction last input by the user.
	static String direction = null;

	public static void main(String[] args)
			throws InterruptedException {

		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		/*
		 * This block of code handles the KeyListener so the user can input
		 * the direction they want snek to go.
		 */

		// Turns off the red output and keeps the cmd clear of gross stuff.
		// Get the logger for "org.jnativehook" and set the level to off.
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		// Disable Parent Handlers
		logger.setUseParentHandlers(false);
		// Thread that runs the NativeKeyListener
		Thread thread = new Thread() {
			@Override
			public void run() {
				GlobalScreen.addNativeKeyListener(new EtchaSketch());
			}
		};

		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		// Start thread
		try {
			GlobalScreen.registerNativeHook();
			thread.start();
		} catch (NativeHookException ex) {
			System.err.println("NativeHookException");
		}

		String[][] board = gridInit();
		String [][] directions = new String[board.length][board[0].length];
		boolean hasLost = false;

		// package parallel 2 dimensional arrays
		ArrayList<String[][]> arrs = new ArrayList<>();

		arrs.add(board);
		arrs.add(directions);

		// Start Game Loop:
		while(!hasLost) {
			try {
				updateFrame(arrs);
			} catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
			clear();
			printBoard(board);
			// checkForLoss will happen in updateFrame method
			Thread.sleep(150);
		}
		System.out.println("You Lost!");
	}

	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~

	/*
	 * Checks what os the program is being run on. if it is run on windows, a new command is created that clears the
	 * command prompt when called. Otherwise the pre-existing command is used.
	 */
	public static void clear() {

		try {

			if(System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		}
		catch (IOException | InterruptedException ex) {}
	}

	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~

	/*
	 * Isaak Weidman, Brennan Mulligan	Pd.8	5/23/2019
	 * This is my own work, IW
	 * This is my own work, BM
	 *
	 * Description:
	 * Is called when user presses a key and only does something if they press an arrow key.
	 * nativeKeyReleased and nativeKeyTyped don't do anything and only need to be here to make java happy because snek
	 * implements NativeKeyListener.
	 * TODO replace "system.out.println("DIRECTION");" with "updateDirection(direction);"
	 */
	@Override
	public void nativeKeyPressed(NativeKeyEvent e)
	{
		if(e.getKeyCode() == NativeKeyEvent.VC_RIGHT)
			direction = "RIGHT";

		else if (e.getKeyCode() == NativeKeyEvent.VC_LEFT)
			direction = "LEFT";

		else if (e.getKeyCode() == NativeKeyEvent.VC_UP)
			direction = "UP";

		else if (e.getKeyCode() == NativeKeyEvent.VC_DOWN)
			direction = "DOWN";
	}

	public void nativeKeyReleased(NativeKeyEvent e) {}
	public void nativeKeyTyped(NativeKeyEvent e) {}

	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~

	/*
	 * Brian Kiss, Isaak Weidman	Pd. 8	5/21/2019
	 * This is my own work, BK
	 * This is my own work, IW
	 *
	 * Description:
	 * Method for snake that creates a 2d array for game. Allows user to choose from 3 sizes (with idiot proofing) and
	 * creates an array of '0's of that size (with added null buffer).
	 */
	public static String[][] gridInit()
	{
		// Brian Kiss:
		Scanner reader = new Scanner (System.in);
		int size = 0;
		int center;
		boolean invalid = true;
		String[][] grid = new String[0][0];

		while (invalid == true)
		{
			try
			{
				System.out.println("Choose size of game.\n\n1\tSmall\n2\tMedium\n3\tLarge");
				size = reader.nextInt();
				invalid = false;
			}
			catch (InputMismatchException e)
			{
				System.out.println("Invalid value!");
				reader.next(); // this consumes the invalid token
				continue;
			}
		}

		if (size == 1)	// selects different sizes based upon user answer
		{
			grid = new String[17][17];	// +2 to each array for invisible boarder on all sides
		}
		else if (size == 2)
		{
			grid = new String[21][21];
		}
		else if (size == 3)
		{
			grid = new String[29][29];
		}

		// Isaak Weidman:

		// Fills entire array with null values
		for(String[] sa: grid) {
			for (String s : sa)
				s = null;
		}

		// Leaves a border of null values around the array and makes the rest spaces.
		for(int row = 1; row < (grid.length - 1); row++) {
			for (int col = 1; col < (grid[0].length - 1); col++)
				grid[row][col] = " ";
		}

		// Places the head of the snake in the center of the board
		center = (grid.length / 2);
		grid[center][center] = "@";

		return grid;
	}

	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~

	/*
	 * Brian Kiss	Pd. 8	5/21/2019
	 * This is my own work, BK
	 *
	 * Description:
	 * Initializes 2d array grid with a buffer of null around the outside, and the inside to '0'.
	 */
	public static String[][] setArray (String[][] grid)
	{
		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[i].length; j++)
			{
				if ( i == 0 || i == grid.length - 1)
					grid[i][j] = null;
				else
				{
					if ( j == 0 || j == grid[i].length - 1)
						grid[i][j] = null;
					else
						grid[i][j] = "0";
				}
			}
		}
		return grid;
	}
	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~

	/*
	 * Brennan Mulligan	Pd.8	5/23/2019
	 * This is my own work, BM
	 *
	 * Description:
	 * Takes in the main snake grid (contains the location of the snake, food, and borders),
	 * the directional grid (contains metadata for the direction each segment of the snake is moving), and the
	 * directional input from the user to update the position of the snake in the next frame.
	 *
	 * Returns an ArrayList containing both the updated main grid and the updated directional grid.
	 */
	public static ArrayList<String[][]> updateFrame (ArrayList<String[][]> grids) throws ArrayIndexOutOfBoundsException
	{
		String [][] grid = grids.get(0);
		String [][] directions = grids.get(1);
		String whereDoesButtGo = "";

		int colHead = 0;
		int rowHead = 0;
		int colButt = 0;
		int rowButt = 0;
		int colMoney = 0;
		int rowMoney = 0;
		boolean extend = false;
		boolean butt = false;
		boolean noBody = true;

		// Find location of head, butt, and money
		for (int row = 0; row < grid.length; row++)
		{
			for (int col = 0; col < grid[row].length; col++)
			{
				if ("@".equals(grid[row][col]))
				{
					colHead = col;
					rowHead = row;
				}
				else if ("0".equals(grid[row][col]))
				{
					colButt = col;
					rowButt = row;
					butt = true;
				}
				else if ("$".equals(grid[row][col]))
				{
					colMoney = col;
					rowMoney = row;
				}
				else if ("O".equals(grid[row][col]))
				{
					noBody = false;
				}
			}
		}
		//if (direction != null && noBody)
		//		grid[rowHead][colHead] = "O";

		grid[rowButt][colButt] = "";
		whereDoesButtGo = directions[colButt][rowButt];
		directions[rowButt][colButt] = "";

		// move "@" (the "Head") to new location based on user input
		if ("RIGHT".equals(direction))
		{
			directions[rowHead][colHead] = "RIGHT";

			//If the "head" will reach the "Money", the snake should get bigger
			if ((colHead + 1) == colMoney && rowHead == rowMoney)
			{
				extend = true;
			}

			//Snake will stay small until its
			//if (!(direction == null) && extend)
			//	grid[rowHead][colHead] = "O";

			grid[rowHead][colHead + 1] = "@";
			grid[rowHead][colHead] = "O";

		}
		else if ("LEFT".equals(direction))
		{
			directions[rowHead][colHead] = "LEFT";
			if ((colHead - 1) == colMoney && rowHead == rowMoney)
				extend = true;

			//if (!(direction == null) && extend)
			//	grid[rowHead][colHead] = "O";

			grid[rowHead][colHead - 1] = "@";
			grid[rowHead][colHead] = "O";

		}
		else if ("UP".equals(direction))
		{
			directions[rowHead][colHead] = "UP";
			if (colHead == colMoney && (rowHead - 1) == rowMoney)
				extend = true;

			//if (!(direction == null) && extend)
			//	grid[rowHead][colHead] = "O";

			grid[rowHead - 1][colHead] = "@";
			grid[rowHead][colHead] = "O";
		}
		else if ("DOWN".equals(direction))
		{
			directions[rowHead][colHead] = "DOWN";
			if (colHead == colMoney && (rowHead + 1) == rowMoney)
				extend = true;

			//if (!(direction == null) && extend)
			//	grid[rowHead][colHead] = "O";

			grid[rowHead + 1][colHead] = "@";
			grid[rowHead][colHead] = "O";

		}

		//If there IS a butt on the snake, the butt should follow
		//unless it needs to extend from reaching money
		if (butt && !extend)
		{
			if (whereDoesButtGo.equals("RIGHT"))
				grid[rowButt][colButt + 1] = "0";

			else if (whereDoesButtGo.equals("LEFT"))
				grid[rowButt][colButt - 1] = "0";

			else if (whereDoesButtGo.equals("UP"))
				grid[rowButt - 1][colButt] = "0";

			else if (whereDoesButtGo.equals("DOWN"))
				grid[rowButt + 1][colButt] = "0";
		}

		grids.set(0, grid);
		grids.set(1, directions);

		return grids;
	}

	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~

	/*
	 * Isaak Weidman	Pd.8 5/23/2019
	 * This is my own work, IW
	 *
	 * Description:
	 * Takes in the main grid and formats / prints the board to the screen.
	 */
	public static void printBoard(String[][] board){

		for(String[] sa: board) {
			for(String s: sa) {
				if (s == null)
					System.out.print("# ");
				else
					System.out.print(s + " ");
			}
			// Go to next line of the array
			System.out.println();
		}
	}
}