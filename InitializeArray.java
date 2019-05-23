/*
Brian Kiss	Pd. 8	5/21/2019
This is my own work, BK
Initializes 2d array grid with a buffer of null around the outside, and the inside to '0'.
*/
package array;

public class InitializeArray
{
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
}
