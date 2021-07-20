package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.shape.Circle;

public class Controller {
	
	@FXML
	private Button testButton;

	public void testMethod() {
		System.out.println("Test successful!");
		testButton.setText("Test successful!");
	}

}
