package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.shape.Circle;

public class Controller implements Initializable {
	
	@FXML
	private Button testButton;
	
	ObservableList<Transaction> transactionsList;
	
	TableView<Transaction> transactionsTable;
	
	TableColumn<Transaction, String> dateCol;
	TableColumn<Transaction, String> payeeCol;
	TableColumn<Transaction, String> categoryCol;
	TableColumn<Transaction, String> noteCol;
	TableColumn<Transaction, Double> amountCol;
	
	/**
	 * Initializes data and other components of the UI/Backend on program launch (no user action required).
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// TODO Add a series of method calls to get the TableView initialized properly
		
		// Initialize necessary objects
		transactionsList = FXCollections.observableArrayList();
		transactionsTable = new TableView<>();
		dateCol = new TableColumn<>();
		payeeCol = new TableColumn<>();
		categoryCol = new TableColumn<>();
		noteCol = new TableColumn<>();
		amountCol = new TableColumn<>();
		
		// Establish connection to database
		
		
	}	
	
	
	
	
	
	
/**
 * Controls the test button. Test button makes sure UI can still communicate with .fxml and Controller files.
 */
	public void testMethod() {
		System.out.println("Test successful!");
		testButton.setText("Test successful!");
	}

}
