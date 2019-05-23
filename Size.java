/*
Brian Kiss	Pd. 8	5/21/2019
This is my own work, BK
Method for snake that creates a 2d array for game. Allows user to choose from 3 sizes (with idiot proofing) and creates an array of '0's of that size (with added null buffer).
*/
package array;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Size
{
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
}
