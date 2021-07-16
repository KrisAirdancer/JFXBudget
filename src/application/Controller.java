package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class Controller {

//	@FXML // Injects the code from your .fxml file into this class
//	private Circle myCircle;
//	private double x;
//	private double y;
	
	public void budgetButton(ActionEvent e) {
		System.out.println("Budget");
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
