/*
Brian Kiss	Pd. 8	5/21/2019
This is my own work, BK
Method for snake that checks if the number exits the array.
*/
package array;

public class CheckForLoss
{
	public static void outOfBounds (int[][] grid, int[][] pos)
	{
		for (int i = 0; i < grid.length - 1; i++)
		{
			for (int j = 0; j < grid[i].length - 1; j++)
			{
				if (pos == null)
				{
					System.out.println("Game Over!");
					System.exit(0);
				}
			}
		}
	}
}