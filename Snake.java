import java.util.*;
import java.io.*;

public class Snake {
    public static void main(String[] args) throws InterruptedException{

        Scanner test = new Scanner(System.in);

        // print frame
        System.out.println("1     ");
        // wait .5 seconds
        Thread.sleep(500);
        // clear the screen
        clear();
        System.out.println(" 1    ");
        Thread.sleep(500);
        clear();
        System.out.println("  1   ");
        Thread.sleep(500);
        clear();
        System.out.println("   1  ");

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