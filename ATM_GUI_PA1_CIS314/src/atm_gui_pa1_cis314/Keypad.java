/* Filename:        Keypad.java
 * Last Modified:   28 Jan 2014
 * Author:          Todd Parker
 * Email:           todd.i.parker@maine.edu
 * Course:          CIS314 - Advanced Java
 * 
 * NOTE: Code was adopted from "Java - How To Program" by Deitel and Deitel
 * 
 * Keypad simulates the keypad of the ATM. It is what allows user input to be
 * received into the program via ActionEvents fired by their respective keypad
 * clicks (e.g. Enter, Clear, 0-9). The actual keypad JPanel is created within
 * this class and returned to calling classes (ATM) via an Accessor method.
 * Method getInput() sleeps the main thread until an ActionEvent sets input to
 * greater than 0, returns the valid input to calling class, then resets input
 * to -1. Keypad maintains a Screen object such that the a user can clear only
 * the numbers they've typed, and, so the keys typed (0-9) may be displayed on
 * the ATM Screen as they are entered.
 */

package atm_gui_pa1_cis314;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout; // reference page 613
import javax.swing.JButton;
import javax.swing.JPanel;

public class Keypad extends JPanel
{
    private int input; // value entered from keypad
    private String response; // temp variable to hold GUI input
    private JPanel keyPanel; // panel to hold keypad buttons
    private JButton[] keypadButtons; // array of keypad buttons
    private String[] buttonNames = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "Clear", "Enter" };
    private Screen screen; // ATM screen object
    
    // no-argument constructor initializes the Scanner
    public Keypad(Screen atmScreen)
    {
        input = -1;
        response = "";
        screen = atmScreen;
        keypadButtons = new JButton[ buttonNames.length ]; // set array size
        keyPanel = new JPanel();
        keyPanel.setLayout( new GridLayout( 4, 3));
        KeypadHandler handler = new KeypadHandler();
        
        // create JButtons, add to panel, and add action listeners
        for ( int count = 0; count < buttonNames.length; count++ )
        {
            keypadButtons[ count ] = new JButton( buttonNames[ count ] );
            keypadButtons[ count ].setPreferredSize( new Dimension( 60, 60 ));
            keypadButtons[ count ].setBackground( new Color(174, 174, 174) );
            keypadButtons[ count ].addActionListener( handler );
            keyPanel.add( keypadButtons[ count ] ); // add button to panel
        } // end for        
    } // end no-argument Keypad constructor
    
    // private inner class for event handling
    private class KeypadHandler implements ActionListener
    {
        // handle button events
        public void actionPerformed( ActionEvent event )
        {
            // don't process enter command without first receiving input
            if ( event.getActionCommand().equalsIgnoreCase("Enter"))
            {
                if ( !response.equalsIgnoreCase( "" ) )
                {
                    input = Integer.parseInt( response );

                }                
            }
            // if there is user input, then clear response variable and user input printed to screen
            else if ( event.getActionCommand().equalsIgnoreCase("Clear") )
            {    
                if ( !response.equalsIgnoreCase( "" ) )
                {
                    screen.clearScreenText( response );
                    response = "";
                }
            } // end else if
            //  User clicked keys 0-9, save keyclicks
            else
            {
                String temp = "";
                response += event.getActionCommand();
                temp = event.getActionCommand();
                screen.displayMessage( temp );
            } // end else
        } // end method actionPerformed  
    } // end private inner class KeypadHandler
    
    // return an integer value entered by user

    // get method to return panel object
    public JPanel getPanel()
    {
        return keyPanel;
    } // end method getPanel()
    
    // Sleep thread until ActionEvent detects valid input to return
    public int getInput()
    {
        while ( input == -1)
        {
            try
            {
                Thread.sleep( 30 );                
            }
            catch (Exception e)
            {
            }
        }
        int temp = input;
        input = -1;
        response = "";
        
        return temp;
    } // end method getInput
} // end class Keypad
