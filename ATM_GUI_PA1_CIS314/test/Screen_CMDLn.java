/* Screen.java
 * Represents the screen of the ATM
 */

package atm_gui_pa1_cis314;

public class Screen_CMDLn
{
    // display a message without a carriage return
    public void displayMessage( String message )
    {
        System.out.print( message );
    } // end method displayMessage
    
    // display a message with a carriage return
    public void displayMessageLine( String message )
    {
        System.out.println( message );
    } // end method displayMessageLine
    
    // display a dollar amount
    public void displayDollarAmount( double amount )
    {
        System.out.printf( "$%,.2f", amount);
    } // end method displayDollarAmount
} // end class Screen
