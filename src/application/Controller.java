package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.shape.Circle;

import application.DBConnection; // TODO I don't think this is doing anything - delete it

public class Controller {
	
	DBConnection connection;

//	@FXML // Injects the code from your .fxml file into this class
//	ListView<String> list;
//	
//	ObservableList<String> items = FXCollections.observableArrayList();
	
	public Controller() {
		
		// These lines are a test
		connection = new DBConnection();
//		connection.connectToDatabase();
//		connection.printTableDataToConsole();
	}

	
	
	public void budgetButton(ActionEvent e) {
		System.out.println("Budget");
		
		// This is a test
		connection.connectToDatabase();
		connection.printTableDataToConsole();
		
//		myCircle.setCenterY(y = y - 1);
	}
	
	public void statsButton(ActionEvent e) {
		System.out.println("Stats");
//		myCircle.setCenterY(y = y + 1);
	}
	
	public void toBudgetButton(ActionEvent e) {
		System.out.println("To Budget");
//		myCircle.setCenterX(x = x - 1);
	}

}
