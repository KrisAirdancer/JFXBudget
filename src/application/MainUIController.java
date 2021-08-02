package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class MainUIController implements Initializable {
	
	private DBConnection dbc;
	
	/************************
	 * TRANSACTIONS VARIABLES
	 ************************/
	private ObservableList<Transaction> transactions;
	
	@FXML
	public TableView<Transaction> transactionsTable;
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
	public TableView<Account> accountsTable;
	@FXML
	public TableColumn<Account, String> nameCol;
	@FXML
	public TableColumn<Account, Double> balanceCol;
	
	/*********************************
	 * ACCOUNT AND TRANSACTION BUTTONS
	 *********************************/
	@FXML
	private TableColumn<Transaction, Button> transactionButtonCol;
	@FXML
	private TableColumn<Account, Button> accountButtonCol;

	/******************
	 * OTHER UI BUTTONS
	 ******************/
	@FXML
	private Button newAcctButton; // New Account button
	@FXML
	private Button addTransButton; // Add Transaction button
	
	/*************
	 * TEXT FIELDS
	 *************/
	@FXML
	private TextField acctName;
	@FXML
	private TextField acctBal;
	@FXML
	private TextField transDate;
	@FXML
	private TextField transPayee;
	@FXML
	private TextField transCategory;
	@FXML
	private TextField transNote;
	@FXML
	private TextField transAmt;
	
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
		
		// Make cells editable
		dateCol.setCellFactory(TextFieldTableCell.forTableColumn());
		payeeCol.setCellFactory(TextFieldTableCell.forTableColumn());
		categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());
		noteCol.setCellFactory(TextFieldTableCell.forTableColumn());
		amountCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		
		/*************************************************
		 *  Set up EventListeners for transactions columns - allows us to change the value that is stored in the cell's data model and to update the database
		 *************************************************/
		
		dateCol.setOnEditCommit(new EventHandler<CellEditEvent<Transaction, String>>() { // This is an abstract class

			@Override
			public void handle(CellEditEvent<Transaction, String> event) {
				// Get the Transaction object for the row that is being edited
				Transaction transaction = event.getRowValue();
				
				// Set the value to the new value that was entered by the user - do this by calling the setter in the Transaction class
				transaction.setDateProperty(event.getNewValue());
				
				// Create SQL Statement
				String SQLStatement = "UPDATE Transactions SET date = '" + transaction.getDate() + "' WHERE ID = '" + transaction.getId() + "'";
				
				// Call database setter method with appropriate data and SQL statement
				dbc.updateDatabase(SQLStatement);
			}
			
		});
		
		payeeCol.setOnEditCommit(new EventHandler<CellEditEvent<Transaction, String>>() { // This is an abstract class

			@Override
			public void handle(CellEditEvent<Transaction, String> event) {
				// Get the Transaction object for the row that is being edited
				Transaction transaction = event.getRowValue();
				
				// Set the value to the new value that was entered by the user - do this by calling the setter in the Transaction class
				transaction.setPayeeProperty(event.getNewValue());
				
				// Create SQL Statement
				String SQLStatement = "UPDATE Transactions SET payee = '" + transaction.getPayee() + "' WHERE ID = '" + transaction.getId() + "'";
				
				// Call database setter method with appropriate data and SQL statement
				dbc.updateDatabase(SQLStatement);
			}
			
		});
		
		categoryCol.setOnEditCommit(new EventHandler<CellEditEvent<Transaction, String>>() { // This is an abstract class

			@Override
			public void handle(CellEditEvent<Transaction, String> event) {
				// Get the Transaction object for the row that is being edited
				Transaction transaction = event.getRowValue();
				
				// Set the value to the new value that was entered by the user - do this by calling the setter in the Transaction class
				transaction.setCategoryProperty(event.getNewValue());
				
				// Create SQL Statement
				String SQLStatement = "UPDATE Transactions SET category = '" + transaction.getCategory() + "' WHERE ID = '" + transaction.getId() + "'";
				
				// Call database setter method with appropriate data and SQL statement
				dbc.updateDatabase(SQLStatement);
			}
			
		});
		
		noteCol.setOnEditCommit(new EventHandler<CellEditEvent<Transaction, String>>() { // This is an abstract class

			@Override
			public void handle(CellEditEvent<Transaction, String> event) {
				// Get the Transaction object for the row that is being edited
				Transaction transaction = event.getRowValue();
				
				// Set the value to the new value that was entered by the user - do this by calling the setter in the Transaction class
				transaction.setNoteProperty(event.getNewValue());
				
				// Create SQL Statement
				String SQLStatement = "UPDATE Transactions SET note = '" + transaction.getNote() + "' WHERE ID = '" + transaction.getId() + "'";
				
				// Call database setter method with appropriate data and SQL statement
				dbc.updateDatabase(SQLStatement);
			}
			
		});
		
		amountCol.setOnEditCommit(new EventHandler<CellEditEvent<Transaction, Double>>() { // This is an abstract class

			@Override
			public void handle(CellEditEvent<Transaction, Double> event) {
				// Get the Transaction object for the row that is being edited
				Transaction transaction = event.getRowValue();
				
				// Set the value to the new value that was entered by the user - do this by calling the setter in the Transaction class
				transaction.setAmountProperty(event.getNewValue());
				
				// Create SQL Statement
				String SQLStatement = "UPDATE Transactions SET amount = '" + transaction.getAmount() + "' WHERE ID = '" + transaction.getId() + "'";
				
				// Call database setter method with appropriate data and SQL statement
				dbc.updateDatabase(SQLStatement);
				
				// Call updateItem() method to properly format the new value in the TableView
				
			}
			
		});
		
		
		// Load data into the table
		transactionsTable.setItems(transactions);
		
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
		System.out.println("Initialization complete."); // TODO Delete this eventually
		
	}	
	
	/**
	 * Controls the Add Transaction button
	 */
	public void addTransaction() {
		
		System.out.println("transButton Clicked.");
		
		/* NOTE: I shouldn't need to set the ID, it should be set automatically when I add a
		 * new transaction to the DB. I will need to add a new overloaded constructor in the
		 * transaction class that doesn't include the ID though. */
		
		// If not all fields are filled in (Notes can be left blank), display a notification (use an Alert) and exit this method
		if (transDate.getText() == "" || transPayee.getText() == ""
				|| transCategory.getText() == "" ||transAmt.getText() == "") {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Empty Fields");
			alert.setContentText("One or more required fields is empty.\nRequired: Date, Payee, Category, and Amount");
			alert.show();
			
			return; // Skip the rest of the method
		}
		
		// Pull fields from TextFields
		String date = transDate.getText();
		String payee = transPayee.getText();
		String category = transCategory.getText();
		String note = transNote.getText();
		double amount = Double.parseDouble(transAmt.getText());
		
		// Add the new Transaction to the transactionsTable
			// INSERT INTO "main"."Transactions"("ID","Date","Payee","Category","Note","Amount") VALUES (24,NULL,NULL,NULL,NULL,NULL);
		// Create SQL Statement - How to do this: https://www.w3schools.com/sql/sql_ref_values.asp
		String uniqueID = Integer.toString(dbc.highestTransID + 1);
		String newValues = "'" + uniqueID + "', '" + date + "', '" + payee + "', '" + category + "', '" + note + "', '" + Double.toString(amount) + "'";
		String SQLStatement = "INSERT INTO transactions (ID, Date, Payee, Category, Note, Amount) VALUES (" + newValues + ")";
		
		System.out.println(newValues);
		System.out.println(SQLStatement);
		
		// Call database setter method with appropriate data and SQL statement
		dbc.updateDatabase(SQLStatement);
		
		// Create a new Transaction object
		Transaction transaction = new Transaction(Integer.parseInt(uniqueID), date, payee, category, note, amount);
		
		// Add the new Transaction to the database by adding it to the transactions ObservableList
		transactions.add(transaction);
		
		// Update highestTransID in dbc
		dbc.highestTransID++;
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
