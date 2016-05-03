//importing necessary classes
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.BasicStroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.lang.NullPointerException;

/*
 * Name: Jack Lin
 * Date: Apr, 16, 2015
 * Description: This class performs the actual drawing in memory by creating different 
 *  shapes and calling their related classes and methods.
 */

public class DrawPanel extends JPanel {
    //delaring necessary global constants
    final int LINE = 0;
    final int RECT = 1;
    final int OVAL = 2;
    final int INCREMENT = 1;
    
    //declaring necessary variables and class references
    private LinkedList<MyShape> shapeObjects;
    private LinkedList<MyShape> undoObjects;
    private int currentShapeType;
    private int currentShapeLineWidth;
    private int currentShapeDashLength;
    private MyShape currentShapeObject;
    private Color currentShapeColor1;
    private Color currentShapeColor12;
    private boolean currentShapeFilled;
    private boolean currentShapeGradient;
    private boolean dashed;
    private JLabel statusLabel;
    
    //This constructor instantiates all the class data and it takes a singe JLabel parameter.
    public DrawPanel(JLabel text) {
        //instantiate all the necessary variables
        statusLabel = text;
        
        shapeObjects = new LinkedList<MyShape>();
        undoObjects = new LinkedList<MyShape>();
        currentShapeColor1 = Color.BLACK;
        currentShapeColor12 = Color.BLACK;
        currentShapeType = LINE;
        
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        
        add(statusLabel, BorderLayout.SOUTH);
        addMouseListener( new MouseHandler() );
        
        // create and register listener for mouse motion events
        MouseHandler handler = new MouseHandler(); 
        this.addMouseMotionListener( handler );
    } //end DrawPanel constructor
    
    //This method draws the individual shapes in the array of shapes, it takes a graphics parameter and returns nothing.
    public void paintComponent (Graphics g) {
        
        super .paintComponent(g); //calls parent constructor
        
        //draws all the shapes stored in the list
        for(int count = 0; count < shapeObjects.size(); count++) {
            shapeObjects.last().draw(g);
            shapeObjects.addFront(shapeObjects.last());
            shapeObjects.removeEnd();
        } //end for
        
        //draws the current object
        if(currentShapeObject != null && !shapeObjects.contains(currentShapeObject)) {
            //checking to draw in gardients or not
            if(currentShapeGradient) {
                currentShapeObject.setColor1(currentShapeColor1);
                currentShapeObject.setColor2(currentShapeColor12);
            }
            else {
                currentShapeObject.setColor1(currentShapeColor1);
                currentShapeObject.setColor2(currentShapeColor1);
            }
            currentShapeObject.setLineWidth(currentShapeLineWidth);
            currentShapeObject.setDashed(dashed);
            currentShapeObject.setDashLength(currentShapeDashLength);
            currentShapeObject.draw(g);
        }
    } // end method paintComponent
    
    //inner class to handle mouse events
    private class MouseHandler extends MouseAdapter implements MouseMotionListener{
        
        //This method handles event when mouse pressed, takes mouse event as parameter, and reutrns nothing
        public void mousePressed (MouseEvent event) {
            //check for shape type to instantiate
            if(currentShapeType == LINE) {
                currentShapeObject = new MyLine(event.getX(), event.getY(), event.getX(), event.getY(), 
                                                currentShapeColor1, currentShapeColor12, currentShapeLineWidth, 
                                                dashed, currentShapeDashLength);
            }
            else if(currentShapeType == RECT) {
                currentShapeObject = new MyRectangle(event.getX(), event.getY(), event.getX(), event.getY(), 
                                                     currentShapeColor1, currentShapeColor12, currentShapeFilled, 
                                                     currentShapeLineWidth, dashed, currentShapeDashLength);
            }
            else {
                currentShapeObject = new MyOval(event.getX(), event.getY(), event.getX(), event.getY(), 
                                                currentShapeColor1, currentShapeColor12, currentShapeFilled, 
                                                currentShapeLineWidth, dashed, currentShapeDashLength);
            }

            undoObjects.makeEmpty();
        } //end mousePressed method
        
