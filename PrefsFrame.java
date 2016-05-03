//import necessary classes
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.lang.NumberFormatException;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.InputMismatchException;

/*
 * Name: Jack Lin
 * Date: Apr, 16, 2015
 * Description: This class provides a GUI to enable the user to initialize different drawing options.
 */

public class PrefsFrame extends JFrame {
    //instatiate necessary variables
    private JButton saveButton;
    private JComboBox colorBox1;
    private JComboBox colorBox2;
    private JComboBox shapeBox;
    private JCheckBox fillBox;
    private JCheckBox gradientBox;
    private JCheckBox dashedBox;
    private JTextField lineWidthTextField;
    private JTextField dashLengthTextField;
    private FlowLayout layout;
    private Container container;
    private JLabel textLabel;
    private JLabel lineWidthLabel;
    private JLabel dashLengthLabel;
    private int shapeType;
    private int filled;
    private int gradient;
    private int color;
    private int color2;
    private int lineWidth;
    private int dashed;
    private int dashLength;
    
    //instantiate necessary global constants
    final private String stringColors[] = { "Black", "Blue", "Cyan", 
        "Dark Gray", "Gray", "Green", "Light Gray", "Magenta",
        "Orange", "Pink", "Red", "White", "Yellow" };  
    final private String stringShapes[] = {"Lines", "Rectangles", "Oval"};
    final private int shapes[] = {0, 1, 2};
    final private JPanel messagePanel = new JPanel();
    
