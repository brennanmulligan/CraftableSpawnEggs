public class UpdatedFrameo
{
	public static ArrayList<String[][]> UpdateFrame (ArrayList<String[][]> grids, String direction)
	{
		String [][] grid = grids.get(0);
		String [][] directions = grids.get(1);

		int colHead, rowHead, colButt, rowButt, colMoney, rowMoney = 0;
		boolean extend = false;
		//String [][] directions = new String[grid.length][grid[0].length];			*Use this in main*

		for (int row = 0; row < grid.length(); row++)
		{
			for (int col = 0; col < grid[row].length(); col++)
			{
				if (grid[col][row].equals("@")
				{
					colHead = col;
					rowHead = row;
				}
				else if (grid[col][row].equals("0")
				{
					colButt = col;
					rowButt = row;
				}
				else if (grid[col][row].equals("$")
				{
					colMoney = col;
					rowMoney = row;
				}
			}
		}

		if (colHead == colMoney && rowHead == rowMoney)
		{
			extend = true;
		}

		if (direction.equals("Right")
		{

		}

		grids.set(0, grid);
		grids.set(1, directions);

		return grids;
	}
}