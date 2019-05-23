import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Snek
		implements NativeKeyListener {

	public static void main(String[] args)
			throws InterruptedException {
		
		/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
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
		Thread thread = new Thread()
		{
			@Override
			public void run()
			{
				GlobalScreen.addNativeKeyListener(new Snek());
			}
		};
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		// Start thread
		try {
			GlobalScreen.registerNativeHook();
			thread.start();
		} catch (NativeHookException ex) {
			System.err.println("Error");
		}
		

		// Start Game Loop:
		while(/*!hasLost*/ true) // remove true later
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
}
