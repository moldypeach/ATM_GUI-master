/* Screen.java
 */

package atm_gui_pa1_cis314;

import java.awt.Color;
import javax.swing.JTextArea;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;

public class Screen
{
    private JTextArea textArea; // textarea to display output
//    private JPanel displayPanel;
//    private JScrollPane scrollPane;
    
    // no-argument constructor to create JPanel
    public Screen()
    {
//        displayPanel = new JPanel();
        textArea = new JTextArea();
        textArea.setEditable( false );
        textArea.setLineWrap( true );
        textArea.setColumns( 70 );
        textArea.setRows( 10 );
        textArea.setBackground( Color.BLACK );
        textArea.setForeground( Color.GREEN );
        textArea.setFocusable( false );
//        scrollPane = new JScrollPane( textArea );
//        displayPanel.add( new JScrollPane( textArea ) );
//        displayPanel.add( textArea );
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
//        textArea.setText( message );
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
