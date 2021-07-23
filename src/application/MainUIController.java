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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;
import javafx.util.Callback;

public class MainUIController implements Initializable {
	
	private DBConnection dbc;
	
	/************************
	 * TRANSACTIONS VARIABLES
	 ************************/
	private ObservableList<Transaction> transactions;
	
	@FXML
	private TableView<Transaction> transactionsTable;
	@FXML
	private TableColumn<Transaction, String> dateCol;
	@FXML
	private TableColumn<Transaction, String> payeeCol;
	@FXML
	private TableColumn<Transaction, String> categoryCol;
	@FXML
	private TableColumn<Transaction, String> noteCol;
	@FXML
	private TableColumn<Transaction, Double> amountCol;
	
	/********************
	 * ACCOUNTS VARIABLES
	 ********************/
	ObservableList<Account> accounts;
	
	@FXML
	private TableView<Account> accountsTable;
	@FXML
	public TableColumn<Account, String> nameCol;
	@FXML
	public TableColumn<Account, Double> balanceCol;
	
	/*********************************
	 * ACCOUNT AND TRANSACTION BUTTONS
	 *********************************/
//	@FXML
//	private Button transactionButton;
	@FXML
	private TableColumn<Transaction, Button> transactionButtonCol;
	@FXML
	private TableColumn<Account, Button> accountButtonCol;
	
	/*******************
	 * TESTING VARIABLES
	 *******************/
	@FXML
	private Button testButton;
	@FXML
	private Button dataLoadTest;
	
	/**
	 * Initializes data and other components of the UI/Backend on program launch
	 * (no user action required).
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Initialize DBConnection object
		dbc = new DBConnection();
		
		// Establish connection to database
		dbc.connectToDatabase();
		
		// Read transactions data from database and store it in the ObservableList
		transactions = dbc.readInTransactions(); 
		
		/*******************************
		 * Set up Transactions TableView
		 *******************************/
		
		// Connect the data (objects - Transaction) to the TableColumns
		dateCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date")); // The last parameter here - in this case "date" - must match the variable "date" in the Transaction class exactly.
		payeeCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("payee"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("category"));
		noteCol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("note"));
		amountCol.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("amount"));
		
		// Load data into the table
		transactionsTable.setItems(transactions);
		
		/***********************************
		 * Set up Transactions Table buttons
		 ***********************************/
		
		// Create a Cell Factory
		Callback<TableColumn<Transaction, Button>, TableCell<Transaction, Button>> TransactionsCellFactory = (param) -> {
			
			// Make a TableCell to house the button
			final TableCell<Transaction, Button> cell = new TableCell<Transaction, Button>() {
				
				// Override updateItems method
				@Override
				public void updateItem(Button item, boolean empty) {
					super.updateItem(item, empty);
					
					// Ensure that cells are only create in non-empty rows
					if (empty) {
						setGraphic(null);
						setText(null);
					} else {
						// Create a button
						final Button transactionEditButton = new Button("Edit"); // Statement from example video (39:42):https://www.youtube.com/watch?v=gvko7jLPZT0&ab_channel=DanMlayah
						
						// Attach listener to button
						transactionEditButton.setOnAction(event -> { // This controls what happens when the button is clicked
							
							// Extract the clicked Transaction object
							Transaction trans = getTableView().getItems().get(getIndex());
							
							System.out.println("You cliked it!");
						});
						
						// Assign the created button to a cell
						setGraphic(transactionEditButton);
						setText(null);
					}
				}
				
			;	
				
			};
			return cell;
		};
		
		// Assign the custom CellFactory to the appropriate TableColumn
		transactionButtonCol.setCellFactory(TransactionsCellFactory);
		
		/***********************************
		 * Set up Accounts Table buttons
		 ***********************************/
		
		// Create a Cell Factory
		Callback<TableColumn<Account, Button>, TableCell<Account, Button>> accountsCellFactory = (param) -> {
			
			// Make a TableCell to house the button
			final TableCell<Account, Button> cell = new TableCell<Account, Button>() {
				
				// Override updateItems method
				@Override
				public void updateItem(Button item, boolean empty) {
					super.updateItem(item, empty);
					
					// Ensure that cells are only create in non-empty rows
					if (empty) {
						setGraphic(null);
						setText(null);
					} else {
						// Create a button
						final Button accountEditButton = new Button("Edit"); // Statement from example video (39:42):https://www.youtube.com/watch?v=gvko7jLPZT0&ab_channel=DanMlayah
						
						// Attach listener to button
						accountEditButton.setOnAction(event -> { // This controls what happens when the button is clicked
							
							// Extract the clicked Transaction object
							Account acct = getTableView().getItems().get(getIndex());
							
							System.out.println("You cliked it!");
						});
						
						// Assign the created button to a cell
						setGraphic(accountEditButton);
						setText(null);
					}
				}
				
			;	
				
			};
			return cell;
		};
		
		// Assign the custom CellFactory to the appropriate TableColumn
		accountButtonCol.setCellFactory(accountsCellFactory);
		
		/***************************
		 * Set up Accounts TableView
		 ***************************/
		
		// Connect the data (objects - Account) to the TableColumns
		nameCol.setCellValueFactory(new PropertyValueFactory<Account, String>("name"));
		balanceCol.setCellValueFactory(new PropertyValueFactory<Account, Double>("balance"));
		
		// Populate accounts ObservableList
		accounts = dbc.readInAccounts();
		
		// Load data into table
		accountsTable.setItems(accounts);
		
		/******************
		 * STATUS & TESTING
		 ******************/
		
		// Print status and tests to the console - TODO Delete these eventually
		System.out.println("Transactions: " + transactions);
		System.out.println("Accounts: " + accounts);
		System.out.println("Initialization complete."); // TODO Delete this eventually
		
	}	

	/**
	 * Controls the test button. Test button makes sure UI can still communicate with .fxml and Controller files.
	 */
	public void commTest() {
		System.out.println("Test successful!");
	}
	
	/**
	 * Prints the contents of the database to the console to demonstrate that the data has
	 * been loaded and that the load was done properly.
	 */
	public void dataLoadTest() {
		dbc.printTableDataToConsole();
	}

}
