package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBConnection {

	private Connection connection;
	
	private ObservableList<Transaction> transactions;
	
	/**
	 * Connects the program to the Dummy.db database.
	 * 
	 * TODO Could be made a general method by 1) allowing the user to input their own URL and
	 * 2) by returning the connection object.
	 */
	public void connectToDatabase() {
		
		String jdbcURL = "jdbc:sqlite:/C:\\Users\\chris\\eclipse-workspace\\JFXBudget\\Databases\\Dummy.db"; // This is the directory of the database - if this doesn't work, try the directory to the sqlite3.exe (C:\sqlite3) - removing the "/" befor the ":C" may also help
		
		try {
			// 1) Access this database
			connection = DriverManager.getConnection(jdbcURL); // This connects our program to the database (I think this means that it reads the data in?) - Must be surrounded by a try-catch block
			
		} catch (SQLException e) {
			System.out.println("Failed to connect to database.");
		}
	}
	
	/**
	 * Reads data in from the database, initializes transaction objects with the data, and
	 * stores the data in an ObservableList.
	 * 
	 * TODO Could be made general by 1) allowing the SQL query to be set by the user and
	 * 2) having the method return an ObservableList object.
	 */
	public ObservableList<Transaction> readInTransactions() {
		
		// Initialize the Transactions ObservableList
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
		return transactions;
	}

	/**
	 * Prints the entire contents of the database to the console.
	 */
	public void printTableDataToConsole() {
		
		try {
			// 2) Create an SQL query that selects all rows from the table
			String sql = "SELECT * FROM Transactions";
			
			// 3) Create a Statement object to execute the SQL query
			Statement statement = connection.createStatement();
			
			// 4) Execute SQL query and store the results in a ResultSet object
			ResultSet result = statement.executeQuery(sql);
			
			// 5) Pull and print the data from the database
			while (result.next()) {
				// Time Stamp: 11:59 https://www.youtube.com/watch?v=293M9-QRZ0c&ab_channel=CodeJava
				// Pull all of the data from the database
				String ID = result.getString("Transaction ID");
				String Date = result.getString("Date");
				String Payee = result.getString("Payee");
				String Category = result.getString("Category");
				String Note = result.getString("Note");
				String Amount = result.getString("Amount");
				
				// Print the data from the database
				System.out.println(ID + " | " + Date + " | " + Payee + " | " + Category + " | " + Note + " | " + Amount);
			}
		} catch (Exception e) {
			System.out.println("Failed to print data to console");
		}
	}

	/**
	 * Returns the Connection object.
	 */
	public Connection getConnection() {
		return connection;
	}
	
	/**
	 * Returns the transactions ObservableList.
	 */
	public ObservableList<Transaction> getTransactionsList() {
		return transactions;
	}
}
