/*
Brennan Mulligan
This is my own work, BCM
5/21/2019
Period 8
This method (or class depending on future use
of this) continuously takes in the input of
the user but only through the arrow keys to
change the direction for the full program.
*/

public class DirectionPicker extends KeyAdapter
{
	//Override allows the method to continuously happen
    @Override
    public String getDirection(KeyEvent k) {

        int key = k.getKeyCode();

        if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
            return "left";
        }

        if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
            return "right";
        }

        if ((key == KeyEvent.VK_UP) && (!downDirection)) {
			return "up";
        }

        if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
            return "down";
        }
    }
}