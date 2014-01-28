/* Filename:        ATM_GUI_PA1_CIS314.java
 * Last Modified:   28 Jan 2014
 * Author:          Todd Parker
 * Email:           todd.i.parker@maine.edu
 * Course:          CIS314 - Advanced Java
 * 
 * NOTE: Code was adopted from "Java - How To Program" by Deitel and Deitel
 * 
 * This program provides a simulated ATM machine with a GUI component that repr-
 * esents an actual atm. A backend database is simulated by an object of
 * BankDatabase, which is hard-coded with two predefined user accounts:
 *  
 *  Account 1: Account Number = 12345   Pin Number = 54321
 *  Account 2: Account Number = 98765   Pin Number = 56789
 *
 * This classes only job is to create an ATM object, and the rest of the work
 * is begun from said objects run() method.
 */

package atm_gui_pa1_cis314;

public class ATM_GUI_PA1_CIS314
{
    // main method creates and runs the ATM
    public static void main(String[] args)
    {
        ATM theATM = new ATM();
        theATM.run();
    } // end main
} // end class ATM_GUI_PA1_CIS314
