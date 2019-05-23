import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Snek
		implements NativeKeyListener {

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
				GlobalScreen.addNativeKeyListener(new Snek());
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
		

		// Start Game Loop:
		while(/*!hasLost*/ true) // TODO remove "true" later
		{
			// updateFrame(board, direction);
			// clear();
			// printBoard(board);
			// hasLost = outOfBounds();
			Thread.sleep(150);
		}
		// System.out.println("You Lost!");
	}
	
	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~
	
	/*
	 * Checks what os the program is being run on. if it is run on windows, a new command is created that clears the
	 * command prompt when called. Otherwise the pre-existing command is used.
	 *
	 * NOT OUR CODE, FOUND ON INTERNET
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
	 * Is called when user presses a key and only does something if they press an arrow key.
	 * nativeKeyReleased and nativeKeyTyped don't do anything and only need to be here to make java happy because snek
	 * implements NativeKeyListener.
	 * TODO replace "system.out.println("DIRECTION");" with "updateDirection(direction);"
	 *
	 * Coded by:
	 * Brennan Mulligan and Isaak Weidman
	 */
	@Override
	public void nativeKeyPressed(NativeKeyEvent e)
	{
		if(e.getKeyCode() == NativeKeyEvent.VC_RIGHT)
			System.out.println("RIGHT");
		
		else if (e.getKeyCode() == NativeKeyEvent.VC_LEFT)
			System.out.println("LEFT");
		
		else if (e.getKeyCode() == NativeKeyEvent.VC_UP)
			System.out.println("UP");
		
		else if (e.getKeyCode() == NativeKeyEvent.VC_DOWN)
			System.out.println("DOWN");
	}
	
	public void nativeKeyReleased(NativeKeyEvent e) {}
	public void nativeKeyTyped(NativeKeyEvent e) {}
	
	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~
	
	/*
	Brian Kiss	Pd. 8	5/21/2019
	This is my own work, BK
	Method for snake that checks if the number exits the array.
	*/
	public static boolean outOfBounds (int[][] grid, int row, int col)	//grid of coordinates/position of snek
	{
		for (int i = 0; i < grid.length - 1; i++)
		{
			for (int j = 0; j < grid[i].length - 1; j++)
			{
				if (grid[row][col] == null)	//if snek touches null border, returns TRUE for outOfBounds
				{
					return true;
				}
			}
		}
	}
	
	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~
	
	/*
	Brian Kiss	Pd. 8	5/21/2019
	This is my own work, BK
	Method for snake that creates a 2d array for game. Allows user to choose from 3 sizes (with idiot proofing) and creates an array of '0's of that size (with added null buffer).
	*/
	public static String[][] selectSize()
	  {
		  Scanner reader = new Scanner (System.in);
		  int size = 0;
		  boolean invalid = true;
		  
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
		  
		  if (size == 1)	//selects different sizes based upon user answer
		  {
			  String[][] grid = new String[7][7];	//+2 to each array for invisible boarder on all sides
			  return grid;
	 	  }
		  if (size == 2)
		  {
			  String[][] grid = new String[12][12];
			  return grid;
		  }
		  if (size == 3)
		  {
			  String[][] grid = new String[17][17];
			  return grid;
		  }
		  String[][] grid = new String[0][0];
		  return grid;
	  }
	
	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~
	
	/*
	Brian Kiss	Pd. 8	5/21/2019
	This is my own work, BK
	Initializes 2d array grid with a buffer of null around the outside, and the inside to '0'.
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
}
