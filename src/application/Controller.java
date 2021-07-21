package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;

public class Controller implements Initializable {
	
	private Connection connection;
	
	@FXML
	private Button testButton;
	
	@FXML
	private ObservableList<Transaction> transactions;
	
	@FXML
	private TableView<Transaction> transactionsTable;
	
	@FXML
	public TableColumn<Transaction, String> dateCol;
	@FXML
	public TableColumn<Transaction, String> payeeCol;
	@FXML
	public TableColumn<Transaction, String> categoryCol;
	@FXML
	public TableColumn<Transaction, String> noteCol;
	@FXML
	public TableColumn<Transaction, Double> amountCol;
	
	/**
	 * Initializes data and other components of the UI/Backend on program launch (no user action required).
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Initialize necessary objects
		transactions = FXCollections.observableArrayList();
		transactionsTable = new TableView<Transaction>();
		dateCol = new TableColumn<Transaction, String>();
		payeeCol = new TableColumn<Transaction, String>();
		categoryCol = new TableColumn<Transaction, String>();
		noteCol = new TableColumn<Transaction, String>();
		amountCol = new TableColumn<Transaction, Double>();
		
		// Establish connection to database
		connectToDatabase();
		
		// Read transactions data from database and store it in the ObservableList
		readInTransactions();
		
		// TEST: Print the contents of the table to the console. TODO Delete this test code
		printTableDataToConsole();
		
		// Connect the data (objects - Product) to the TableColumns
		dateCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date")); // The last parameter here - in this case "date" - must match the variable "date" in the Trasaction class exactly.
		payeeCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("payee"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("category"));
		noteCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("note"));
		amountCol.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("amount"));
		
		// Load data into the table
		transactionsTable.setItems(transactions);
		
		// Add columns to the table - TODO The warning here states that a Generic Array is created. I may want to make my own array, populate it, and then assign it to the TableView object so that Java doesn't have to make one itself.
		transactionsTable.getColumns().addAll(dateCol, payeeCol, categoryCol, noteCol, amountCol);
		
		
		System.out.println(transactionsTable.getColumns());
		
		System.out.println("Initialization complete.");
	}	
	
	/**
	 * Connects the program to the Dummy.db database.
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
	 * Reads data in from the database, initializes transaction objects with the data, and'
	 * stores the data in an ObservableList.
	 */
	public void readInTransactions() {
		
		try {
			// Setting SQL query
			String query = "SELECT * FROM Transactions";

			// Create a Statement object to execute the SQL query
			Statement statement = connection.createStatement();

			// Execute SQL query and store the results in a ResultSet object
			ResultSet result = statement.executeQuery(query);

			// Create Transaction object(s)
			while (result.next()) {

				int id = result.getInt("Transaction ID");
				String date = result.getString("Date");
				String payee = result.getString("Payee");
				String category = result.getString("Category");
				String note = result.getString("Note");
				double amount = result.getDouble("Amount");

				Transaction trans = new Transaction(id, date, payee, category, note, amount);

				transactions.add(trans);
			}
		} catch (Exception e) {
			System.out.println("Failed to create Transaction object(s) from database.");
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Prints the contents of the database to the console.
	 */
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
	
	
	
/**
 * Controls the test button. Test button makes sure UI can still communicate with .fxml and Controller files.
 */
	public void testMethod() {
		System.out.println("Test successful!");
		testButton.setText("Test successful!");
	}

}
