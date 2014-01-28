/* Filename:        DepositSlot.java
 * Last Modified:   28 Jan 2014
 * Author:          Todd Parker
 * Email:           todd.i.parker@maine.edu
 * Course:          CIS314 - Advanced Java
 * 
 * NOTE: Code was adopted from "Java - How To Program" by Deitel and Deitel
 * 
 * Represents the deposit slot of the ATM. The only purpose of DepositSlot is
 * to simulate the deposit slot of the ATM and to wait until a user as deposit-
 * ed an envelope to continue. The program is halted until such an action is
 * detected; which, is similar to the process of CashDispenser.
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
