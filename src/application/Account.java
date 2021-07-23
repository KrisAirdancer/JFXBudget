package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
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
		// TODO Build this out
	}
	
    // Define getters for the Properties' values. Note: This allows for a non-Property type to be returned.
    public String getName() {return name.get();}
    public double getBalance() {return balance.get();}
 
    // Define getters for the Property itself Note: This returns a Property type.
    public StringProperty nameProperty() {return name;}
    public DoubleProperty doubleProperty() {return balance;}

	// Setters
    public void setName(String name) {this.name = new SimpleStringProperty(name);}
    public void setBalance(double balance) {this.balance = new SimpleDoubleProperty(balance);}
	
	
    /**
     * Returns a String representation of the Account object.
     */
    public String toString() {
    	
    	String stringAccount = "[" + name.get() + ", " + "$" + Double.toString(balance.get()) + "]"; 
    	return stringAccount;
    }

}