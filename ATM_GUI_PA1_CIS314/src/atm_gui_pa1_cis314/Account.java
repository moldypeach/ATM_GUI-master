/* Filename:        Account.java
 * Last Modified:   28 Jan 2014
 * Author:          Todd Parker
 * Email:           todd.i.parker@maine.edu
 * Course:          CIS314 - Advanced Java
 * 
 * NOTE: Code was adopted from "Java - How To Program" by Deitel and Deitel
 * 
 * This class is used to represent a user account. Account objects each maint-
 * ain the account number, pin, available balance, and total balance for each
 * object created.  Accessor and Mutator methods are provided for each of the 
 * fields, and also a method validatePIN serves to authenticate a user ( i.e. 
 * each Account objects knows the validity of its own account information )
 */

package atm_gui_pa1_cis314;

import java.io.Serializable;

public class Account implements Serializable
{
    private int accountNumber; // account number
    private int pin; // PIN for authentication
    private double availableBalance; // funds available for withdrawl
    private double totalBalance; // funds available + pendings deposits
    
    // Account constructor initializes attributes
    public Account( int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance )
    {
        accountNumber = theAccountNumber;
        pin = thePIN;
        availableBalance = theAvailableBalance;
        totalBalance = theTotalBalance;
    } // end Account constructor
    
    // determines whether a user-specified PIN matches PIN in Account
    public boolean validatePIN( int userPIN )
    {
        if ( userPIN == pin )
            return true;
        else
            return false;
    } // end method validatePIN
    
    // returns available balance
    public double getAvailableBalance()
    {
        return availableBalance;
    } // end getAvailableBalance
    
    // returns the total balance
    public double getTotalBalance()
    {
        return totalBalance;
    } // end method getTotalBalance
    
    // credits an amount to the account
    public void credit( double amount )
    {
        totalBalance += amount; // add to total balance
    } // end method credit
    
    // debits an amount from the account
    public void debit( double amount )
    {
        availableBalance -= amount; // subtract from available balance
        totalBalance -= amount; // subtract from total balance
    } // end method debit
    
    // returns account number
    public int getAccountNumber()
    {
        return accountNumber;
    } // end method getAccountNumber
} // end class Account
