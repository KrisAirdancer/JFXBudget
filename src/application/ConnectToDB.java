package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Note: Make sure all imports are from java.sql. - Not from other libraries.
 * 
 * @author Chris Marston
 *
 */

public class ConnectToDB {

	public static void main(String[] args) {
		String jdbcURL = "jdbc:sqlite:/C:\\Users\\chris\\eclipse-workspace\\JFXBudget\\Databases\\Dummy.db"; // This is the directory of the database - if this doesn't work, try the directory to the sqlite3.exe (C:\sqlite3) - removing the "/" befor the ":C" may also help
		
		try {
			// 1) Access this database
			Connection connection = DriverManager.getConnection(jdbcURL); // This connects our program to the database (I think this means that it reads the data in?) - Must be surrounded by a try-catch block
			
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
			
		} catch (SQLException e) {
			System.out.println("Failed to connect to database.");
		}

	}

}
