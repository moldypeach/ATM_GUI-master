/* CashDispenser.java
 * Represents the cash dispenser of the ATM
 */

package atm_gui_pa1_cis314;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class CashDispenser extends JButton
{
    private JButton dispenserButton; // button to dispense cash
    // the default inital number of bills in the cash dispenser
    private final static int INITIAL_COUNT = 500;
    private int count; // number of $20 bills 
    private boolean cashTaken; // flag to determine if cash was taken from dispenser
    
    // no-argument CashDispenser constructor initializes count to default
    public CashDispenser()
    {
        count = INITIAL_COUNT; // set count attribute to default
        cashTaken = false;
        dispenserButton = new JButton("Take cash here");
        CashDispenserHandler handler = new CashDispenserHandler();
        dispenserButton.addActionListener( handler );
    } // end CashDispenser constructor
    
    // inner class to handler dispenserButton events
    private class CashDispenserHandler implements ActionListener
    {
        // handle button event
        public void actionPerformed( ActionEvent event )
        {
            if ( event.getActionCommand().equalsIgnoreCase( "Take cash here" ) )    
                cashTaken = true;
        } // end actionPerformed method
    } // end private class CashDispenserHandler
    
    // simulates dispensing of specified amount of cash
    public void dispenseCash( int amount )
    {
        int billsRequired = amount / 20; // number of $20 bills required
        count -= billsRequired; // update the count of 
    } // end method dispenseCash
    
    // Accessor method to return Cash Dispenser Button
    public JButton getDispenserButton()
    {
        return dispenserButton;
    } // end get method getDispenserButton
    
    // Accessor method to return cashTaken
    public boolean getCashTaken()
    {
        return cashTaken;
    } // end method getCashTaken
    
    // Mutator method to set cashTaken
    public void setCashTaken()
    {
        cashTaken = false;
    } // end method setCashTaken
    // indicates whether cash dispenser can dispense desired amount
    public boolean isSufficientCashAvailable( int amount )
    {
        int billsRequired = amount / 20; // number of $20 bills required
        
        if ( count >= billsRequired )
            return true; // enough bills available
        else
            return false; // not enough bills available
    } // end method isSufficientCashAvailable
} // end class CashDispenser
