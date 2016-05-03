//import necessary classes
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.GradientPaint;
import java.awt.BasicStroke;

/*
 * Name: Jack Lin
 * Date: Apr, 16, 2015
 * Description: This class is designed to draw ovals.
 */

public class MyOval extends MyBoundedShape
{
    //This is a constructor method calls its parent constructor, it takes no parameters.
    public MyOval()
    {
        super(); //refers to its parent class with no parameter passes
    } //end of MyOval constructor
    
    ///This is a constructor method that calls its parent constructor passing the parameter values.
    public MyOval( int x1, int y1, int x2, int y2, Color color, Color color2, boolean fill, int lineWidth,
                  boolean dashed, int dashLength)
    {
        super(x1, y1, x2, y2, color, color2, fill, lineWidth, dashed, dashLength); //refers to its parent class with parameter passes
    } // end MyOval constructor
    
    //This method sets the drawing color and draws the line on to the drawing panel, it takes one graphics parameter and
    //returns nothing.
    public void draw( Graphics g )
    {
        Graphics2D g2d = ( Graphics2D ) g; // cast g to Graphics2D
        
        //check if the stroke is dashed or not, if not, a different setStroke method is called
        if(getDashed()) {
            float dashes[] = { getDashLength() }; //float value for dash length
            g2d.setStroke( new BasicStroke( getLineWidth(), BasicStroke.CAP_ROUND,
                                           BasicStroke.JOIN_ROUND, 10, dashes, 0 ) ); 
        }
        else {
            g2d.setStroke(new BasicStroke(getLineWidth()));
        }
        
        // draw 2D ellipse
        g2d.setPaint( new GradientPaint( getUpperLeftX(), getUpperLeftY(), getColor1(), getWidth(), getHeight(), getColor2(), true ) );  
        g2d.draw( new Ellipse2D.Double( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() ) );
        
        if (getFillColor())
        {
            g2d.fill( new Ellipse2D.Double( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() ) );
        }
    } // end method draw
} // end class MyLine