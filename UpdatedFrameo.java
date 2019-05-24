import java.util.ArrayList;

public class UpdatedFrameo
{
	public static ArrayList<String[][]> updateFrame (ArrayList<String[][]> grids, String direction)
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

		//String [][] directions = new String[grid.length][grid[0].length];			*Use this in main*

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
			}
		}

		//if (colHead == colMoney && rowHead == rowMoney)
		//	extend = true;

		grid[rowHead][colHead] = "O";
		grid[rowButt][colButt] = "";
		whereDoesButtGo = directions[colButt][rowButt];
		directions[rowButt][colButt] = "";

		// move "@" (the "Head") to new location based on user input
		if (direction.equals("Right"))
		{
			directions[rowHead][colHead] = "Right";

			//If the "head" will reach the "Money", the snake should get bigger
			if ((colHead + 1) == colMoney && rowHead == rowMoney)
				extend = true;

			grid[rowHead][colHead + 1] = "@";
		}
		else if (direction.equals("Left"))
		{
			directions[rowHead][colHead] = "Left";
			if ((colHead - 1) == colMoney && rowHead == rowMoney)
				extend = true;

			grid[rowHead][colHead - 1] = "@";
		}
		else if (direction.equals("Up"))
		{
			directions[rowHead][colHead] = "Up";
			if (colHead == colMoney && (rowHead - 1) == rowMoney)
				extend = true;

			grid[rowHead - 1][colHead] = "@";
		}
		else if (direction.equals("Down"))
		{
			directions[rowHead][colHead] = "Down";
			if ((colHead + 1) == colMoney && (rowHead + 1) == rowMoney)
				extend = true;

			grid[rowHead + 1][colHead] = "@";
		}

		//If there IS a butt on the snake, the butt should follow
		//unless it needs to extend from reaching money
		if (butt && !extend)
		{
			if (whereDoesButtGo.equals("Right"))
				grid[rowButt][colButt + 1] = "0";

			else if (whereDoesButtGo.equals("Left"))
				grid[rowButt][colButt - 1] = "0";

			else if (whereDoesButtGo.equals("Up"))
				grid[rowButt - 1][colButt] = "0";

			else if (whereDoesButtGo.equals("Down"))
				grid[rowButt + 1][colButt] = "0";
		}

		grids.set(0, grid);
		grids.set(1, directions);

		return grids;
	}
}