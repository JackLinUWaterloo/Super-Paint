//import all the necessary classes
import java.awt.Color;
import java.awt.Graphics;

/*
 * Name: Jack Lin
 * Date: Apr, 16, 2015
 * Desc: This is an abstract class which represents shapes in general.
 */

abstract public class MyShape {
    //initialize all necessary object and variables
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int lineWidth;
    private Color myColor1;
    private Color myColor2;
    private boolean dashed;
    private int dashLength;
        
    //construtor for MyShape class that sets all the coordinates of the shape to 0 and colour to black 
    public MyShape() {
        x1 = 0; 
        y1 = 0;
        x2 = 0;
        y2 = 0;
        lineWidth = 0;
        myColor1 = Color.BLACK;
        myColor2 = Color.BLACK;
        dashed = false;
        dashLength = 5;
    } //end of MyShape constructor
    
    //constuctor for MyShape class with parameters used to assign to the according data attributes
    public MyShape(int x1, int y1, int x2, int y2, Color color1, Color color2, int width, boolean dashed, 
                   int dashLength) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        myColor1 = color1;
        myColor2 = color2;
        lineWidth = width;
        this.dashed = dashed;
        this.dashLength = dashLength;
    } //end of MyShape constructor
    
    //mutator method that changes variable x1, it takes one integer parameter and returns nothing.
    public void setX1(int x1) {
        this.x1 = x1;
    } //end of setX1
    
    //mutator method that changes variable y1, it takes one integer parameter and returns nothing.
    public void setY1(int y1) {
        this.y1 = y1;
    } //end of setY1
    
    //mutator method that changes variable x2, it takes one integer parameter and returns nothing.
    public void setX2(int x2) {
        this.x2 = x2;
    } //end of setX2
    
    //mutator method that changes variable y2, it takes one integer parameter and returns nothing.
    public void setY2(int y2) {
        this.y2 = y2;
    } //end of setY2
    
    //mutator method that changes variable color, it takes one color parameter and returns nothing.
    public void setColor1(Color color1) {
        myColor1 = color1;
    } //end of setColor
    
    //mutator method that changes variable color2, it takes one color parameter and returns nothing.
    public void setColor2(Color color2) {
        myColor2 = color2;
    } //end of setColor2
    
    //mutator method that changes variable lineWidth, it takes one integer parameter and returns nothing.
    public void setLineWidth(int width) {
        lineWidth = width;
    } //end of setLineWidth
    
    //mutator method that changes variable dashed, it takes one boolean parameter and returns nothing.
    public void setDashed (boolean dashed) {
        this.dashed = dashed;
    } //end of setDashed
    
    //mutator method that changes variable dashLength, it takes one integer parameter and returns nothing.
    public void setDashLength (int dashLength) {
        this.dashLength = dashLength;
    } //end of setDashLength
    
    //accessor method used to return the variable x1 value, takes no parameter
    public int getX1() {
        return x1;
    } //end of getX1
    
    //accessor method used to return the variable y1 value, takes no parameter
    public int getY1() {
        return y1;
    } //end of getY1
    
    //accessor method used to return the variable x2 value, takes no parameter
    public int getX2() {
        return x2;
    } //end of getX2
    
    //accessor method used to return the variable y2 value, takes no parameter
    public int getY2() {
        return y2;
    }  //end of getY1
    
    //accessor method used to return the variable myColor1 value, takes no parameter
    public Color getColor1() {
        return myColor1;
    } //end of getColor
    
    //accessor method used to return the variable myColor2 value, takes no parameter
    public Color getColor2() {
        return myColor2;
    } //end of getColor2
    
    //accessor method used to return the variable lineWidth value, takes no parameter
    public int getLineWidth() {
        return lineWidth;
    } //end of getLineWidth
    
    //accessor method used to return the variable dashed value, takes no parameter
    public boolean getDashed() {
        return dashed;
    } //end of getDashed
    
    //accessor method used to return the variable dashLength value, takes no parameter
    public int getDashLength() {
        return dashLength;
    } //end of getDashLength
    
    //An abstract drawing method that requires its child classes to override this method and provide a method
    //body, it takes a graphics parameter.
    public abstract void draw (Graphics g);

} //end of MyShape