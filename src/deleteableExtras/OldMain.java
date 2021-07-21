package deleteableExtras;
	
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import application.Transaction;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class OldMain extends Application {
	
	Stage window;
	// Note: Tables require at least one column to be created
	TableView<Transaction> table;
	
	Connection connection;
	
	@Override
	public void start(Stage primaryStage) {
		
		window = primaryStage;
		window.setTitle("JFXBudget");
		
		// Create columns - they need 1) the type of data stored in the table and 2) the type of data in the specific column
		TableColumn<Transaction, String> dateCol = new TableColumn<>("Date"); // The last "Name" string is the column header name
		TableColumn<Transaction, String> payeeCol = new TableColumn<>("Payee");
		TableColumn<Transaction, String> categoryCol = new TableColumn<>("Category");
		TableColumn<Transaction, String> noteCol = new TableColumn<>("Note");
		TableColumn<Transaction, Double> amountCol = new TableColumn<>("Amount");
		
		// Set column widths - not necessary - can also be done in the FXML file
		dateCol.setMinWidth(200);
		payeeCol.setMinWidth(200);
		categoryCol.setMinWidth(200);
		noteCol.setMinWidth(200);
		amountCol.setMinWidth(200);
		
		// Connect the data (objects - Product) to the TableView/TableColumns
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date")); // The last parameter here - in this case "name" - must match the variable name in the Product class exactly.
		payeeCol.setCellValueFactory(new PropertyValueFactory<>("payee"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
		noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));
		amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
		
		// Initializing the table
		table = new TableView<>();
		// Load our data into the table - in this case we are using the getProduct method below
		table.setItems(getTransactions());
		// Add columns to the table
		table.getColumns().addAll(dateCol, payeeCol, categoryCol, noteCol, amountCol);
		
		
		// Create our layout 
		VBox vBox = new VBox();
		// Add the table to the layout
		vBox.getChildren().addAll(table);
		
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();
		
//		try {
//			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
//			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * - This method grabs the data to be added to the products.
	 * - Normally, this would be connected to a database (online
	 * or offline) or a spreadsheet/CSV on your computer.
	 * 
	 * - The way this method is set up is to create the list,
	 * then create and add all of the items to the list, then
	 * return the list. It can be modified to have the "create and add"
	 * step connect to a database and then create + add the items
	 * from that database, instead of having them be hard-coded
	 * in as is done in this example.
	 */
	public ObservableList<Transaction> getTransactions() {
		// This ObservableList can likely be declared outside this method
		ObservableList<Transaction> transactionsList = FXCollections.observableArrayList();
		
		// The items that are created and added here can be from a database - just add a database connection here
		
		// Create DBConnection object - TODO This line and the following two lines should be somewhere else (maybe...)
		DBConnection dbc = new DBConnection();
		// Establish connection with Database
		dbc.connectToDatabase();
		// Bring connection object into this class
		connection = dbc.getConnection();
		
		// Adding transactions to the list - TODO This should be a separate method somewhere else (maybe...)
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
				
				int id = result.getInt("Transaction ID");
				String date = result.getString("Date");
				String payee = result.getString("Payee");
				String category = result.getString("Category");
				String note = result.getString("Note");
				double amount = result.getDouble("Amount");
				
				transactionsList.add(new Transaction(id, date, payee, category, note, amount));
			}
		} catch (Exception e) {
			System.out.println("Failed to add data to list.");
		}
		
//		productList.add(new Trans("Cat", 99.99, 1000000));
//		productList.add(new Product("Porpoise", 999.95, 2));
//		productList.add(new Product("Eel", 12.00, 123));
		
		return transactionsList;
	}
}


/*
 * Processing Steps
 * 
 * 1) Get this class to print data from the Dummy database to the TableView
 * 2) Fork this fork (yes, again) to save progress
 * 3) Reorganize the existing code
 * 	- The code that connects the database to the program should be somewhere other than the getTransactions() method
 * 4) Set the application up to run on a .fxml UI file instead of building the UI directly in this class.
 */






