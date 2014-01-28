/* DepositSlot.java
 * Represents the deposit slot of the ATM
 */

package atm_gui_pa1_cis314;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class DepositSlot
{
    private JButton depositButton; // button to deposit cash
    private boolean depositCompleted; // flag to determine if deposit was completed
    // no-argument DepositSlot constructor
    public DepositSlot()
    {
        depositButton = new JButton("Insert deposit envelop here");
        depositCompleted = false;
        DepositButtonHandler handler = new DepositButtonHandler();
        depositButton.addActionListener( handler );
    } // end DispostSlot constructor
    
    // inner class to handle depositButton events
    private class DepositButtonHandler implements ActionListener
    {
        // handle button event
        public void actionPerformed( ActionEvent event )
        {
            if ( event.getActionCommand().equalsIgnoreCase( "Insert deposit envelop here" ) )
                depositCompleted = true;
        } // end actionPerformed method
    } // end private inner class DepositButtonHandler
    
    // indicates whether envelope was received ( always returns true, because
    // this is only a software simulation of a real deposit slot)
    
    // Accessor method to return depositCompleted
    public boolean getDepositCompleted()
    {
        return depositCompleted;
    } // end getDepositCompleted method
    
    // Mutator method to set depositCompleted
    public void setDepositCompleted()
    {
        depositCompleted = false;
    } // end setDepositCompleted method
    
    // Accessor method to return Deposit Slot Button
    public JButton getDepositButton()
    {
        return depositButton;
    } // end getDepositButton method
} // end class DepositSlot
