//import necessary classes
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.lang.NumberFormatException;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.InputMismatchException;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.imageio.ImageIO;

/*
 * Name: Jack Lin
 * Date: Apr, 16, 2015
 * Description: This class provides a GUI to enable the user to control various aspects of drawing.
 */

public class DrawFrame extends JFrame implements ActionListener {
    //instatiate necessary variables
    private JButton undoButton;
    private JButton redoButton;
    private JButton clearButton;
    private JComboBox color1Box1;
    private JComboBox color1Box2;
    private JComboBox shapeBox;
    private JCheckBox fillBox;
    private JCheckBox gradientBox;
    private JCheckBox dashedBox;
    private JLabel statusLabel;
    private DrawPanel drawPanel;
    private JPanel actionPanel;
    private JTextField lineWidthTextField;
    private JTextField dashLengthTextField;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem aboutMenuItem;
    private JMenuItem prefsMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem screenshotMenuItem;
    private JToolBar itemsToolBar;
    private int shapeType;
    private int filled;
    private int gradient;
    private int color1;
    private int color12;
    private int lineWidth;
    private int dashed;
    private int dashLength;
    
    //instantiate necessary global constants
    final private int numOfSwingComponents = 6;
    final private String stringColors[] = { "Black", "Blue", "Cyan", 
        "Dark Gray", "Gray", "Green", "Light Gray", "Magenta",
        "Orange", "Pink", "Red", "White", "Yellow" };
    final private Color color1s[] = { Color.BLACK, Color.BLUE, Color.CYAN, 
        Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, 
        Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, 
        Color.YELLOW };    
    final private String stringShapes[] = {"Lines", "Rectangles", "Oval"};
    final private int shapes[] = {0, 1, 2};
    final private JPanel messagePanel = new JPanel();
    
