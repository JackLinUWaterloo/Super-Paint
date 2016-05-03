//import necessary classes
import javax.swing.JFrame;

/*
 * Name: Jack Lin
 * Date: Apr, 16, 2015
 * Description: This class instantiates a drawing frame and sets its size.
 */

public class SuperPaint {
    //static method to start program execution
    public static void main(String args[]) {
        //necessary data
        final int SCREEN_WIDTH = 800;
        final int SCREEN_HEIGHT = 800;
        
        DrawFrame superPaintFrame = new DrawFrame();  //create a new frame
        superPaintFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set the frame to exit when it's closed
        superPaintFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT); //set the size of the frame
        superPaintFrame.setVisible(true); //make the frame visible
    } // end main
} // end class SuperPaintDemo