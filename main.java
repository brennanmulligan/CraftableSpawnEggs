import java.util.*;
import java.io.*;
import java.awt.event.*;

public class main
		implements KeyListener {
	
	static char[] line = {' ', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', ' '};
	
	public static void main(String[] args)
			throws InterruptedException {
		
		
		while(true) {
			
			System.out.println(line);
			Thread.sleep(150);
			clear();
		}
	}
	
	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~
	
	/*
	 * Handles user input
	 */
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
		
			move(line, "RIGHT");
			
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			move(line, "LEFT");
		}
	}
	
	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~
	
	/*
	 * Moves asterisk value in given direction
	 */
	public static char[] move(char[] arr, String direction) {
		
		int startLocation = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == '*')
				startLocation = i;
		}
		
		if(direction.equals("RIGHT")) {
			
			arr[startLocation] = ' ';
			arr[startLocation + 1] = '*';
			
		}else if(direction.equals("LEFT")) {
			
			arr[startLocation] = ' ';
			arr[startLocation - 1] = '*';
			
		}
		
		return arr;
	}
	
	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~
	
	/*
	 * These don't do anything useful for this project, they're just have to be here because of superclass.
	 */
	public void keyTyped(KeyEvent e) {
	
	}
	
	public void keyPressed(KeyEvent e) {
	
	}
	
	//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~
	
	public static void clear() {
		
		try {
			
			if(System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		}
		catch (IOException | InterruptedException ex) {}
	}
}
