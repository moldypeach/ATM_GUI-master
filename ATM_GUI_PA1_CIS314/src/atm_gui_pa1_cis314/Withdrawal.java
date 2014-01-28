/* Filename:        Withdrawal.java
 * Last Modified:   28 Jan 2014
 * Author:          Todd Parker
 * Email:           todd.i.parker@maine.edu
 * Course:          CIS314 - Advanced Java
 * 
 * NOTE: Code was adopted from "Java - How To Program" by Deitel and Deitel
 * 
 * Withdrawal represents a withdrawl ATM transaction, and is a concrete imple-
 * mentation of abstract class Transaction. It displays a withdrawal menu to an
 * ATM user, interacts with CasDispenser to determine available ATM bills, and 
 * interactions with BankDatabase to determine if sufficient user funds are
 * available. The ATM is halted upon withdrawal until detection of cash receiv-
 * ed by CashDispenser.
 */

package atm_gui_pa1_cis314;

public class Withdrawal extends Transaction
{
    private int amount; // amount to withdraw
    private Keypad keypad; // reference to keypad
    private CashDispenser cashDispenser; // reference to case dispenser
    
    // constant corresponding to menu option to cancel
    private final static int CANCELED = 6;
    
    // withdrawal constructor
    public Withdrawal( int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, CashDispenser atmCashDispenser )
    {
        // initialize superclass variables
        super( userAccountNumber, atmScreen, atmBankDatabase );
        
        // initialize references to keypad and cash dispenser
        keypad = atmKeypad;
        cashDispenser = atmCashDispenser;
    } // end Withdrawal constructor
    
    // perform transaction
    @Override
    public void execute()
    {
        boolean cashDispensed = false; // cash was not dispensed yet
        double availableBalance; // amount available for withdrawal
        
        // get refereces to bank database and screen
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        
        // loop until cash is dispensed or the user cancels
        do
        {
            // obtain a chosen withdrawal amount form the user
            amount = displayMenuOfAmounts();
            
            // check whether user chose a withdrawal amount or canceled
            if ( amount != CANCELED )
            {
                // get available balance of account involved
                availableBalance = bankDatabase.getAvailableBalance( getAccountNumber() );
            
                // check whether the user has enough money in the account
                if ( amount <= availableBalance )
                {
                    // check whether the cash dispenser has enough money
                    if ( cashDispenser.isSufficientCashAvailable( amount ) )
                    {
                        // update the account involved to reflect the withdrawal
                        bankDatabase.debit( getAccountNumber() , amount );

                        cashDispenser.dispenseCash( amount ); // dispense case

                        // instruct user to take cash
                        screen.resetDisplay(); // clear screen
                        screen.displayMessageLine( "\nYour cash has been dispensed. " +
                        "Please take your cash now." );
                        // Halt program until user accepts cash
                        while ( !cashDispensed )
                        {
                            screen.pause( 2 );
                            cashDispensed = cashDispenser.getCashTaken();
                        }
                        cashDispenser.setCashTaken(); // reset cash taken flag
                    } // end if
                    else // cash dispenser does not have enough cash
                    {
                        screen.resetDisplay(); // clear screen
                        screen.displayMessageLine( "\nInsufficient cash available in the ATM." +
                        "\n\nPlease choose a smaller amount.");
                        screen.pause( 3 );
                    } // end else
                } // end if
                else // not enough money available in user's account
                {
                    screen.resetDisplay(); // clear screen
                    screen.displayMessageLine( "\nInsufficient cash available in your account." +
                    "\n\nPlease choose a smaller amount.");
                    screen.pause( 3 );
                } // end else
            } // end if
            else // user chose cancel menu option
            {
                screen.resetDisplay(); // clear screen
                screen.displayMessageLine( "\nCanceling transaction..." );
                screen.pause( 3 );
                return; // return to main menu because user canceled
            } // end else
        } while ( !cashDispensed );
    } // end method execute
    
    // display a menu of withdrawal amounts and the option to cancel;
    // return the chosen amount or 0 if the user choosed to cancel
    private int displayMenuOfAmounts()
    {
        int userChoice = 0; // local variable to store return value
        
        Screen screen = getScreen();  // get screen reference
        
        // array of amounts to correspond to menu numbers
        int[] amounts = { 0, 20, 40, 60, 100, 200 };
        
        // loop while no valid choice has been made
        while ( userChoice == 0)
        {
            // display the withdrawal menu
            screen.resetDisplay(); // clear screen
            screen.displayMessageLine( "Withdrawal Menu: " );
            screen.displayMessageLine( "1 - $20" );
            screen.displayMessageLine( "2 - $40" );
            screen.displayMessageLine( "3 - $60" );
            screen.displayMessageLine( "4 - $100" );
            screen.displayMessageLine( "5 - $200" );
            screen.displayMessageLine( "6 - Cancel transaction" );
            screen.displayMessage( "\nChoose a withdrawal amount: " );
            
            int input = keypad.getInput(); // get user input through keypad
            
            // determine how to proceed based on the input value
            switch ( input )
            {
                case 1: // if the user chose a withdrawal amount
                case 2: // (i.e. chose option 1, 2, 3, 4 or 5), return the
                case 3: // corresponding amount from amounts array
                case 4:
                case 5:
                    userChoice = amounts[ input ]; // save user's choice
                    break;
                case CANCELED: // the user chose to cancel
                    userChoice = CANCELED; // save user's choice
                    break;
                default: // the user did not enter a value from 1-6
                    screen.resetDisplay(); // clear screen
                    screen.displayMessageLine( "\nInvalid selection. Try again." );
                    screen.pause( 3 );
            } // end switch
        } // end while
        
        return userChoice; // return withdrawal amount or CANCELED
    } // end method displayMenuOfAmounts
} // end class Withdrawal