    //constructor class that instatiate variables calls its parent class
    public DrawFrame() {
        
        super("Super Paint"); //calls parent constructor
        
        statusLabel = new JLabel("(0, 0)");
        drawPanel = new DrawPanel(statusLabel);
        
        actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1, numOfSwingComponents, 10, 10));
        
        undoButton = new JButton("Undo");
        undoButton.addActionListener(this);
        actionPanel.add(undoButton);
        
        redoButton = new JButton("Redo");
        redoButton.addActionListener(this);
        actionPanel.add(redoButton);
        
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        actionPanel.add(clearButton);
        
        //import all the inital values from configFile.txt and assign to variables
        try {
            Scanner fileInput = new Scanner ( new File("configFile.txt"));
            int counter = 0;
            //using a while loop for individual lines to a specific variable
            while(fileInput.hasNext()) {
                counter++;
                try {
                    if(counter == 1) {
                        shapeType = fileInput.nextInt();
                    }
                    else if (counter == 2) {
                        filled = fileInput.nextInt();
                    }
                    else if (counter == 3) {
                        gradient = fileInput.nextInt();
                    }
                    else if (counter == 4) {
                        color1 = fileInput.nextInt();
                    }
                    else if (counter == 5) {
                        color12 = fileInput.nextInt();
                    }
                    else if (counter == 6) {
                        lineWidth = fileInput.nextInt();
                    }
                    else if (counter == 7) {
                        dashed = fileInput.nextInt();
                    }
                    else if (counter == 8) {
                        dashLength = fileInput.nextInt();
                    }
                }
                catch (InputMismatchException e) {
                    fileInput.next(); // skip the invalid data
                    continue;
                }
            }
            fileInput.close();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(messagePanel, "Input Exception!", "Error", 
                                          JOptionPane.ERROR_MESSAGE);
        }
        
        shapeBox = new JComboBox(stringShapes);
        shapeBox.setMaximumRowCount(3);
        shapeBox.setSelectedItem(stringShapes[shapeType]); //selecting initial item in the comboBox
        drawPanel.setCurrentShapeType(shapes[shapeType]);
        shapeBox.addItemListener(new ItemListener() {
            //check item state change and make changes accordingly
            public void itemStateChanged( ItemEvent event) {
                if(event.getStateChange() == ItemEvent.SELECTED)
                    drawPanel.setCurrentShapeType(shapes[shapeBox.getSelectedIndex()]);
            } //end itemStateChanged method
        } //end anonymous inner class
        ); //end call to addItemListener
        actionPanel.add(shapeBox);
        
        fillBox = new JCheckBox("Filled");
        //checks filled variable value, if equals to 1, fillBox is checked initially and shape will be filled when drawn
        if(filled == 1){
            fillBox.setSelected(true);
            drawPanel.setCurrentShapeFilled(true);
        }
        fillBox.addItemListener(new ItemListener() {
            //check item state change and make changes accordingly
            public void itemStateChanged( ItemEvent event) {
                boolean state = fillBox.isSelected() ? true : false;
                drawPanel.setCurrentShapeFilled(state);
            } //end itemStateChanged method
        } //end anonymous inner class
        ); //end call to addItemListener
        actionPanel.add(fillBox);
        
        color1Box1 = new JComboBox(stringColors);
        color1Box1.setMaximumRowCount(13);
        color1Box1.setSelectedItem(stringColors[color1]); //selecting initial item in the comboBox
        drawPanel.setCurrentShapeColor1(color1s[color1]);
        color1Box1.addItemListener(new ItemListener() {
            //check item state change and make changes accordingly
            public void itemStateChanged( ItemEvent event) {
                if(event.getStateChange() == ItemEvent.SELECTED) 
                    drawPanel.setCurrentShapeColor1(color1s[color1Box1.getSelectedIndex()]);
            } //end itemStateChanged method
        } //end anonymous inner class
        ); //end call to addItemListener
        actionPanel.add(color1Box1);
        
        color1Box2 = new JComboBox(stringColors);
        color1Box2.setMaximumRowCount(13);
        color1Box2.setSelectedItem(stringColors[color12]); //selecting initial item in the comboBox
        drawPanel.setCurrentShapeColor2(color1s[color12]);
        color1Box2.addItemListener(new ItemListener() {
            //check item state change and make changes accordingly
            public void itemStateChanged( ItemEvent event) {
                if(event.getStateChange() == ItemEvent.SELECTED)
                    drawPanel.setCurrentShapeColor2(color1s[color1Box2.getSelectedIndex()]);
            } //end itemStateChanged method
        } //end anonymous inner class
        ); //end call to addItemListener
        actionPanel.add(color1Box2);
        
        gradientBox = new JCheckBox("Gradient");
        //checks gradient variable value, if equals to 1, gradientBox is checked initially and shape will drawn in gradient
        if(gradient == 1){
            gradientBox.setSelected(true);
            drawPanel.setCurrentShapeGradient(true);
        }
        gradientBox.addItemListener(new ItemListener() {
            //check item state change and make changes accordingly
            public void itemStateChanged( ItemEvent event) {
                boolean state = gradientBox.isSelected() ? true : false;
                drawPanel.setCurrentShapeGradient(state);
            } //end itemStateChanged method
        } //end anonymous inner class
        ); //end call to addItemListener
        actionPanel.add(gradientBox);
        
        lineWidthTextField = new JTextField( "Line Width", 5 );
        drawPanel.setCurrentShapeLineWidth(lineWidth);
        lineWidthTextField.addActionListener(new ActionListener() {
            //check user for entering any values
            public void actionPerformed( ActionEvent event ) {
                int width = 0;
                if( event.getSource() == lineWidthTextField ) {
                    //limts user to enter between 0 and 20 and if a string is entered, an error message will display
                    try {
                        width = Integer.parseInt(event.getActionCommand());
                        if(width < 0) {
                            width = 0;
                        }
                        else if(width > 20){
                            width = 20;
                        }
                        drawPanel.setCurrentShapeLineWidth(width);
                    }
                    catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(messagePanel, "Please enter an integer!", "Error", 
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
            } //end actionPerformed method
        } //end anonymous inner class
        ); //end call to actionPerformed
        actionPanel.add(lineWidthTextField);
        
        dashedBox = new JCheckBox("Dashed");
        //check if dashed variable value is 1, if so, dashedBox is selected by default and drawing will be in dashes
        if(dashed == 1){
            dashedBox.setSelected(true);
            drawPanel.setDashed(true);
        }
        dashedBox.addItemListener(new ItemListener() {
            //check item state change and make changes accordingly
            public void itemStateChanged( ItemEvent event) {
                boolean state = dashedBox.isSelected() ? true : false;
                drawPanel.setDashed(state);
            } //end itemStateChanged method
        } //end anonymous inner class
        ); //end call to addItemListener
        actionPanel.add(dashedBox);
        
        dashLengthTextField = new JTextField( "Dash Length", 5 );
        drawPanel.setCurrentShapeDashLength(dashLength);
        dashLengthTextField.addActionListener(new ActionListener() {
            //check user for entering any values
            public void actionPerformed( ActionEvent event ) {
                int length = 0;
                if( event.getSource() == dashLengthTextField ) {
                    //limts user to enter between 1 and 50 and if a string is entered, an error message will display
                    try {
                        length = Integer.parseInt(event.getActionCommand());
                        if(length < 1) {
                            length = 1;
                        }
                        else if(length > 50){
                            length = 50;
                        }
                        drawPanel.setCurrentShapeDashLength(length);
                    }
                    catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(messagePanel, "Please enter an integer!", "Error", 
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
            } //end actionPerformed method
        } //end anonymous inner class 
        ); //end call to addItemListener
        actionPanel.add(dashLengthTextField);
        
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        
        //creating sub menu items that will be placed in the fileMenu
        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener( new ActionListener () {
            //displays JOptionPane when user clicked on the menuItem
            public void actionPerformed( ActionEvent event ) {
                JOptionPane.showMessageDialog(messagePanel, "Creator: Jack Lin \nGrade: 12 \nTeacher: Mr. Rao", "About",
                                              JOptionPane.INFORMATION_MESSAGE);
            } //end actionPerformed method
        } //end anonymous inner class 
        ); //end call to addItemListener
        fileMenu.add(aboutMenuItem);
        
        prefsMenuItem = new JMenuItem("Prefs");
        prefsMenuItem.addActionListener( new ActionListener () {
            //displays a preference frame when user clicks on the prefs menuItem
            public void actionPerformed( ActionEvent event ) {
                //necessary constants
                final int SCREEN_WIDTH = 1100;
                final int SCREEN_HEIGHT = 100;
                
                PrefsFrame prefsFrame = new PrefsFrame();
                prefsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                prefsFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT); //set the size of the frame
                prefsFrame.setVisible(true); //make the frame visible
            } //end actionPerformed method
        } //end anonymous inner class 
        ); //end call to addItemListener
        fileMenu.add(prefsMenuItem);
        
        screenshotMenuItem = new JMenuItem("Screenshot");
        screenshotMenuItem.addActionListener( new ActionListener () {
            //creates a screenshot when screenshot menuItem clicked, saves to the same directory
            public void actionPerformed( ActionEvent event ) {
                try {
                    BufferedImage image = new BufferedImage (getSize().width, getSize().height, 
                                                             BufferedImage.TYPE_INT_RGB);
                    paint(image.createGraphics());
                    ImageIO.write(image, "JPEG", new File ("MyImage.jpg"));
                }
                catch (IOException e) {}
            } //end actionPerformed method
        } //end anonymous inner class 
        ); //end call to addItemListener
        fileMenu.add(screenshotMenuItem);
        
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener( new ActionListener () {
            //system exits when exit menuItem is clicked
            public void actionPerformed( ActionEvent event ) {
                System.exit(0);
            } //end actionPerformed method
        } //end anonymous inner class
        ); //end call to addItemListener
        fileMenu.add(exitMenuItem);
        
        menuBar.add(fileMenu); //add fileMenu to the menuBar
        setJMenuBar(menuBar);
        
        actionPanel.setBackground(Color.LIGHT_GRAY);
        itemsToolBar = new JToolBar("Action Tool Bar");
        itemsToolBar.add(actionPanel); //add actionPanel to itemsToolBar
        
        add( itemsToolBar, BorderLayout.NORTH);
        add( drawPanel, BorderLayout.CENTER);
    } //end DrawPanel constructor
    
    //This method checks the action performed to the buttons pressed and react accordingly, takes action event as 
    //parameter and returns nothing
    public void actionPerformed(ActionEvent event) {
        //checks for which button is pressed
        if(event.getActionCommand() == "Undo") {
            drawPanel.clearLastShape();
        }
        else if(event.getActionCommand() == "Redo") {
            drawPanel.redoLastShape();
        }
        else if(event.getActionCommand() == "Clear") {
            drawPanel.clearDrawing();
        }
    } //end actionPerformed method
} //end DrawFrame class