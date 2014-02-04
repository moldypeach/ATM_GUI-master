/* Filename:        Transaction.java
 * Last Modified:   28 Jan 2014
 * Author:          Todd Parker
 * Email:           todd.i.parker@maine.edu
 * Course:          CIS314 - Advanced Java
 * 
 * NOTE: Code was adopted from "Java - How To Program" by Deitel and Deitel
 * 
 *  Abstract superclass Transaction represents an ATM transaction. This class
 * serves only to allow polymorphic capabilities for each concrete class to
 * maintain their own versions of Transaction's fields and execute() method.
 * Concrete implementations are: Withdrawl, Deposit, and BalanceInquiry.
 */

package atm_gui_pa1_cis314;

import java.io.*;

public abstract class Transaction implements Serializable
{
    private int accountNumber; // indicates account involved
    private Screen screen; // ATM's screen
    private BankDatabase bankDatabase; // account info database
    
    // Transaction constructor invoked by subclasses using super()
    public Transaction( int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase )
    {
        accountNumber = userAccountNumber;
        screen = atmScreen;
        bankDatabase = atmBankDatabase;
    } // end Transaction constructor
    
    // return account number
    public int getAccountNumber()
    {
        return accountNumber;
    } // end method getAccountNumber
    
    // return reference to screen
    public Screen getScreen()
    {
        return screen;
    } // end method getScreen
    
    // return reference to bank database
    public BankDatabase getBankDatabase()
    {
        return bankDatabase;
    } // end method getBankDatabase
    
    // perform the transaction (overridden by each subclass)
    abstract public void execute();
    
} // end class Transaction
