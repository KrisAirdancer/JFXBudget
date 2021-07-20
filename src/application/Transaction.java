package application;

public class Transaction {
    
	// Each of these variables will become a column in our TableView
	private int id;
	private String date;
	private String payee;
	private String category;
	private String note;
	private double amount;
	
	// First constructor to initialize instance variables
	public Transaction () { // This constructor sets default values
		// TODO Use this space to set the values to a default value, such as 0 or "EMPTY" or "UNKNOWN"
		
	}
	
	// Second constructor to initialize instance variables to desired values
	// The two constructors are overloaded
	public Transaction(int id, String date, String payee, String category, String note, double amount) {
		this.id = id;
		this.date = date;
		this.payee = payee;
		this.category = category;
		this.note = note;
		this.amount = amount;
	}
	
	/*
	 * JavaFX tables will look for (behind the scenes) getters and setters for the 
	 * table values. The naming convention get"PropertyName" must be followed.
	 * Ex. getName Ex. getPrice Ex. getQuantity
	 * 
	 * Note: id intentionally left unaccessible - id is should NOT be changeable
	 */
	public int getID() {
		return id;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	
	
}