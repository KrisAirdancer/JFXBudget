package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Note: Make sure all imports are from java.sql. - Not from other libraries.
 * 
 * @author Chris Marston
 *
 */
public class DBConnection {
	
	public Connection connection; // TODO Does this need to be public? Can it be private or protected and still work? private/protected would be better for encapsulation.
	public ObservableList<Transaction> transactions = FXCollections.observableArrayList(); // A list in which to store transactions
	public TableView<Transaction> transactionsTable;
	
	public DBConnection() {
		
	}
	
	/**
	 * Establishes a connection between the application and a specified database. TODO Should I set it up to go to a specified database, or should I just hard-code the database? This will depend on whether or not I need to access more than one database.
	 * 
	 * @param path The local location of the database to be connected to as a String-type file path. Must have
	 * double backslashes "\\"
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
	
	public void printTableDataToConsole() {
		
		try {
			// 2) Select all rows from the table...
			String sql = "SELECT * FROM Transactions";
			
			// 3) ???
			Statement statement = connection.createStatement();
			
			// 4) ???
			ResultSet result = statement.executeQuery(sql);
			
			// 5) Print the data pulled from the database
			while (result.next()) {
				// Time Stamp: 11:59 https://www.youtube.com/watch?v=293M9-QRZ0c&ab_channel=CodeJava
				
				String ID = result.getString("Transaction ID");
				String Date = result.getString("Date");
				String Payee = result.getString("Payee");
				String Category = result.getString("Category");
				String Note = result.getString("Note");
				String Amount = result.getString("Amount");
				
				System.out.println(ID + " | " + Date + " | " + Payee + " | " + Category + " | " + Note + " | " + Amount);
			}
		} catch (Exception e) {
			System.out.println("Failed to print data to console");
		}
	}
	
	public Connection getConnection() {

		return connection;
	}

}
