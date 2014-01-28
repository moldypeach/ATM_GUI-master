/* Filename:        Screen.java
 * Last Modified:   28 Jan 2014
 * Author:          Todd Parker
 * Email:           todd.i.parker@maine.edu
 * Course:          CIS314 - Advanced Java
 * 
 * NOTE: Code was adopted from "Java - How To Program" by Deitel and Deitel
 * 
 * Screen represents the ATM's display screen and utilizes a series of methods
 * maintain the screen. Method clearScreenText() deletes the string passed to it
 * from the screen, resetDisplay() clears the entire screen, getScreen() returns
 * a Screen object, and pause() is used to temporarily halt the ATM operations.
 * pause() is necessary in order to allow for calling classes to ensure a user
 * has ample time to read displayed messages, and to ensure a user has deposited
 * an envelope or received their cash. The value recieved by pause is in mili-
 * seconds, but is multipled by 1000 so that calling classes need only input
 * values as seconds to pause. The display methods are self-explanitory via
 * their naming, and they simulate the Screen itself via their operations.
 */

package atm_gui_pa1_cis314;

import java.awt.Color;
import javax.swing.JTextArea;

public class Screen
{
    private JTextArea textArea; // textarea to display output
    
    // no-argument constructor to create JPanel
    public Screen()
    {
        textArea = new JTextArea();
        textArea.setEditable( false );
        textArea.setLineWrap( true );
        textArea.setColumns( 70 );
        textArea.setRows( 10 );
        textArea.setBackground( Color.BLACK );
        textArea.setForeground( Color.GREEN );
        textArea.setFocusable( false );
    } // end Screen constructor 
    
    // get method to return panel object
    public JTextArea getScreen()
    {
        return textArea;
    } // end method getPanel()
    
    // replace selected range
    public void clearScreenText(String s)
    {
        String screenText = textArea.getText();
        int startPos = screenText.length() - s.length();
        int endPos = screenText.length();
        
        textArea.replaceRange( "" , startPos, endPos);
    } // end method clearText
    
    // display a message without a carriage return
    public void displayMessage( String message )
    {
        textArea.append( message );
    } // end method displayMessage
    
    // display a message with a carriage return
    public void displayMessageLine( String message )
    {
        textArea.append( message + "\n" );        
    } // end method displayMessageLine
    
    // display a dollar amount
    public void displayDollarAmount( double amount )
    {
        String amountFormatted = String.format( "$%,.2f", amount );
        textArea.append( amountFormatted );
    } // end method displayDollarAmount
    
    // clear ATM screen
    public void resetDisplay()
    {
        textArea.setText( "" );
    } // end method resetDisplay
    
    // delay screen refresh
    public void pause(int sec)
    {
        sec = sec * 1000; // convert to seconds
        try
        {
            Thread.sleep( sec );
        }
        catch ( Exception e)
        {

        }        
    } // end method pause
} // end class Screen
