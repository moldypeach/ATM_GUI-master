/* ATM.java
 * Represents an automated teller machine
 */

package atm_gui_pa1_cis314;

//import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Color;
//import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class ATM extends JFrame
{
    private boolean userAuthenticated; // whether user is authenticated
    private int currentAccountNumber; // current user's account number
    private Screen screen; // ATM's screen
    private Keypad keypad; // ATM's keypad
    private CashDispenser cashDispenser; // ATM's cash dispenser
    private DepositSlot depositSlot; // ATM's deposit slot
    private BankDatabase bankDatabase; // account information database
//    private JPanel cashSlotPanels; // ATM's panel to hold withdrawal and deposit slots
    private GridBagLayout layout; // layout of frame
    private GridBagConstraints constraints; // Constraints of layout
    private Student_Sig_Block sig; // create signature block object
    
    // constants corresponding to main menu options
    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 4;
    
    // no-argument ATM constructor initializes instance variables
    public ATM()
    {
        super( "ATM with GUI" );
        sig = new Student_Sig_Block();
        userAuthenticated = false; // user is not authenticated to start
        currentAccountNumber = 0; // no current account number to start
        screen = new Screen(); // create screen
        keypad = new Keypad( screen); // create keypad
        cashDispenser = new CashDispenser(); // create cash dispenser
        depositSlot = new DepositSlot(); // create deposit slot
        bankDatabase = new BankDatabase(); // create acct info database
        initFrame();        
    } // end no-argument ATM constructor
    
    // start ATM
    public void run()
    {
        // welcome and authenticate user; perform transactions
        while ( true )
        {
            // loop while user is not yet authenticated
            while ( !userAuthenticated )
            {
                screen.displayMessageLine( "Welcome!" );
                authenticateUser(); // authenticate user
            } // end while
            
            performTransactions(); // user is now authenticated
            userAuthenticated = false; // reset before next ATM session
            currentAccountNumber = 0; // reset before next ATM session
            screen.displayMessageLine( "Thank you! Goodbye!" );            
            screen.pause( 5 );
            screen.resetDisplay(); // clear screen
        } // end while
    } // end method run
    
    // attempts to authenticate user against database
    private void authenticateUser()
    {
        screen.displayMessage( "\nPlease enter your account number: " );
        int accountNumber = keypad.getInput(); // input account number
        screen.displayMessage( "\nEnter your PIN: " ); // prompt for PIN
        int pin = keypad.getInput(); // input PIN
        
        // set userAuthenticated to boolean value returned by database
        userAuthenticated = bankDatabase.authenticateUser( accountNumber , pin );
        
        // check whether authentication succeeded
        if ( userAuthenticated )
        {
            currentAccountNumber = accountNumber; // save user's account #
        } // end if
        else
        {
            screen.resetDisplay(); // clear screen
            screen.displayMessageLine( "Invalid account number or PIN. Please try again." );
            screen.pause( 3 );
            screen.resetDisplay(); // clear screen (called twice to welcome can display after error)
        } // end else
    } // end method authenticateUser
    
    private void performTransactions()
    {
        // local variable to store transaction currently being processed
        Transaction currentTransaction = null;
        boolean userExited = false; // user has not chosen to exit
        
        // loop while user has not chosen option to exit system
        while ( !userExited )
        {
            // show main menu and get user selection
            int mainMenuSelection = displayMainMenu();
            
            // decide how to proceed based on user's menu selection
            switch ( mainMenuSelection )
            {
                // user chose to perform one of three transaction types
                case BALANCE_INQUIRY:
                case WITHDRAWAL:
                case DEPOSIT:
                    
                    // initialize as new object of chosen type
                    currentTransaction = createTransaction( mainMenuSelection );
                    
                    currentTransaction.execute(); // execute transaction
                    break;
                case EXIT: // user chose to terminate session
                    screen.resetDisplay(); // clear screen
                    screen.displayMessageLine( "\nExiting the system..." );
                    userExited = true; // this ATM session should end
                    break;
                default: // user did not enter an integer from 1-4
                    screen.resetDisplay(); // clear screen
                    screen.displayMessageLine( "\nYou did not enter a valid selection. Try again." );
                    screen.pause( 3 );
                    break;
            } // end switch
        } // end while
    } // end method performTransactions
    
    // display the main menu and return an input selection
    private int displayMainMenu()
    {
        screen.resetDisplay(); // clear screen
        screen.displayMessageLine( "Main menu:" );
        screen.displayMessageLine( "1 - View my balance" );
        screen.displayMessageLine( "2 - Withdraw cash" );
        screen.displayMessageLine( "3 - Deposit funds" );
        screen.displayMessageLine( "4 - Exit\n" );
        screen.displayMessage( "Enter a choice: " );
        return keypad.getInput(); // return user's selection
    } // end method displayMainMenu
    
    // return object of specified Transaction subclass
    private Transaction createTransaction( int type )
    {
        Transaction temp = null; // temporary Transaction variable
        
        // determine which type of Transaction to create
        switch ( type )
        {
            case BALANCE_INQUIRY: // create new BalanceInquiry transaction
                temp = new BalanceInquiry( currentAccountNumber, screen, bankDatabase );
                break;
            case WITHDRAWAL: // create new Withdrawal transaction
                temp = new Withdrawal( currentAccountNumber, screen, bankDatabase, keypad, cashDispenser );
                break;
            case DEPOSIT: // create new Deposit transaction
                temp = new Deposit( currentAccountNumber, screen, bankDatabase, keypad, depositSlot );
                break;
        } // end switch
        
        return temp; // return the newly created object
    } // end method createTransaction
    
    public void initFrame()
    {
        // Initialize layout
        layout = new GridBagLayout();
        setLayout( layout );        
        constraints = new GridBagConstraints(); // instantiate constraints
        
        // Create GUI Components
        JPanel keypadPanel = new JPanel(); // ATM's panel to hold the keypad
        JPanel fillerPanel = new JPanel(); // empty panel
        JPanel fillerPanel1 = new JPanel(); // empty panel
        JButton cashDispenserButton = new JButton(); // ATM's cash dispenser
        JButton depositSlotButton = new JButton(); // ATM's deposit slot
        JTextArea screenArea = new JTextArea(); // ATM's screen
        JTextArea sigBlock = new JTextArea(sig.toString()); // Signature block label
        
        // ATM's Display
        screenArea = screen.getScreen();
        screenArea.setBorder( BorderFactory.createCompoundBorder( new EmptyBorder( 5,5,5,5), BorderFactory.createLineBorder( Color.DARK_GRAY, 4, false )) );
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.3;
        constraints.weighty = 0.3;
        addComponent( screenArea, 0, 0, 3, 1);
        
        // Signature Block
        sigBlock.setBackground( Color.LIGHT_GRAY );
        sigBlock.setForeground( Color.BLACK );
        sigBlock.setRows( 7 );
        sigBlock.setColumns( 40 );
        sigBlock.setMargin( new Insets(10,10,10,10) );
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.3;
        constraints.weighty = 0;
        addComponent( sigBlock, 0, 3, 1, 1 );     
        
        // ATM Cash Dispenser
        cashDispenserButton = cashDispenser.getDispenserButton();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0;
        constraints.weighty = 0.2;
        addComponent( cashDispenserButton, 1, 0, 2, 1);
        
        // Filler panel
        fillerPanel1.setBackground( Color.LIGHT_GRAY );
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0;
        constraints.weighty = 0.3;
        addComponent( fillerPanel1, 2, 0, 2, 2);
        
        // ATM Deposit Slot
        depositSlotButton = depositSlot.getDepositButton();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.2;
        constraints.weighty = 0.2;        
        addComponent( depositSlotButton, 4, 0, 2, 1);
        
        // Filler Panel
        fillerPanel.setBackground( Color.LIGHT_GRAY );
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.2;
        constraints.weighty = 0;        
        addComponent( fillerPanel, 1, 2, 1, 4);
        
        // ATM Keypad
        keypadPanel = keypad.getPanel();
        keypadPanel.setBorder( BorderFactory.createCompoundBorder( new EmptyBorder( 5,5,5,5), BorderFactory.createLineBorder( Color.GRAY, 4, false )));
        keypadPanel.setBackground( Color.LIGHT_GRAY );
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0;
        constraints.weighty = 0;
        addComponent(keypadPanel, 1, 3, 1, 4);        
        
        ATM.this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        ATM.this.getContentPane().setBackground( Color.LIGHT_GRAY );
        ATM.this.setSize( 1024, 768 ); // set frame size
        ATM.this.setResizable( false );        
        ATM.this.setVisible( true ); // display frame
//        ATM.this.pack();
    } // end initFrame
    
    // method to set constraints on GridBagLayout
    private void addComponent( Component component, int row, int col, int colSpan, int rowSpan)
    {
        constraints.gridx = col; // set gridx
        constraints.gridy = row; // set gridy
        constraints.gridwidth = colSpan; // set gridwidth
        constraints.gridheight = rowSpan; // set gridheight
        layout.setConstraints( component, constraints ); // set constraints
        add( component ); // add component
    } // end method addComponent
} // end class ATM
