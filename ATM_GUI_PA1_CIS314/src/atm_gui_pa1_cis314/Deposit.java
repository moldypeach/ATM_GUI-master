// Deposit.java
// Represents a deposit ATM transaction

package atm_gui_pa1_cis314;

public class Deposit extends Transaction
{
    private double amount; // amount to deposit
    private Keypad keypad; // reference to keypad
    private DepositSlot depositSlot; // reference to deposit slot
    private final static int CANCELED = 0; // constant for cancel option
    
    // Deposit constructor
    public Deposit( int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, DepositSlot atmDepositSlot )
    {
        // initialize superclass variable
        super( userAccountNumber, atmScreen, atmBankDatabase );
        
        // initializze references to keypad and deposit slot
        keypad = atmKeypad;
        depositSlot = atmDepositSlot;
    } // end Deposit Constructor
    
    // perform transaction
    @Override
    public void execute()
    {
        BankDatabase bankDatabase = getBankDatabase(); // get reference
        Screen screen = getScreen(); // get reference
        boolean envelopeReceived = false; // cash not yet deposited
        
        amount = promptForDepositAmount(); // get deposit amount from user
        
        // check whether user entered a deposit amount or canceled
        if ( amount != CANCELED )
        {
            // request deposit envelope containing specified amount
            screen.resetDisplay(); // clear screen
            screen.displayMessage( "\nPlease insert a deposit envelop containing " );
            screen.displayDollarAmount( amount );
            screen.displayMessage( "." );
            screen.pause( 3 );
            
            // halt program until user deposits cash
            while ( !envelopeReceived )
            {
                screen.pause( 2 );
                envelopeReceived = depositSlot.getDepositCompleted();
            }
            depositSlot.setDepositCompleted();
            
            // check whether deposit envelope was received
            if ( envelopeReceived )
            {
                screen.displayMessageLine( "\nYour envelope has been received. " +
                "\nNOTE: The money just deposited will not be available until " +
                "we verify the amount of any enclosed cash and your checks have " +
                "cleared.");
                screen.pause( 5 );
                
                // credit account to reflect the deposit
                bankDatabase.credit( getAccountNumber(), amount);
            } // end if
            else // deposit envelope not received
            {
                screen.resetDisplay(); // clear screen
                screen.displayMessageLine( "\nYou did not insert an envelope. The " +
                "transaction has been canceled.");
                screen.pause( 3 );
            } // end else
        } // end if
        else // user canceled instead of entering an amount
        {
            screen.resetDisplay(); // clear screen
            screen.displayMessageLine( "\n Canceling transaction...");
            screen.pause( 3 );
        } // end else
    } // end method execute
    
    // prompt user to enter a deposit amount in cents
    private double promptForDepositAmount()
    {
        Screen screen = getScreen(); // get reference to screen
        
        // display the prompt
        screen.displayMessage( "\nPlease enter a deposit amount in CENTS (or 0 to " +
        "cancel): ");
        int input = keypad.getInput(); // receive input of deposit amount
        
        // check whether the user canceled or entered a valid amount
        if ( input == CANCELED )
            return CANCELED;
        else
        {
            return ( double ) input / 100; // return dollar amount
        } // end else
    } // end method promptForDepositAmount
} // end class Deposit