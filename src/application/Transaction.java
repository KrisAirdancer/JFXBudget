package application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Transaction {
    
	// Each of these variables will become a column in our TableView
	private SimpleIntegerProperty id;
	private SimpleStringProperty date;
	private SimpleStringProperty payee;
	private SimpleStringProperty category;
	private SimpleStringProperty note;
	private SimpleDoubleProperty amount;
	
	// Second constructor to initialize instance variables to desired values
	// The two constructors are overloaded
	public Transaction(int id, String date, String payee, String category, String note, double amount) {
		this.id = new SimpleIntegerProperty(id);
		this.date = new SimpleStringProperty(date);
		this.payee = new SimpleStringProperty(payee);
		this.category = new SimpleStringProperty(category);
		this.note = new SimpleStringProperty(note);
		this.amount = new SimpleDoubleProperty(amount);
	}

	/*
	 * JavaFX tables will look for (behind the scenes) getters and setters for the 
	 * table values. The naming convention get"PropertyName" must be followed.
	 * Ex. getName Ex. getPrice Ex. getQuantity
	 * 
	 * Note: id intentionally left unaccessible - id is should NOT be changeable
	 */
	public SimpleIntegerProperty getId() {
		return id;
	}

	public SimpleStringProperty getDate() {
		return date;
	}

	public void setDate(SimpleStringProperty date) {
		this.date = date;
	}

	public SimpleStringProperty getPayee() {
		return payee;
	}

	public void setPayee(SimpleStringProperty payee) {
		this.payee = payee;
	}

	public SimpleStringProperty getCategory() {
		return category;
	}

	public void setCategory(SimpleStringProperty category) {
		this.category = category;
	}

	public SimpleStringProperty getNote() {
		return note;
	}

	public void setNote(SimpleStringProperty note) {
		this.note = note;
	}

	public SimpleDoubleProperty getAmount() {
		return amount;
	}

	public void setAmount(SimpleDoubleProperty amount) {
		this.amount = amount;
	}
	

	
	
}