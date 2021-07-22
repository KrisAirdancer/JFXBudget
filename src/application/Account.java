package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Account {
	
	Connection connection;

	ObservableList<Transaction> transactions;
	
	private StringProperty name;
	private DoubleProperty balance;
	
	public Account(String name, double balance) {
		
		// Initialize variables		
		this.name = new SimpleStringProperty(name);
		this.balance = new SimpleDoubleProperty(balance);
		
		// Initialize and populate ObservableList
//		transactions = getTransactions
		
	}
	
	/**
	 * This method pulls the transactions associated with this account from the list of
	 * transactions read in by the DBConnection class. 
	 */
	public void getTransactions() {
		
	}
	
    /**
     * Returns a String representation of the Account object.
     */
    public String toString() {
    	
    	String stringAccount = "[" + name.get() + ", " + "$" + Double.toString(balance.get()) + "]"; 
    	return stringAccount;
    }

}



// TODO Transfer the below notes to Obsidian or to somewhere else that makes sense - the comments in the codebase may do enough explaining that I can just delete all of this anyway.
/* - Each Transaction will know which account it is in, and when the program is loaded,
 * each account will be populated with the transactions from the Dummy.db database
 * that have the matching account associated with them.
 * 
 * - To save Accounts after the program is shut down, new accounts, when created, will
 * be added to their own database Accounts.db. On program start, the Accounts will be
 * created from the Accounts.db database.
 * 
 * NEEDED/TODO:
 * - A list to store the transactions associated with an account in (ObservableList - we want it
 * to be updatable).
 * - Methods + Constructor to read in the appropriate transactions.
 * - Create a database to house a list of accounts.
 * - Get accounts to display in the TableView in UI
 */