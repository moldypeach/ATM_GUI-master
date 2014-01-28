/* BalanceInquiry.java
 * Represents a balance inquiry ATM transaction
 */

package atm_gui_pa1_cis314;

public class BalanceInquiry extends Transaction
{
    // BalanceInquiry constructor
    public BalanceInquiry( int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase )
    {
        super( userAccountNumber, atmScreen, atmBankDatabase );
    } // end BalanceInquiry constructor
    
    // performs the transaction
    @Override
    public void execute()
    {
        // get references to bank database and screen
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        
        // get the avaialable balance for the account involved
        double availableBalance = bankDatabase.getAvailableBalance( getAccountNumber() );
        
        // get the total balance for the account involved
        double totalBalance = bankDatabase.getTotalBalance( getAccountNumber() );
        
        // display the balance information on the screen
        screen.resetDisplay(); // clear screen
        screen.displayMessageLine( "\nBalance Information:" );
        screen.displayMessage( "\t - Available balance: " );
        screen.displayDollarAmount( availableBalance );
        screen.displayMessage( "\n\t - Total balance:     " );
        screen.displayDollarAmount( totalBalance );
        screen.displayMessageLine( "" );
        // Allow 5 seconds to review balance information before clearing screen
        screen.pause( 5 );
    } // end method execute
} // end class BalanceInquiry
