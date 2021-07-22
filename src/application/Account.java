package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Account {
	
	Connection connection;

	ObservableList<Transaction> transactions;
	
	public Account(String name, double balance) {
		
		// initialize stuff
	}
	
	private void readInTransactions(){
		
		// Initialize ObservableList to house Transactions
		transactions = FXCollections.observableArrayList();
		
		try {
			// Setting SQL query
			String query = "SELECT * FROM Transactions";

			// Create a Statement object to execute the SQL query
			Statement statement = connection.createStatement();

			// Execute SQL query and store the results in a ResultSet object
			ResultSet result = statement.executeQuery(query);

			// Pull data from database and use it to populate Transaction object(s)
			while (result.next()) {
				// Pull all desired fields from the database
				int id = result.getInt("Transaction ID");
				String date = result.getString("Date");
				String payee = result.getString("Payee");
				String category = result.getString("Category");
				String note = result.getString("Note");
				double amount = result.getDouble("Amount");

				// Create a Transaction object and populate it with data from the database
				Transaction trans = new Transaction(id, date, payee, category, note, amount);
				// Add the Transaction object to the transactions Observable List
				transactions.add(trans);
			}
		} catch (Exception e) {
			System.out.println("Failed to create Transaction object(s) from database.");
			e.printStackTrace();
		}
		
		
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