    //constructor class that instatiate variables calls its parent class
    public PrefsFrame() {
        
        super("Preferences"); //calls parent constructor
        
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
                        color = fileInput.nextInt();
                    }
                    else if (counter == 5) {
                        color2 = fileInput.nextInt();
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
        
        layout = new FlowLayout();
        container = getContentPane();
        setLayout(layout);
        
        textLabel = new JLabel("Set your default data: ");
        add(textLabel);
        
        shapeBox = new JComboBox(stringShapes);
        shapeBox.setMaximumRowCount(3);
        shapeBox.setSelectedItem(stringShapes[shapeType]);
        shapeBox.addItemListener(new ItemListener() {
            //check item state change and make changes accordingly
            public void itemStateChanged( ItemEvent event) {
                if(event.getStateChange() == ItemEvent.SELECTED)
                    shapeType = shapes[shapeBox.getSelectedIndex()];
            } //end itemStateChanged method
        } //end anonymous inner class
        ); //end call to addItemListener
        add(shapeBox);
        
        fillBox = new JCheckBox("Filled");
        //checks filled variable value, if equals to 1, fillBox is checked initially
        if(filled == 1){
            fillBox.setSelected(true);
        }
        fillBox.addItemListener(new ItemListener() {
            //check item state change and make changes accordingly
            public void itemStateChanged( ItemEvent event) {
                filled = fillBox.isSelected() ? 1 : 0;
            } //end itemStateChanged method
        } //end anonymous inner class
        ); //end call to addItemListener
        add(fillBox);
        
        colorBox1 = new JComboBox(stringColors);
        colorBox1.setMaximumRowCount(13);
        colorBox1.setSelectedItem(stringColors[color]); //selecting initial item in the comboBox
        colorBox1.addItemListener(new ItemListener() {
            //check item state change and make changes accordingly
            public void itemStateChanged( ItemEvent event) {
                if(event.getStateChange() == ItemEvent.SELECTED)
                    color = colorBox1.getSelectedIndex();
            } //end itemStateChanged method
        } //end anonymous inner class
        ); //end call to addItemListener
        add(colorBox1);
        
        colorBox2 = new JComboBox(stringColors);
        colorBox2.setMaximumRowCount(13);
        colorBox2.setSelectedItem(stringColors[color2]); //selecting initial item in the comboBox
        colorBox2.addItemListener(new ItemListener() {
        //check item state change and make changes accordingly
            public void itemStateChanged( ItemEvent event) {
                if(event.getStateChange() == ItemEvent.SELECTED)
                    color2 = colorBox2.getSelectedIndex();
            } //end itemStateChanged method
        } //end anonymous inner class
        ); //end call to addItemListener
        add(colorBox2);
        
        gradientBox = new JCheckBox("Gradient");
        //checks gradient variable value, if equals to 1, gradientBox is checked initially
        if(gradient == 1){
            gradientBox.setSelected(true);
        }
        gradientBox.addItemListener(new ItemListener() {
            //check item state change and make changes accordingly
            public void itemStateChanged( ItemEvent event) {
                gradient = gradientBox.isSelected() ? 1 : 0;
            } //end itemStateChanged method
        } //end anonymous inner class
        ); //end call to addItemListener
        add(gradientBox);
        
        lineWidthLabel = new JLabel(" Hit enter to take effect:");
        add(lineWidthLabel);
        
        lineWidthTextField = new JTextField( "Line Width", 6 );
        lineWidthTextField.addActionListener(new ActionListener() {
            //check user for entering any values
            public void actionPerformed( ActionEvent event ) {
                if( event.getSource() == lineWidthTextField ) {
                    //limts user to enter between 0 and 20 and if a string is entered, an error message will display
                    try {
                        lineWidth = Integer.parseInt(event.getActionCommand());
                        if(lineWidth < 0) {
                            lineWidth = 0;
                        }
                        else if(lineWidth > 20){
                            lineWidth = 20;
                        }
                    }
                    catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(messagePanel, "Please enter an integer!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } //end actionPerformed method
        } //end anonymous inner class
        ); //end call to actionPerformed
        add(lineWidthTextField);
        
        dashedBox = new JCheckBox("Dashed");
        //check if dashed variable value is 1, if so, dashedBox is selected by default
        if(dashed == 1){
            dashedBox.setSelected(true);
        }
        dashedBox.addItemListener(new ItemListener() {
            //check item state change and make changes accordingly
            public void itemStateChanged( ItemEvent event) {
                dashed = dashedBox.isSelected() ? 1 : 0;
            } //end itemStateChanged method
        }//end itemStateChanged
        ); //end call to addItemListener
        add(dashedBox);
        
        dashLengthLabel = new JLabel(" Hit enter to take effect:");
        add(dashLengthLabel);
        
        dashLengthTextField = new JTextField( "Dash Length", 7 );
        dashLengthTextField.addActionListener(new ActionListener() {
            //check user for entering any values
            public void actionPerformed( ActionEvent event ) {
                if( event.getSource() == dashLengthTextField ) {
                    //limts user to enter between 1 and 50 and if a string is entered, an error message will display
                    try {
                        dashLength = Integer.parseInt(event.getActionCommand());
                        if(dashLength < 1) {
                            dashLength = 1;
                        }
                        else if(dashLength > 50){
                            dashLength = 50;
                        }
                    }
                    catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(messagePanel, "Please enter an integer!", "Error", 
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
            } //end actionPerformed method
        } //end anonymous inner class 
        ); //end call to addItemListener
        add(dashLengthTextField);
        
        saveButton = new JButton("Save");
        saveButton.addActionListener( new ActionListener () {
            //checks if save button is clicked, if so, all initial variable values will be exported and saved in a file
            public void actionPerformed (ActionEvent event) {
                try {
                    PrintWriter fileOutput = new PrintWriter("configFile.txt");
                    fileOutput.println(shapeType);
                    fileOutput.println(filled);
                    fileOutput.println(gradient);
                    fileOutput.println(color);
                    fileOutput.println(color2);
                    fileOutput.println(lineWidth);
                    fileOutput.println(dashed);
                    fileOutput.println(dashLength);
                    fileOutput.close();
                }
                catch (IOException e) {
                    JOptionPane.showMessageDialog(messagePanel,"Error with configFile.txt.", "Error", 
                                                  JOptionPane.ERROR_MESSAGE);
                }
            } //end actionPerformed method
        } //end anonymous inner class 
        ); //end call to addItemListener
        add(saveButton);
    } //end PrefsFrame constructor
} //edn PrefsFrame class