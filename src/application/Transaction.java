package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

/**
 * See this on how to properly set up the Property object types in this class: https://docs.oracle.com/javafx/2/binding/jfxpub-binding.htm
 * @author Chris Marston
 *
 */
public class Transaction {
    
	// Each of these variables will become a column in our TableView
	private final IntegerProperty id;
	private StringProperty date;
	private StringProperty payee;
	private StringProperty category;
	private StringProperty note;
	private DoubleProperty amount;
	
	private Button transactionButton;
	
	/**
	 * Constructor class for the Transaction class.
	 */
	public Transaction(int id, String date, String payee, String category, String note, double amount) {
		this.id = new SimpleIntegerProperty(id);
		this.date = new SimpleStringProperty(date);
		this.payee = new SimpleStringProperty(payee);
		this.category = new SimpleStringProperty(category);
		this.note = new SimpleStringProperty(note);
		this.amount = new SimpleDoubleProperty(amount);
		this.transactionButton = new Button("Edit");
		
	}
	/**
	 * Overloaded constructor for Transaction class. Does not have ID parameter.
	 */
//	public Transaction(String date, String payee, String category, String note, double amount) {
//		this.date = new SimpleStringProperty(date);
//		this.payee = new SimpleStringProperty(payee);
//		this.category = new SimpleStringProperty(category);
//		this.note = new SimpleStringProperty(note);
//		this.amount = new SimpleDoubleProperty(amount);
//		this.transactionButton = new Button("Edit");
//		
//	}
	
    // Define getters for the Properties' values. Note: This allows for a non-Property type to be returned.
    public final int getId() {return id.get();}
    public final String getDate() {return date.get();}
    public final String getPayee() {return payee.get();}
    public final String getCategory() {return category.get();}
    public final String getNote() {return note.get();}
    public final double getAmount() {return amount.get();}
    public Button getButton() {return transactionButton;}
 
    // Define getters for the property itself Note: This returns a Property type.
    public final IntegerProperty idProperty() {return id;}
    public final StringProperty dateProperty() {return date;}
    public final StringProperty payeeProperty() {return payee;}
    public final StringProperty categoryProperty() {return category;}
    public final StringProperty noteProperty() {return note;}
    public final DoubleProperty amountProperty() {return amount;}
	
    // ***THE BELOW LINES HAVE BEEN COMMENTED OUT B/C THE SETTERS ARE NOT YET NEEDED AND THEY CONFLICT WITH THE FINAL MODIFIER OF THE PROPERTIES IN THIS CLASS - if setters are needed later, change the final modifier of the Properties***
	// Define setters for the properties. Note: No need to have a setter for the "property's value," the value will be set by these setters
    public final void setDateProperty(String date) {this.date = new SimpleStringProperty(date);}
    public final void setPayeeProperty(String payee) {this.payee = new SimpleStringProperty(payee);}
    public final void setCategoryProperty(String category) {this.category = new SimpleStringProperty(category);}
    public final void setNoteProperty(String note) {this.note = new SimpleStringProperty(note);}
    public final void setAmountProperty(double amount) {this.amount = new SimpleDoubleProperty(amount);}
    // Note: There is no setter for the id Property - the id Property should NOT be modified.
    
    /**
     * Returns a String representation of the Transaction object.
     */
    public String toString() {
    	
    	String stringTransaction = "[" + Integer.toString(id.get()) + ", " +
    								date.get() + ", " +
    								payee.get() + ", " +
    								category.get() + ", " +
    								note.get() + ", " +
    								"$" + Double.toString(amount.get()) + "]"; 
    	return stringTransaction;
    }
    
}



