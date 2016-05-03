//import necessary classes
import java.awt.Color;

/*
 * Name: Jack Lin
 * Date: Apr, 16, 2015
 * Description: This is an general abstract class of the bounded shapes such as ovals and rectangles.
 */

abstract public class MyBoundedShape extends MyShape
{
    //initialize necessary variables here
    private boolean fillColor;
        
    //construtor for MyBoundedShape class that calls its parent constructor and initializes fillColor variable. 
    public MyBoundedShape()
    {
        super(); //calls parent constructor without parameters
        fillColor = false;
    } //end of MyLine constructor
    
    //constuctor for MyBoundedShape class with parameters for data initilization.
    public MyBoundedShape(int x1, int y1, int x2, int y2, Color color, Color color2, boolean fill, int lineWidth,
                          boolean dashed, int dashLength)
    {
        super(x1, y1, x2, y2, color, color2, lineWidth, dashed, dashLength); //calls parent constructor with parameters
        //initialize data attributes accordingly
        fillColor = fill;
    }//end of MyLine constructor
    
    //accessor method to the color variable, it takes no parameter and returns a boolean value.
    public boolean getFillColor()
    {
        return fillColor;
    } //end of getFillColor accessor
    
    //accessor method used to return the first x value, takes no parameter.
    public int getUpperLeftX()
    {
        return(Math.min(getX1(),getX2()));
    } //end of getUpperLeftX accessor
    
    //Accessor method used to return the first y value, takes no parameter.
    public int getUpperLeftY()
    {
        return(Math.min(getY1(),getY2()));
    } //end of getUpperLeftY accessor
    
    //accessor method used to return the width of the shape, takes no parameter.
    public int getWidth()
    {
        return(Math.max(getX2(),getX1())-getUpperLeftX());
    } //end of getWidth accessor
    
    //accessor method used to return the height of the shape, takes no parameter.
    public int getHeight()
    {
        return(Math.max(getY2(),getY1())-getUpperLeftY());
    } //end of getHeight accessor
    
    //mutator method for the fillColor instance variable, takes a boolean parameter and returns nothing.
    public void setFillColor(boolean fill)
    {
        fillColor = fill;
    } //end of fillColor mutator
} //end of MyBoundedShape class