package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transaction {
    
	// Each of these variables will become a column in our TableView
	private final IntegerProperty id;
	private final StringProperty date;
	private final StringProperty payee;
	private final StringProperty category;
	private final StringProperty note;
	private final DoubleProperty amount;
	
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
	
	
 
    // Define a getter for the property's value
    public final int getId(){return id.get();}
    public final String getDate(){return date.get();}
    public final String getPayee(){return payee.get();}
    public final String getCategory(){return category.get();}
    public final String getNote(){return note.get();}
    public final double getAmount(){return amount.get();}
 
     // Define a getter for the property itself
    public IntegerProperty idProperty() {return id;}
    public StringProperty dateProperty() {return date;}
    public StringProperty payeeProperty() {return payee;}
    public StringProperty categoryProperty() {return category;}
    public StringProperty noteProperty() {return note;}
    public DoubleProperty amountProperty() {return amount;}
	

	/*
	 * JavaFX tables will look for (behind the scenes) getters and setters for the 
	 * table values. The naming convention get"PropertyName" must be followed.
	 * Ex. getName Ex. getPrice Ex. getQuantity
	 * 
	 * Note: id intentionally left unaccessible - id is should NOT be changeable
	 */
//	public SimpleIntegerProperty getId() {
//		return id;
//	}
//
//	public SimpleStringProperty getDate() {
//		return date;
//	}
//
//	public SimpleStringProperty getPayee() {
//		return payee;
//	}
//
//	public SimpleStringProperty getCategory() {
//		return category;
//	}
//
//
//	public SimpleStringProperty getNote() {
//		return note;
//	}
//
//	public SimpleDoubleProperty getAmount() {
//		return amount;
//	}
	

	
	
}