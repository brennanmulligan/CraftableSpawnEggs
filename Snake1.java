import java.util.*;
import java.io.*;

public class Snake1 {
    public static void main(String[] args) throws InterruptedException {
        
        /*
        while(!hasLost) {
            
            updateFrame(getDirection());
            clear();
            printBoard(board);
            lose = outOfBounds();
            Thread.sleep(150);
        }
        System.out.println("You have lost!");
        */
        
        //Temporary method call to keep terminal clean as I test at home
        clear();
        
        char[][] board;

        Scanner keyboard = new Scanner(System.in);
        String direction;
        int speed;
        boolean inValid = false;
        
        board = boardInit(15);
        
        while(inValid = false) {
            
            System.out.print("What direction to move '*'\n> ");
            direction = keyboard.nextLine().toUpperCase();

            if (!direction.equals("LEFT") && !direction.equals("RIGHT") && !direction.equals("UP") && !direction.equals("DOWN"))
                System.out.println("Invalid input");
        }

        System.out.print("How fast do you want it to move? (enter number in miliseconds)\n> ");
        speed = keyboard.nextInt();
        
        
    }
    
    
    
    public static void print(char[][] b) {
        
        for(char[] ca: b) {
            for(char c: ca)
                System.out.printf("%c ", c);
            System.out.println();
        }
    }



    public static char[][] boardInit(int size) {

        char[][] board = new char[size][size];
        int center = (size / 2) + 1;

        for(char ca[] : board) {
            for (char c : ca)
            c = ' ';
        }
        
        board[center][center] = '*';

        return board;
    }

    
    
    /*
     * Checks what os the program is being run on. if it is run on windows, a new command
     * is created that clears the command prompt when called. Otherwise the pre-existing
     * command is used.
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
}