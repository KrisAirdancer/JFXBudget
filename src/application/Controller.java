package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import application.DBConnection; // TODO I don't think this is doing anything - delete it

public class Controller {
	
//	@FXML
	
	private DBConnection databaseConnection;
	private Connection connection;
	public ObservableList<Transaction> transactions = FXCollections.observableArrayList(); // A list in which to store transactions // TODO This should be private or protected - fix it
	public TableView<Transaction> transactionsTable; // TODO This should be private or protected - fix it
	public TableColumn<Transaction, String> dateCol; // TODO This should be private or protected - fix it
	public TableColumn<Transaction, String> payeeCol; // TODO This should be private or protected - fix it
	public TableColumn<Transaction, String> categoryCol; // TODO This should be private or protected - fix it
	public TableColumn<Transaction, String> noteCol; // TODO This should be private or protected - fix it
	public TableColumn<Transaction, Double> amountCol; // TODO This should be private or protected - fix it

	public Controller() {
		
		// These lines are a test
		databaseConnection = new DBConnection();
//		connection.connectToDatabase();
//		connection.printTableDataToConsole();
		
		// Get connection object from DBConnection class
		connection = databaseConnection.getConnection();
		
		// Creating a TableVeiw to display user's transactions
		createTransactionsTableView(); 
	}

	public void budgetButton(ActionEvent e) {
		System.out.println("Budget");
		
		// This is a test
		databaseConnection.connectToDatabase();
		databaseConnection.printTableDataToConsole();
		
//		myCircle.setCenterY(y = y - 1);
	}
	
	public void statsButton(ActionEvent e) {
		System.out.println("Stats");
	}
	
	public void toBudgetButton(ActionEvent e) {
		System.out.println("To Budget");
	}
	
	public void createTransactionsTableView() {
		
		// CC - Create a Transaction class
			// CC - Should have instance variables for each property of a transaction
			// CC - Should have getters/setters for proper encapsulation
		// CC - Create a readInTransactions() method to initialize Transactions objects from the read-in data
		// CC - Follow the steps in this link to set up the TableView https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableView.html
		// Figure out how to connect the newly created TableView to the one in the UI
		// Write documentation (JavaDocs) for all written code!!!!
		
		// Initialize the TableView
		transactionsTable = new TableView<Transaction>();
		
		// Add the data model to the TableView - in this case, the transactions ObservableList
		transactionsTable.setItems(transactions);
		
		// Create TableColumn objects to display the data. Note: The unique id is intentionally not displayed.
		dateCol = new TableColumn<Transaction, String>("Date");
		dateCol.setCellValueFactory(new PropertyValueFactory("Date"));
		payeeCol = new TableColumn<Transaction, String>("Payee");
		payeeCol.setCellValueFactory(new PropertyValueFactory("Payee"));
		categoryCol = new TableColumn<Transaction, String>("Category");
		categoryCol.setCellValueFactory(new PropertyValueFactory("Category"));
		noteCol = new TableColumn<Transaction, String>("Note");
		noteCol.setCellValueFactory(new PropertyValueFactory("Note"));
		amountCol = new TableColumn<Transaction, Double>("Amount");
		amountCol.setCellValueFactory(new PropertyValueFactory("Amount"));
		
		// Add the TableColumns to the TableView
		transactionsTable.getColumns().setAll(dateCol, payeeCol, categoryCol, noteCol, amountCol);
		
		// TODO Have this method return the TableView?
	}
	
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
				
				int id = Integer.parseInt(result.getString("Transaction ID"));
				String date = result.getString("Date");
				String payee = result.getString("Payee");
				String category = result.getString("Category");
				String note = result.getString("Note");
				double amount = Double.parseDouble(result.getString("Amount"));
				
				Transaction trans = new Transaction(id, date, payee, category, note, amount);
				
				transactions.add(trans);
			}
		} catch (Exception e) {
			System.out.println("Failed to create Transaction object(s) from database.");
		}
		
		
	}
	
	public void main(String[] args) {

	}

}
