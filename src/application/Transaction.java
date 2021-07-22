package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * See this on how to properly set up the Property object types in this class: https://docs.oracle.com/javafx/2/binding/jfxpub-binding.htm
 * @author Chris Marston
 *
 */
public class Transaction {
    
	// Each of these variables will become a column in our TableView
	private final IntegerProperty id;
	private final StringProperty date;
	private final StringProperty payee;
	private final StringProperty category;
	private final StringProperty note;
	private final DoubleProperty amount;
	
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
		
	}
	
    // Define getters for the property's value. Note: This allows for a non-Property type to be returned.
    public final int getId(){return id.get();}
    public final String getDate(){return date.get();}
    public final String getPayee(){return payee.get();}
    public final String getCategory(){return category.get();}
    public final String getNote(){return note.get();}
    public final double getAmount(){return amount.get();}
 
    // Define getters for the property itself Note: This returns a Property type.
    public IntegerProperty idProperty() {return id;}
    public StringProperty dateProperty() {return date;}
    public StringProperty payeeProperty() {return payee;}
    public StringProperty categoryProperty() {return category;}
    public StringProperty noteProperty() {return note;}
    public DoubleProperty amountProperty() {return amount;}
	
    // ***THE BELOW LINES HAVE BEEN COMMENTED OUT B/C THE SETTERS ARE NOT YET NEEDED AND THEY CONFLICT WITH THE FINAL MODIFIER OF THE PROPERTIES IN THIS CLASS - if setters are needed later, change the final modifier of the Properties***
	// Define setters for the properties. Note: No need to have a setter for the "property's value," the value will be set by these setters
//    public void setDateProperty(String date) {this.date = new SimpleStringProperty(date);}
//    public void setPayeeProperty(String payee) {this.payee = new SimpleStringProperty(payee);}
//    public void setCategoryProperty(String category) {this.category = new SimpleStringProperty(category);}
//    public void setNoteProperty(String note) {this.note = new SimpleStringProperty(note);}
//    public void setAmountProperty(double amount) {this.amount = new SimpleDoubleProperty(amount);}
    // Note: There is no setter for the id Property - the id Property should NOT be modified.
    
    
    
    
}