        //This method handles event when mouse released, takes mouse event as parameter, and reutrns nothing
        public void mouseReleased( MouseEvent event ) {
            //exception handles in case the user drags from the tool bar frame
            try{
                //set coordinates accordingly
                currentShapeObject.setX2(event.getX());
                currentShapeObject.setY2(event.getY());
                shapeObjects.addFront(currentShapeObject);
                repaint();
            }
            catch (NullPointerException e) {}
        } //end mouseReleased method
        
        //This method handles event when user drags mouse with button pressed, , takes mouse event as parameter, 
        //and reutrns nothing
        public void mouseDragged( MouseEvent event ) {
            //exception handles in case the user drags from the tool bar frame
            try{
                //set coordinates accordingly
                currentShapeObject.setX2(event.getX());
                currentShapeObject.setY2(event.getY());
                repaint();
                statusLabel.setText("(" + event.getX() +", "+event.getY()+")");
            }
            catch (NullPointerException e) {}
        } //end mouseDragged
        
        //This method handles event when user moves mouse, takes mouse event as parameter, and reutrns nothing
        public void mouseMoved( MouseEvent event ) {
            statusLabel.setText("(" + event.getX() +", "+event.getY()+")");
        } //end mouseMoved method
    } //end MouseHandler
    
    //This method changes the current shape type, takes one integer variable and returns nothing
    public void setCurrentShapeType(int type) {
        currentShapeType = type;
    } //end setCurrentShapeType method
    
    //This method changes the current shape color, takes a Color variable, and returns nothing
    public void setCurrentShapeColor1(Color color1) {
        currentShapeColor1 = color1;
    } //end setCurrentShapeColor method
    
    //This method changes the current shape color, takes a Color variable, and returns nothing
    public void setCurrentShapeColor2(Color color2) {
            currentShapeColor12 = color2;
    } //end setCurrentShapeColor method
    
    //This method changes the current shape filling, takes a boolean variable, and returns nothing
    public void setCurrentShapeFilled(boolean fill) {
        currentShapeFilled = fill;
    } //end setCurrentShapeFilled method
    
    //This method sets the current shape to draw in gradient or not, takes a boolean variable, and returns nothing
    public void setCurrentShapeGradient(boolean gradient) {
        currentShapeGradient = gradient;
    } //end setCurrentShapeGradient method
    
    //This method changes the current shape line width, takes a boolean variable, and returns nothing
    public void setCurrentShapeLineWidth(int width) {
        currentShapeLineWidth = width;
    } //end setCurrentShapeLineWidth method
        
    //This method sets the current shape to draw in dashes or not, takes a boolean variable, and returns nothing 
    public void setDashed(boolean dashed) {
        this.dashed = dashed;
    } //end setDashed method
    
    //This method changes the current shape dash length, takes a boolean variable, and returns nothing
    public void setCurrentShapeDashLength(int length) {
        currentShapeDashLength = length;
    } //end setCurrentShapeDashLength method
    
    //This method clears the last shape drawn, takes no parameters and returns nothing
    public void clearLastShape() {
        if(!shapeObjects.isEmpty()) {
            currentShapeObject = null;
            undoObjects.addFront(shapeObjects.first());
            shapeObjects.removeFront();
        }
        repaint();
    } //end clearLastShape method
    
    //This method redraws the last cleared shape, takes no parameters and returns nothing
    public void redoLastShape() {
        if(undoObjects != null && !(undoObjects.size()==0)) {
            shapeObjects.addFront(undoObjects.first());
            undoObjects.removeFront();
        }
        repaint();
    } //end redoLastShapeType method
    
    //This method remove all the shapes in the current drawing
    public void clearDrawing() {
        currentShapeObject = null;
        undoObjects.makeEmpty();
        shapeObjects.makeEmpty();
        repaint();
    } //end clearDrawing method
} //end DrawPanel class